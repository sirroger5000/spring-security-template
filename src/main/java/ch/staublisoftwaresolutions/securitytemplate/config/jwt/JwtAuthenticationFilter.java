package ch.staublisoftwaresolutions.securitytemplate.config.jwt;

import ch.staublisoftwaresolutions.securitytemplate.config.twofa.TwofaUsernamePasswordAuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager1, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager1;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            TwofaUsernamePasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), TwofaUsernamePasswordAuthenticationRequest.class);

            Authentication authentication = new TwofaUsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword(),
                    authenticationRequest.getCode()
            );

            Authentication authenticate = authenticationManager.authenticate(authentication);
            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
}

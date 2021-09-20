package ch.staublisoftwaresolutions.securitytemplate.config.security;

import ch.staublisoftwaresolutions.securitytemplate.config.auth.CustomUserDetailsService;
import ch.staublisoftwaresolutions.securitytemplate.config.jwt.JwtAuthenticationFilter;
import ch.staublisoftwaresolutions.securitytemplate.config.jwt.JwtConfig;
import ch.staublisoftwaresolutions.securitytemplate.config.jwt.JwtTokenVerifier;
import ch.staublisoftwaresolutions.securitytemplate.config.twofa.CustomAuthenticationProvider;
import ch.staublisoftwaresolutions.securitytemplate.config.twofa.TwofaAuthentication;
import ch.staublisoftwaresolutions.securitytemplate.config.twofa.TwofaConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final private PasswordEncoder passwordEncoder;
    final private CustomUserDetailsService userDetailsService;
    final private TwofaConfig twofaConfig;
    final private TwofaAuthentication twofaAuthentication;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService, TwofaConfig twofaConfig, TwofaAuthentication twofaAuthentication, SecretKey secretKey, JwtConfig jwtConfig) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.twofaConfig = twofaConfig;
        this.twofaAuthentication = twofaAuthentication;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // TODO: 20.09.2021 Check if we can enable it in future
                // because we are using stateless jwt tokens
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add filter to generate the token on login
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                // add filter to check the token to access resources
                .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new CustomAuthenticationProvider(twofaConfig, twofaAuthentication);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}

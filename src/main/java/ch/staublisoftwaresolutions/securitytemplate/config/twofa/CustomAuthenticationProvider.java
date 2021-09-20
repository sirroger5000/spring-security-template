package ch.staublisoftwaresolutions.securitytemplate.config.twofa;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    final private TwofaConfig twofaConfig;
    final private TwofaAuthentication twofaAuthentication;

    public CustomAuthenticationProvider(TwofaConfig twofaConfig, TwofaAuthentication twofaAuthentication) {
        this.twofaConfig = twofaConfig;
        this.twofaAuthentication = twofaAuthentication;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (twofaConfig.getEnableTwofa()) {
            authentication = twofaAuthentication.authenticate(authentication);
        }

        Authentication authResult = super.authenticate(authentication);

        return authResult;
    }

}

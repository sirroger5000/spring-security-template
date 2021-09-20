package ch.staublisoftwaresolutions.securitytemplate.config.twofa;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

// test implementation for the TwofaAuthentication interface. Change that according to your logic
@Service
public class TwofaAuthenticationImpl implements TwofaAuthentication {

    final private String CODE = "1234";

    @Override
    public TwofaUsernamePasswordAuthenticationToken authenticate(Authentication authentication) {
        if (!(authentication instanceof TwofaUsernamePasswordAuthenticationToken)) {
            throw new RuntimeException("No code provided for 2fa");
        }
        TwofaUsernamePasswordAuthenticationToken token = (TwofaUsernamePasswordAuthenticationToken) authentication;
        if (!CODE.equals(token.getVerificationCode())) {
            throw new RuntimeException("Wrong 2fa code");
        }
        return token;
    }
}

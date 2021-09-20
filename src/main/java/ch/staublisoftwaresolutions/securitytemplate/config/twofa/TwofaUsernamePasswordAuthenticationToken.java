package ch.staublisoftwaresolutions.securitytemplate.config.twofa;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class TwofaUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    final private String verificationCode;

    public TwofaUsernamePasswordAuthenticationToken(Object principal, Object credentials, String verificationCode) {
        super(principal, credentials);
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }
}

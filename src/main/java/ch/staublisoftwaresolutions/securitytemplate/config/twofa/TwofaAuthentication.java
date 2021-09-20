package ch.staublisoftwaresolutions.securitytemplate.config.twofa;

import org.springframework.security.core.Authentication;

public interface TwofaAuthentication {

    TwofaUsernamePasswordAuthenticationToken authenticate(Authentication authentication);
}

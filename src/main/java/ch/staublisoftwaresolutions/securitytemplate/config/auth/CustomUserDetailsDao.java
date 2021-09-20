package ch.staublisoftwaresolutions.securitytemplate.config.auth;

import java.util.Optional;

// Implement this interface with your custom db logic to retrieve a CustomUserDetails object by username.
// For an example see CustomUserDetailsDaoImpl
public interface CustomUserDetailsDao {

    Optional<CustomUserDetails> getCustomUserDetailsByUsername(String username);
}

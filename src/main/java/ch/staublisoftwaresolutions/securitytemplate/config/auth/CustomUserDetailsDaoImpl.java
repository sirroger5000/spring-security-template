package ch.staublisoftwaresolutions.securitytemplate.config.auth;

import ch.staublisoftwaresolutions.securitytemplate.config.security.UserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// test implementation for the CustomUserDetailsDao interface
@Repository("test")
public class CustomUserDetailsDaoImpl implements CustomUserDetailsDao {

    final private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<CustomUserDetails> getCustomUserDetailsByUsername(String username) {
        return getTestList()
                .stream()
                .filter(customUserDetails -> username.equals(customUserDetails.getUsername()))
                .findFirst();
    }

    private List<CustomUserDetails> getTestList() {
        List<CustomUserDetails> customUserDetails = Lists.newArrayList(
                new CustomUserDetails(
                        "bob",
                        passwordEncoder.encode("password"),
                        UserRole.USER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new CustomUserDetails(
                        "alice",
                        passwordEncoder.encode("password"),
                        UserRole.ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return customUserDetails;
    }
}

package ch.staublisoftwaresolutions.securitytemplate.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final private CustomUserDetailsDao customUserDetailsDao;

    @Autowired
    public CustomUserDetailsService(@Qualifier("test") CustomUserDetailsDao customUserDetailsDao) {
        this.customUserDetailsDao = customUserDetailsDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customUserDetailsDao
                .getCustomUserDetailsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with username %s was not found", username)));
    }
}

package ch.staublisoftwaresolutions.securitytemplate.config.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

// User roles which contain a set of UserPermission. Use roles and permissions with the @PreAuthorize annotation
public enum UserRole {

    ADMIN(Sets.newHashSet(UserPermission.READ, UserPermission.WRITE)),
    USER(Sets.newHashSet(UserPermission.READ));

    final private Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream().map(userPermission -> new SimpleGrantedAuthority(userPermission.getName()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}

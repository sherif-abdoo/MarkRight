package com.MarkRight.Models;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.MarkRight.Models.UserPermissions.*;

@Getter
@AllArgsConstructor
public enum UserRoles {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_BAN , USER_UNBAN));

    private final Set<UserPermissions> permissions;

    public Set<GrantedAuthority>getGrantedAuthorities() {
        Set<GrantedAuthority> grantedAuthorities =
                permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        grantedAuthorities.add(new SimpleGrantedAuthority("Role_"+this.name()));
        return grantedAuthorities;
    }
}

package com.mcddhub.config.security;

import com.mcddhub.dto.urp.UserRolePermissionDto;
import com.mcddhub.service.UserRolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRolePermissionService userRolePermissionService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<UserRolePermissionDto> queryOptional =
            userRolePermissionService.queryUniqueUserWithRolePermission(Long.valueOf(id));
        UserRolePermissionDto userRolePermissionDto =
            queryOptional.orElseThrow(
                () -> new UsernameNotFoundException(String.format("uid %s user not found", id)));
        return new User(
            userRolePermissionDto.getUsername(),
            userRolePermissionDto.getPassword(),
            userRolePermissionDto.getEnable(),
            true,
            true,
            true,
            userRolePermissionDto.getPermissions().stream()
                .map((permission) -> new SimpleGrantedAuthority(permission.getCode()))
                .collect(Collectors.toSet()));
    }
}


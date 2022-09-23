package com.base.basic.domain.vo.v0;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class CurrentUserVO implements UserDetails {

    private String username;

    private String realName;

    private String password;

    private String phone;

    private String email;

    private Set<GrantedAuthority> authorities;

    public CurrentUserVO(){}

    public CurrentUserVO(String username, String realName, String password, String phone, String email){
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

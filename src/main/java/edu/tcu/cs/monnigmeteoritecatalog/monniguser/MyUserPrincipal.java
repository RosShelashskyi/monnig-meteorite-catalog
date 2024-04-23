package edu.tcu.cs.monnigmeteoritecatalog.monniguser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;

public class MyUserPrincipal implements UserDetails {

    private MonnigUser monnigUser;

    public MyUserPrincipal(MonnigUser monnigUser) {
        this.monnigUser = monnigUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Arrays.stream(StringUtils.tokenizeToStringArray(this.monnigUser.getRoles(), " "))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override
    public String getPassword() {
        return this.monnigUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.monnigUser.getUsername();
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
        return this.monnigUser.isEnabled();
    }

    public MonnigUser getMonnigUser() {
        return monnigUser;
    }

}

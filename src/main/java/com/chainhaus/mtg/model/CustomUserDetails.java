package com.chainhaus.mtg.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * Created by Asad Sarwar on 20/06/2020.
 */
public class CustomUserDetails implements UserDetails {


    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Set<SimpleGrantedAuthority> authorities;

    /**
     * enable value assigned to three more fields
     * @param userId
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param enabled
     * @param authorities
     */
    public CustomUserDetails(Long userId, String username, String password, String firstName, String lastName, String email, boolean enabled, Set<SimpleGrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountNonExpired = enabled;
        this.accountNonLocked = enabled;
        this.credentialsNonExpired = enabled;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public Long getUserId(){
        return this.userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

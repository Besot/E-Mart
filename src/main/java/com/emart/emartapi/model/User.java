package com.emart.emartapi.model;

import com.emart.emartapi.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User extends BaseEntity implements UserDetails {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private Role userRole;
    private BigDecimal balance;

    private  boolean isEnabled;
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();
    public User(String firstName, String lastName, String email, String userName, String password, Role userRole, Boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.isEnabled = isEnabled;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(this.userRole.name())));
    }

    @Override
    @JsonIgnore
    public String getPassword() { return this.password; }

    @Override
    @JsonIgnore
    public String getUsername() { return this.userName;}

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.isEnabled;
    }
}

package com.mps.MPSServer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredential implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long credentialId;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private MPSUser MPSUser;

    @Column(columnDefinition = "varchar(32)")
    private String login;

    @Column(columnDefinition = "varchar(32)")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE/*, CascadeType.PERSIST*/})
//    @JoinColumn(name = "roles", referencedColumnName = "idCargo")
//    @JoinTable(
//            name = "credenciais_cargos",
//            joinColumns = {@JoinColumn(name = "idCredencial")},
//            inverseJoinColumns = {@JoinColumn(name = "idCargo")})
    private List<Role> roles = new ArrayList<>();

    public UserCredential(MPSUser MPSUser, String login, String password) {
        this.MPSUser = MPSUser;
        this.login = login;
        this.password = password;
        this.roles.add(new Role("USER"));
    }

    public UserCredential(MPSUser MPSUser, String login, String password, String... cargos) {
        this.MPSUser = MPSUser;
        this.login = login;
        this.password = password;

        List<Role> addRoles = new ArrayList<>();
        for (String nomeCargo : cargos) {
            addRoles.add(new Role(nomeCargo));
        }
        this.roles = addRoles;
    }

    public UserCredential(MPSUser MPSUser, String login, String password, List<Role> roles) {
        this.MPSUser = MPSUser;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.login;
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

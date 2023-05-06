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
public class CredencialUsuario implements UserDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long idCredencial;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @Column(columnDefinition = "varchar(32)")
    private String login;

    @Column(columnDefinition = "varchar(32)")
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE/*, CascadeType.PERSIST*/})
//    @JoinColumn(name = "cargos", referencedColumnName = "idCargo")
//    @JoinTable(
//            name = "credenciais_cargos",
//            joinColumns = {@JoinColumn(name = "idCredencial")},
//            inverseJoinColumns = {@JoinColumn(name = "idCargo")})
    private List<Cargo> cargos = new ArrayList<>();

    public CredencialUsuario(Usuario usuario, String login, String senha) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;
        this.cargos.add(new Cargo("USUARIO"));
    }

    public CredencialUsuario(Usuario usuario, String login, String senha, String... cargos) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;

        List<Cargo> addCargos = new ArrayList<>();
        for (String nomeCargo : cargos) {
            addCargos.add(new Cargo(nomeCargo));
        }
        this.cargos = addCargos;
    }

    public CredencialUsuario(Usuario usuario, String login, String senha, List<Cargo> cargos) {
        this.usuario = usuario;
        this.login = login;
        this.senha = senha;
        this.cargos = cargos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Cargo cargo : this.cargos) {
            authorities.add(new SimpleGrantedAuthority(cargo.getNome()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
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

package com.mps.MPSServer.service;

import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.Cargo;
import com.mps.MPSServer.domain.CredencialUsuario;
import com.mps.MPSServer.repo.CargoRepo;
import com.mps.MPSServer.repo.CredencialRepo;
import com.mps.MPSServer.repo.UsuarioRepo;
import com.mps.MPSServer.domain.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CredencialRepo credencialRepo;

    @Autowired
    private CargoRepo cargoRepo;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    private List<GrantedAuthority> getAuthorities(List<Cargo> cargos) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Cargo cargo : cargos) {
            authorities.add(new SimpleGrantedAuthority(cargo.getNome()));
        }

        return authorities;
    }

    @Override
    public CredencialUsuario getCredencialUsuarioByLogin(String login) {
        Optional<CredencialUsuario> optionalCredencialUsuario = credencialRepo.findByLogin(login);

        if (optionalCredencialUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return optionalCredencialUsuario.get();
    }

    @Override
    public Usuario getUsuarioByCpf(String cpf) throws ObjectNotFoundInDBException {
        Optional<Usuario> optionalUsuario = usuarioRepo.findUsuarioByCpf(cpf);

        if (optionalUsuario.isEmpty()) {
            throw new ObjectNotFoundInDBException("Sem usuario com CPF " + cpf);
        }

        return optionalUsuario.get();
    }

    @Override
    public Usuario getUsuarioByLogin(String login) throws UsernameNotFoundException {
        Optional<CredencialUsuario> optionalCredencialUsuario = credencialRepo.findByLogin(login);

        if (optionalCredencialUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Login nao encontrado");
        }

        return optionalCredencialUsuario.get().getUsuario();

    }

    public boolean checkForUsuarioByCpf(String cpf) {
        try {
            getUsuarioByCpf(cpf);
            return true;
        } catch (ObjectNotFoundInDBException e) {
            return false;
        }
    }

    public boolean checkForCredencialUsuarioByLogin(String login) {
        try {
            getUsuarioByLogin(login);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public Usuario registerNewUsuario(Usuario usuario) throws ObjectAlreadyInDB {
        Optional<Usuario> optionalUsuario = usuarioRepo.findUsuarioByCpf(usuario.getCpf());
        if (optionalUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("CPF ja registrado");
        }
        return usuarioRepo.save(usuario);
    }

    @Override
    public CredencialUsuario registerNewCredencialUsuario(CredencialUsuario credencialUsuario) throws ObjectAlreadyInDB {
        Optional<Usuario> optionalUsuario = usuarioRepo.findUsuarioByCpf(credencialUsuario.getUsuario().getCpf());
        if (optionalUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("CPF ja registrado");
        }

        Optional<CredencialUsuario> optionalCredencialUsuario = credencialRepo.findByLogin(credencialUsuario.getLogin());
        if (optionalCredencialUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("login ja registrado");
        }

        if (credencialUsuario.getCargos().isEmpty()) {
            Cargo addCargo = cargoRepo.findByNome("USUARIO");
            credencialUsuario.getCargos().add(addCargo);
        }
        return credencialRepo.save(credencialUsuario);
    }
}

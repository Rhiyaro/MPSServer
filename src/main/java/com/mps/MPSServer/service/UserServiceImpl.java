package com.mps.MPSServer.service;

import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.MPSUser;
import com.mps.MPSServer.domain.Role;
import com.mps.MPSServer.domain.UserCredential;
import com.mps.MPSServer.repo.RoleRepo;
import com.mps.MPSServer.repo.CredentialRepo;
import com.mps.MPSServer.repo.UserRepo;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CredentialRepo credentialRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<MPSUser> getUser() {
        return userRepo.findAll();
    }

    @Override
    public MPSUser saveUser(MPSUser MPSUser) {
        return userRepo.save(MPSUser);
    }

    private List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public UserCredential getUserCredentialByLogin(String login) {
        Optional<UserCredential> optionalCredencialUsuario = credentialRepo.findByLogin(login);

        if (optionalCredencialUsuario.isEmpty()) {
            throw new UsernameNotFoundException("MPSUser nao encontrado");
        }

        return optionalCredencialUsuario.get();
    }

    @Override
    public MPSUser getUserByCpf(String cpf) throws ObjectNotFoundInDBException {
        Optional<MPSUser> optionalUsuario = userRepo.findUserByCpf(cpf);

        if (optionalUsuario.isEmpty()) {
            throw new ObjectNotFoundInDBException("Sem usuario com CPF " + cpf);
        }

        return optionalUsuario.get();
    }

    @Override
    public MPSUser getUserByLogin(String login) throws UsernameNotFoundException {
        Optional<UserCredential> optionalCredencialUsuario = credentialRepo.findByLogin(login);

        if (optionalCredencialUsuario.isEmpty()) {
            throw new UsernameNotFoundException("Login nao encontrado");
        }

        return optionalCredencialUsuario.get().getMPSUser();

    }

    public boolean checkForUsuarioByCpf(String cpf) {
        try {
            getUserByCpf(cpf);
            return true;
        } catch (ObjectNotFoundInDBException e) {
            return false;
        }
    }

    public boolean checkForCredencialUsuarioByLogin(String login) {
        try {
            getUserByLogin(login);
            return true;
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public MPSUser registerNewUser(MPSUser MPSUser) throws ObjectAlreadyInDB {
        Optional<MPSUser> optionalUsuario = userRepo.findUserByCpf(MPSUser.getCpf());
        if (optionalUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("CPF ja registrado");
        }
        return userRepo.save(MPSUser);
    }

    @Override
    public UserCredential registerNewUserCredential(UserCredential userCredential) throws ObjectAlreadyInDB {
        Optional<MPSUser> optionalUsuario = userRepo.findUserByCpf(userCredential.getMPSUser().getCpf());
        if (optionalUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("CPF already registered");
        }

        Optional<UserCredential> optionalCredencialUsuario = credentialRepo.findByLogin(userCredential.getLogin());
        if (optionalCredencialUsuario.isPresent()) {
            throw new ObjectAlreadyInDB("login already registered");
        }

        if (userCredential.getRoles().isEmpty()) {
            Role addRole = roleRepo.findByName("USER");
            userCredential.getRoles().add(addRole);
        }
        return credentialRepo.save(userCredential);
    }
}

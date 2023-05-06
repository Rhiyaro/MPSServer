package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.CredencialUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepo extends JpaRepository<CredencialUsuario, Long> {

    Optional<CredencialUsuario> findByLogin(String login);

    //TODO: Remove (probably)
    Optional<CredencialUsuario> findByLoginAndSenha(String login, String senha);
}
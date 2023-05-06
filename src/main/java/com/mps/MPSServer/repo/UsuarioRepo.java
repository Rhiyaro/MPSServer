package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findUsuarioByIdUsuario(Long idUsuario);

    Optional<Usuario> findUsuarioByNome(String nome);

    Optional<Usuario> findUsuarioByCpf(String cpf);

}

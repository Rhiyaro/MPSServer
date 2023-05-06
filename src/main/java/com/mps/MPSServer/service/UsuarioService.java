package com.mps.MPSServer.service;

import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.CredencialUsuario;
import com.mps.MPSServer.domain.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> getUsuarios();

    Usuario saveUsuario(Usuario usuario);

    CredencialUsuario getCredencialUsuarioByLogin(String login);

    Usuario getUsuarioByLogin(String login);

    Usuario getUsuarioByCpf(String cpf) throws ObjectNotFoundInDBException;

    Usuario registerNewUsuario(Usuario usuario) throws ObjectAlreadyInDB;

    CredencialUsuario registerNewCredencialUsuario(CredencialUsuario credencialUsuario) throws ObjectAlreadyInDB;
}

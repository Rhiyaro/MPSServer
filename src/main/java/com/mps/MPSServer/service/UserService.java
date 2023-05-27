package com.mps.MPSServer.service;

import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.MPSUser;
import com.mps.MPSServer.domain.UserCredential;

import java.util.List;

public interface UserService {

    List<MPSUser> getUser();

    MPSUser saveUser(MPSUser MPSUser);

    UserCredential getUserCredentialByLogin(String login);

    MPSUser getUserByLogin(String login);

    MPSUser getUserByCpf(String cpf) throws ObjectNotFoundInDBException;

    MPSUser registerNewUser(MPSUser MPSUser) throws ObjectAlreadyInDB;

    UserCredential registerNewUserCredential(UserCredential userCredential) throws ObjectAlreadyInDB;
}

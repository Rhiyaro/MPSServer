package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialRepo extends JpaRepository<UserCredential, Long> {

    Optional<UserCredential> findByLogin(String login);
}
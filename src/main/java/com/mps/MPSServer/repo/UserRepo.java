package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.MPSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MPSUser, Long> {

    Optional<MPSUser> findUserByUserId(Long userId);

    Optional<MPSUser> findUserByName(String name);

    Optional<MPSUser> findUserByCpf(String cpf);

}

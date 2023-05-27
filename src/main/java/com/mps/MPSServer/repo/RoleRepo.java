package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);
}

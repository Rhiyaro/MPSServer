package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepo extends JpaRepository<Cargo, Long> {

    Cargo findByNome(String nome);
}

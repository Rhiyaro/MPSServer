package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkRepo extends JpaRepository<Park, Long> {
}

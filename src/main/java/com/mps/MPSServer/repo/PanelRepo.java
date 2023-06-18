package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Panel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PanelRepo extends JpaRepository<Panel, Long> {

    Optional<Panel> findPanelByPanelName(String name);
}

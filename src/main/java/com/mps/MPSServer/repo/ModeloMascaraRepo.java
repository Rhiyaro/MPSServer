package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.ModeloMascara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeloMascaraRepo extends JpaRepository<ModeloMascara, Short> {
    Optional<ModeloMascara> getModeloMascaraByIdModeloMascara(short idModeloMascara);

    Optional<List<ModeloMascara>> getModeloMascaraByModelo(String modelo);

}

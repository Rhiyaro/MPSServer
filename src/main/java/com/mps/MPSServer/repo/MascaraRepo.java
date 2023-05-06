package com.mps.MPSServer.repo;

import com.mps.MPSServer.domain.Mascara;
import com.mps.MPSServer.domain.ModeloMascara;
import com.mps.MPSServer.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascaraRepo extends JpaRepository<Mascara, Long> {

    List<Mascara> findAllMascarasByUsuario(Usuario usuario);

    List<Mascara> findAllMascarasByModeloMascara(ModeloMascara modeloMascara);
}

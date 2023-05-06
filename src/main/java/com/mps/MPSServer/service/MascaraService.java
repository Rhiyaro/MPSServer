package com.mps.MPSServer.service;

import com.mps.MPSServer.domain.Mascara;

import java.util.List;

public interface MascaraService {
    Mascara saveMascara(Mascara mascara);
    List<Mascara> getMascaras();
    Mascara getMascaraById(long id);
    List<Mascara> getMascarasByUsuarioCpf(String nome);

    List<Mascara> getMascarasByModelo(String modelo);

}

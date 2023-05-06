package com.mps.MPSServer.service;

import com.mps.MPSServer.domain.ModeloMascara;

import java.util.List;

public interface ModeloMascaraService {
    ModeloMascara saveModeloMascara(ModeloMascara modeloMascara);
    ModeloMascara getModeloMascaraByIdModelo(short idModelo);
    List<ModeloMascara> getModelosMascaras();
}

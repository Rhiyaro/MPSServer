package com.mps.MPSServer.service;

import com.mps.MPSServer.repo.ModeloMascaraRepo;
import com.mps.MPSServer.domain.ModeloMascara;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ModeloMascaraServiceImpl implements ModeloMascaraService {

    private final ModeloMascaraRepo modeloMascaraRepo;

    @Override
    public ModeloMascara saveModeloMascara(ModeloMascara modeloMascara) {
        return modeloMascaraRepo.save(modeloMascara);
    }

    @Override
    public ModeloMascara getModeloMascaraByIdModelo(short idModelo) {
        Optional<ModeloMascara> modeloMascara = modeloMascaraRepo.getModeloMascaraByIdModeloMascara(idModelo);
        if (modeloMascara.isEmpty()){
            throw new NoSuchElementException("Sem Modelo com ID " + idModelo);
        }

        return modeloMascara.get();
    }

    @Override
    public List<ModeloMascara> getModelosMascaras() {
        return modeloMascaraRepo.findAll();
    }
}

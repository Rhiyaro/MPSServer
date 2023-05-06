package com.mps.MPSServer.api;

import com.mps.MPSServer.domain.ModeloMascara;
import com.mps.MPSServer.service.ModeloMascaraServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/modelo")
public class ModeloMascaraController {

    private final ModeloMascaraServiceImpl modeloMascaraService;

    @PostMapping("v1/buscar-todos-modelos")
    public List<ModeloMascara> getAllModelosMascaras(){
        return modeloMascaraService.getModelosMascaras();
    }

}

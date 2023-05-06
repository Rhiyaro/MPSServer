package com.mps.MPSServer.service;

import com.mps.MPSServer.domain.Mascara;
import com.mps.MPSServer.domain.ModeloMascara;
import com.mps.MPSServer.domain.Usuario;
import com.mps.MPSServer.repo.MascaraRepo;
import com.mps.MPSServer.repo.ModeloMascaraRepo;
import com.mps.MPSServer.repo.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MascaraServiceImpl implements MascaraService {

    private final MascaraRepo mascaraRepo;
    private final ModeloMascaraRepo modeloMascaraRepo;
    private final UsuarioRepo usuarioRepo;

    @Override
    public Mascara saveMascara(Mascara mascara) {
        return mascaraRepo.save(mascara);
    }

    @Override
    public List<Mascara> getMascaras() {
        return mascaraRepo.findAll();
    }

    @Override
    public Mascara getMascaraById(long id) {
        Optional<Mascara> optionalMascara = mascaraRepo.findById(id);
        if (optionalMascara.isEmpty()) {
            throw new NoSuchElementException("Sem mascara com id " + id);
        }

        return optionalMascara.get();
    }

    @Override
    public List<Mascara> getMascarasByUsuarioCpf(String cpf) {
        Optional<Usuario> usuario = usuarioRepo.findUsuarioByCpf(cpf);
        if (usuario.isEmpty()) {
            throw new NoSuchElementException("Sem Usuario com CPF '" + cpf + "'");
        }

        List<Mascara> mascaraByUsuario = mascaraRepo.findAllMascarasByUsuario(usuario.get());
        if (mascaraByUsuario.isEmpty()) {
            throw new NoSuchElementException("Sem Mascaras do Usuario com CPF '" + usuario.get().getCpf() + "'");
        }

        return mascaraByUsuario;
    }

    @Override
    public List<Mascara> getMascarasByModelo(String modelo) {
        Optional<List<ModeloMascara>> modelosMascarasOpt = modeloMascaraRepo.getModeloMascaraByModelo(modelo);
        if (modelosMascarasOpt.isEmpty()){
            throw new NoSuchElementException("Sem mascaras do Modelo " + modelo);
        }
        List<ModeloMascara> modelosMascara = modelosMascarasOpt.get();
        List<Mascara> mascarasModelo = new ArrayList<>();

        modelosMascara.forEach(modeloMascara -> mascarasModelo.addAll(
                mascaraRepo.findAllMascarasByModeloMascara(modeloMascara)
        ));

        return mascarasModelo;
    }

}

package com.mps.MPSServer.api;

import com.mps.MPSServer.domain.Mascara;
import com.mps.MPSServer.domain.ModeloMascara;
import com.mps.MPSServer.domain.Usuario;
import com.mps.MPSServer.service.MascaraServiceImpl;
import com.mps.MPSServer.service.ModeloMascaraServiceImpl;
import com.mps.MPSServer.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/mascara")
public class MascaraController {

    private final MascaraServiceImpl mascaraService;
    private final UsuarioServiceImpl usuarioService;
    private final ModeloMascaraServiceImpl modeloMascaraService;

    @PostMapping("v1/buscar-por-cpf")
    public List<Mascara> getMascarasByUsuario(@RequestParam String cpf) {
        return mascaraService.getMascarasByUsuarioCpf(cpf);
    }

    @PostMapping("v1/buscar-por-modelo")
    public List<Mascara> getMascarasByModelo(@RequestParam String modelo) {
        return mascaraService.getMascarasByModelo(modelo);
    }

    @PostMapping("v1/inserir-mascara")
    public ResponseEntity<String> insertMascara(@RequestParam String login,
                                                @RequestParam short idModeloMascara,
                                                @RequestParam String apelido) {
        Usuario usuario = usuarioService.getUsuarioByLogin(login);
        ModeloMascara modeloMascara = modeloMascaraService.getModeloMascaraByIdModelo(idModeloMascara);
        Mascara mascara = new Mascara(modeloMascara, usuario, apelido);
        mascaraService.saveMascara(mascara);
        return ResponseEntity.ok("Mascara inserido com sucesso");
    }

    @PostMapping("v1/atualizar-mascara")
    public ResponseEntity<String> updateMascara(@RequestParam long idMascara,
                                                @RequestParam String campo,
                                                @RequestParam String valor) {
        Mascara mascara = mascaraService.getMascaraById(idMascara);
        switch (campo.toLowerCase()) {
            case "login":
            case "usuario":
                Usuario novoUsuario = usuarioService.getUsuarioByLogin(valor);
                mascara.setUsuario(novoUsuario);
                break;
            case "apelido":
                mascara.setApelido(valor);
                break;
            case "ciclos":
                mascara.setCiclos(Short.parseShort(valor));
                break;
            default:
                return new ResponseEntity<>("Campo invalido", HttpStatus.BAD_REQUEST);
        }

        mascaraService.saveMascara(mascara);

        return ResponseEntity.ok("Mascara atualizada com sucesso");
    }

}

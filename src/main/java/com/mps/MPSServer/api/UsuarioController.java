package com.mps.MPSServer.api;

import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.Usuario;
import com.mps.MPSServer.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @PostMapping("v1/buscar-por-cpf")
    public Usuario getUsuarioByCpf(@RequestParam String cpf) throws ObjectNotFoundInDBException {
        return usuarioService.getUsuarioByCpf(cpf);
    }

    @PostMapping("v1/buscar-por-login")
    public Usuario getUsuarioByLogin(@RequestParam String login) {
        return usuarioService.getUsuarioByLogin(login);
    }

    @PostMapping("v1/atualizar-usuario")
    public ResponseEntity<String> updateUsuario(@RequestParam String cpf,
                                                @RequestParam String campo,
                                                @RequestParam String valor) throws ObjectNotFoundInDBException {
        Usuario usuario = usuarioService.getUsuarioByCpf(cpf);

        switch (campo.toLowerCase()) {
            case "nome":
                usuario.setNome(valor);
                break;
            case "endereco":
                usuario.setEndereco(valor);
                break;
            case "cidade":
                usuario.setCidade(valor);
                break;
            case "estado":
                usuario.setEstado(valor);
                break;
            case "telefone":
                usuario.setTelefone(valor);
                break;
            case "localtrabalho":
                usuario.setLocalTrabalho(valor);
                break;
            default:
                return new ResponseEntity<>("Campo invalido", HttpStatus.BAD_REQUEST);
        }
        usuarioService.saveUsuario(usuario);

        return ResponseEntity.ok("Usuario atualizado com sucesso");
    }
}

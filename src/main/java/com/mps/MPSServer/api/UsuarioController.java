package com.mps.MPSServer.api;

import com.mps.MPSServer.CustomExceptions.ObjectNotFoundInDBException;
import com.mps.MPSServer.domain.MPSUser;
import com.mps.MPSServer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserServiceImpl usuarioService;

    @PostMapping("v1/buscar-por-cpf")
    public MPSUser getUsuarioByCpf(@RequestParam String cpf) throws ObjectNotFoundInDBException {
        return usuarioService.getUserByCpf(cpf);
    }

    @PostMapping("v1/buscar-por-login")
    public MPSUser getUsuarioByLogin(@RequestParam String login) {
        return usuarioService.getUserByLogin(login);
    }

    @PostMapping("v1/atualizar-usuario")
    public ResponseEntity<String> updateUsuario(@RequestParam String cpf,
                                                @RequestParam String campo,
                                                @RequestParam String valor) throws ObjectNotFoundInDBException {
        MPSUser MPSUser = usuarioService.getUserByCpf(cpf);

        switch (campo.toLowerCase()) {
            case "nome":
                MPSUser.setName(valor);
                break;
            case "telefone":
                MPSUser.setPhone(valor);
                break;
            case "email":
                MPSUser.setEmail(valor);
                break;
            default:
                return new ResponseEntity<>("Campo invalido", HttpStatus.BAD_REQUEST);
        }
        usuarioService.saveUser(MPSUser);

        return ResponseEntity.ok("MPSUser atualizado com sucesso");
    }
}

package com.mps.MPSServer.api;

import com.mps.MPSServer.security.JwtUtil;
import com.mps.MPSServer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl usuarioService;
    private final JwtUtil jwtUtil;

    /*@PostMapping("v2/login")
    public ResponseEntity<AuthenticationResponse> loginUsuario(@RequestBody AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));

        UserDetails userDetails = usuarioService.getCredencialUsuarioByLogin(request.getLogin());

        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .token(jwtUtil.generateToken(userDetails))
                        .build());

    }

    @PostMapping("v1/login")
    public ResponseEntity<AuthenticationResponse> loginUsuario(@RequestParam String login,
                                                               @RequestParam String senha) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, senha));

        UserDetails userDetails = usuarioService.getCredencialUsuarioByLogin(login);

        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .token(jwtUtil.generateToken(userDetails))
                        .build());

    }

    @PostMapping("v1/cadastrar")
    public ResponseEntity<String> registerUsuario(@RequestParam String login,
                                                  @RequestParam String senha,
                                                  @RequestParam String nome,
                                                  @RequestParam String cpf,
                                                  @RequestParam String endereco,
                                                  @RequestParam String cidade,
                                                  @RequestParam String estado,
                                                  @RequestParam String telefone,
                                                  @RequestParam String localTrabalho) {

        try {
            MPSUser usuario = new MPSUser(nome, cpf, endereco, cidade, estado, telefone, localTrabalho);
            UserCredential credencialUsuario = new UserCredential(usuario, login, senha, new ArrayList<>());

            usuarioService.registerNewCredencialUsuario(credencialUsuario);

        } catch (ObjectAlreadyInDB e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok("Registrado com sucesso");

    }*/
}

package com.mps.MPSServer.api;

import com.mps.MPSServer.domain.CredencialUsuario;
import com.mps.MPSServer.dto.AuthenticationRequest;
import com.mps.MPSServer.dto.AuthenticationResponse;
import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.domain.Usuario;
import com.mps.MPSServer.security.JwtUtil;
import com.mps.MPSServer.service.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioServiceImpl usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping("v2/login")
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
            Usuario usuario = new Usuario(nome, cpf, endereco, cidade, estado, telefone, localTrabalho);
            CredencialUsuario credencialUsuario = new CredencialUsuario(usuario, login, senha, new ArrayList<>());

            usuarioService.registerNewCredencialUsuario(credencialUsuario);

        } catch (ObjectAlreadyInDB e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok("Registrado com sucesso");

    }
}

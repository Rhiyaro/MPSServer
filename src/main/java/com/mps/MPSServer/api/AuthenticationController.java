package com.mps.MPSServer.api;

import com.mps.MPSServer.CustomExceptions.ObjectAlreadyInDB;
import com.mps.MPSServer.domain.MPSUser;
import com.mps.MPSServer.domain.UserCredential;
import com.mps.MPSServer.dto.AuthenticationResponse;
import com.mps.MPSServer.security.JwtUtil;
import com.mps.MPSServer.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "mps/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
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

    }*/

    @PostMapping("v1/login")
    public ResponseEntity<AuthenticationResponse> loginUsuario(@RequestParam String login,
                                                               @RequestParam String senha) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, senha));

        UserDetails userDetails = userService.getUserCredentialByLogin(login);

        return ResponseEntity.ok(
                AuthenticationResponse
                        .builder()
                        .token(jwtUtil.generateToken(userDetails))
                        .build());

    }

    @PostMapping("v1/cadastrar")
    public ResponseEntity<String> registerUsuario(@RequestParam String login,
                                                  @RequestParam String password,
                                                  @RequestParam String name,
                                                  @RequestParam String cpf,
                                                  @RequestParam String phone,
                                                  @RequestParam String email) {

        try {
//            MPSUser usuario = new MPSUser(nome, cpf, endereco, cidade, estado, telefone, localTrabalho);
            MPSUser mpsUser = new MPSUser(name, cpf, phone, email);
//            UserCredential credencialUsuario = new UserCredential(usuario, login, senha, new ArrayList<>());
            UserCredential userCredential = new UserCredential(mpsUser, login, password);
            userService.registerNewUserCredential(userCredential);

        } catch (ObjectAlreadyInDB e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok("Registrado com sucesso");

    }
}

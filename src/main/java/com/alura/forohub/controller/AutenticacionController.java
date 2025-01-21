package com.alura.forohub.controller;

import com.alura.forohub.domain.user.DTOAutenticacionUsuario;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.infra.security.DTOJWTToken;
import com.alura.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DTOAutenticacionUsuario dtoAutenticacionUsuario) {

        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoAutenticacionUsuario.correoElectronico(),
                dtoAutenticacionUsuario.password());

        System.out.println("aca");

        var usuarioAutenticado = authenticationManager.authenticate(authToken);

        var JWTtoken = tokenService.generarToken((User) usuarioAutenticado.getPrincipal());

        System.out.println("token");
        System.out.println(JWTtoken);

        return ResponseEntity.ok(new DTOJWTToken(JWTtoken));
    }
}

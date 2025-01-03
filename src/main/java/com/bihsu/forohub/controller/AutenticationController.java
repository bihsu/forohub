package com.bihsu.forohub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bihsu.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.bihsu.forohub.domain.usuario.Usuario;
import com.bihsu.forohub.infra.security.DatosJWTToken;
import com.bihsu.forohub.infra.security.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticationController {
	
	private AuthenticationManager authenticationManager;
	private TokenService tokenService;
	
	public AutenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
		// TODO Auto-generated constructor stub
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping
	public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
		Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correo(), datosAutenticacionUsuario.clave());
		var usuarioAutenticado = authenticationManager.authenticate(authToken);
		var JWTToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
		return ResponseEntity.ok(new DatosJWTToken(JWTToken));
	}
}

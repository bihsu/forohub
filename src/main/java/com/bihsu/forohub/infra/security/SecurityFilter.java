package com.bihsu.forohub.infra.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bihsu.forohub.domain.usuario.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;
	
	public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		// TODO Auto-generated constructor stub
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		var authHeader = request.getHeader("Authorization");
		
		if(authHeader != null) {
			var token = authHeader.replace("Bearer ", "");
			var correoUsuario = tokenService.getSubject(token);
			if(correoUsuario != null) {
				var usuario = usuarioRepository.findByCorreoElectronico(correoUsuario);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);			
			}
		}
		filterChain.doFilter(request, response);
	}

}

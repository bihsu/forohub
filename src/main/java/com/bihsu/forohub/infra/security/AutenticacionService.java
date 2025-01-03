package com.bihsu.forohub.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bihsu.forohub.domain.usuario.UsuarioRepository;

@Service
public class AutenticacionService implements UserDetailsService{
	
	private UsuarioRepository usuarioRepository;
	
	public AutenticacionService(UsuarioRepository usuarioRepository) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return usuarioRepository.findByCorreoElectronico(username);
	}

}

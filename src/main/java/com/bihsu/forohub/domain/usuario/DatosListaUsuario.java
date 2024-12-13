package com.bihsu.forohub.domain.usuario;

public record DatosListaUsuario(
		Long id,
		String nombre,
		String correoElectronico,
		String contrasena
		) {
	public DatosListaUsuario(Usuario usuario) {
		this(usuario.getId(),usuario.getNombre(),usuario.getCorreoElectronico(),usuario.getContrasena());
	}
}

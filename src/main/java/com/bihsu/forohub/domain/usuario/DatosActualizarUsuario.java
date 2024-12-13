package com.bihsu.forohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
		@NotNull
		Long id,
		String nombre,
		String correoElectronico,
		String contrasena
		) {

}

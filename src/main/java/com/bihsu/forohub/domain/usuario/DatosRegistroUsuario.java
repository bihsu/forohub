package com.bihsu.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroUsuario(
		@NotBlank
		String nombre,
		@NotBlank
		String correoElectronico,
		@NotBlank
		String contrasena
		) {

}

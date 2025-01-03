package com.bihsu.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
		@NotBlank
		String correo,
		@NotBlank
		String clave) {

}

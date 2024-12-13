package com.bihsu.forohub.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActulizarCurso(
		@NotNull
		Long id,
		String nombre,
		Categoria categoria
		) {

}

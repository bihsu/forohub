package com.bihsu.forohub.domain.curso;

public record DatosListarCurso(
		Long id,
		String nombre,
		Categoria categoria
		) {
	public DatosListarCurso(Curso curso) {
		// TODO Auto-generated constructor stub
		this(curso.getId(),curso.getNombre(),curso.getCategoria());
	}
}

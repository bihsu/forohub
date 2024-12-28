package com.bihsu.forohub.domain.topico;

import java.time.LocalDate;

public record DatosListaTopico(
		Long id,
		String titulo,
		String mensaje,
		LocalDate fechaCreacion,
		Boolean estatus,
		String autor,
		String curso
		) {

}

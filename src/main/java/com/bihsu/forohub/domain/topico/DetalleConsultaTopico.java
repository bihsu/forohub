package com.bihsu.forohub.domain.topico;

import java.time.LocalDate;

public record DetalleConsultaTopico(
		String titulo,
		String mensaje,
		LocalDate fechaCreacion,
		Boolean estatus,
		String usuario,
		String curso
		) {

	public DetalleConsultaTopico(Topico topico) {
		// TODO Auto-generated constructor stub
		this(topico.getTitulo(),
				topico.getMensaje(),
				topico.getFechaCreacion(),
				topico.getEstatus(),
				topico.getUsuario().getNombre(),
				topico.getCurso().getNombre()
				);
	}
}

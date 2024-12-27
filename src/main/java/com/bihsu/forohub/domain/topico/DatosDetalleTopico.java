package com.bihsu.forohub.domain.topico;

import java.time.LocalDate;

public record DatosDetalleTopico(
		Long id,
		String titulo,
		String mensaje,
		LocalDate fechaCreacion,
		Boolean estatus,
		Long usuarioId,
		Long cursoId
		) {

	public DatosDetalleTopico(Topico topico) {
		// TODO Auto-generated constructor stub
		this(topico.getId(),
				topico.getTitulo(),
				topico.getMensaje(),
				topico.getFechaCreacion(),
				topico.getEstatus(),
				topico.getUsuario().getId(),
				topico.getCurso().getId()
				);
		
	}
}

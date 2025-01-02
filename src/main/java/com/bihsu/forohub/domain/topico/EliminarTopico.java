package com.bihsu.forohub.domain.topico;

import org.springframework.stereotype.Service;

import com.bihsu.forohub.domain.topico.validaciones.ValidarIdDetalleTopico;

@Service
public class EliminarTopico {
	
	private TopicoRepository topicoRepository;
	private ValidarIdDetalleTopico validarIdDetalleTopico;
	
	public EliminarTopico(TopicoRepository topicoRepository, ValidarIdDetalleTopico validarIdDetalleTopico) {
		// TODO Auto-generated constructor stub
		this.topicoRepository = topicoRepository;
		this.validarIdDetalleTopico = validarIdDetalleTopico;
	}
	
	public void eliminar(String id) {
		validarIdDetalleTopico.validar(id);
		topicoRepository.deleteById(Long.valueOf(id));
	}

}

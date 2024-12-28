package com.bihsu.forohub.domain.topico;

import org.springframework.stereotype.Service;

import com.bihsu.forohub.domain.topico.validaciones.ValidarIdDetalleTopico;

@Service
public class DetalleTopico {
	private ValidarIdDetalleTopico validarIdDetalleTopico;
	private TopicoRepository topicoRepository;
	
	public DetalleTopico(ValidarIdDetalleTopico validarIdDetalleTopico, TopicoRepository topicoRepository) {
		// TODO Auto-generated constructor stub
		this.validarIdDetalleTopico = validarIdDetalleTopico;
		this.topicoRepository = topicoRepository;
	}
	
	public DetalleConsultaTopico consultarPorId(String id) {
		
		validarIdDetalleTopico.validar(id);
		
		Topico topico = topicoRepository.findById(Long.parseLong(id)).get();
		return new DetalleConsultaTopico(topico);
	}
}

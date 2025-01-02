package com.bihsu.forohub.domain.topico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bihsu.forohub.domain.topico.validaciones.ValidadorDeTopico;
import com.bihsu.forohub.domain.topico.validaciones.ValidarIdDetalleTopico;

@Service
public class ActualizarTopico {

	private TopicoRepository topicoRepository;
	private List<ValidadorDeTopico> validadores;
	private ValidarIdDetalleTopico validarIdDetalleTopico;
	
	public ActualizarTopico(TopicoRepository topicoRepository,
			List<ValidadorDeTopico> validadores,
			ValidarIdDetalleTopico validarIdDetalleTopico) {
		this.topicoRepository = topicoRepository;
		this.validadores = validadores;
		this.validarIdDetalleTopico = validarIdDetalleTopico;
	}
	
	public DatosDetalleTopico actualizar(DatosRegistroTopico datosRegistroTopico, String id) {
		validarIdDetalleTopico.validar(id);
		
		Topico topico = topicoRepository.findById(Long.valueOf(id)).get();
		
		validadores.forEach(v->v.validar(datosRegistroTopico));
		
		topico.actualizar(datosRegistroTopico);
		return new DatosDetalleTopico(topico);
	}
}

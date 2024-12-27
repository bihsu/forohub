package com.bihsu.forohub.domain.topico.validaciones;

import org.springframework.stereotype.Component;

import com.bihsu.forohub.domain.ValidacionException;
import com.bihsu.forohub.domain.topico.DatosRegistroTopico;
import com.bihsu.forohub.domain.topico.TopicoRepository;

@Component
public class ValidarTituloRepetido implements ValidadorDeTopico{
	
	private TopicoRepository topicoRepository;
	
	public ValidarTituloRepetido(TopicoRepository topicoRepository) {
		// TODO Auto-generated constructor stub
		this.topicoRepository = topicoRepository;
	}

	@Override
	public void validar(DatosRegistroTopico datos) {
		// TODO Auto-generated method stub
		if(topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
			throw new ValidacionException("Topico con título y mensaje repetido");
		}
	}

}

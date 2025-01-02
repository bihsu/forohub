package com.bihsu.forohub.domain.topico.validaciones;

import org.springframework.stereotype.Component;

import com.bihsu.forohub.domain.ValidacionException;
import com.bihsu.forohub.domain.topico.TopicoRepository;

@Component
public class ValidarIdDetalleTopico{
	
	private TopicoRepository topicoRepository;
	
	public ValidarIdDetalleTopico(TopicoRepository topicoRepository) {
		// TODO Auto-generated constructor stub
		this.topicoRepository = topicoRepository;
	}
	
	public void validar(String id) {
		if(id == null) {
			throw new ValidacionException("el id proporcionado es nulo");
		}
		if(isNumeric(id) == false) {
			throw new ValidacionException("el id proporcionado no es un número");
		}
		if(!topicoRepository.existsById(Long.valueOf(id))) {
			throw new ValidacionException("El elemento no existe en la base de datos");
		}
	}
	private static boolean isNumeric(String id) {

        boolean resultado;

        try {
            Integer.parseInt(id);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }

}

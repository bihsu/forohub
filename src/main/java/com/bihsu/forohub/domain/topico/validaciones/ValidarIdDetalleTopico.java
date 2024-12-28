package com.bihsu.forohub.domain.topico.validaciones;

import org.springframework.stereotype.Component;

import com.bihsu.forohub.domain.ValidacionException;

@Component
public class ValidarIdDetalleTopico{

	public void validar(String id) {
		if(id == null) {
			throw new ValidacionException("el id proporcionado es nulo");
		}
		if(isNumeric(id) == false) {
			throw new ValidacionException("el id proporcionado no es un número");
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

package com.bihsu.forohub.domain.topico.validaciones;

import org.springframework.stereotype.Component;

import com.bihsu.forohub.domain.ValidacionException;
import com.bihsu.forohub.domain.topico.DatosRegistroTopico;

@Component
public class ValidarCamposVacios implements ValidadorDeTopico{

	@Override
	public void validar(DatosRegistroTopico datos) {
		// TODO Auto-generated method stub
		if(datos.titulo().isBlank()) {
			throw new ValidacionException("Titulo vacío");
		}
		if(datos.mensaje().isBlank()) {
			throw new ValidacionException("Mensaje vacío");
		}
		if(datos.idAutor() == null) {
			throw new ValidacionException("Autor vacío");
		}
		if(datos.idCurso() == null) {
			throw new ValidacionException("Curso vacío");
		}
	}

}

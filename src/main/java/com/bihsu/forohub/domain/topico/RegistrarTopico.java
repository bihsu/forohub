package com.bihsu.forohub.domain.topico;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bihsu.forohub.domain.ValidacionException;
import com.bihsu.forohub.domain.curso.CursoRepository;
import com.bihsu.forohub.domain.topico.validaciones.ValidadorDeTopico;
import com.bihsu.forohub.domain.usuario.UsuarioRepository;

import jakarta.validation.Valid;

@Service
public class RegistrarTopico {

	private TopicoRepository topicoRepository;
	private UsuarioRepository usuarioRepository;
	private CursoRepository cursoRepository;
	private List<ValidadorDeTopico> validadores;
	
	public RegistrarTopico(TopicoRepository topicoRepository,
			UsuarioRepository usuarioRepository,
			CursoRepository cursoRepository,
			List<ValidadorDeTopico> validadores) {
		// TODO Auto-generated constructor stub
		this.topicoRepository = topicoRepository;
		this.usuarioRepository = usuarioRepository;
		this.cursoRepository = cursoRepository;
		this.validadores = validadores;
	}

	public DatosDetalleTopico crearTopico(@Valid DatosRegistroTopico datosRegistroTopico) {
		// TODO Auto-generated method stub
		if(!usuarioRepository.existsById(datosRegistroTopico.idAutor())) {
			throw new ValidacionException("No existe autor con el id indicado");
		}
		if(!cursoRepository.existsById(datosRegistroTopico.idCurso())) {
			throw new ValidacionException("No existe un curso con el id indicado");
		}
		
		//aplicamos los validadores
		validadores.forEach(v->v.validar(datosRegistroTopico));
		
		var curso = cursoRepository.findById(datosRegistroTopico.idCurso()).get();
		var autor = usuarioRepository.findById(datosRegistroTopico.idAutor()).get();
		
		var topico = new Topico(
				null,
				datosRegistroTopico.titulo(),
				datosRegistroTopico.mensaje(),
				LocalDate.now(),
				true,
				autor,
				curso);
		topicoRepository.save(topico);
		return new DatosDetalleTopico(topico);
	}
	
	
}

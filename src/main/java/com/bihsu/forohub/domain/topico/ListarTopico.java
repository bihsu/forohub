package com.bihsu.forohub.domain.topico;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListarTopico {

	private TopicoRepository topicoRepository;
	
	public ListarTopico(TopicoRepository topicoRepository) {
		this.topicoRepository = topicoRepository;
	}
	//lisatr todo con el pageable por defecto
	public Page<DatosListaTopico> listar(Pageable pageable){
		return topicoRepository.findAll(pageable).map(t -> new DatosListaTopico(
				t.getId(),
				t.getTitulo(),
				t.getMensaje(),
				t.getFechaCreacion(),
				t.getEstatus(),
				t.getUsuario().getNombre(),
				t.getCurso().getNombre()
				));
	}
	
	//listar 10 ordenados por fecha de creación
	public Page<DatosListaTopico> listarPorFechaDeCreacion(Pageable pageable){
		return topicoRepository.findAllByOrderByFechaCreacion(pageable)
				.map(t -> new DatosListaTopico(
						t.getId(),
						t.getTitulo(),
						t.getMensaje(),
						t.getFechaCreacion(),
						t.getEstatus(),
						t.getUsuario().getNombre(),
						t.getCurso().getNombre()
						));
	}
	
	//buscar por nombre del curso
	public Page<DatosListaTopico> buscarPorNombeCurso(String nombre, Pageable pageable){
		return topicoRepository.buscarPorcurso(nombre, pageable)
				.map(t -> new DatosListaTopico(
						t.getId(),
						t.getTitulo(),
						t.getMensaje(),
						t.getFechaCreacion(),
						t.getEstatus(),
						t.getUsuario().getNombre(),
						t.getCurso().getNombre()
						));

	}
	
	//buscar por año
	public Page<DatosListaTopico> buscarPorAnio(Integer anio, Pageable pageable){
		List<DatosListaTopico> lista =  topicoRepository.findAll().stream()
				.filter(t -> t.getFechaCreacion().getYear() == anio)
				.map(t -> new DatosListaTopico(
						t.getId(),
						t.getTitulo(),
						t.getMensaje(),
						t.getFechaCreacion(),
						t.getEstatus(),
						t.getUsuario().getNombre(),
						t.getCurso().getNombre()
						))
				.toList();
		return new PageImpl<>(lista);		
	}
}

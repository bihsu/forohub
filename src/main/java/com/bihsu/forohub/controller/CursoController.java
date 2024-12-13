package com.bihsu.forohub.controller;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bihsu.forohub.domain.curso.Curso;
import com.bihsu.forohub.domain.curso.CursoRepository;
import com.bihsu.forohub.domain.curso.DatosActulizarCurso;
import com.bihsu.forohub.domain.curso.DatosListarCurso;
import com.bihsu.forohub.domain.curso.DatosRegistroCurso;
import com.bihsu.forohub.domain.curso.DatosRespuestaCurso;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CursoController {
	
	private CursoRepository cursoRepository;
	
	public CursoController(CursoRepository cursoRepository) {
		// TODO Auto-generated constructor stub
		this.cursoRepository = cursoRepository;
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListarCurso>> listar(@PageableDefault Pageable paginacion){
		return ResponseEntity.ok(cursoRepository.findAll(paginacion).map(DatosListarCurso::new));
	}
	
	@PostMapping
	public ResponseEntity<DatosRespuestaCurso> registrarCurso(@RequestBody @Valid DatosRegistroCurso datosCurso, UriComponentsBuilder uriComponentsBuilder) {
		Curso curso = cursoRepository.save(new Curso(datosCurso));
		DatosRespuestaCurso respueta = new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria());
		URI url = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
		return ResponseEntity.created(url).body(respueta);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaCurso> actualizarCurso(@RequestBody @Valid DatosActulizarCurso datosActulizarCurso){
		Curso curso = cursoRepository.getReferenceById(datosActulizarCurso.id());
		curso.actualizarDatos(datosActulizarCurso);
		return ResponseEntity.ok(
				new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria())
				);
	}
	
	//ver la manera de implementar el delete
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaCurso> buscarCursoPorId(@PathVariable("id") Long id){
		Curso curso = cursoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DatosRespuestaCurso(curso.getId(), curso.getNombre(), curso.getCategoria()));
	}

}

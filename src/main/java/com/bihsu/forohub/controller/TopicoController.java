package com.bihsu.forohub.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bihsu.forohub.domain.topico.DatosDetalleTopico;
import com.bihsu.forohub.domain.topico.DatosListaTopico;
import com.bihsu.forohub.domain.topico.DatosRegistroTopico;
import com.bihsu.forohub.domain.topico.ListarTopico;
import com.bihsu.forohub.domain.topico.RegistrarTopico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	private RegistrarTopico registrarTopico;
	private ListarTopico listarTopico;
	
	public TopicoController(RegistrarTopico registrarTopico, ListarTopico listarTopico) {
		// TODO Auto-generated constructor stub
		this.registrarTopico = registrarTopico;
		this.listarTopico = listarTopico;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DatosDetalleTopico> crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
		DatosDetalleTopico detalle = registrarTopico.crearTopico(datosRegistroTopico);
		return ResponseEntity.ok(detalle);
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListaTopico>> listarTopicos(@PageableDefault Pageable pageable){
		return ResponseEntity.ok(listarTopico.listar(pageable));
	}
	
	@GetMapping("/porfecha")
	public ResponseEntity<Page<DatosListaTopico>>listarPorFecha(Pageable pageable){
		return ResponseEntity.ok(listarTopico.listarPorFechaDeCreacion(pageable));
	}
	
	@GetMapping("/porcurso/{curso}")
	public ResponseEntity<Page<DatosListaTopico>> buscarPorCurso(@PathVariable("curso")String curso, Pageable pageable){
		return ResponseEntity.ok(listarTopico.buscarPorNombeCurso(curso, pageable));
	}
	
	@GetMapping("/poranio/{anio}")
	public ResponseEntity<Page<DatosListaTopico>> buscarPorAnioCreacion(@PathVariable("anio") Integer anio, Pageable pageable){
		return ResponseEntity.ok(listarTopico.buscarPorAnio(anio, pageable));
	}

}

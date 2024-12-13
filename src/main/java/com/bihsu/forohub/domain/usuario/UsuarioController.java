package com.bihsu.forohub.domain.usuario;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		// TODO Auto-generated constructor stub
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosListaUsuario>> listar(@PageableDefault Pageable paginacion){
		return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new));
	}
	
	@PostMapping
	public ResponseEntity<DatosRespuestaUsuario> agregarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
		Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuario));
		DatosRespuestaUsuario respuesta = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getContrasena());
		URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(url).body(respuesta);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DatosRespuestaUsuario> actualizarUsuario(@RequestBody  @Valid DatosActualizarUsuario datosActualizarUsuario){
		Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
		usuario.actualizar(datosActualizarUsuario);
		return ResponseEntity.ok(new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getContrasena()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosRespuestaUsuario> buscarPorId(@PathVariable("id") Long id){
		Usuario usuario = usuarioRepository.getReferenceById(id);
		var respuesta = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getContrasena());
		return ResponseEntity.ok(respuesta);
	}

}

package com.bihsu.forohub.domain.topico;

import java.time.LocalDate;
import java.util.List;

import com.bihsu.forohub.domain.curso.Curso;
import com.bihsu.forohub.domain.respuesta.Respuesta;
import com.bihsu.forohub.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String mensaje;
	@Column(name="fecha_creacion")
	private LocalDate fechaCreacion;
	private Boolean estatus;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name = "autor_id")
	private Usuario usuario;
	
	@ManyToOne(targetEntity = Curso.class)
	@JoinColumn(name = "curso_id")
	private Curso curso;
	
	@OneToMany(targetEntity = Respuesta.class, fetch = FetchType.LAZY, mappedBy = "topico")
	private List<Respuesta> respuestas;
	
	public Topico(Long id, String titulo, String mensaje, LocalDate fechaCreacion, boolean estatus,
			Usuario autor, Curso curso) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
		this.usuario = autor;
		this.curso = curso;
	}
}

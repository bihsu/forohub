package com.bihsu.forohub.domain.curso;

import java.util.List;

import com.bihsu.forohub.domain.topico.Topico;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cursos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@OneToMany(targetEntity = Topico.class, fetch = FetchType.LAZY, mappedBy = "curso")
	private List<Topico> topicos;
	
	public Curso(DatosRegistroCurso datosCurso) {
		this.nombre = datosCurso.nombre();
		this.categoria = datosCurso.categoria();
	}
	
	public Curso(Long id) {
		this.id = id;
	}
	
	public void actualizarDatos(DatosActulizarCurso datosActulizarCurso) {
		if(datosActulizarCurso.nombre() != null) {
			this.nombre = datosActulizarCurso.nombre();
		}
		if(datosActulizarCurso.categoria() != null) {
			this.categoria = datosActulizarCurso.categoria();
		}
	}
}

package com.bihsu.forohub.domain.usuario;

import java.util.List;

import com.bihsu.forohub.domain.respuesta.Respuesta;
import com.bihsu.forohub.domain.topico.Topico;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name="usuarios")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(name="correo_electronico")
	private String correoElectronico;
	private String contrasena;
	
	@OneToMany(targetEntity = Topico.class, fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<Topico> topicos;
	
	@OneToMany(targetEntity = Respuesta.class, fetch = FetchType.LAZY, mappedBy = "usuario")
	private List<Respuesta> respuestas;

}
package com.bihsu.forohub.domain.respuesta;

import java.util.Date;

import com.bihsu.forohub.domain.topico.Topico;
import com.bihsu.forohub.domain.usuario.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="respuestas")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensaje;
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne(targetEntity = Topico.class)
	@JoinColumn(name="topico_id")
	private Topico topico;
	
	@ManyToOne(targetEntity = Usuario.class)
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	private String solucion;

}

package com.bihsu.forohub.domain.usuario;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Usuario implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	
	public Usuario(DatosRegistroUsuario datosRegistroUsuario) {
		this.nombre = datosRegistroUsuario.nombre();
		this.correoElectronico = datosRegistroUsuario.correoElectronico();
		this.contrasena = datosRegistroUsuario.contrasena();
	}
	
	public Usuario(Long id) {
		this.id = id;
	}
	
	public void actualizar(DatosActualizarUsuario datosActualizarUsuario) {
		if(datosActualizarUsuario.nombre() != null) {
			this.nombre = datosActualizarUsuario.nombre();
		}
		if(datosActualizarUsuario.correoElectronico() != null) {
			this.correoElectronico = datosActualizarUsuario.correoElectronico();
		}
		if(datosActualizarUsuario.contrasena() != null) {
			this.contrasena = datosActualizarUsuario.contrasena();
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasena;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return correoElectronico;
	}
}

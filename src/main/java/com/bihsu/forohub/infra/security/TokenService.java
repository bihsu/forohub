package com.bihsu.forohub.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bihsu.forohub.domain.usuario.Usuario;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String apiSecret;
	
	@Value("${jwt.expiration}")
	private long expiratioonHours;
	
	public String generarToken(Usuario usuario) {
		System.out.println("HORAS ---> "+expiratioonHours);
		System.out.println("HORAS ---> "+apiSecret);
		try {
			Algorithm algorithm = Algorithm.HMAC256(apiSecret);
			return JWT.create()
					.withIssuer("bihsu tech")
					.withSubject(usuario.getCorreoElectronico())
					.withClaim("id", usuario.getId())
					.withExpiresAt(generarFechaExpiracion())
					.sign(algorithm);
		} catch (JWTCreationException e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
	public String getSubject(String token) {
		if(token == null) {
			throw new RuntimeException("Token llegó vacío");
		}
		DecodedJWT verifier = null;
		try {
			Algorithm algorithm = Algorithm.HMAC256(apiSecret);
			verifier = JWT.require(algorithm)
					.withIssuer("bihsu tech")
					.build()
					.verify(token);
			verifier.getSubject();
		} catch (JWTVerificationException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		if(verifier.getSubject() == null) {
			throw new RuntimeException("Verifier falló");
		}
		
		return verifier.getSubject();
	}
	
	//función para poner fecha de expiración del token, aquí se ponen dos horas
		private Instant generarFechaExpiracion() {
			return LocalDateTime.now().plusHours(expiratioonHours).toInstant(ZoneOffset.of("-05:00"));
		}
}

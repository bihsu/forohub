package com.bihsu.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TopicoRepository extends JpaRepository<Topico, Long>{

	boolean existsByTituloAndMensaje(String titulo, String mensaje);
	
	Page<Topico> findAllByOrderByFechaCreacion(Pageable pageable);
	
	@Query("""
			SELECT t FROM Topico t
			LEFT JOIN Curso c ON c.id = t.curso.id
			WHERE c.nombre LIKE %:curso%
			""")
	Page<Topico> buscarPorcurso(@Param(value = "curso") String curso, Pageable pagebale);
}

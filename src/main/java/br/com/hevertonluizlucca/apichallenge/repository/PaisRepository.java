package br.com.hevertonluizlucca.apichallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.hevertonluizlucca.apichallenge.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long>{

	@Query("SELECT e FROM Pais e WHERE e.nome LIKE '%?1%'")
	List<Pais> findByFilter(String filter);

}

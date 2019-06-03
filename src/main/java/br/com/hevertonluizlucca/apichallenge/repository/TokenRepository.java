package br.com.hevertonluizlucca.apichallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.hevertonluizlucca.apichallenge.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

	@Query("SELECT e FROM Token e WHERE e.token =?1 ")
	Token getByToken(String token);

	void save(String token);
	
}

package br.com.hevertonluizlucca.apichallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hevertonluizlucca.apichallenge.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByLogin(String login);
	
}

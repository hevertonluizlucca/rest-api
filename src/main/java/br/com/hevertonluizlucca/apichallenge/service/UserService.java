package br.com.hevertonluizlucca.apichallenge.service;

import java.util.Optional;

import br.com.hevertonluizlucca.apichallenge.model.User;
import br.com.hevertonluizlucca.apichallenge.model.UsuarioAutenticado;

public interface UserService {

	/**
	 * Busca e retorna um usuário dado um login.
	 * 
	 * @param login
	 * @return Optional<User>
	 */
	
	Optional<User> buscarPorLogin(String login);

	UsuarioAutenticado autenticar(String login, String senha);

	Boolean renovarToken(String token);

}

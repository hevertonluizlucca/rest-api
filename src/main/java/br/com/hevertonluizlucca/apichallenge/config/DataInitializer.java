package br.com.hevertonluizlucca.apichallenge.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.hevertonluizlucca.apichallenge.model.Pais;
import br.com.hevertonluizlucca.apichallenge.model.User;
import br.com.hevertonluizlucca.apichallenge.model.enums.PerfilEnum;
import br.com.hevertonluizlucca.apichallenge.repository.PaisRepository;
import br.com.hevertonluizlucca.apichallenge.repository.UserRepository;
import br.com.hevertonluizlucca.apichallenge.security.utils.SenhaUtils;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PaisRepository paisRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		List<User> users = userRepository.findAll();
		if (users == null || users.isEmpty()) {

			users.add(new User(null, "heverton.lucca", SenhaUtils.gerarBCrypt("master"), "Héverton Luiz Lucca", PerfilEnum.ROLE_ADMIN));
			users.add(new User(null, "kelly.adam", SenhaUtils.gerarBCrypt("senha001"),"Kelly Adam", PerfilEnum.ROLE_USUARIO));

			userRepository.saveAll(users);
			
		}
		
		List<Pais> paises = paisRepository.findAll();
		if (paises == null || paises.isEmpty()) {

			paises.add(new Pais(null, "Brasil", "BR", "Brasileiro"));
			paises.add(new Pais(null, "Argentina", "AR", "Argentino"));
			paises.add(new Pais(null, "Alemanha", "AL", "Alemão"));

			paisRepository.saveAll(paises);
			
		}
	}
}

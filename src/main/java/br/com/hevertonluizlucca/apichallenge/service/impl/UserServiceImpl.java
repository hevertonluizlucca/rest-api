package br.com.hevertonluizlucca.apichallenge.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.hevertonluizlucca.apichallenge.model.Token;
import br.com.hevertonluizlucca.apichallenge.model.User;
import br.com.hevertonluizlucca.apichallenge.model.UsuarioAutenticado;
import br.com.hevertonluizlucca.apichallenge.repository.TokenRepository;
import br.com.hevertonluizlucca.apichallenge.repository.UserRepository;
import br.com.hevertonluizlucca.apichallenge.security.utils.JwtTokenUtil;
import br.com.hevertonluizlucca.apichallenge.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	public Optional<User> buscarPorLogin(String login) {
		return Optional.ofNullable(this.userRepository.findByLogin(login));
	}

	@Override
	public UsuarioAutenticado autenticar(String login, String senha) {
		
		UsuarioAutenticado userResponse = null;
		UserDetails userDetails = userDetailsService.loadUserByUsername(login);
		User user = this.userRepository.findByLogin(login);
		String token = jwtTokenUtil.obterToken(userDetails);
		
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha, userDetails.getAuthorities()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			userResponse = new UsuarioAutenticado(login, user.getNome(), token, user.isAdmin(), authentication.isAuthenticated());
			Token tokenDto = new Token(token, login, jwtTokenUtil.gerarDataExpiracao(), user.isAdmin());
			this.tokenRepository.save(tokenDto);
		} catch (BadCredentialsException e) {
			return userResponse = new UsuarioAutenticado(login, user.getNome(), null, user.isAdmin(), false);
		}


		return userResponse;
	}

	@Override
	public Boolean renovarToken(String token) {
		Token tokenDto = this.tokenRepository.getByToken(token);
		
		if(tokenDto == null || StringUtils.isEmpty(tokenDto.getToken())) {
			return false;
		}
		
		tokenDto.updateToken(jwtTokenUtil.gerarDataExpiracao());
		
		this.tokenRepository.save(tokenDto);
		
		return true;
	}
}

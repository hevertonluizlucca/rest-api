package br.com.hevertonluizlucca.apichallenge.security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import br.com.hevertonluizlucca.apichallenge.model.Token;
import br.com.hevertonluizlucca.apichallenge.repository.TokenRepository;
import br.com.hevertonluizlucca.apichallenge.security.utils.JwtTokenUtil;
import io.jsonwebtoken.Jwts;

public class AuthenticationFilter extends GenericFilterBean {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private TokenRepository tokenRepository;

    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain
        ) throws IOException, ServletException {

        Authentication authentication = null;
        
        String token = ((HttpServletRequest) request).getHeader("token");
        
        if (token != null && Jwts.parser().isSigned(token)) {
        	UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
        	authentication  = userDetails != null ? new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()) : null;

        	Token tokenDto = tokenRepository.getByToken(token);
        	
        	if(tokenDto == null || !jwtTokenUtil.tokenValido(tokenDto.getExpiracao())) {
        		authentication.setAuthenticated(false);
        	}
        }


        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

}

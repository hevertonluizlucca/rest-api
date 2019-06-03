package br.com.hevertonluizlucca.apichallenge.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Token {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String token;
	private String login;
	private Timestamp expiracao;
	private Boolean administrador;
	
	public Token() {
		
	}

	public Token(String token, String login, Timestamp expiration, Boolean admin) {
		this.token = token;
		this.login = login;
		this.expiracao = expiration;
		this.administrador = admin;
	}

	public void updateToken(Timestamp expiration) {
		this.expiracao = expiration;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Timestamp getExpiracao() {
		return expiracao;
	}
	public void setExpiracao(Timestamp expiracao) {
		this.expiracao = expiracao;
	}
	public Boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	
}

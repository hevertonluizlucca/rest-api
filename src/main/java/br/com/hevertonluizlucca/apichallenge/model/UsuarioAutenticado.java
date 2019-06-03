package br.com.hevertonluizlucca.apichallenge.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;


public class UsuarioAutenticado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(position = 0)
	private String login;
	@ApiModelProperty(position = 1)
	private String nome;
	@ApiModelProperty(position = 2)
	private String token;
	@ApiModelProperty(position = 3)
	private Boolean administrador;
	@ApiModelProperty(position = 4)
	private Boolean autenticado;
	
	public UsuarioAutenticado(String login, String name, String token, Boolean admin, Boolean authenticated) {
		this.login = login;
		this.nome = name;
		this.token = token;
		this.administrador = admin;
		this.autenticado = authenticated;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Boolean getAdministrador() {
		return administrador;
	}
	public void setAdministrador(Boolean administrador) {
		this.administrador = administrador;
	}
	public Boolean getAutenticado() {
		return autenticado;
	}
	public void setAutenticado(Boolean autenticado) {
		this.autenticado = autenticado;
	}
	
	

}

package br.com.hevertonluizlucca.apichallenge.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.hevertonluizlucca.apichallenge.model.enums.PerfilEnum;
import lombok.Data;

@Entity
@Data
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String login;
	private String senha;
	private String nome;
	private PerfilEnum perfil;
	
	public User(Long id, String login, String senha, String nome, PerfilEnum perfil) {
		this.id = id;
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.perfil = perfil;
	}
	
	public User(){
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	public Boolean isAdmin() {
		return (this.perfil != null && this.perfil.equals(PerfilEnum.ROLE_ADMIN));
	}
	
	
	
	
}

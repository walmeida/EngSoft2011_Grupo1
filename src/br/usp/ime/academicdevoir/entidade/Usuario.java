package br.usp.ime.academicdevoir.entidade;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.usp.ime.academicdevoir.infra.Privilegio;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Usuario {
	
	/**
	 * @uml.property  name="id"
	 */
	@Id @GeneratedValue
	private Long id;
	/**
	 * @uml.property  name="nome"
	 */
	private String nome;
	/**
	 * @uml.property  name="login"
	 */
	private String login;
	/**
	 * @uml.property  name="senha"
	 */
	private String senha;
	/**
	 * @uml.property  name="email"
	 */
	private String email;
	/**
	 * @uml.property  name="privilegio"
	 * @uml.associationEnd  
	 */
	private Privilegio privilegio;

	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id
	 * @uml.property  name="id"
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return
	 * @uml.property  name="nome"
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome
	 * @uml.property  name="nome"
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return
	 * @uml.property  name="login"
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login
	 * @uml.property  name="login"
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return
	 * @uml.property  name="senha"
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * @param senha
	 * @uml.property  name="senha"
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	/**
	 * @return
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return
	 * @uml.property  name="privilegio"
	 */
	public Privilegio getPrivilegio() {
		return privilegio;
	}
	/**
	 * @param privilegio
	 * @uml.property  name="privilegio"
	 */
	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}
	
	public int getIntPrivilegio() {
		return privilegio.privilegioToInt();
	}
}

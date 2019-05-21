package com.bmartins.modelagem.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bmartins.modelagem.domain.Cliente;

public class ClienteDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres" )
	@Email(message = "email inválido")
	private String email;
	
	@NotEmpty
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres" )
	private String nome;
	
	public ClienteDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ClienteDTO(Cliente cli) {
		super();
		this.id = cli.getId();
		this.email = cli.getEmail();
		this.nome = cli.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
}

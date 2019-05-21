package com.bmartins.modelagem.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.bmartins.modelagem.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Cliente
	@NotEmpty
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres" )
	private String nome;
	@NotEmpty
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres" )
	@Email(message = "email inválido")
	private String email;
	
	@NotNull
	private Integer tipo;
	
	@NotEmpty
	private String documento;
	
	//Telefone
	@NotEmpty
	private List<String> telefones;
	
	//Endereco
	@NotEmpty
	private String logradouro;
	@NotEmpty
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty
	private String cep;
	
	//Cidade
	private Integer cidadeId;
	
	public ClienteNewDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
	
	
}

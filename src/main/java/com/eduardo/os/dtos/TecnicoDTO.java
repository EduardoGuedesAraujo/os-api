package com.eduardo.os.dtos;

import java.io.Serializable;

import org.hibernate.validator.constraints.br.CPF;

import com.eduardo.os.domain.Tecnico;

import jakarta.validation.constraints.NotEmpty;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message = "O Campo nome é Requerido")
	private String nome;

	@CPF
	@NotEmpty(message = "o campo CPF requerido")
	 private String cpf;
	
	@NotEmpty(message = "o campo Telefone requerido")
	private String telefone;

	public TecnicoDTO() {
		super();

	}

	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		 this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}

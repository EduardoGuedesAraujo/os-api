package com.eduardo.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;


import com.eduardo.os.domain.enums.OS;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;

public class OSDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@JsonFormat(pattern = "dd/mm/yyyy HH:MM")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/mm/yyyy HH:MM")
	private LocalDateTime datafechamento;
	private Integer prioridade;
	@NotEmpty(message = "O campo OBSERVAÇÕES é Requerido ")
	private String observacao;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	
	private Integer tecnico;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Integer cliente;
	
	
	public OSDTO() {
		super();
		
	}

	public OSDTO(OS obj ) {
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.datafechamento = obj.getDatafechamento();
		this.prioridade = obj.getPrioridade().getCod();
		this.observacao = obj.getObservacao();
		this.status = obj.getStatus().getCod();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDatafechamento() {
		return datafechamento;
	}

	public void setDatafechamento(LocalDateTime datafechamento) {
		this.datafechamento = datafechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	

	
}

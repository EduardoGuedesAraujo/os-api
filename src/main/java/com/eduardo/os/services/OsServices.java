package com.eduardo.os.services;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Cliente;
import com.eduardo.os.domain.Tecnico;
import com.eduardo.os.domain.enums.OS;
import com.eduardo.os.domain.enums.Prioridade;
import com.eduardo.os.domain.enums.Status;
import com.eduardo.os.dtos.OSDTO;
import com.eduardo.os.repositoryes.OSRepository;
import com.eduardo.os.services.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsServices {
	
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findByid(Integer id) {
		
		java.util.Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! id: " +id+ ", Tipo:" + OS.class.getName()));
		
	}
	public List<OS> findAll(){
	return repository.findAll();
		
	}
	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findByid(obj.getId());
		return fromDTO(obj);
		
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacao(obj.getObservacao());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		
		Tecnico tec = tecnicoService.findByid(obj.getTecnico());
		Cliente cli = clienteService.findByid(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
	
		
		if(newObj.getStatus().getCod().equals(2)) {
		
			newObj.setDatafechamento(LocalDateTime.now());
		}
		return repository.save(newObj);
				
	}
	
}

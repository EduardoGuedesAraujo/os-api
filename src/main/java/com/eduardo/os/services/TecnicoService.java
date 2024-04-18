package com.eduardo.os.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Pessoa;
import com.eduardo.os.domain.Tecnico;
import com.eduardo.os.dtos.TecnicoDTO;
import com.eduardo.os.repositoryes.PessoaReposiory;
import com.eduardo.os.repositoryes.TecnicoRepository;

import com.eduardo.os.services.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class TecnicoService {
	@Autowired
	private TecnicoRepository repository;
	@Autowired
	private PessoaReposiory pessoaReposiory;
	
	
	public Tecnico findByid(Integer id) {
		
		Optional<Tecnico>  obj= repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não Encontrado! Id: "+ id + ",Tipo:"+Tecnico.class.getName()));
		
	}
	public java.util.List<Tecnico> findAll() {
		return repository.findAll();
	}
	public Tecnico create (TecnicoDTO objDto) {
		if(findbyCPF(objDto) !=null) {
			throw new DataIntegrityViolationException ("CPF já cadastrado na base de dados! ");
		}         
		return repository.save(new Tecnico(null,objDto.getNome(),objDto.getCpf(),objDto.getTelefone()));
	}
	
	public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
		Tecnico oldObj = findByid(id);
		
		if(findbyCPF(objDto) != null && findbyCPF(objDto).getId() !=id) {
			throw new DataIntegrityViolationException ("CPF já cadastrado na base de dados! ");
		}
		oldObj.setNome(oldObj.getNome());
		oldObj.setCpf(oldObj.getCpf());
		oldObj.setTelefone(oldObj.getTelefone());
		
		return repository.save(oldObj);
		
		
	}
	public void delete(Integer id) {
		Tecnico obj = findByid(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrityViolationException ("Técnico possui Ordens de serviço ,não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	
	
		
	private Pessoa findbyCPF(TecnicoDTO objDto) {
		Pessoa obj = pessoaReposiory.findbyCPF(objDto.getCpf());
		if(obj !=null) {
			return obj ;
		}
		return null;
	}
	
	
	
	
}

package com.eduardo.os.services;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Pessoa;
import com.eduardo.os.domain.Cliente;
import com.eduardo.os.dtos.ClienteDTO;
import com.eduardo.os.repositoryes.PessoaReposiory;
import com.eduardo.os.repositoryes.ClienteRepository;

import com.eduardo.os.services.exception.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaReposiory pessoaReposiory;
	
	
	public Cliente findByid(Integer id) {
		
		Optional<Cliente>  obj= repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não Encontrado! Id: "+ id + ",Tipo:"+Cliente.class.getName()));
		
	}
	public java.util.List<Cliente> findAll() {
		return repository.findAll();
	}
	public Cliente create (ClienteDTO objDto) {
		if(findbyCPF(objDto) !=null) {
			throw new DataIntegrityViolationException ("CPF já cadastrado na base de dados! ");
		}         
		return repository.save(new Cliente(null,objDto.getNome(),objDto.getCpf(),objDto.getTelefone()));
	}
	
	public Cliente update(Integer id, @Valid ClienteDTO objDto) {
		Cliente oldObj = findByid(id);
		
		if(findbyCPF(objDto) != null && findbyCPF(objDto).getId() !=id) {
			throw new DataIntegrityViolationException ("CPF já cadastrado na base de dados! ");
		}
		oldObj.setNome(oldObj.getNome());
		oldObj.setCpf(oldObj.getCpf());
		oldObj.setTelefone(oldObj.getTelefone());
		
		return repository.save(oldObj);
		
		
	}
	public void delete(Integer id) {
		Cliente obj = findByid(id);
		if(obj.getList().size() > 0) {
			throw new DataIntegrityViolationException ("Pessoa possui Ordens de serviço ,não pode ser deletado!");
		}
		repository.deleteById(id);
		
	}
	
	
		
	private Pessoa findbyCPF(ClienteDTO objDto) {
		Pessoa obj = pessoaReposiory.findbyCPF(objDto.getCpf());
		if(obj !=null) {
			return obj ;
		}
		return null;
	}
	
	
	
	
}

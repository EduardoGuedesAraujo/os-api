package com.eduardo.os.resources;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eduardo.os.domain.Cliente;
import com.eduardo.os.dtos.ClienteDTO;
import com.eduardo.os.services.ClienteService;

import jakarta.validation.Valid;
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {
 
	@Autowired
	private ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
	
		ClienteDTO obtDTO = new ClienteDTO(service.findByid(id));
		return ResponseEntity.ok().body(obtDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll(){
				List<ClienteDTO>listDto = service.findAll().stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDto);		
	}
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create (@Valid @RequestBody ClienteDTO objDto){
		Cliente newOBJ = service.create(objDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOBJ.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	
	/*ATUALIZA TÉCNICO */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update (@PathVariable Integer id,@Valid @RequestBody ClienteDTO objDto ){
		ClienteDTO newOBJ = new ClienteDTO(service.update(id,objDto));
		
		return ResponseEntity.ok().body(newOBJ);
	}
	/*DELETE TÉCNICO*/
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

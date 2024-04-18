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

import com.eduardo.os.domain.Tecnico;
import com.eduardo.os.dtos.TecnicoDTO;
import com.eduardo.os.services.TecnicoService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResources {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
	
		TecnicoDTO obtDTO = new TecnicoDTO(service.findByid(id));
		return ResponseEntity.ok().body(obtDTO);
	}
	
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll(){
				List<TecnicoDTO>listDto = service.findAll().stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
				return ResponseEntity.ok().body(listDto);		
	}
	
	@PostMapping
	public ResponseEntity<TecnicoDTO> create (@Valid @RequestBody TecnicoDTO objDto){
		Tecnico newOBJ = service.create(objDto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOBJ.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	
	/*ATUALIZA TÉCNICO */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update (@PathVariable Integer id,@Valid @RequestBody TecnicoDTO objDto ){
		TecnicoDTO newOBJ = new TecnicoDTO(service.update(id,objDto));
		
		return ResponseEntity.ok().body(newOBJ);
	}
	/*DELETE TÉCNICO*/
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete (@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}

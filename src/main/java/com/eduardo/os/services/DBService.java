package com.eduardo.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.eduardo.os.domain.Cliente;
import com.eduardo.os.domain.Tecnico;
import com.eduardo.os.domain.enums.OS;
import com.eduardo.os.domain.enums.Prioridade;
import com.eduardo.os.domain.enums.Status;
import com.eduardo.os.repositoryes.ClienteRepository;
import com.eduardo.os.repositoryes.OSRepository;
import com.eduardo.os.repositoryes.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OSRepository osRepository;

	
	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Eduardo Guedes Araujo", "416.864.760-94", "(51)9892-31491");
		Cliente c1 = new Cliente(null, "Betina Campos", "267.975.480-80", "(55)9943-3455");
		OS os1 = new OS(null, "teste OS", Status.ANDAMENTO, Prioridade.ALTA, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}

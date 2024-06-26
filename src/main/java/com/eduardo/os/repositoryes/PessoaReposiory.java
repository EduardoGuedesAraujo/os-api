package com.eduardo.os.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.eduardo.os.domain.Pessoa;


@Repository
public interface PessoaReposiory extends JpaRepository<Pessoa, Integer>{
	
	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf=:cpf")

	Pessoa findbyCPF(@Param("cpf")String cpf);

}

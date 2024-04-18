package com.eduardo.os.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eduardo.os.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

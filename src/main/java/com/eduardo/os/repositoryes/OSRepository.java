package com.eduardo.os.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.eduardo.os.domain.enums.OS;
@Repository
public interface OSRepository extends JpaRepository<OS, Integer>{

}

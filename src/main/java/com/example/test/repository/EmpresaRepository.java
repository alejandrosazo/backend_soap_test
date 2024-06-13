package com.example.test.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.test.models.Empresa;

@Repository
@Transactional(rollbackOn = {Exception.class})
public interface EmpresaRepository extends CrudRepository<Empresa, Long>, 
PagingAndSortingRepository<Empresa, Long>, 
JpaSpecificationExecutor<Empresa>, 
JpaRepository<Empresa, Long> {
	
	 @Query("SELECT e FROM Empresa e " +
	           "JOIN FETCH e.sucursales s " +
	           "JOIN FETCH s.colaboradores " +
	           "WHERE e.empresaId = :empresaId")
	    Optional<Empresa> findByIdWithDetails(Long empresaId);
}


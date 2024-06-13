package com.example.test.repository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.test.models.Colaborador;


@Repository
@Transactional(rollbackOn = {Exception.class})
public interface ColaboradorRepository extends  CrudRepository<Colaborador, Long>, 
PagingAndSortingRepository<Colaborador, Long>, 
JpaSpecificationExecutor<Colaborador>, 
JpaRepository<Colaborador, Long> {

}
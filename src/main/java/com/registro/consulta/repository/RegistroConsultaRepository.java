package com.registro.consulta.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.registro.consulta.model.RegistroConsulta;

@Repository
public interface RegistroConsultaRepository extends CrudRepository<RegistroConsulta, String> {

	RegistroConsulta findBySequencial(long sequencial);

}

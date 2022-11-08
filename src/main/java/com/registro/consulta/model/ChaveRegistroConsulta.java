package com.registro.consulta.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ChaveRegistroConsulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String chave;

	@Id
	private Integer identificadorChave;

	@Id
	@ManyToOne // Muitas chaves para um registro de consulta
	@JoinColumn(name = "sequencial") // Renomear a coluna para não ficar com o nome padrão
	// (REGISTRO_CONSULTA_SEQUENCIAL
	private RegistroConsulta registroConsulta;

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Integer getIdentificadorChave() {
		return identificadorChave;
	}

	public void setIdentificadorChave(Integer identificadorChave) {
		this.identificadorChave = identificadorChave;
	}

	public RegistroConsulta getRegistroConsulta() {
		return registroConsulta;
	}

	public void setRegistroConsulta(RegistroConsulta registroConsulta) {
		this.registroConsulta = registroConsulta;
	}

}

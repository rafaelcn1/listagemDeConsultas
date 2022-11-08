package com.registro.consulta.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class RegistroConsulta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sequencial;

	private Date dataEmissao;
	
	private Date dataFinal;

	private String cpfCnpjDestinatario;

	@OneToMany(mappedBy = "registroConsulta", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // Um registro de
																									// consulta para
																									// muitas chaves
																									// consultadas
	private List<ChaveRegistroConsulta> chavesConsulta;

	public long getSequencial() {
		return sequencial;
	}

	public void setSequencial(long sequencial) {
		this.sequencial = sequencial;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getCpfCnpjDestinatario() {
		return cpfCnpjDestinatario;
	}

	public void setCpfCnpjDestinatario(String cpfCnpjDestinatario) {
		this.cpfCnpjDestinatario = cpfCnpjDestinatario;
	}

	public List<ChaveRegistroConsulta> getChavesConsulta() {
		return chavesConsulta;
	}

	public void setChavesConsulta(List<ChaveRegistroConsulta> chavesConsulta) {
		this.chavesConsulta = chavesConsulta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RegistroConsulta [sequencial=" + sequencial + ", dataEmissao=" + dataEmissao + ", dataFinal="
				+ dataFinal + ", cpfCnpjDestinatario=" + cpfCnpjDestinatario + ", chavesConsulta=" + chavesConsulta
				+ "]";
	}

}

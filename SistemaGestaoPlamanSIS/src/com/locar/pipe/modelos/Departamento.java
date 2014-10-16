package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_departamento")
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos Primitivos
	@Id
	@GeneratedValue
	private long id;

	private String nome;
	private String sigla;

	@OneToMany(mappedBy="setor", fetch=FetchType.EAGER)
	private List<Colaborador> colaboradores;
	
	@OneToMany(mappedBy="setor")
	private List<OrdemServicoCorretiva> ordems;
	
	@OneToMany(mappedBy="setor")
	private List<SolicitacaoServico> solicitacoes;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<OrdemServicoCorretiva> getOrdems() {
		return ordems;
	}

	public void setOrdems(List<OrdemServicoCorretiva> ordems) {
		this.ordems = ordems;
	}

	public List<SolicitacaoServico> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<SolicitacaoServico> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

}

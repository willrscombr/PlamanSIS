package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_solicitacao_servico")
public class SolicitacaoServico  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	public long id;
	public String equipamento;
	public String componente;
	@Column(name="descricao_acao")
	public String DescricaoAcao;
	@ManyToOne
	public Departamento setor;
	@Column(name="data_criacao")
	public Date dataCriacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	public String getDescricaoAcao() {
		return DescricaoAcao;
	}
	public void setDescricaoAcao(String descricaoAcao) {
		DescricaoAcao = descricaoAcao;
	}
	public Departamento getSetor() {
		return setor;
	}
	public void setSetor(Departamento setor) {
		this.setor = setor;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		SolicitacaoServico other = (SolicitacaoServico) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}

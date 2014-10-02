package com.locar.pipe.modelos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Material implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id @GeneratedValue
	private long id;
	@Column(name="nome_item")
	private String nomeItem;
	@Column(name="descricao_item")
	private String descricaoItem;
	private int quantidade;
	@ManyToOne
	@JoinColumn(name="ordem_id")
	private OrdemServico ordemServico;
	@OneToOne
	private Colaborador solicitante;
	
	
	
	//--------Getters and Setters----------
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeItem() {
		return nomeItem;
	}
	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}
	public String getDescricaoItem() {
		return descricaoItem;
	}
	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public OrdemServico getOrdemServico() {
		return ordemServico;
	}
	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}
	public Colaborador getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Colaborador solicitante) {
		this.solicitante = solicitante;
	}
	
	
	//-----Equals and HashCode----------
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
		Material other = (Material) obj;
		if (id != other.id)
			return false;
		return true;
	}
}

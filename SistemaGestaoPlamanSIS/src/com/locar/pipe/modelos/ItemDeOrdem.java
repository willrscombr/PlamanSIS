package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.locar.pipe.enuns.Status;

@Entity
@Table(name="tb_item_de_ordem")
public class ItemDeOrdem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Atributos Primitivos----------------
	@Id @GeneratedValue
	private long id;
	private String solucao;
	
	//Classes para mapeamento----------------
	@OneToOne
	private SolicitacaoServico solicitacaoServico;
	@ManyToOne
	private OrdemServico ordem;
	
	//Tipos enumerated----------------
	@Enumerated
	private Status status;
	
	//Atributos Temporais----------------
	@Temporal(TemporalType.DATE)
	private Date dataInicio;
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	@Temporal(TemporalType.DATE)
	private Date dataFinal;
	@Temporal(TemporalType.TIME)
	private Date horaFinal;
	
	
	//GETTERS AND SETTERS--------------------
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public SolicitacaoServico getSolicitacaoServico() {
		return solicitacaoServico;
	}
	public void setSolicitacaoServico(SolicitacaoServico solicitacaoServico) {
		this.solicitacaoServico = solicitacaoServico;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
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
		ItemDeOrdem other = (ItemDeOrdem) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public OrdemServico getOrdem() {
		return ordem;
	}
	public void setOrdem(OrdemServico ordem) {
		this.ordem = ordem;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getSolucao() {
		return solucao;
	}
	public void setSolucao(String solucao) {
		this.solucao = solucao;
	} 
	
	
	
	
}

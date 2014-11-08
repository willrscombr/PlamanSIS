package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.locar.pipe.enuns.TipoDoReport;

@Entity
public class Report implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_report")
	private TipoDoReport tipoReport;
	private String local;
	private String descricao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataReport;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public TipoDoReport getTipoReport() {
		return tipoReport;
	}
	public void setTipoReport(TipoDoReport tipoReport) {
		this.tipoReport = tipoReport;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataReport() {
		return dataReport;
	}
	public void setDataReport(Date dataReport) {
		this.dataReport = dataReport;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Report other = (Report) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}

package com.locar.pipe.service;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.locar.pipe.modelos.Colaborador;

@Entity
public class BackLogApplication implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long id;
	@Temporal(TemporalType.TIMESTAMP) @Column(name="data_log")
	private Date dataLog;
	@Column(name="texto_log")
	private String textoLog;
	@ManyToOne @JoinColumn(name="id_colaborador")
	private Colaborador colaborador;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataLog() {
		return dataLog;
	}
	public void setDataLog(Date dataLog) {
		this.dataLog = dataLog;
	}
	public String getTextoLog() {
		return textoLog;
	}
	public void setTextoLog(String textoLog) {
		this.textoLog = textoLog;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
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
		BackLogApplication other = (BackLogApplication) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}

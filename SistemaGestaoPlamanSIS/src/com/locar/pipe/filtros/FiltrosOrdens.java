package com.locar.pipe.filtros;

import java.io.Serializable;
import java.util.Date;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.modelos.Departamento;

public class FiltrosOrdens implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String equipamento;
	private String componente;
	private Status status;
	private TipoTrabalho tipoTrabalho;
	private TipoOrdem tipoOrdem;
	private Date dataInicio;
	private Date dataFinal;
	private Departamento setor;
	private int ciclo;

	
	public FiltrosOrdens() {
		setor = null;
		equipamento = "";
		componente = "";
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public TipoTrabalho getTipoTrabalho() {
		return tipoTrabalho;
	}
	public void setTipoTrabalho(TipoTrabalho tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	public Departamento getSetor() {
		return setor;
	}
	public void setSetor(Departamento setor) {
		this.setor = setor;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TipoOrdem getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(TipoOrdem tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}	
}

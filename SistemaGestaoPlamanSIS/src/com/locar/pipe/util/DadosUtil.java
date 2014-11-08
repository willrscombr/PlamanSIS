package com.locar.pipe.util;

import java.io.Serializable;

public class DadosUtil implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int aberta,andamento,encerrada,pendente,confirmada;

	
	public int getAberta() {
		return aberta;
	}

	public void setAberta(int aberta) {
		this.aberta = aberta;
	}

	public int getAndamento() {
		return andamento;
	}

	public void setAndamento(int andamento) {
		this.andamento = andamento;
	}

	public int getEncerrada() {
		return encerrada;
	}

	public void setEncerrada(int encerrada) {
		this.encerrada = encerrada;
	}

	public int getPendente() {
		return pendente;
	}

	public void setPendente(int pendente) {
		this.pendente = pendente;
	}

	public int getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(int confirmada) {
		this.confirmada = confirmada;
	}
	
	
}

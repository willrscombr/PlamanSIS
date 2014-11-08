package com.locar.pipe.util;

import java.io.Serializable;

public class DataGridUtil implements Serializable{
	private static final long serialVersionUID = 1L;

	private DadosUtil eletrica,mecanica,operacional,manutencao,planejamento,administracao,refrigeracao;

	public DadosUtil getEletrica() {
		return eletrica;
	}

	public void setEletrica(DadosUtil eletrica) {
		this.eletrica = eletrica;
	}

	public DadosUtil getMecanica() {
		return mecanica;
	}

	public void setMecanica(DadosUtil mecanica) {
		this.mecanica = mecanica;
	}

	public DadosUtil getOperacional() {
		return operacional;
	}

	public void setOperacional(DadosUtil operacional) {
		this.operacional = operacional;
	}

	public DadosUtil getManutencao() {
		return manutencao;
	}

	public void setManutencao(DadosUtil manutencao) {
		this.manutencao = manutencao;
	}

	public DadosUtil getPlanejamento() {
		return planejamento;
	}

	public void setPlanejamento(DadosUtil planejamento) {
		this.planejamento = planejamento;
	}

	public DadosUtil getAdministracao() {
		return administracao;
	}

	public void setAdministracao(DadosUtil administracao) {
		this.administracao = administracao;
	}

	public DadosUtil getRefrigeracao() {
		return refrigeracao;
	}

	public void setRefrigeracao(DadosUtil refrigeracao) {
		this.refrigeracao = refrigeracao;
	}
	
	
}

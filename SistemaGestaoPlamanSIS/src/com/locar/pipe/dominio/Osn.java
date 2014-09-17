package com.locar.pipe.dominio;

import com.locar.pipe.generalizacoes.OrdemServicoGeneralizacao;

public class Osn extends OrdemServicoGeneralizacao{
	
	private static final long serialVersionUID = 1L;
	private String tipoCorretiva;


	
	public String getTipo() {
		return tipoCorretiva;
	}

	public void setTipo(String tipo) {
		this.tipoCorretiva = tipo;
	}
	
}

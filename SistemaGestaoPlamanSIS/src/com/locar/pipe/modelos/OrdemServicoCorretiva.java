package com.locar.pipe.modelos;

import com.locar.pipe.generalizacoes.OrdemServicoGeneralizacao;

public class OrdemServicoCorretiva extends OrdemServicoGeneralizacao{
	
	private static final long serialVersionUID = 1L;
	private String tipoCorretiva;


	
	public String getTipo() {
		return tipoCorretiva;
	}

	public void setTipo(String tipo) {
		this.tipoCorretiva = tipo;
	}
	
}

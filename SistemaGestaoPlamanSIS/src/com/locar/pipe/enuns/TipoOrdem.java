package com.locar.pipe.enuns;

public enum TipoOrdem {

	CORRETIVA("corretiva"),
	PREVENTIVA("preventiva");
	
	private String tipo;
	
	private TipoOrdem(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}

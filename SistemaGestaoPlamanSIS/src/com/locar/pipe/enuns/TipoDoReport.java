package com.locar.pipe.enuns;

public enum TipoDoReport {

	BUG("bug"),
	MELHORIA("melhoria"),
	CORRECAO("correcao");
	
	private String tipo;
	
	private TipoDoReport(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
}

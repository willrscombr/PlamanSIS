package com.locar.pipe.enuns;

public enum TipoDeOrdem {

	CORRETIVA("corretiva"), PREVENTIVA("preventiva");

	String tipoOs;

	TipoDeOrdem(String tipo) {
		this.tipoOs = tipo;
	}

	public String getTipoOs() {
		return tipoOs;
	}
}

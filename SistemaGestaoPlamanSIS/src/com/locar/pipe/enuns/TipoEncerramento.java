package com.locar.pipe.enuns;

public enum TipoEncerramento {

	TECNICO("técnico"), COMERCIAL("comercial");

	String tipoEncerramento;

	TipoEncerramento(String tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}

	public String getTipoEncerramento() {
		return tipoEncerramento;
	}
}

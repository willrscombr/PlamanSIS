package com.locar.pipe.enuns;

public enum TipoEncerramento {

	TECNICO("tecnico"), COMERCIAL("comercial"),PARCIAL("parcial");

	String tipoEncerramento;

	TipoEncerramento(String tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}

	public String getTipoEncerramento() {
		return tipoEncerramento;
	}
}

package com.locar.pipe.enuns;

public enum TipoEncerramento {

	TECNICO("técnico"), COMERCIAL("comercial"),NAOENCERRADO("não encerrado"),PARCIAL("parcial");

	String tipoEncerramento;

	TipoEncerramento(String tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}

	public String getTipoEncerramento() {
		return tipoEncerramento;
	}
}

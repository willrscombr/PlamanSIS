package com.locar.pipe.enuns;

public enum TipoEncerramento {

	TECNICO("t�cnico"), COMERCIAL("comercial"),NAOENCERRADO("n�o encerrado"),PARCIAL("parcial");

	String tipoEncerramento;

	TipoEncerramento(String tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}

	public String getTipoEncerramento() {
		return tipoEncerramento;
	}
}

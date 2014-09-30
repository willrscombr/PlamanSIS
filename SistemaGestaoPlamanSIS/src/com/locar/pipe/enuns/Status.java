package com.locar.pipe.enuns;

public enum Status {

	ABERTO("aberto"), PROGRAMADO("programado"), IMPRESSO("impresso"), CANCELADO(
			"cancelado"), ATRASO("atraso"), ENCERRADO("encerrado"), PENDENTE(
			"pendente"),FECHADO("fechado");

	
	String status;
	
	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

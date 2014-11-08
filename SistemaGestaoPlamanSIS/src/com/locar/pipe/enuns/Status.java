package com.locar.pipe.enuns;

public enum Status {

	ABERTO("aberto"), PROGRAMADO("programado"), IMPRESSO("impresso"),ANDAMENTO("andamento"), CANCELADO(
			"cancelado"), ATRASO("atraso"), ENCERRADO("encerrado"), PENDENTE(
			"pendente"),ATENDIDO("atendido"), CONFIRMADO("confirmado");

	
	String status;
	
	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

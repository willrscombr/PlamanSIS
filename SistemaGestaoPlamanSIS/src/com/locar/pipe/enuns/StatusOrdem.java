package com.locar.pipe.enuns;

public enum StatusOrdem {

	ABERTO("aberto"), DESIGNADO("designado"), IMPRESSO("impresso"), CANCELADO(
			"cancelado"), ATRASO("atraso"), ENCERRADO("encerrado"), PENDENTE(
			"pendente");

	
	String status;
	
	StatusOrdem(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}

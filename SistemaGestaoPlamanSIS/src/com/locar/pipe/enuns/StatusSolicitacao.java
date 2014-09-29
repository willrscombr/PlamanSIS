package com.locar.pipe.enuns;

public enum StatusSolicitacao {

	ABERTO("aberto"),
	FECHADO("fechado"),
	CACELADO("cancelado");
	
	
	private String status;
	
	StatusSolicitacao(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status.toString();
	}
	
	
}

package com.locar.pipe.enuns;

public enum TipoTrabalho {

	LUBRIFICACAO("lubrifica��o"),
	REAPERTO("reaperto"),
	RETRABALHO("retrabalho"),
	INSPE�AO("inspe��o"),
	REVISAO("revis�o"),
	SEGURAN�A("seguran�a");
	
	private String tipoTrabalho;
	
	TipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	
	public String getTipoTrabalho() {
		return tipoTrabalho;
	}
}

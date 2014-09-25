package com.locar.pipe.enuns;

public enum TipoTrabalho {

	LUBRIFICACAO("lubrificão"),
	REAPERTO("reaperto"),
	RETRABALHO("retrabalho"),
	INSPEÇAO("inspeção"),
	REVISAO("revisão"),
	SEGURANÇA("segurança");
	
	private String tipoTrabalho;
	
	TipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	
	public String getTipoTrabalho() {
		return tipoTrabalho;
	}
}

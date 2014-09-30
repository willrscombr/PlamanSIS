package com.locar.pipe.enuns;

public enum TipoTrabalho {

	LUBRIFICACAO("lubrificão"),
	REAPERTO("reaperto"),
	REPARO("reparo"),
	RETRABALHO("retrabalho"),
	INSPEÇAO("inspeção"),
	REVISAO("revisão"),
	SEGURANÇA("segurança"),
	OUTROS("outros");
	
	private String tipoTrabalho;
	
	TipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	
	public String getTipoTrabalho() {
		return tipoTrabalho;
	}
}

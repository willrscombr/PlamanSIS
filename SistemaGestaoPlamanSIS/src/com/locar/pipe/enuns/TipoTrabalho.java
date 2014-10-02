package com.locar.pipe.enuns;

public enum TipoTrabalho {

	LUBRIFICACAO("Lubrificacão"),
	REAPERTO("Reaperto"),
	REPARO("Reparo"),
	RETRABALHO("Retrabalho"),
	INSPEÇAO("Inspeção"),
	REVISAO("Revisão"),
	SEGURANÇA("Segurança"),
	OUTROS("Outros");
	
	private String tipoTrabalho;
	
	TipoTrabalho(String tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}
	
	public String getTipoTrabalho() {
		return tipoTrabalho;
	}
	
	@Override
	public String toString() {
		
		return tipoTrabalho;
	}
}

package com.locar.pipe.enuns;

public enum TipoDePesquisa {

	ID("id"),
	STATUS("status"),
	DATA("data"),
	EQUIPAMENTO("equipamento");
	
	private String pesquisa;
	
	TipoDePesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
}

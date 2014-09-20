package com.locar.pipe.enuns;

public enum ModoCorretivo {

	PROGRAMADA("programada"), EMERGENCIAL("emerg�ncial");
	
	String modo;
	
	ModoCorretivo(String modo) {
		this.modo = modo;
	}

	public String getModo() {
		return modo;
	}
}

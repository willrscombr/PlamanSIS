package com.locar.pipe.enuns;

public enum ModoCorretivo {

	PROGRAMADA("programada"), EMERGENCIAL("emerg�ncial"), AUTONOMA("autonoma");
	
	String modo;
	
	ModoCorretivo(String modo) {
		this.modo = modo;
	}

	public String getModo() {
		return modo;
	}
}

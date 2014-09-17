package com.locar.pipe.enuns;

public enum StatusOrdem {
	
	PROGRAMADA,
	ABERTA,
	ANDAMENTO,
	CANCELADA,
	ATRASO,
	ENCERRADA,
	PENDENTE;
	
	
	public String toString(){
		return name(); 
	}

}

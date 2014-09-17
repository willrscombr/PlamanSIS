package com.locar.pipe.dominio;

import com.locar.pipe.generalizacoes.OrdemServicoGeneralizacao;

public class Osp extends OrdemServicoGeneralizacao{

	private static final long serialVersionUID = 1L;
	private int cicloEmHoras;
	private int cicloEmDias;
	
	
	public int getCicloEmHoras() {
		return cicloEmHoras;
	}
	public void setCicloEmHoras(int cicloEmHoras) {
		this.cicloEmHoras = cicloEmHoras;
	}
	public int getCicloEmDias() {
		return cicloEmDias;
	}
	public void setCicloEmDias(int cicloEmDias) {
		this.cicloEmDias = cicloEmDias;
	}
	
	
	

}

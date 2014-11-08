package com.locar.pipe.filtros;

import java.util.Date;

import com.locar.pipe.modelos.Usuario;

public class FiltroLog {

	private Date inicio;
	private Date fim;
	private Usuario usuario;
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFim() {
		return fim;
	}
	public void setFim(Date fim) {
		this.fim = fim;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.locar.pipe.dominio.CentroTrabalho;

@ManagedBean
@ApplicationScoped
public class CentroTrabalhoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private CentroTrabalho centro;
	private List<CentroTrabalho> centrosCadastrado;

	// --------------CONSTRUTORES----------------------
	public CentroTrabalhoBean() {
		centro = new CentroTrabalho();
		centrosCadastrado = new ArrayList<CentroTrabalho>();
	}

	// --------------METODOS DA VIEW-------------------
	public void salvarCentroDeTrabalho() {		
		centrosCadastrado.add(centro);
		centro = new CentroTrabalho();
		FacesMessage msg = new FacesMessage("Salvo Com Sucesso!");
		FacesContext.getCurrentInstance().addMessage("Sucesso", msg);

	}

	// --------------GETTERS AND SETTERS--------------
	public CentroTrabalho getCentro() {
		return centro;
	}

	public void setCentro(CentroTrabalho centro) {
		this.centro = centro;
	}

	public List<CentroTrabalho> getCentrosCadastrado() {
		return centrosCadastrado;
	}

	public void setCentrosCadastrado(List<CentroTrabalho> centrosCadastrado) {
		this.centrosCadastrado = centrosCadastrado;
	}

}

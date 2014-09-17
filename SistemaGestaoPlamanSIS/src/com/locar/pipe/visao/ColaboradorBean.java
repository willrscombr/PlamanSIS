package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.locar.pipe.modelos.Colaborador;

@ManagedBean
@SessionScoped
public class ColaboradorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Colaborador colaborador;
	private List<Colaborador> colaboradores;
	private List<Colaborador> ColaboradoresFiltrados;
	private Colaborador colaboradorSelecionado;
	private String textoPesquisa;
	
	public ColaboradorBean(){
		this.colaborador = new Colaborador();
		this.colaboradorSelecionado = new Colaborador();
		this.colaboradores = new ArrayList<Colaborador>();
		this.ColaboradoresFiltrados = new ArrayList<Colaborador>();
		this.textoPesquisa = new String("");
	}
	
	//-----------------------------------------Metodos Gerais----------------------------------------------------------

	public void salvarColaborador(){
		if(validarColaborador(colaborador)){
			this.colaboradores.add(colaborador);
			this.colaborador = new Colaborador();
			
			FacesMessage msg = new FacesMessage("Salvo com Sucesso :) !");
			FacesContext.getCurrentInstance().addMessage("Sucesso", msg);
		}else{
			FacesMessage msg = new FacesMessage("Ouve algum problema ao tentar salvar :( !");
			FacesContext.getCurrentInstance().addMessage("Sucesso", msg);
		}
	}
	
	public void excluirColaborador(){
		this.colaboradores.remove(this.colaboradorSelecionado);
		pesquisa();
	}
	
	public String iniciarListaColaborador(){
		return "listaColaborador?faces-redirect=true";
	}
	
	public void pesquisa(){
		this.ColaboradoresFiltrados.clear();

		for(Colaborador colab : colaboradores){
			if(colab.getNome().toLowerCase().contains(textoPesquisa.toLowerCase())){
				this.ColaboradoresFiltrados.add(colab);
			}
		}
	}
	
	public void preencherTextoPesquisa(ValueChangeEvent event){
		System.out.println(textoPesquisa);
	}
	
	//------------------------------------------------Validação Dados---------------------------------------------------
	
	public boolean validarColaborador(Colaborador colab){
		if( ( !"".equals(colab.getNome()) ) && ( !"".equals(colab.getMatricula()) ) && (!"".equals(colab.getSetor()) ) ){
			return true;
		}else{
			return false;
		}
	}
	
	//----------------------------------------------Getters and Setters-------------------------------------------------
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public Colaborador getColaboradorSelecionado() {
		return colaboradorSelecionado;
	}

	public void setColaboradorSelecionado(Colaborador colaboradorSelecionado) {
		this.colaboradorSelecionado = colaboradorSelecionado;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public List<Colaborador> getColaboradoresFiltrados() {
		return ColaboradoresFiltrados;
	}

	

}

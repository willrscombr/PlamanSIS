package com.locar.pipe.visao;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.service.GestaoPlaman;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@SessionScoped
public class SegurancaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	private Colaborador colaboradorLogado;
	private GestaoPlaman gestao;
	
	@PostConstruct
	public void init(){
		colaboradorLogado = new Colaborador();
		gestao = new GestaoPlaman();
	}
	
	
	public String logar() {
		try {
			this.getRequest().login(login, senha);
			colaboradorLogado = gestao.colaboradorLogado();
			return "index?faces-redirect=true";
		} catch (ServletException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR, "Login ou senha nao confere");
			return null;
		}
	}

	public String logout() throws ServletException {
		this.limpaDados();
		return "/login?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public void limpaDados() throws ServletException {
		this.getRequest().logout();
		login = new String();
		senha = new String();
		colaboradorLogado = new Colaborador();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Colaborador getColaboradorLogado() {
		return colaboradorLogado;
	}


	public void setColaboradorLogado(Colaborador colaboradorLogado) {
		this.colaboradorLogado = colaboradorLogado;
	}

}

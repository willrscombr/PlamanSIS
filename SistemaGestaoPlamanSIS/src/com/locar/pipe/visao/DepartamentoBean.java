package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ApplicationScoped
public class DepartamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Departamento centro;
	private Departamento setorSelecionado;
	private DepartamentoRepository departamento;
	private List<Departamento> centrosCadastrado;

	// --------------CONSTRUTORES----------------------
	@PostConstruct
	public void init() {
		centro = new Departamento();
		departamento = new DepartamentoRepository();
		setorSelecionado = new Departamento();
		centrosCadastrado = new ArrayList<Departamento>();
		centrosCadastrado = departamento.listarSetor();
	}

	// --------------METODOS DA VIEW-------------------
	public void salvarCentroDeTrabalho() {		
		departamento.salvar(centro);
		centro = new Departamento();
		MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Departamento Salvo!");
	}
	
	public void editarCentroDeTrabalho(){
		departamento.editar(setorSelecionado);
		MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Departamento Editado!");
		this.init();
	}

	public void deletarCentroDeTrabalho(){
		departamento.excluir(setorSelecionado);
		MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Departamento excluido!");
		init();
	}
	// --------------GETTERS AND SETTERS--------------
	public Departamento getCentro() {
		return centro;
	}

	public void setCentro(Departamento centro) {
		this.centro = centro;
	}

	public List<Departamento> getCentrosCadastrado() {
		return centrosCadastrado;
	}

	public void setCentrosCadastrado(List<Departamento> centrosCadastrado) {
		this.centrosCadastrado = centrosCadastrado;
	}

	public Departamento getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Departamento setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

}

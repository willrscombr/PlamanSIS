package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.repository.infra.ColaboradorRepository;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.service.GestaoPlaman;

@ManagedBean
@ViewScoped
public class ColaboradorBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Colaborador colaboradorLogado;
	private Colaborador colaborador;
	private Colaborador colaboradorSelecionado;
	private List<Departamento> departamentos;
	public static List<Colaborador> colaboradores;
	private ColaboradorRepository registro;
	private DepartamentoRepository setor;
	private GestaoPlaman colaboradorService;

	//Metodos Funcionais
	@PostConstruct
	public void init(){
		colaboradorService = new GestaoPlaman();
		colaboradorLogado = new Colaborador();
		colaborador = new Colaborador();
		colaboradores = new ArrayList<Colaborador>();
		departamentos = new ArrayList<Departamento>();
		registro = new ColaboradorRepository();
		setor = new DepartamentoRepository();
		colaboradores = registro.listarTodos();
		departamentos = setor.listarSetor();
		colaboradorLogado = colaboradorService.colaboradorLogado();
	}

	public void atualizarListas(){
		colaboradores.clear();
		departamentos.clear();
		colaboradores = registro.listarTodos();
		departamentos = setor.listarSetor();
	}
	// ----------------Metodos Gerais---------------------

	public String salvarColaborador() {		
		registro.salvar(colaborador);
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Salvo com sucesso");
		FacesContext.getCurrentInstance().addMessage("", msg);
		colaborador = new Colaborador();
		atualizarListas();
		
		return "colaboradorIndex?faces-redirect=true";
	}
	
	
	public String editarColaborador(){
	//	registro.editar(colaboradorSelecionado);
		registro.editar(colaboradorSelecionado);
				
		atualizarListas();
		return "colaboradorIndex?faces-redirect=true";
	}
	
	public void deletar(){
		registro.excluir(colaboradorSelecionado);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Excluido dos Registros!");
		FacesContext.getCurrentInstance().addMessage("", msg);
		colaboradorSelecionado = new Colaborador();
		
		atualizarListas();
	}
	
	// ---------Getters and Setters-------------------
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public Colaborador getColaboradorSelecionado() {
		return colaboradorSelecionado;
	}

	public void setColaboradorSelecionado(Colaborador colaboradorSelecionado) {
		this.colaboradorSelecionado = colaboradorSelecionado;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public Colaborador getColaboradorLogado() {
		return colaboradorLogado;
	}

	public void setColaboradorLogado(Colaborador colaboradorLogado) {
		this.colaboradorLogado = colaboradorLogado;
	}

}

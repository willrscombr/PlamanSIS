package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.service.GestaoSolicitacao;
import com.locar.pipe.service.SolicitacaoException;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ViewScoped
public class SolicitacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private GestaoSolicitacao solicitacaoService; //Repositorio de regras de negocio da solicitacao
	private long qntSolicitacaoAberta;
	private long qntOrdemoAberta;
	private Colaborador colaboradorLogado;
	private List<SolicitacaoServico> solicitacoesAbertas;
	private List<SolicitacaoServico> solicitacoes;
	private List<Departamento> departamentos;
	private List<OrdemServico> ultimasOrdens;
	private SolicitacaoServico solicitacao;
	private OrdemServico ordemSelecionada;

	
	
	
	
	
	//--------------Metodos de apoio ao managerbean---------------------------
	@PostConstruct
	public void init(){
		solicitacaoService = new GestaoSolicitacao();
		colaboradorLogado = new Colaborador();
		solicitacoesAbertas = new ArrayList<SolicitacaoServico>();
		solicitacoes = new ArrayList<SolicitacaoServico>();
		departamentos = new ArrayList<Departamento>();
		ultimasOrdens = new ArrayList<OrdemServico>();
		solicitacao = new SolicitacaoServico();
		ordemSelecionada = new OrdemServico();
		
		this.carregarDados();
	}
	
	//--------Metodos de Servico do manangerbean-------------------
	
	public void salvar(){
		try {
			solicitacaoService.salvarSolicitacao(solicitacao);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Solicitação foi enviada ao setor, "+ solicitacao.getSetor().getNome());
			solicitacao = new SolicitacaoServico();
		} catch (SolicitacaoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN, e.getMessage());
		}
	}

	// ------Metodos para as Views de do setor planejamento-----------------
	public void carregarDados() {
		colaboradorLogado = solicitacaoService.colaboradorLogado();
		solicitacoesAbertas = solicitacaoService.solicitacoesAbertas();
		solicitacoes = solicitacaoService.todasSolicitacoes();
		departamentos = solicitacaoService.todosDepartamento();
		ultimasOrdens = solicitacaoService.ordensInicio();
		qntOrdemoAberta = solicitacaoService.ordensAbertas();
		qntSolicitacaoAberta = solicitacaoService.totalSolicitaçãoAberta();
	}

	public TipoTrabalho[] tiposTrabalho() {
		return TipoTrabalho.values();
	}

	// ----------------------Getters and Setters------------------------------
	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public OrdemServico getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(OrdemServico ordemSelecionada) {
		this.ordemSelecionada = ordemSelecionada;
	}

	public long getQntSolicitacaoAberta() {
		return qntSolicitacaoAberta;
	}

	public long getQntOrdemoAberta() {
		return qntOrdemoAberta;
	}

	public Colaborador getColaboradorLogado() {
		return colaboradorLogado;
	}

	public List<SolicitacaoServico> getsolicitacoesAbertas() {
		return solicitacoesAbertas;
	}

	public List<SolicitacaoServico> getSolicitacoes() {
		return solicitacoes;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public List<OrdemServico> getUltimasOrdens() {
		return ultimasOrdens;
	}

}
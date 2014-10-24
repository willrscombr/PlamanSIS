package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.exceptions.SolicitacaoException;
import com.locar.pipe.filtros.FiltrosSolicitacoes;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.service.GestaoPlaman;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ViewScoped
public class SolicitacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private GestaoPlaman solicitacaoService; //Repositorio de regras de negocio da solicitacao
	private long qntSolicitacaoAberta;
	private long qntOrdemoAberta;
	private int qntTotalOrdem;
	private Colaborador colaboradorLogado;
	private List<SolicitacaoServico> solicitacoesAbertas;
	private List<SolicitacaoServico> solicitacoes;
	private List<Departamento> departamentos;
	private List<OrdemServicoCorretiva> ultimasOrdens;
	private SolicitacaoServico solicitacao;
	private OrdemServicoCorretiva ordemSelecionada;
	
	private FiltrosSolicitacoes filtros;
	private boolean pesquisaAvancada;

	
	
	
	
	
	//--------------Metodos de apoio ao managerbean---------------------------
	@PostConstruct
	public void init(){
		solicitacaoService = new GestaoPlaman();
		colaboradorLogado = new Colaborador();
		solicitacoesAbertas = new ArrayList<SolicitacaoServico>();
		solicitacoes = new ArrayList<SolicitacaoServico>();
		departamentos = new ArrayList<Departamento>();
		ultimasOrdens = new ArrayList<OrdemServicoCorretiva>();
		solicitacao = new SolicitacaoServico();
		ordemSelecionada = new OrdemServicoCorretiva();
		filtros = new FiltrosSolicitacoes();
		
		this.carregarDados();
	}
	
	//--------Metodos de Servico do manangerbean-------------------
	
	public void salvar(){
		try {
			solicitacaoService.salvarSolicitacao(solicitacao);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Solicitação foi enviada ao Planejamento");
			solicitacao = new SolicitacaoServico();
		} catch (SolicitacaoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN, e.getMessage());
		}
	}

	// ------Metodos para as Views de do setor planejamento-----------------
	public void carregarDados() {
		ultimasOrdens.clear();
		colaboradorLogado = solicitacaoService.colaboradorLogado();
		solicitacoesAbertas = solicitacaoService.solicitacoesAbertas();
		solicitacoes = solicitacaoService.pesquisarPorFiltro(filtros);
		departamentos = solicitacaoService.todosDepartamento();
		ultimasOrdens = solicitacaoService.listarUltimasOrdem();
		qntOrdemoAberta = solicitacaoService.quantidadeOrdensAbertas();
		qntSolicitacaoAberta = solicitacaoService.totalSolicitaçãoAberta();
		qntTotalOrdem = solicitacaoService.totalDeOrdem();
	}

	public TipoTrabalho[] tiposTrabalho() {
		return TipoTrabalho.values();
	}
	
	public void chamaPesquisa(ValueChangeEvent event){
		filtros.setEquipamento(event.getNewValue().toString());
		this.pesquisar();
	}
	public void pesquisar(){
		solicitacoes = solicitacaoService.pesquisarPorFiltro(filtros);
		filtros = new FiltrosSolicitacoes();
	}
	
	public Status[] todosStatus(){
		return Status.values();
	}
	// ----------------------Getters and Setters------------------------------
	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public OrdemServicoCorretiva getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(OrdemServicoCorretiva ordemSelecionada) {
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

	public List<OrdemServicoCorretiva> getUltimasOrdens() {
		return ultimasOrdens;
	}

	public FiltrosSolicitacoes getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltrosSolicitacoes filtros) {
		this.filtros = filtros;
	}

	public boolean isPesquisaAvancada() {
		return pesquisaAvancada;
	}

	public void setPesquisaAvancada(boolean pesquisaAvancada) {
		this.pesquisaAvancada = pesquisaAvancada;
	}

	public int getQntTotalOrdem() {
		return qntTotalOrdem;
	}

	public void setQntTotalOrdem(int qntTotalOrdem) {
		this.qntTotalOrdem = qntTotalOrdem;
	}

}
package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.interfaces.OrdemServicoInterface;
import com.locar.pipe.interfaces.SolicitacoesInterface;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.OrdemServicoRepository;
import com.locar.pipe.repository.SolicitacoesRepositorio;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ViewScoped
public class SolicitacaoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private long qntSolicitacaoAberta;
	private long qntOrdemoAberta;
	private Colaborador colaboradorLogado;
	private List<SolicitacaoServico> listaPorStatus;
	private List<SolicitacaoServico> solicitacoes;
	private List<OrdemServico> ultimasOrdens;
	private SolicitacaoServico solicitacao;
	private SolicitacoesInterface solicitacaoOrdem;
	private OrdemServicoInterface servidorOrdem;
	private OrdemServico ordemSelecionada;
	
	public SolicitacaoBean() {
		qntOrdemoAberta = 0;
		qntSolicitacaoAberta = 0;
		colaboradorLogado = new Colaborador();
		ordemSelecionada = new OrdemServico();
		solicitacoes = new ArrayList<SolicitacaoServico>();
		listaPorStatus = new ArrayList<SolicitacaoServico>();
		ultimasOrdens = new ArrayList<OrdemServico>();
		solicitacaoOrdem = new SolicitacoesRepositorio();
		solicitacao = new SolicitacaoServico();
		servidorOrdem = new OrdemServicoRepository();
	}
	
	@PostConstruct
	public void init(){
		solicitacoes = solicitacaoOrdem.listarTodas();
		qntSolicitacaoAberta = listaPorStatus.size();
		colaboradorLogado = SegurancaBean.colaboradorLogado;
		listaPorStatus = solicitacaoOrdem.listarPorStatusSetor(colaboradorLogado.getSetor(),Status.ABERTO);
		ultimasOrdens = servidorOrdem.listarUltimasCinco(colaboradorLogado.getSetor());
		qntOrdemoAberta = servidorOrdem.qntDeOrdemPorSetorStatus(colaboradorLogado.getSetor(),Status.ABERTO);
		qntSolicitacaoAberta = solicitacaoOrdem.qntPorSetorStatus(colaboradorLogado.getSetor(), Status.ABERTO);
	}
	
	//-------METODOS DE GERENCIAMENTO---------
	public void salvar(){
		solicitacao.setDataCriacao(Calendar.getInstance().getTime());
		solicitacao.setStatus(Status.ABERTO);
		solicitacaoOrdem.salvarSolicitcao(solicitacao);
		MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Sucesso", "Solicitação foi enviada ao planejamento");
		this.init();
	}
	
	//-------METODOS AUXILIARES---------------
	
	public TipoTrabalho[] tiposDeTrabalhos(){
		return TipoTrabalho.values();
	}

	//-------GETTERS AND SETTERS---------
	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public SolicitacoesInterface getSolicitacaoOrdem() {
		return solicitacaoOrdem;
	}

	public void setSolicitacaoOrdem(SolicitacoesInterface solicitacaoOrdem) {
		this.solicitacaoOrdem = solicitacaoOrdem;
	}

	public List<SolicitacaoServico> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<SolicitacaoServico> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public long getQntSolicitacaoAberta() {
		return qntSolicitacaoAberta;
	}

	public void setQntSolicitacaoAberta(long qntSolicitacaoAberta) {
		this.qntSolicitacaoAberta = qntSolicitacaoAberta;
	}

	public List<SolicitacaoServico> getListaPorStatus() {
		return listaPorStatus;
	}

	public void setListaPorStatus(List<SolicitacaoServico> listaPorStatus) {
		this.listaPorStatus = listaPorStatus;
	}

	public long getQntOrdemoAberta() {
		return qntOrdemoAberta;
	}

	public void setQntOrdemoAberta(int qntOrdemoAberta) {
		this.qntOrdemoAberta = qntOrdemoAberta;
	}

	public List<OrdemServico> getUltimasOrdens() {
		return ultimasOrdens;
	}

	public OrdemServico getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(OrdemServico ordemSelecionada) {
		this.ordemSelecionada = ordemSelecionada;
	}
}

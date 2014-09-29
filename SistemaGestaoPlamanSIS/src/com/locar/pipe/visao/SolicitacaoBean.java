package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.locar.pipe.converters.SetorConverter;
import com.locar.pipe.enuns.StatusSolicitacao;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.interfaces.ColecaoOsInterface;
import com.locar.pipe.interfaces.SolicitacoesOrdem;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.ColecaoOsRepository;
import com.locar.pipe.repository.SolicitacoesRepositorio;
import com.locar.pipe.util.HibernateUtil;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ViewScoped
public class SolicitacaoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int qntSolicitacaoAberta;
	private long qntOrdemoAberta;
	private Colaborador colaboradorLogado;
	private List<SolicitacaoServico> listaPorStatus;
	private SolicitacaoServico solicitacao;
	private List<SolicitacaoServico> solicitacoes;
	private SolicitacoesOrdem solicitacaoOrdem;
	private ColecaoOsInterface servidorOrdem;
	
	@PostConstruct
	public void init(){
		qntOrdemoAberta = 0;
		qntSolicitacaoAberta = 0;
		colaboradorLogado = new Colaborador();
		solicitacoes = new ArrayList<SolicitacaoServico>();
		listaPorStatus = new ArrayList<SolicitacaoServico>();
		solicitacaoOrdem = new SolicitacoesRepositorio();
		solicitacao = new SolicitacaoServico();
		solicitacoes = solicitacaoOrdem.listarTodas();
		listaPorStatus = solicitacaoOrdem.listarPorStatus(StatusSolicitacao.ABERTO);
		qntSolicitacaoAberta = listaPorStatus.size();
		servidorOrdem = new ColecaoOsRepository();
		colaboradorLogado = SegurancaBean.colaboradorLogado;
		qntOrdemoAberta = servidorOrdem.qntDeOrdemPorSetor(colaboradorLogado.getSetor());
	}
	
	//-------METODOS DE GERENCIAMENTO---------
	public void salvar(){
		solicitacao.setDataCriacao(Calendar.getInstance().getTime());
		solicitacao.setStatusSolicitacao(StatusSolicitacao.ABERTO);
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

	public SolicitacoesOrdem getSolicitacaoOrdem() {
		return solicitacaoOrdem;
	}

	public void setSolicitacaoOrdem(SolicitacoesOrdem solicitacaoOrdem) {
		this.solicitacaoOrdem = solicitacaoOrdem;
	}



	public List<SolicitacaoServico> getSolicitacoes() {
		return solicitacoes;
	}



	public void setSolicitacoes(List<SolicitacaoServico> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public int getQntSolicitacaoAberta() {
		return qntSolicitacaoAberta;
	}

	public void setQntSolicitacaoAberta(int qntSolicitacaoAberta) {
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
}

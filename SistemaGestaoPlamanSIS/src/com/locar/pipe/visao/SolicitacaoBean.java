package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.locar.pipe.enuns.StatusSolicitacao;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.interfaces.SolicitacoesOrdem;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.SolicitacoesRepositorio;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ApplicationScoped
public class SolicitacaoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private SolicitacaoServico solicitacao;
	private List<SolicitacaoServico> solicitacoes;
	private SolicitacoesOrdem solicitacaoOrdem;
	
	@PostConstruct
	public void init(){
		solicitacoes = new ArrayList<SolicitacaoServico>();
		solicitacaoOrdem = new SolicitacoesRepositorio();
		solicitacao = new SolicitacaoServico();
		solicitacoes = solicitacaoOrdem.listarTodas();
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
	
	

}

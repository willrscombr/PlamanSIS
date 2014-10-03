package com.locar.pipe.service;

import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.DepartamentosDB;
import com.locar.pipe.repository.OrdemServicoDB;
import com.locar.pipe.repository.SolicitacoesDB;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.repository.infra.OrdemServicoRepository;
import com.locar.pipe.repository.infra.SolicitacoesRepositorio;
import com.locar.pipe.visao.SegurancaBean;

public class GestaoSolicitacao {

	private OrdemServicoDB dominioOrdem;
	private DepartamentosDB dominioDepartamento;
	private SolicitacoesDB dominioSolicitacao;

	// -------------- Construtor------------------------
	public GestaoSolicitacao() {
		dominioOrdem = new OrdemServicoRepository();
		dominioDepartamento = new DepartamentoRepository();
		dominioSolicitacao = new SolicitacoesRepositorio();

	}

	public void salvarSolicitacao(SolicitacaoServico solicitacao)
			throws SolicitacaoException {
		
		solicitacao.setDataCriacao(Calendar.getInstance().getTime());
		solicitacao.setStatus(Status.ABERTO);
		solicitacao.setSolicitante(colaboradorLogado());
		
		if (jaExisteSolicitacao(solicitacao)) {
			throw new SolicitacaoException(
					"Já existe uma solicitação igual a essa no sistema");
		}
		dominioSolicitacao.save(solicitacao);
	}

	// -------------Metodos Auxiliares para o ManangerBean
	// SolicitacaoBean---------------------
	public Colaborador colaboradorLogado() {
		return SegurancaBean.colaboradorLogado;
	}

	public List<SolicitacaoServico> solicitacoesAbertas() {
		return dominioSolicitacao.listarPorStatusSetor(colaboradorLogado()
				.getSetor(), Status.ABERTO);
	}

	public List<SolicitacaoServico> todasSolicitacoes() {
		return dominioSolicitacao.getAll();
	}

	public List<Departamento> todosDepartamento() {
		return dominioDepartamento.listarSetor();
	}

	public List<OrdemServico> ordensInicio() {
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("planejamento")){
			return dominioOrdem.listarUltimasCinco();
		}
		
		return dominioOrdem.listarPorStatus(colaboradorLogado().getSetor(), Status.PENDENTE);
	}

	public long ordensAbertas() {
		return dominioOrdem.qntDeOrdemPorSetorStatus(colaboradorLogado()
				.getSetor(), Status.ABERTO);
	}

	public long qntSolicitacoesPorSetor(Departamento setor) {
		return dominioSolicitacao.qntPorSetor(setor);
	}
	
	public long qntSolicitacoesPorStatus(Status status) {
		return dominioSolicitacao.qntPorStatus(status);
	}

	private boolean jaExisteSolicitacao(SolicitacaoServico solicitacao) {
		SolicitacaoServico solicita = dominioSolicitacao.jaExiste(solicitacao);
		if (solicita != null) {
			System.out.println("Solicitacao nao é");
		}
		return solicita != null && !solicita.equals(solicitacao);

	}

	public long totalSolicitaçãoAberta(){
		
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("supervisao")){
			return solicitacoesAbertas().size();
		}
		
		return qntSolicitacoesPorStatus(Status.ABERTO);
	}
}

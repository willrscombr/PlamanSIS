package com.locar.pipe.service;

import java.util.List;

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

	//-------------- Construtor------------------------
	public GestaoSolicitacao() {
		dominioOrdem = new OrdemServicoRepository();
		dominioDepartamento = new DepartamentoRepository();
		dominioSolicitacao = new SolicitacoesRepositorio();

	}

	
	//-------------Metodos Auxiliares para o ManangerBean SolicitacaoBean---------------------
	public Colaborador colaboradorLogado() {
		return SegurancaBean.colaboradorLogado;
	}

	public List<SolicitacaoServico> solicitacoesAbertas() {
		return dominioSolicitacao.listarPorStatusSetor(colaboradorLogado()
				.getSetor(), Status.ABERTO);
	}

	public List<SolicitacaoServico> todasSolicitacoes() {
		return dominioSolicitacao.listarTodas();
	}

	public List<Departamento> todosDepartamento() {
		return dominioDepartamento.listarSetor();
	}

	public List<OrdemServico> ultimasOrdem() {
		return dominioOrdem.listarUltimasCinco(colaboradorLogado().getSetor());
	}


	public long ordensAbertas() {
		return dominioOrdem.qntDeOrdemPorSetorStatus(colaboradorLogado().getSetor(), Status.ABERTO);
	}


	public long solicitacoesAberta() {
		return dominioSolicitacao.qntPorSetorStatus(colaboradorLogado().getSetor(), Status.ABERTO);
	}


	public void salvarSolicitacao(SolicitacaoServico solicitacao) {
				
	}
	

}

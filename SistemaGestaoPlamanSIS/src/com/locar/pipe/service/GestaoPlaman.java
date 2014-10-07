package com.locar.pipe.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.filtros.FiltrosSolicitacoes;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.ColaboradorDB;
import com.locar.pipe.repository.DepartamentosDB;
import com.locar.pipe.repository.OrdemServicoDB;
import com.locar.pipe.repository.SolicitacoesDB;
import com.locar.pipe.repository.infra.ColaboradorRepository;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.repository.infra.OrdemServicoRepository;
import com.locar.pipe.repository.infra.SolicitacoesRepositorio;
import com.locar.pipe.util.MensagensUtil;
import com.locar.pipe.visao.SegurancaBean;
import com.locar.pipe.visao.SolicitacaoBean;

public class GestaoPlaman implements Serializable {
	private static final long serialVersionUID = 1L;

	private OrdemServicoDB dominioOrdem;
	private DepartamentosDB dominioDepartamento;
	private SolicitacoesDB dominioSolicitacao;
	private ColaboradorDB dominioColaborador;

	// -------------- Construtor------------------------
	public GestaoPlaman() {
		dominioColaborador = new ColaboradorRepository();
		dominioOrdem = new OrdemServicoRepository();
		dominioDepartamento = new DepartamentoRepository();
		dominioSolicitacao = new SolicitacoesRepositorio();

	}

	// -------- METODOS UTIL--------------
	public Colaborador colaboradorLogado() {
		return SegurancaBean.colaboradorLogado;
	}

	public List<Departamento> todosDepartamento() {
		return dominioDepartamento.listarSetor();
	}

	public String cortaLetras(String texto) {
		texto = texto.toLowerCase();
		texto = texto.replace("a", "");
		texto = texto.replace("b", "");
		texto = texto.replace("c", "");
		texto = texto.replace("d", "");
		texto = texto.replace("e", "");
		texto = texto.replace("f", "");
		texto = texto.replace("g", "");
		texto = texto.replace("h", "");
		texto = texto.replace("i", "");
		texto = texto.replace("j", "");
		texto = texto.replace("k", "");
		texto = texto.replace("l", "");
		texto = texto.replace("m", "");
		texto = texto.replace("n", "");
		texto = texto.replace("o", "");
		texto = texto.replace("p", "");
		texto = texto.replace("q", "");
		texto = texto.replace("r", "");
		texto = texto.replace("s", "");
		texto = texto.replace("t", "");
		texto = texto.replace("u", "");
		texto = texto.replace("v", "");
		texto = texto.replace("w", "");
		texto = texto.replace("y", "");
		texto = texto.replace("x", "");
		texto = texto.replace("z", "");

		return texto;
	}

	// ------ VIEW SOLICITAÇÃO BEAN ---------------------------------
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

	public List<SolicitacaoServico> solicitacoesAbertas() {
		return dominioSolicitacao.listarPorStatusSetor(colaboradorLogado()
				.getSetor(), Status.ABERTO);
	}

	public List<SolicitacaoServico> todasSolicitacoes() {

		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("planejamento")) {
			return dominioSolicitacao.getAll();
		}

		return dominioSolicitacao.listarPorStatusSetor(colaboradorLogado()
				.getSetor(), null);
	}

	public List<OrdemServico> listarUltimasOrdem() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("planejamento")) {
			return dominioOrdem.listarUltimasCinco();
		}

		return dominioOrdem.listarPorStatus(colaboradorLogado().getSetor(),
				Status.PENDENTE);
	}

	public long quantidadeOrdensAbertas() {
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

	public long totalSolicitaçãoAberta() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("supervisao")) {
			return solicitacoesAbertas().size();
		}

		return qntSolicitacoesPorStatus(Status.ABERTO);
	}

	public List<SolicitacaoServico> pesquisarPorFiltro(
			FiltrosSolicitacoes filtro) {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("supervisao")) {
			filtro.setSetor(colaboradorLogado().getSetor());
			return dominioSolicitacao.pesquisarPorFiltros(filtro);
		}
		return dominioSolicitacao.pesquisarPorFiltros(filtro);
	}

	// ------ VIEW ORDEM SERVIÇO BEAN ---------------------------------
	public void salvarOrdemServico(OrdemServico ordem, boolean impresso)
			throws OrdemServicoException {

		if (impresso) {
			ordem.setStatus(Status.IMPRESSO);
		} else {
			ordem.setStatus(Status.ABERTO);
		}
		ordem.setDataCriacao(Calendar.getInstance().getTime());
		if (dominioOrdem.jaExiste(ordem)) {
			throw new OrdemServicoException(
					"Ja existe uma Ordem de serviço com dados iguais a este");
		} else {
			dominioOrdem.salvar(ordem);
			dominioSolicitacao.trocaStatus(ordem.getId_solicitacao(),
					Status.FECHADO);
		}
	}

	public List<OrdemServico> pesquisarPorFiltro(FiltrosOrdens filtro) {
		boolean ehSupervisor = FacesContext.getCurrentInstance()
				.getExternalContext().isUserInRole("supervisao");

		if (ehSupervisor) {
			filtro.setSetor(colaboradorLogado().getSetor());
			return dominioOrdem.pesquisarPorFiltros(filtro);
		}
		return dominioOrdem.pesquisarPorFiltros(filtro);
	}

	public long stringToValue(String pesquisa) throws OrdemServicoException {
		long id = 0;
		if (pesquisa.isEmpty()) {
			return id;
		} else {
			pesquisa = this.cortaLetras(pesquisa);
			
			try {
				if (!pesquisa.isEmpty()) {
					id = Long.parseLong(pesquisa);
				}else{
					throw new OrdemServicoException("Você não pode digitar isso");
				}
			} catch (Exception e) {
				throw new OrdemServicoException(
						"Voce deve digitar o numero da Ordem");
			}
		}
		
		return id;
	}
}

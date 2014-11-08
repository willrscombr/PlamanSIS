package com.locar.pipe.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.Status;
import com.locar.pipe.exceptions.FinalInvalidoException;
import com.locar.pipe.exceptions.IntervaloException;
import com.locar.pipe.exceptions.OrdemServicoException;
import com.locar.pipe.exceptions.SolicitacaoException;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.filtros.FiltrosSolicitacoes;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.ConfirmacaoOrdem;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.modelos.Usuario;
import com.locar.pipe.repository.BackLogDB;
import com.locar.pipe.repository.ColaboradorDB;
import com.locar.pipe.repository.ConfirmacaoOrdemDB;
import com.locar.pipe.repository.DepartamentosDB;
import com.locar.pipe.repository.OrdemServicoDB;
import com.locar.pipe.repository.SolicitacoesDB;
import com.locar.pipe.repository.UsuarioDB;
import com.locar.pipe.repository.infra.BackLogRepository;
import com.locar.pipe.repository.infra.ColaboradorRepository;
import com.locar.pipe.repository.infra.ConfirmacaoOrdemRepository;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.repository.infra.OrdemServicoRepository;
import com.locar.pipe.repository.infra.SolicitacoesRepositorio;
import com.locar.pipe.repository.infra.UsuarioRepository;

public class GestaoPlaman implements Serializable {
	private static final long serialVersionUID = 1L;

	private ColaboradorDB dominioColaborador;
	private OrdemServicoDB dominioOrdem;
	private DepartamentosDB dominioDepartamento;
	private SolicitacoesDB dominioSolicitacao;
	private ConfirmacaoOrdemDB dominioConfirmacao;
	private UsuarioDB dominioUsuario;
	private BackLogDB dominioBacklog;

	// -------------- Construtor------------------------
	public GestaoPlaman() {
		dominioBacklog = new BackLogRepository();
		dominioColaborador = new ColaboradorRepository();
		dominioOrdem = new OrdemServicoRepository();
		dominioDepartamento = new DepartamentoRepository();
		dominioSolicitacao = new SolicitacoesRepositorio();
		dominioConfirmacao = new ConfirmacaoOrdemRepository();
		dominioUsuario = new UsuarioRepository();

	}

	// -------- METODOS UTIL--------------
	public Colaborador colaboradorLogado() {
		Colaborador colaborador = new Colaborador();
		colaborador = dominioColaborador.buscarPorNome(FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal()
				.getName());
		return colaborador;
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

	public List<Colaborador> todosColaboradores() {
		return dominioColaborador.listarTodos();
	}

	public int totalDeOrdem() {
		return dominioOrdem.listarTodas().size();
	}
	
	public void registrarBacklog(Colaborador colab, String acao){
		BackLogApplication log = new BackLogApplication();
		log.setDataLog(Calendar.getInstance().getTime());
		log.setColaborador(colab);
		
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String dataFormated = dtf.format(log.getDataLog());
		String txtLog = dataFormated+" "+colab.getNome()+" -  "+acao;
		
		log.setTextoLog(txtLog);
		
		dominioBacklog.salvar(log);
		
		System.out.println(log.getTextoLog().toUpperCase());
	}

	// ------ VIEW SOLICITAÇÃO BEAN ---------------------------------
	public void salvarSolicitacao(SolicitacaoServico solicitacao)
			throws SolicitacaoException {

		solicitacao.setDataCriacao(Calendar.getInstance().getTime());
		solicitacao.setStatus(Status.ABERTO);
		solicitacao.setSolicitante(colaboradorLogado());

		if (jaExisteSolicitacao(solicitacao)) {
			registrarBacklog(colaboradorLogado(), "tentou abrir uma solicitação de serviço");
			throw new SolicitacaoException(
					"Já existe uma solicitação igual a essa no sistema");
		}else{
			dominioSolicitacao.save(solicitacao);
			registrarBacklog(colaboradorLogado(), "abriu uma solicitação de serviço");
		}
	}

	public List<SolicitacaoServico> solicitacoesAbertas() {
		Colaborador col = colaboradorLogado();
		System.out.println("SETOR DO COLABORADOR LOGADO: "
				+ col.getSetor().getNome());
		return dominioSolicitacao.listarPorStatusSetor(col.getSetor(),
				Status.ABERTO);
	}

	public List<SolicitacaoServico> todasSolicitacoes() {

		if (FacesContext.getCurrentInstance().getExternalContext()
				.isUserInRole("planejamento")) {
			return dominioSolicitacao.getAll();
		}

		return dominioSolicitacao.listarPorStatusSetor(colaboradorLogado()
				.getSetor(), null);
	}

	public List<OrdemServicoCorretiva> listarUltimasOrdem() {
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
	public void salvarOrdemServicoCorretiva(OrdemServicoCorretiva ordem,
			boolean impresso) throws OrdemServicoException {

		if (impresso) {
			ordem.setStatus(Status.IMPRESSO);
		} else {
			ordem.setStatus(Status.ABERTO);
			
		}

		if (ordem.getId_solicitacao() != 0) {
			ordem.setModoCorretivo(ModoCorretivo.PROGRAMADA);
			dominioSolicitacao.trocaStatus(ordem.getId_solicitacao(),
					Status.ATENDIDO);
		}

		if (dominioOrdem.jaExiste(ordem)) {
			registrarBacklog(colaboradorLogado(), "tentou criar uma ordem corretiva");
			throw new OrdemServicoException(
					"Ja existe uma Ordem de serviço com dados iguais a este");
		} else {
			ordem.setDataCriacao(Calendar.getInstance().getTime());
			dominioOrdem.salvar(ordem);
			registrarBacklog(colaboradorLogado(), "criou uma ordem corretiva");

		}
	}

	public List<OrdemServicoCorretiva> pesquisarPorFiltro(FiltrosOrdens filtro) {
		return dominioOrdem.pesquisarPorFiltros(filtro);
	}
	
	public List<OrdemServicoCorretiva> todasOrdens(){
		return dominioOrdem.listarTodas();
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
				} else {
					throw new OrdemServicoException(
							"Você não pode digitar isso");
				}
			} catch (Exception e) {
				throw new OrdemServicoException(
						"Voce deve digitar o numero da Ordem");
			}
		}

		return id;
	}

	public void programarOs(OrdemServicoCorretiva os) {
		os.setStatus(Status.PROGRAMADO);
		dominioOrdem.editar(os);
	}

	public void alterarOrdem(OrdemServicoCorretiva os, Status status) {
		os.setStatus(status);
		dominioOrdem.editar(os);
	}

	public List<Colaborador> colaboradoresPorSetor(Departamento setor) {
		return dominioColaborador.listarPorSetor(setor);
	}

	public void dataHoraPermitida(ConfirmacaoOrdem confirmacao)
			throws IntervaloException, FinalInvalidoException {
		
		LocalDateTime inicio = pegarDateTime(confirmacao.getDataInicio(),confirmacao.getHoraInicio());
		LocalDateTime fim = pegarDateTime(confirmacao.getDataFim(),confirmacao.getHoraFim());
		Colaborador colab = dominioColaborador.buscarPorNome(confirmacao.getColaborador().getNome());
		boolean foraDosIntervalos = false;
		
		if (!fim.isAfter(inicio)) {
			throw new FinalInvalidoException(
					"A hora final deve vir depois da hora inicial ex: inicio 10:00 - fim 11:00");
		}

		List<ConfirmacaoOrdem> confirmacoesPorColab = new ArrayList<ConfirmacaoOrdem>();
		if (colab != null) {
			confirmacoesPorColab = dominioConfirmacao.listaPorDataColaborador(
					colab, confirmacao.getDataInicio());
		}
		if (!confirmacoesPorColab.isEmpty()) {

			LocalDateTime inicioComparacao;
			LocalDateTime fimComparacao;

			for (ConfirmacaoOrdem confirm : confirmacoesPorColab) {

				inicioComparacao = pegarDateTime(confirm.getDataInicio(),
						confirm.getHoraInicio());
				fimComparacao = pegarDateTime(confirm.getDataFim(),
						confirm.getHoraFim());
				try {
					if (inicio.isAfter(inicioComparacao)
							&& inicio.isAfter(fimComparacao)
							|| fim.isBefore(inicioComparacao)
							&& inicio.isBefore(fim)) {
						foraDosIntervalos = true;
					} else {
						throw new IntervaloException(""
								+ confirm.getOrdemCorretiva().getId());
					}
				} catch (Exception e) {
					if (!foraDosIntervalos) {
						throw new IntervaloException(
								"Você ja possui uma confirmaçao neste periodo na OS: "
										+ e.getMessage());
					}
				}
			}
		}

	}

	public LocalDateTime pegarDateTime(Date data, Date hora) {
		Instant inst = Instant.ofEpochMilli(data.getTime());
		LocalDate dataInicio = LocalDateTime.ofInstant(inst,
				ZoneId.systemDefault()).toLocalDate();
		inst = Instant.ofEpochMilli(hora.getTime());
		LocalTime horaIni = LocalDateTime.ofInstant(inst,
				ZoneId.systemDefault()).toLocalTime();

		LocalDateTime dataTime = LocalDateTime.of(dataInicio, horaIni);

		return dataTime;
	}
	
	public int qntOrdemPorStatus(Status status){
		return (int)(dominioOrdem.qntDeOrdemPorSetorStatus(null, status));
	}
	
	public int qntOrdemPorSetor(Departamento depart){
		return (int)(dominioOrdem.qntDeOrdemPorSetorStatus(depart, null));
	}
	
	public int qntOrdemPorSetorStatus(Departamento depart, Status status){
		return (int)(dominioOrdem.qntDeOrdemPorSetorStatus(depart, status));
	}
	//--------------------------------BACKLOGS-------------------------------
	
	public List<BackLogApplication> logsPorUsuario(Colaborador colab){
		return dominioBacklog.listarPorColab(colab);
		
	}
	public List<Usuario> listarTodosUsers(){
		return dominioUsuario.listarTodos();
	}
}

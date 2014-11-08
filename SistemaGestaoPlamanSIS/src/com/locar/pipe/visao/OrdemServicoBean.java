package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoDePesquisa;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.exceptions.FinalInvalidoException;
import com.locar.pipe.exceptions.IntervaloException;
import com.locar.pipe.exceptions.OrdemServicoException;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.modelos.ConfirmacaoOrdem;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.service.GestaoPlaman;
import com.locar.pipe.util.DadosUtil;
import com.locar.pipe.util.DataGridUtil;
import com.locar.pipe.util.MensagensUtil;
import com.locar.pipe.util.RelatorioUtil;

@ManagedBean
@ViewScoped
public class OrdemServicoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private GestaoPlaman osService;
	private FiltrosOrdens filtros;
	private List<Departamento> departamentos;
	private List<Colaborador> filtroColaborador;
	private List<Colaborador> executores;
	private List<OrdemServicoCorretiva> ordensDeServico;
	private ConfirmacaoOrdem confirmacao;
	private List<ConfirmacaoOrdem> confirmacoes;
	private OrdemServicoCorretiva ordemServico;
	private SolicitacaoServico solicitacao;
	private OrdemServicoCorretiva ordemSelecionada;
	private String txtPesquisa;
	private int prazoParaOrdem;
	private boolean impresso;
	private boolean pesquisaAvancada;
	private boolean programarOs;
	private List<OrdemServicoCorretiva> ordensCorretivasRelatorio;
	private int totalDeOrdem;
	private int ordemAberta;
	private int ordemAndamento;
	private int ordemPendente;
	private int ordemConfirmada;
	private int ordemEncerrada;
	private BarChartModel barModel;
	private DataGridUtil dataGridUtil;

	@PostConstruct
	public void init() {
		this.dataGridUtil = new DataGridUtil();
		this.ordensCorretivasRelatorio = new ArrayList<OrdemServicoCorretiva>();
		this.confirmacoes = new ArrayList<ConfirmacaoOrdem>();
		this.confirmacao = new ConfirmacaoOrdem();
		this.executores = new ArrayList<Colaborador>();
		this.osService = new GestaoPlaman();
		this.solicitacao = new SolicitacaoServico();
		this.filtros = new FiltrosOrdens();
		this.ordensDeServico = new ArrayList<OrdemServicoCorretiva>();
		this.ordemSelecionada = new OrdemServicoCorretiva();
		this.ordemServico = new OrdemServicoCorretiva();
		this.departamentos = new ArrayList<Departamento>();
		this.filtroColaborador = new ArrayList<Colaborador>();
		this.departamentos = osService.todosDepartamento();
		this.ordensDeServico = osService.pesquisarPorFiltro(filtros);
		this.filtroColaborador = osService.todosColaboradores();
		createBarModel();
	}

	private BarChartModel initBarChartModel() {
		BarChartModel barModel = new BarChartModel();

		ChartSeries criadas = new ChartSeries();
		criadas.setLabel("Criadas");
		int totalCriada = 0;
		for (Departamento dep : departamentos) {
			if (!dep.getNome().equalsIgnoreCase("administracao")) {
				totalCriada = osService.qntOrdemPorSetor(dep);
				criadas.set(dep.getNome(), totalCriada);
			}
		}

		ChartSeries encerradas = new ChartSeries();
		encerradas.setLabel("Encerradas");
		int totalEncerrada = 0;
		for (Departamento dep : departamentos) {
			if (!dep.getNome().equalsIgnoreCase("administracao")) {
				totalEncerrada = osService.qntOrdemPorSetorStatus(dep,
						Status.ENCERRADO);
				encerradas.set(dep.getNome(), totalEncerrada);
			}
		}

		barModel.addSeries(criadas);
		barModel.addSeries(encerradas);
		return barModel;
	}

	private void createBarModel() {
		carregaDataGridUtil();
		barModel = initBarChartModel();
		barModel.setTitle("Grafico de desempenho(CRIADAS X ENCERRADAS)");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Centro de Trabalho");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");

	}

	private void carregaDataGridUtil() {
		for (Departamento dep : departamentos) {
			if (dep.getNome().equalsIgnoreCase("eletrica")) {
				DadosUtil eletrica = new DadosUtil();
				eletrica.setAberta((int) osService.qntOrdemPorSetorStatus(dep,
						Status.ABERTO));
				eletrica.setAndamento((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ANDAMENTO));
				eletrica.setConfirmada((int) osService.qntOrdemPorSetorStatus(
						dep, Status.CONFIRMADO));
				eletrica.setEncerrada((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ENCERRADO));
				eletrica.setPendente((int) osService.qntOrdemPorSetorStatus(
						dep, Status.PENDENTE));
				dataGridUtil.setEletrica(eletrica);
			}
			if (dep.getNome().equalsIgnoreCase("mecanica")) {
				DadosUtil mecanica = new DadosUtil();
				mecanica.setAberta((int) osService.qntOrdemPorSetorStatus(dep,
						Status.ABERTO));
				mecanica.setAndamento((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ANDAMENTO));
				mecanica.setConfirmada((int) osService.qntOrdemPorSetorStatus(
						dep, Status.CONFIRMADO));
				mecanica.setEncerrada((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ENCERRADO));
				mecanica.setPendente((int) osService.qntOrdemPorSetorStatus(
						dep, Status.PENDENTE));
				dataGridUtil.setMecanica(mecanica);
			}
			if (dep.getNome().equalsIgnoreCase("manutencao")) {
				DadosUtil manutencao = new DadosUtil();
				manutencao.setAberta((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ABERTO));
				manutencao.setAndamento((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ANDAMENTO));
				manutencao.setConfirmada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.CONFIRMADO));
				manutencao.setEncerrada((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ENCERRADO));
				manutencao.setPendente((int) osService.qntOrdemPorSetorStatus(
						dep, Status.PENDENTE));
				dataGridUtil.setManutencao(manutencao);
			}
			if (dep.getNome().equalsIgnoreCase("operacional")) {
				DadosUtil operacional = new DadosUtil();
				operacional.setAberta((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ABERTO));
				operacional.setAndamento((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ANDAMENTO));
				operacional.setConfirmada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.CONFIRMADO));
				operacional.setEncerrada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ENCERRADO));
				operacional.setPendente((int) osService.qntOrdemPorSetorStatus(
						dep, Status.PENDENTE));
				dataGridUtil.setOperacional(operacional);
			}
			if (dep.getNome().equalsIgnoreCase("planejamento")) {
				DadosUtil planejamento = new DadosUtil();
				planejamento.setAberta((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ABERTO));
				planejamento.setAndamento((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ANDAMENTO));
				planejamento.setConfirmada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.CONFIRMADO));
				planejamento.setEncerrada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ENCERRADO));
				planejamento.setPendente((int) osService
						.qntOrdemPorSetorStatus(dep, Status.PENDENTE));
				dataGridUtil.setPlanejamento(planejamento);
			}
			if (dep.getNome().equalsIgnoreCase("refrigeracao")) {
				DadosUtil refrigeracao = new DadosUtil();
				refrigeracao.setAberta((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ABERTO));
				refrigeracao.setAndamento((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ANDAMENTO));
				refrigeracao.setConfirmada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.CONFIRMADO));
				refrigeracao.setEncerrada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ENCERRADO));
				refrigeracao.setPendente((int) osService
						.qntOrdemPorSetorStatus(dep, Status.PENDENTE));
				dataGridUtil.setRefrigeracao(refrigeracao);
			}
			if (dep.getNome().equalsIgnoreCase("administracao")) {
				DadosUtil administracao = new DadosUtil();
				administracao.setAberta((int) osService.qntOrdemPorSetorStatus(
						dep, Status.ABERTO));
				administracao.setAndamento((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ANDAMENTO));
				administracao.setConfirmada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.CONFIRMADO));
				administracao.setEncerrada((int) osService
						.qntOrdemPorSetorStatus(dep, Status.ENCERRADO));
				administracao.setPendente((int) osService
						.qntOrdemPorSetorStatus(dep, Status.PENDENTE));
				dataGridUtil.setAdministracao(administracao);
				;
			}
		}
	}

	// ------------Metodos da View---------------
	public String salvar() {
		try {
			ordemServico.setTipoOrdem(TipoOrdem.CORRETIVA);// toda ordem criada
															// atraves de
															// solicitação são
															// corretivas
			osService.salvarOrdemServicoCorretiva(ordemServico, impresso);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO,
					"Ordem de serviço gerada com sucesso");
			ordemServico = new OrdemServicoCorretiva();

			return "";
		} catch (OrdemServicoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN,
					e.getMessage());
		} finally {
			ordensDeServico = osService.pesquisarPorFiltro(filtros);
		}

		return "";
	}

	public void programa() {
		osService.programarOs(ordemSelecionada);
		MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO,
				"Ordem Programada");
		ordemSelecionada = new OrdemServicoCorretiva();
		programarOs = false;
	}

	public void imprimir() {
		impresso = true;
		this.salvar();
	}

	@SuppressWarnings("rawtypes")
	public void gerarOrdem() {
		try {
			String osName = "relatorioModeloPlaman";
			List<OrdemServicoCorretiva> osPrint = new ArrayList<OrdemServicoCorretiva>();
			osPrint.add(ordemSelecionada);
			HashMap parametros = new HashMap();
			RelatorioUtil.imprimeRelatorio(osName, parametros, osPrint);
			FacesContext.getCurrentInstance().responseComplete();
			osService.registrarBacklog(osService.colaboradorLogado(), "relatorio para impresao da OSN"+ordemSelecionada.getId()+" foi gerado");
			if (ordemSelecionada.getStatus() == Status.PROGRAMADO) {
				osService.alterarOrdem(ordemSelecionada, Status.ANDAMENTO);
				osService.registrarBacklog(osService.colaboradorLogado(), "Trocou o status da OSN"+ordemSelecionada.getId()+" para ANDAMENTO");
			}
		} catch (Exception e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu algum erro no Sistema");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	public void gerarRelatorio() {
		try {
			String osName = "relatoriosOrdemModelo";
			HashMap parametros = new HashMap();
			RelatorioUtil.imprimeRelatorio(osName, parametros,
					ordensCorretivasRelatorio);
			FacesContext.getCurrentInstance().responseComplete();
			osService.registrarBacklog(osService.colaboradorLogado(), "Gerado relatorio de ordens para impressão");
		} catch (Exception e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu algum erro no Sistema");
			e.printStackTrace();
		}
	}

	public void addNovaLinhaConfirmacao() {
		confirmacao.setOrdemCorretiva(ordemSelecionada);

		try {
			osService.dataHoraPermitida(confirmacao);
			confirmacoes.add(confirmacao);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO,
					"Foi adicionado uma nova data hora de confirmação ");
		} catch (IntervaloException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					e.getMessage());
			e.printStackTrace();
		} catch (FinalInvalidoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					e.getMessage());
			e.printStackTrace();
		}

		confirmacao = new ConfirmacaoOrdem();
	}

	public void cancelar() {
		confirmacao = new ConfirmacaoOrdem();
		confirmacoes = new ArrayList<ConfirmacaoOrdem>();
	}

	public void executarConfirmacao() {
		System.out.println(confirmacao.getDataInicio());

		if (confirmacao.getDataInicio() != null
				&& confirmacao.getDataFim() != null
				&& confirmacao.getHoraInicio() != null
				&& confirmacao.getHoraFim() != null) {

			try {
				osService.dataHoraPermitida(confirmacao);
				confirmacoes.add(confirmacao);
			} catch (IntervaloException e) {
				MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
						e.getMessage());
				e.printStackTrace();
			} catch (FinalInvalidoException e) {
				MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
						e.getMessage());
				e.printStackTrace();
			}

		}

		if (!confirmacoes.isEmpty()) {
			ordemSelecionada.setConfirmacoes(confirmacoes);
			osService.alterarOrdem(ordemSelecionada, Status.CONFIRMADO);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO,
					"Ordem de serviço " + ordemSelecionada.getId()
							+ " foi confirmada!");
			osService.registrarBacklog(osService.colaboradorLogado(), "Trocou o status da OSN"+ordemSelecionada.getId()+" para CONFIRMADO");
			confirmacao = new ConfirmacaoOrdem();
			confirmacoes.clear();
		} else {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					"Voce precisa informar pelo menos uma data de confirmação");
			osService.registrarBacklog(osService.colaboradorLogado(), "Tentou inserir dados inconsistente na confirmação");
		}

	}

	public void exibirRelatorios() {

		for (OrdemServicoCorretiva os : ordensCorretivasRelatorio) {
			System.out.println("Equipamento: " + os.getEquipamento());
			System.out.println("Componente: " + os.getComponente());
			System.out.println("===============================");
		}

		gerarRelatorio();
	}

	public void carregarOrdens() {
		carregarDados();
		ordensDeServico = osService.todasOrdens();
	}

	public void chamaPesquisa() {
		txtPesquisa = txtPesquisa.toLowerCase();
		txtPesquisa = txtPesquisa.replace("osn", "");
		txtPesquisa = txtPesquisa.replace("osp", "");
		long id = 0;

		try {
			id = osService.stringToValue(txtPesquisa);
			filtros.setId(id);
			pesquisar();
		} catch (OrdemServicoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR,
					e.getMessage());
		}
		txtPesquisa = "";
	}

	public void pesquisar() {
		ordensDeServico = osService.pesquisarPorFiltro(filtros);
		filtros = new FiltrosOrdens();
	}

	public void atualizarPesquisar() {
		pesquisaAvancada = false;
	}

	public void popularExecutores() {
		executores = osService.colaboradoresPorSetor(ordemSelecionada
				.getSetor());
	}

	public TipoOrdem[] tipoDeOrdem() {
		return TipoOrdem.values();
	}

	public ModoCorretivo[] modosCorretivo() {
		return ModoCorretivo.values();
	}

	public TipoTrabalho[] tiposTrabalho() {
		return TipoTrabalho.values();
	}

	public Status[] statusOrdem() {
		return Status.values();
	}

	public TipoDePesquisa[] tipoPesquisa() {
		return TipoDePesquisa.values();
	}

	public void programarExecutor(ActionEvent event) {
		programarOs = true;
	}

	public void solicitacaoToOrdem() {
		this.ordemServico.setEquipamento(solicitacao.getEquipamento());
		this.ordemServico.setComponente(solicitacao.getComponente());
		this.ordemServico.setDescricaoAcao(solicitacao.getDescricaoAcao());
		this.ordemServico.setTipoTrabalho(solicitacao.getTipoTrabalho());
		this.ordemServico.setSetor(solicitacao.getSetor());
		this.ordemServico.setId_solicitacao(solicitacao.getId());
		this.ordemServico.setEmFuncionamento(solicitacao.isEmFuncionamento());
	}

	public void carregarDados() {
		this.ordensDeServico = osService.pesquisarPorFiltro(filtros);
		this.filtroColaborador = osService.todosColaboradores();
	}

	public void carregarInformacoes() {
		totalDeOrdem = osService.totalDeOrdem();

		ordemAndamento = osService.qntOrdemPorStatus(Status.ANDAMENTO);
		ordemAberta = osService.qntOrdemPorStatus(Status.ABERTO);
		ordemPendente = osService.qntOrdemPorStatus(Status.PENDENTE);
		ordemConfirmada = osService.qntOrdemPorStatus(Status.CONFIRMADO);
		ordemEncerrada = osService.qntOrdemPorStatus(Status.ENCERRADO);
	}

	public void encerrarOrdens() {
		List<OrdemServicoCorretiva> confirmadas = new ArrayList<OrdemServicoCorretiva>();

		if (!ordensCorretivasRelatorio.isEmpty()) {
			for (OrdemServicoCorretiva ordem : ordensCorretivasRelatorio) {
				if (ordem.getStatus() == Status.CONFIRMADO) {
					confirmadas.add(ordem);
				}
			}

			if (confirmadas.isEmpty()) {
				MensagensUtil
						.addMensagem(FacesMessage.SEVERITY_WARN,
								"Você deve selecionar somente ordens no Status CONFIRMADO");
			} else {
				for (OrdemServicoCorretiva ordem : confirmadas) {
					osService.alterarOrdem(ordem, Status.ENCERRADO);
					osService.registrarBacklog(osService.colaboradorLogado(), "Trocou o status da OSN"+ordem.getId()+" para ENCERRADA");
				}

				MensagensUtil
						.addMensagem(
								FacesMessage.SEVERITY_INFO,
								ordensCorretivasRelatorio.size() > 1 ? "Foram encerradas "
										+ ordensCorretivasRelatorio.size()
										+ " ordens"
										: "Uma ordem de serviço foi encerrada");
			}
			init();
		} else {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN,
					"Você deve selecionar uma ordem para executar esta função");
		}
	}
	
	public void encerrarOrdem(){
		if(ordemSelecionada != null){
			ordemSelecionada.setDataHoraEncerramento(Calendar.getInstance().getTime());
			osService.alterarOrdem(ordemSelecionada, Status.ENCERRADO);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Ordem de Serviço foi encerrada com exito");
			osService.registrarBacklog(osService.colaboradorLogado(), "Trocou o status da OSN"+ordemSelecionada.getId()+" para ENCERRADO");
			init();
		}
		else{
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN,"Você deve selecionar uma ordem para executar esta função");
		}
	}

	// ----------GETTERS and SETTERS------------

	public List<OrdemServicoCorretiva> getOrdensDeServico() {
		return ordensDeServico;
	}

	public void setOrdensDeServico(List<OrdemServicoCorretiva> ordensDeServico) {
		this.ordensDeServico = ordensDeServico;
	}

	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public OrdemServicoCorretiva getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServicoCorretiva ordemServico) {
		this.ordemServico = ordemServico;
	}

	public OrdemServicoCorretiva getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(OrdemServicoCorretiva ordemSelecionada) {
		this.ordemSelecionada = ordemSelecionada;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public List<Colaborador> getFiltroColaborador() {
		return filtroColaborador;
	}

	public void setFiltroColaborador(List<Colaborador> filtroColaborador) {
		this.filtroColaborador = filtroColaborador;
	}

	public String getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(String txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	public boolean isImpresso() {
		return impresso;
	}

	public void setImpresso(boolean impresso) {
		this.impresso = impresso;
	}

	public boolean isPesquisaAvancada() {
		return pesquisaAvancada;
	}

	public void setPesquisaAvancada(boolean pesquisaAvancada) {
		this.pesquisaAvancada = pesquisaAvancada;
	}

	public FiltrosOrdens getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltrosOrdens filtros) {
		this.filtros = filtros;
	}

	public GestaoPlaman getOsService() {
		return osService;
	}

	public boolean isProgramarOs() {
		return programarOs;
	}

	public void setProgramarOs(boolean programarOs) {
		this.programarOs = programarOs;
	}

	public int getPrazoParaOrdem() {
		return prazoParaOrdem;
	}

	public void setPrazoParaOrdem(int prazoParaOrdem) {
		this.prazoParaOrdem = prazoParaOrdem;
	}

	public List<Colaborador> getExecutores() {
		return executores;
	}

	public void setExecutores(List<Colaborador> executores) {
		this.executores = executores;
	}

	public ConfirmacaoOrdem getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(ConfirmacaoOrdem confirmacao) {
		this.confirmacao = confirmacao;
	}

	public List<ConfirmacaoOrdem> getConfirmacoes() {
		return confirmacoes;
	}

	public List<OrdemServicoCorretiva> getOrdensCorretivasRelatorio() {
		return ordensCorretivasRelatorio;
	}

	public void setOrdensCorretivasRelatorio(
			List<OrdemServicoCorretiva> ordensCorretivasRelatorio) {
		this.ordensCorretivasRelatorio = ordensCorretivasRelatorio;
	}

	public int getTotalDeOrdem() {
		return totalDeOrdem;
	}

	public void setTotalDeOrdem(int totalDeOrdem) {
		this.totalDeOrdem = totalDeOrdem;
	}

	public int getOrdemAberta() {
		return ordemAberta;
	}

	public void setOrdemAberta(int ordemAberta) {
		this.ordemAberta = ordemAberta;
	}

	public int getOrdemAndamento() {
		return ordemAndamento;
	}

	public void setOrdemAndamento(int ordemAndamento) {
		this.ordemAndamento = ordemAndamento;
	}

	public int getOrdemPendente() {
		return ordemPendente;
	}

	public void setOrdemPendente(int ordemPendente) {
		this.ordemPendente = ordemPendente;
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public DataGridUtil getDataGridUtil() {
		return dataGridUtil;
	}

	public int getOrdemConfirmada() {
		return ordemConfirmada;
	}

	public void setOrdemConfirmada(int ordemConfirmada) {
		this.ordemConfirmada = ordemConfirmada;
	}

	public int getOrdemEncerrada() {
		return ordemEncerrada;
	}

	public void setOrdemEncerrada(int ordemEncerrada) {
		this.ordemEncerrada = ordemEncerrada;
	}

}

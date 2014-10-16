package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoDePesquisa;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.service.GestaoPlaman;
import com.locar.pipe.service.OrdemServicoException;
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
	private OrdemServicoCorretiva ordemServico;
	private SolicitacaoServico solicitacao;
	private OrdemServicoCorretiva ordemSelecionada;
	private String txtPesquisa;
	private int prazoParaOrdem;
	private boolean impresso;
	private boolean pesquisaAvancada;
	private boolean programarOs;

	@PostConstruct
	public void init() {
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
		if (!executores.isEmpty()) {
			ordemSelecionada.setColaboradores(executores);
			osService.programarOs(ordemSelecionada);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Ordem Programada");
			ordemSelecionada = new OrdemServicoCorretiva();
			programarOs = false;
		}else{
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR, "Você precisa informar pelo menos 1 colaborador");
		}
	}

	public void imprimir() {
		impresso = true;
		this.salvar();
	}

	public void gerarOrdem(){
		String osName = "relatorioModeloPlaman";
		List<OrdemServicoCorretiva> osPrint = new ArrayList<OrdemServicoCorretiva>();
		osPrint.add(ordemSelecionada);
		HashMap parametros = new HashMap();
		RelatorioUtil.imprimeRelatorio(osName, parametros, osPrint);
	}
	
	public String editar() {

		return "editarOsn?faces-redirect=true";
	}

	public void chamaPesquisa() {
		System.out.println("CHAMOU O CHAMAPESQUISA " + filtros.getTipoOrdem());
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
		System.out.println("VALOR: " + filtros.getTipoOrdem());
		ordensDeServico = osService.pesquisarPorFiltro(filtros);
		filtros = new FiltrosOrdens();
	}

	public void atualizarPesquisar() {
		System.out.println("VALOR: " + filtros.getTipoOrdem());
		pesquisaAvancada = false;
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

}

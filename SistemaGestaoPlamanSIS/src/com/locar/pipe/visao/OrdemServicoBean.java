package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoDePesquisa;
import com.locar.pipe.enuns.TipoTrabalho;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.infra.DepartamentoRepository;
import com.locar.pipe.repository.infra.OrdemServicoRepository;
import com.locar.pipe.service.GestaoPlaman;
import com.locar.pipe.service.OrdemServicoException;
import com.locar.pipe.util.MensagensUtil;

@ManagedBean
@ApplicationScoped
public class OrdemServicoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private GestaoPlaman osService;
	private FiltrosOrdens filtros;
	private List<Departamento> departamentos;
	private List<Colaborador> filtroColaborador;
	private List<OrdemServico> ordensDeServico;
	private OrdemServico ordemServico;
	private SolicitacaoServico solicitacao;
	private OrdemServico ordemSelecionada;
	private String txtPesquisa;
	private boolean impresso;
	private boolean pesquisaAvancada;

	@PostConstruct
	public void init() {
		this.osService = new GestaoPlaman();
		this.solicitacao = new SolicitacaoServico();
		this.ordensDeServico = new ArrayList<OrdemServico>();
		this.ordemSelecionada = new OrdemServico();
		this.ordemServico = new OrdemServico();
		this.departamentos = new ArrayList<Departamento>();
		this.filtroColaborador = new ArrayList<Colaborador>();
	}

	// ------------Metodos da View---------------

	public void salvar() {
		try {
			osService.salvarOrdemServico(ordemServico, impresso);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Ordem "
					+ ordemServico.getTipoOrdem() + " criada com sucesso");
			ordemServico = new OrdemServico();
		} catch (OrdemServicoException e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_WARN,
					e.getMessage());
		}
	}

	public void imprimir(){
		impresso = true;
		this.salvar();
	}
	
	public String editar() {

		return "editarOsn?faces-redirect=true";
	}

	public void chamaPesquisa(ValueChangeEvent event){
		txtPesquisa  = txtPesquisa.replace("OSN", "");
		txtPesquisa  = txtPesquisa.replace("OSP", "");
		long id = 0;
		try {
			id = Long.parseLong(txtPesquisa);
		} catch (Exception e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR, "Digite o numero da Ordem ou APENAS as siglas OSP/OSN");
		}
		filtros.setId(id);
	}
	
	public void pesquisar(){
	  ordensDeServico =  osService.pesquisarPorFiltro(filtros);
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

	public void solicitacaoToOrdem() {

		this.ordemServico.setEquipamento(solicitacao.getEquipamento());
		this.ordemServico.setComponente(solicitacao.getComponente());
		this.ordemServico.setDescricaoAcao(solicitacao.getDescricaoAcao());
		this.ordemServico.setTipoTrabalho(solicitacao.getTipoTrabalho());
		this.ordemServico.setSetor(solicitacao.getSetor());
		this.ordemServico.setId_solicitacao(solicitacao.getId());
	}

	// ----------GETTERS and SETTERS------------

	public List<OrdemServico> getOrdensDeServico() {
		return ordensDeServico;
	}

	public void setOrdensDeServico(List<OrdemServico> ordensDeServico) {
		this.ordensDeServico = ordensDeServico;
	}

	public SolicitacaoServico getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(SolicitacaoServico solicitacao) {
		this.solicitacao = solicitacao;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public OrdemServico getOrdemSelecionada() {
		return ordemSelecionada;
	}

	public void setOrdemSelecionada(OrdemServico ordemSelecionada) {
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


}

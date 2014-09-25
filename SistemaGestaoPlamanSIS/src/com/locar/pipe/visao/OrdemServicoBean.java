package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.StatusOrdem;
import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.enuns.TipoDePesquisa;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.repository.ColecaoOsRepository;
import com.locar.pipe.repository.DepartamentoRepository;

@ManagedBean
@ApplicationScoped
public class OrdemServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ColecaoOsRepository colecaoDeOs;
	private List<Departamento> departamentos;
	private List<Colaborador> filtroColaborador;
	private DepartamentoRepository setores;
	private List<OrdemServico> ordensDeServico;
	private List<OrdemServico> repositorioDeOrdem;
	private OrdemServico ordemServico;
	private OrdemServico ordemSelecionada;
	private TipoDePesquisa pesquisa;
	private String txtPesquisa;

	@PostConstruct
	public void init() {
		this.colecaoDeOs = new ColecaoOsRepository();
		this.ordensDeServico = new ArrayList<OrdemServico>();
		this.repositorioDeOrdem = new ArrayList<OrdemServico>();
		this.ordemSelecionada = new OrdemServico();
		this.ordemServico = new OrdemServico();
		ordensDeServico = colecaoDeOs.listarTodas();
		this.departamentos = new ArrayList<Departamento>();
		this.setores = new DepartamentoRepository();
		this.filtroColaborador = new ArrayList<Colaborador>();
		this.departamentos = setores.listarSetor();
	}

	// ------------Metodos da View---------------

	public void salvar() {

	}

	public String editar() {

		return "editarOsn?faces-redirect=true";
	}

	public void pesquisarPorFiltro() {
		System.out.println("Chegou-valor do texto de pesquisa: "+txtPesquisa);
		switch (pesquisa) {
		case ID:
				int id = Integer.parseInt(txtPesquisa);
				this.ordensDeServico.clear();
				this.ordensDeServico.add(this.repositorioDeOrdem.get(id));

			break;

		case DATA:
			this.ordensDeServico.clear();
			for(OrdemServico os : this.repositorioDeOrdem){
				if(os.getId() == Integer.parseInt(txtPesquisa)){
					this.ordensDeServico.add(os);
				}
			}
			break;

		case STATUS:
			for(OrdemServico os : this.repositorioDeOrdem){
				if(os.getStatus() == StatusOrdem.valueOf(txtPesquisa)){
					this.ordensDeServico.add(os);
				}
			}
			break;
			
		case EQUIPAMENTO:
			for(OrdemServico os : this.repositorioDeOrdem){
				if(os.getId() == Integer.parseInt(txtPesquisa)){
					this.ordensDeServico.add(os);
				}
			}
			break;
			
		default:
			
			break;
		}
	}

	public TipoDeOrdem[] tipoDeOrdem() {
		return TipoDeOrdem.values();
	}

	public ModoCorretivo[] modosCorretivo() {
		return ModoCorretivo.values();
	}

	public StatusOrdem[] statusOrdem() {
		return StatusOrdem.values();
	}

	public TipoDePesquisa[] tipoPesquisa() {
		return TipoDePesquisa.values();
	}

	public void filtrarColaboradores(ValueChangeEvent event) {
		// Mudar Metodo quando for fazer o banco de dados
		filtroColaborador.clear();
		Departamento setor = (Departamento) event.getNewValue();
		for (Colaborador col : ColaboradorBean.colaboradores) {

			if (setor != null) {
				if (col.getSetor().getNome().equalsIgnoreCase(setor.getNome())) {
					filtroColaborador.add(col);
					System.out.println("Entro no IF");

				}
			}
		}

		if (setor == null) {
			filtroColaborador.clear();
		}
	}

	// ----------GETTERS and SETTERS------------

	public List<OrdemServico> getOrdensDeServico() {
		return ordensDeServico;
	}

	public void setOrdensDeServico(List<OrdemServico> ordensDeServico) {
		this.ordensDeServico = ordensDeServico;
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

	public TipoDePesquisa getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(TipoDePesquisa pesquisa) {
		this.pesquisa = pesquisa;
	}

	public String getTxtPesquisa() {
		return txtPesquisa;
	}

	public void setTxtPesquisa(String txtPesquisa) {
		this.txtPesquisa = txtPesquisa;
	}

	public List<OrdemServico> getRepositorioDeOrdem() {
		return repositorioDeOrdem;
	}

	public void setRepositorioDeOrdem(List<OrdemServico> repositorioDeOrdem) {
		this.repositorioDeOrdem = repositorioDeOrdem;
	}

}

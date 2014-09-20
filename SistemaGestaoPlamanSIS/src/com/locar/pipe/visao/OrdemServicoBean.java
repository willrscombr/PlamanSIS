package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.StatusOrdem;
import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.repository.ColecaoOsRepository;


@ManagedBean
@SessionScoped
public class OrdemServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private ColecaoOsRepository colecaoDeOs;
	private List<OrdemServico> ordensDeServico;
	private OrdemServico ordemServico;
	private OrdemServico ordemSelecionada;
	
	// ------------Construtores---------------
	
	public OrdemServicoBean() {
		this.colecaoDeOs = new ColecaoOsRepository();
		this.ordensDeServico = new ArrayList<OrdemServico>();
		this.ordemSelecionada = new OrdemServico();
		this.ordemServico = new OrdemServico();
	}
	
	// ------------Metodos da View---------------

	public String salvar() {

		return "ordemServicoIndex?faces-redirect=true";
	}

	public String editar() {
		
		return "editarOsn?faces-redirect=true";
	}
	
	public TipoDeOrdem[] tipoDeOrdem(){
		return TipoDeOrdem.values();
	}
	
	public ModoCorretivo[] modosCorretivo(){
		return ModoCorretivo.values();
	}
	
	public StatusOrdem[] statusOrdem(){
		return StatusOrdem.values();
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

}

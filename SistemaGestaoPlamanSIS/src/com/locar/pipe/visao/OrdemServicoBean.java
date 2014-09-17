package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.locar.pipe.enuns.StatusOrdem;
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.modelos.OrdemServicoPreventiva;

@ManagedBean
@SessionScoped
public class OrdemServicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private OrdemServicoCorretiva ordemCorretiva;
	private OrdemServicoPreventiva ordemPrevetiva;
	private List<OrdemServicoCorretiva> osn;
	private List<OrdemServicoPreventiva> osp;
	private List<OrdemServicoCorretiva> osnFiltrada;
	private List<OrdemServicoPreventiva> ospFiltrada;

	private boolean tipoOs;
	private String status;
	private OrdemServicoCorretiva ordemCorretivaSelecionada;
	private OrdemServicoPreventiva ordemPreventivaSelecionada;
	private boolean tipoOrdemSelecionada;
	private String textquery;
	private boolean mostrarOsDoRetrabalho;
	private Long idOsRetrabalho;
	private OrdemServicoCorretiva selectOsRetrabalho;
	private boolean checkRetrabalho;
	private String pesquisaCorretiva;
	private String pesquisaPreventiva;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrdemServicoBean() {
		this.ordemCorretiva = new OrdemServicoCorretiva();
		this.ordemPrevetiva = new OrdemServicoPreventiva();
		this.osn = new ArrayList<OrdemServicoCorretiva>();
		this.osp = new ArrayList<OrdemServicoPreventiva>();
		this.ospFiltrada = new ArrayList<OrdemServicoPreventiva>();
		this.osnFiltrada = new ArrayList<OrdemServicoCorretiva>();
		this.selectOsRetrabalho = null;
	}

	// --------------------------------Metodos da
	// View------------------------------------------------

	public String carregarFiltros(){
		this.osnFiltrada.clear();
		this.ospFiltrada.clear();
		pesquisaCorretiva = "ABERTA";
		pesquisaPreventiva = "ABERTA";
		for(OrdemServicoCorretiva os : this.osn){
			if(os.getStatusOrdem().equals(StatusOrdem.ABERTA.toString())){
				this.osnFiltrada.add(os);
			}
		}
		
		for(OrdemServicoPreventiva os : this.osp){
			if(os.getStatusOrdem().equals(StatusOrdem.ABERTA.toString())){
				this.ospFiltrada.add(os);
			}
		}
		return "ordemServico/ordemServicoIndex?faces-redirect=true";
	}
	
	public void selecionarTipoOs(ValueChangeEvent event) {
		System.out.println("Instanciou Nova ordem de serviço");
		this.ordemCorretiva = new OrdemServicoCorretiva();
	}

	public String inicializarListas() {
		return "/ordemServicoIndex?faces-redirect=true";
	}

	public String salvarOrdem() {

		if (tipoOs) {
			this.ordemCorretiva.setTipoOrdem("CORRETIVA");
			this.ordemCorretiva.setId(Long.valueOf(this.osn.size() + 1));
			this.ordemCorretiva.setRetrabalho(checkRetrabalho);
			if (ordemCorretiva.isRetrabalho()) {
				ordemCorretiva.setIdOrdemRetrabalho(idOsRetrabalho);
			}

			this.osn.add(ordemCorretiva);

		} else {
			this.ordemPrevetiva.setTipoOrdem("PREVENTIVA");
			this.ordemPrevetiva.setId(Long.valueOf(this.osp.size() + 1));
			this.ordemPrevetiva
					.setCicloEmDias(ordemPrevetiva.getCicloEmHoras() / 24);
			this.osp.add(ordemPrevetiva);

		}

		this.selectOsRetrabalho = null;
		this.mostrarOsDoRetrabalho = false;
		this.ordemCorretiva = new OrdemServicoCorretiva();
		this.ordemPrevetiva = new OrdemServicoPreventiva();

		return "ordemServicoIndex?faces-redirect=true";
	}

	public String editar() {

		if (ordemCorretivaSelecionada.isRetrabalho()) {
			mostrarOsDoRetrabalho = true;
			checkRetrabalho = ordemCorretivaSelecionada.isRetrabalho();

			for (OrdemServicoCorretiva os : this.osn) {
				if (os.getId() == ordemCorretivaSelecionada
						.getIdOrdemRetrabalho()) {
					selectOsRetrabalho = os;
					textquery = "osn" + selectOsRetrabalho.getId();
				}
			}

		} else {
			mostrarOsDoRetrabalho = false;
			checkRetrabalho = ordemCorretivaSelecionada.isRetrabalho();
			selectOsRetrabalho = null;
			textquery = null;
		}

		return "editarOsn?faces-redirect=true";
	}

	public String editarOrdemCorretiva() {

		if (checkRetrabalho) {
			this.ordemCorretivaSelecionada.setRetrabalho(checkRetrabalho);
			this.ordemCorretivaSelecionada
					.setIdOrdemRetrabalho(selectOsRetrabalho.getId());
		}

		for (OrdemServicoCorretiva osn : this.osn) {
			if (osn.getId() == this.ordemCorretivaSelecionada.getId()) {
				osn = this.ordemCorretivaSelecionada;
			}
		}
		return "ordemServicoIndex?faces-redirect=true";
	}

	public String editarOrdemPreventiva() {

		for (OrdemServicoPreventiva os : this.osp) {
			if (os.getId() == this.ordemPreventivaSelecionada.getId()) {
				os = this.ordemPreventivaSelecionada;
			}
		}
		return "ordemServicoIndex?faces-redirect=true";
	}
	
	public void escolherTipoDeExibicao(ActionEvent e) {

	}

	public void gerarOs() {

		if (tipoOs) {
			for (int x = 0; x <= 10; x++) {
				preencherTestes();
				salvarOrdem();
			}

		} else {
			for (int x = 0; x <= 10; x++) {
				preencherTestes();
				salvarOrdem();
			}

		}

	}

	public void preencherTestes() {

		this.ordemCorretiva
				.setAcao("Dados preenchidos automaticamente pelo Gerador de OS corretiva");
		this.ordemCorretiva.setDataExpedicao(Calendar.getInstance().getTime());
		this.ordemCorretiva.setStatusOrdem(StatusOrdem.ABERTA.toString());
		this.ordemCorretiva.setEquipamento("Rolo compressor");
		this.ordemCorretiva.setHoraExpedicao(Calendar.getInstance().getTime());
		this.ordemCorretiva.setLocalManutencao("Monte Everest");
		this.ordemCorretiva.setMatriculaExecutor("546");
		this.ordemCorretiva.setRetrabalho(false);
		this.ordemCorretiva.setTipoCorretiva("EMERGENCIAL");
		this.ordemCorretiva.setSetorResponsavel("Manutenção");

		this.ordemPrevetiva
				.setAcao("Dados preenchidos automaticamente pelo Gerador de OS PREVENTIVA");
		this.ordemPrevetiva.setDataExpedicao(Calendar.getInstance().getTime());
		this.ordemPrevetiva.setStatusOrdem(StatusOrdem.ABERTA.toString());
		this.ordemPrevetiva.setEquipamento("Rolo compressor");
		this.ordemPrevetiva.setHoraExpedicao(Calendar.getInstance().getTime());
		this.ordemPrevetiva.setLocalManutencao("Monte Everest");
		this.ordemPrevetiva.setMatriculaExecutor("546");
		this.ordemPrevetiva.setRetrabalho(false);
		this.ordemPrevetiva.setCicloEmHoras(1090);
		this.ordemPrevetiva.setSetorResponsavel("Manutenção");
	}

	public String trocaRetrabalho(ValueChangeEvent event) {
		System.out.println("Trocou");
		return "cadastroOS?faces-redirect=true";
	}

	public void checarRetrabalhoSelecionado(ValueChangeEvent ev) {
		System.out.println("METODO  CHECAR RETRABALHO");
		if (!checkRetrabalho) {
			System.out.println("METODO  CHECAR RETRABALHO DENTrO DO IF");
			mostrarOsDoRetrabalho = false;
			textquery = null;
			selectOsRetrabalho = null;
		}
	}

	public void pegaOsRetrabalho(ValueChangeEvent event) {
		System.out.println("METODO PEGAR OS RETRABALHO");
		selectOsRetrabalho = null;
		try {
			textquery = event.getNewValue().toString().toLowerCase()
					.replaceAll("osn", "");
			idOsRetrabalho = Long.valueOf(textquery);

			for (OrdemServicoCorretiva os : osn) {
				if (idOsRetrabalho == os.getId()) {
					selectOsRetrabalho = os;
				}
			}

			if (checkRetrabalho && selectOsRetrabalho != null) {
				mostrarOsDoRetrabalho = true;
			} else {
				FacesMessage msg = new FacesMessage(
						"Nã foi possivel localizar a Ordem com o ID: OSN"
								+ idOsRetrabalho);
				FacesContext.getCurrentInstance().addMessage("Nao Localizada",
						msg);
				mostrarOsDoRetrabalho = false;
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(
					"ID em formato incorreto use a tag seguida do numero ex: OSN1");
			FacesContext.getCurrentInstance().addMessage("Nao Localizada", msg);
			mostrarOsDoRetrabalho = false;
		}
	}

	public void filtrarInicioCorretivo(ValueChangeEvent event) {
		osnFiltrada.clear();
		pesquisaCorretiva = event.getNewValue().toString();
		for(OrdemServicoCorretiva os : this.osn){
			if(pesquisaCorretiva.equals(os.getStatusOrdem())){
				osnFiltrada.add(os);
				System.out.println("Entrou VALOR: "+pesquisaCorretiva);
			}
		}
	}

	public void filtrarInicioPreventivo(ValueChangeEvent event) {
		ospFiltrada.clear();
		pesquisaPreventiva = event.getNewValue().toString();
		for(OrdemServicoPreventiva os : this.osp){
			if(pesquisaPreventiva.equals(os.getStatusOrdem())){
				ospFiltrada.add(os);
				System.out.println("Entrou VALOR: "+pesquisaPreventiva);
			}
		}
	}

	// ------------------------------GETTERS and
	// SETTERS------------------------------------------------

	public OrdemServicoCorretiva getOrdemServico() {
		return ordemCorretiva;
	}

	public void setOrdemServico(OrdemServicoCorretiva ordemServico) {
		this.ordemCorretiva = ordemServico;
	}

	public List<OrdemServicoCorretiva> getOrdens() {
		return osn;
	}

	public void setOrdens(List<OrdemServicoCorretiva> ordens) {
		this.osn = ordens;
	}

	public boolean isTipoOs() {
		return tipoOs;
	}

	public void setTipoOs(boolean tipoOs) {
		this.tipoOs = tipoOs;
	}

	public List<OrdemServicoCorretiva> getOsn() {
		return osn;
	}

	public void setOsn(List<OrdemServicoCorretiva> osn) {
		this.osn = osn;
	}

	public List<OrdemServicoPreventiva> getOsp() {
		return osp;
	}

	public void setOsp(List<OrdemServicoPreventiva> osp) {
		this.osp = osp;
	}

	public OrdemServicoCorretiva getOrdemCorretiva() {
		return ordemCorretiva;
	}

	public void setOrdemCorretiva(OrdemServicoCorretiva ordemCorretiva) {
		this.ordemCorretiva = ordemCorretiva;
	}

	public OrdemServicoPreventiva getOrdemPrevetiva() {
		return ordemPrevetiva;
	}

	public void setOrdemPrevetiva(OrdemServicoPreventiva ordemPrevetiva) {
		this.ordemPrevetiva = ordemPrevetiva;
	}

	public OrdemServicoCorretiva getOrdemCorretivaSelecionada() {
		return ordemCorretivaSelecionada;
	}

	public void setOrdemCorretivaSelecionada(OrdemServicoCorretiva ordemCorretivaSelecionada) {
		this.ordemCorretivaSelecionada = ordemCorretivaSelecionada;
	}

	public OrdemServicoPreventiva getOrdemPreventivaSelecionada() {
		return ordemPreventivaSelecionada;
	}

	public void setOrdemPreventivaSelecionada(OrdemServicoPreventiva ordemPreventivaSelecionada) {
		this.ordemPreventivaSelecionada = ordemPreventivaSelecionada;
	}

	public boolean isTipoOrdemSelecionada() {
		return tipoOrdemSelecionada;
	}

	public void setTipoOrdemSelecionada(boolean tipoOrdemSelecionada) {
		this.tipoOrdemSelecionada = tipoOrdemSelecionada;
	}

	public String getTextquery() {
		return textquery;
	}

	public void setTextquery(String textquery) {
		this.textquery = textquery;
	}

	public boolean isMostrarOsDoRetrabalho() {
		return mostrarOsDoRetrabalho;
	}

	public void setMostrarOsDoRetrabalho(boolean mostrarOsDoRetrabalho) {
		this.mostrarOsDoRetrabalho = mostrarOsDoRetrabalho;
	}

	public OrdemServicoCorretiva getSelectOsRetrabalho() {
		return selectOsRetrabalho;
	}

	public void setSelectOsRetrabalho(OrdemServicoCorretiva selectOsRetrabalho) {
		this.selectOsRetrabalho = selectOsRetrabalho;
	}

	public boolean isCheckRetrabalho() {
		return checkRetrabalho;
	}

	public void setCheckRetrabalho(boolean checkRetrabalho) {
		this.checkRetrabalho = checkRetrabalho;
	}
	
	public List<OrdemServicoCorretiva> getOsnFiltrada() {
		return osnFiltrada;
	}

	public void setOsnFiltrada(List<OrdemServicoCorretiva> osnFiltrada) {
		this.osnFiltrada = osnFiltrada;
	}

	public List<OrdemServicoPreventiva> getOspFiltrada() {
		return ospFiltrada;
	}

	public void setOspFiltrada(List<OrdemServicoPreventiva> ospFiltrada) {
		this.ospFiltrada = ospFiltrada;
	}

	public String getPesquisaCoretiva() {
		return pesquisaCorretiva;
	}

	public void setPesquisaCoretiva(String pesquisaCoretiva) {
		this.pesquisaCorretiva = pesquisaCoretiva;
	}

	public String getPesquisaPreventiva() {
		return pesquisaPreventiva;
	}

	public void setPesquisaPreventiva(String pesquisaPreventiva) {
		this.pesquisaPreventiva = pesquisaPreventiva;
	}

}

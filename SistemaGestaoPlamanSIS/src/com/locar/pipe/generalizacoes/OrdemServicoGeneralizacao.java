package com.locar.pipe.generalizacoes;

import java.io.Serializable;
import java.util.Date;

public class OrdemServicoGeneralizacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String localManutencao;
	private String equipamento;
	private int cicloPreventiva;
	private String tipoCorretiva;
	private String acao;
	private String tipoOrdem;
	private String setorResponsavel;
	private String matriculaExecutor;
	private Date dataExpedicao;
	private Date horaExpedicao;
	private Date dataFinalizacao;
	private Date horaFinalizacao;
	private String observacao;
	private String statusOrdem;
	private boolean encerramentoTecnico;
	private boolean retrabalho;
	private Long idOrdemRetrabalho;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocalManutencao() {
		return localManutencao;
	}
	public void setLocalManutencao(String localManutencao) {
		this.localManutencao = localManutencao;
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	public int getCicloPreventiva() {
		return cicloPreventiva;
	}
	public void setCicloPreventiva(int cicloPreventiva) {
		this.cicloPreventiva = cicloPreventiva;
	}
	public String getTipoCorretiva() {
		return tipoCorretiva;
	}
	public void setTipoCorretiva(String tipoCorretiva) {
		this.tipoCorretiva = tipoCorretiva;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public String getTipoOrdem() {
		return tipoOrdem;
	}
	public void setTipoOrdem(String tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}
	public String getSetorResponsavel() {
		return setorResponsavel;
	}
	public void setSetorResponsavel(String setorResponsavel) {
		this.setorResponsavel = setorResponsavel;
	}
	public String getMatriculaExecutor() {
		return matriculaExecutor;
	}
	public void setMatriculaExecutor(String matriculaExecutor) {
		this.matriculaExecutor = matriculaExecutor;
	}
	public Date getDataExpedicao() {
		return dataExpedicao;
	}
	public void setDataExpedicao(Date dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	public Date getHoraExpedicao() {
		return horaExpedicao;
	}
	public void setHoraExpedicao(Date horaExpedicao) {
		this.horaExpedicao = horaExpedicao;
	}
	public Date getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(Date dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	public Date getHoraFinalizacao() {
		return horaFinalizacao;
	}
	public void setHoraFinalizacao(Date horaFinalizacao) {
		this.horaFinalizacao = horaFinalizacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getStatusOrdem() {
		return statusOrdem;
	}
	public void setStatusOrdem(String statusOrdem) {
		this.statusOrdem = statusOrdem;
	}
	public boolean isEncerramentoTecnico() {
		return encerramentoTecnico;
	}
	public void setEncerramentoTecnico(boolean encerramentoTecnico) {
		this.encerramentoTecnico = encerramentoTecnico;
	}
	public boolean isRetrabalho() {
		return retrabalho;
	}
	public void setRetrabalho(boolean retrabalho) {
		this.retrabalho = retrabalho;
	}
	public Long getIdOrdemRetrabalho() {
		return idOrdemRetrabalho;
	}
	public void setIdOrdemRetrabalho(Long idOrdemRetrabalho) {
		this.idOrdemRetrabalho = idOrdemRetrabalho;
	}
}

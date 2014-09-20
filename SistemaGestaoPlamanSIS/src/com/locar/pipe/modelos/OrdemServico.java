package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.Date;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.StatusOrdem;
import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.enuns.TipoEncerramento;

public class OrdemServico implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private TipoDeOrdem tipo;
	private ModoCorretivo modoCorretivo;
	private int cicloPreventiva;
	private String acao;
	private CentroTrabalho setor;
	private Colaborador executor;
	private String observacao;
	private StatusOrdem status;
	private TipoEncerramento tipoEncerramento;
	private boolean retrabalho;
	private long idOrdemRetrabalho;
	private Date dataCriacao;
	private Date dataExpedicao;
	private Date horaExpedicao;
	private Date dataFinalizacao;
	private Date horaFinalizacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getCicloPreventiva() {
		return cicloPreventiva;
	}
	public void setCicloPreventiva(int cicloPreventiva) {
		this.cicloPreventiva = cicloPreventiva;
	}
	public String getAcao() {
		return acao;
	}
	public void setAcao(String acao) {
		this.acao = acao;
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
	public boolean isRetrabalho() {
		return retrabalho;
	}
	public void setRetrabalho(boolean retrabalho) {
		this.retrabalho = retrabalho;
	}
	public long getIdOrdemRetrabalho() {
		return idOrdemRetrabalho;
	}
	public void setIdOrdemRetrabalho(long idOrdemRetrabalho) {
		this.idOrdemRetrabalho = idOrdemRetrabalho;
	}
	public TipoDeOrdem getTipo() {
		return tipo;
	}
	public void setTipo(TipoDeOrdem tipo) {
		this.tipo = tipo;
	}
	public ModoCorretivo getModoCorretivo() {
		return modoCorretivo;
	}
	public void setModoCorretivo(ModoCorretivo modoCorretivo) {
		this.modoCorretivo = modoCorretivo;
	}
	public CentroTrabalho getSetor() {
		return setor;
	}
	public void setSetor(CentroTrabalho setor) {
		this.setor = setor;
	}
	public Colaborador getExecutor() {
		return executor;
	}
	public void setExecutor(Colaborador executor) {
		this.executor = executor;
	}
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public StatusOrdem getStatus() {
		return status;
	}
	public void setStatus(StatusOrdem status) {
		this.status = status;
	}
	public TipoEncerramento getTipoEncerramento() {
		return tipoEncerramento;
	}
	public void setTipoEncerramento(TipoEncerramento tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}
}

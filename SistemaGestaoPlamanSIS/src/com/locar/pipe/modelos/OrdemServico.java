package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.StatusOrdem;
import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.enuns.TipoEncerramento;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico implements Serializable {

	private static final long serialVersionUID = 1L;

	// Tipos primitivos
	@Id
	@GeneratedValue
	@Column(name = "id_ordem_servico")
	private long id;

	@Column(name = "ciclo_preventiva")
	private int cicloPreventiva;
	private String local;
	private String equipamento;
	private String acao;
	private String observacao;
	private boolean retrabalho;

	@Column(name = "id_os_retrabalho")
	private long idOrdemRetrabalho;

	// Tipos enumarated
	@Enumerated(EnumType.STRING)
	private TipoDeOrdem tipo;

	@Enumerated(EnumType.STRING)
	@Column(name = "modo_corretivo")
	private ModoCorretivo modoCorretivo;

	@Enumerated(EnumType.STRING)
	private StatusOrdem status;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_encerramento")
	private TipoEncerramento tipoEncerramento;

	// Classes para mapeamento
	@ManyToOne
	private Departamento setor;

	@ManyToMany
	@JoinTable(name = "ordem_colaborador_rel", joinColumns = { @JoinColumn(name = "id_ordem") }, inverseJoinColumns = { @JoinColumn(name = "id_colaborador") })
	private List<Colaborador> executores;

	// Tipos Temporal
	@Temporal(TemporalType.DATE)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_expedicao")
	private Date dataExpedicao;

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_expedicao")
	private Date horaExpedicao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_finalizacao")
	private Date dataFinalizacao;

	@Temporal(TemporalType.TIME)
	@Column(name = "hora_finalizacao")
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

	public Departamento getSetor() {
		return setor;
	}

	public void setSetor(Departamento setor) {
		this.setor = setor;
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

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public List<Colaborador> getExecutores() {
		return executores;
	}

	public void setExecutores(List<Colaborador> executores) {
		this.executores = executores;
	}

}
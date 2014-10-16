	package com.locar.pipe.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.locar.pipe.enuns.ModoCorretivo;
import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.enuns.TipoTrabalho;

@Entity
@Table(name = "tb_ordem_servico_corretiva")
public class OrdemServicoCorretiva implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "id_ordem_servico") @Id @GeneratedValue
	private long id;
	@Enumerated(EnumType.STRING)
	private Status status;
	@Enumerated(EnumType.ORDINAL)
	private TipoOrdem tipoOrdem;
	@ManyToOne
	private Departamento setor;
	private String equipamento;
	private String componente;
	@Column(name="descricao_acao")
	private String descricaoAcao;
	@Column(name="descricao_solucao")
	private String DescricaoSolucao;
	@OneToMany(mappedBy="ordemServico")
	private List<Material> materiaisPendentes;
	@Column(name="pendencia_material")
	private boolean pendenciaMaterial;
	@Column(name="em_funcionamento")
	private boolean emFuncionamento;
	private String observacao;
	@Enumerated(EnumType.STRING) @Column(name = "tipo_trabalho")
	private TipoTrabalho tipoTrabalho;
	@Column(name = "modo_corretivo") @Enumerated(EnumType.STRING)
	private ModoCorretivo modoCorretivo;
	@ManyToMany (fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	@JoinTable(name="tb_ordem_colaborador", joinColumns = @JoinColumn(name="id_ordem_servico"), inverseJoinColumns = @JoinColumn(name="id_colaborador"))
	private List<Colaborador> colaboradores = new ArrayList<Colaborador>();
	private long id_solicitacao;
	@Column(name="tipo_encerramento")
	private boolean tipoEncerramento;
	@Temporal(TemporalType.TIMESTAMP) @Column(name = "data_criacao")
	private Date dataCriacao;
	@Temporal(TemporalType.DATE) @Column(name = "data_inicio")
	private Date dataInicio;
	@Temporal(TemporalType.TIME) @Column(name = "hora_inicio")
	private Date horaInicio;
	@Temporal(TemporalType.DATE) @Column(name = "data_final")
	private Date dataFinal;
	@Temporal(TemporalType.TIME) @Column(name = "hora_final")
	private Date horaFinal;
	private int prazoParaOrdem;
	
	//GETTERS AND SETTERS--------------------------
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public boolean isTipoEncerramento() {
		return tipoEncerramento;
	}

	public void setTipoEncerramento(boolean tipoEncerramento) {
		this.tipoEncerramento = tipoEncerramento;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Date getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public TipoTrabalho getTipoTrabalho() {
		return tipoTrabalho;
	}

	public void setTipoTrabalho(TipoTrabalho tipoTrabalho) {
		this.tipoTrabalho = tipoTrabalho;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getDescricaoAcao() {
		return descricaoAcao;
	}

	public void setDescricaoAcao(String descricaoAcao) {
		this.descricaoAcao = descricaoAcao;
	}
	
	public String getDescricaoSolucao() {
		return DescricaoSolucao;
	}

	public void setDescricaoSolucao(String descricaoSolucao) {
		DescricaoSolucao = descricaoSolucao;
	}

	public List<Material> getMateriaisPendentes() {
		return materiaisPendentes;
	}

	public void setMateriaisPendentes(List<Material> materiaisPendentes) {
		this.materiaisPendentes = materiaisPendentes;
	}

	public boolean isPendenciaMaterial() {
		return pendenciaMaterial;
	}

	public void setPendenciaMaterial(boolean pendenciaMaterial) {
		this.pendenciaMaterial = pendenciaMaterial;
	}
	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public long getId_solicitacao() {
		return id_solicitacao;
	}

	public void setId_solicitacao(long id_solicitacao) {
		this.id_solicitacao = id_solicitacao;
	}
	public boolean isEmFuncionamento() {
		return emFuncionamento;
	}

	public void setEmFuncionamento(boolean emFuncionamento) {
		this.emFuncionamento = emFuncionamento;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}


	//------Equals and HashCode---------------
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
		OrdemServicoCorretiva other = (OrdemServicoCorretiva) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public TipoOrdem getTipoOrdem() {
		return tipoOrdem;
	}

	public void setTipoOrdem(TipoOrdem tipoOrdem) {
		this.tipoOrdem = tipoOrdem;
	}

	public int getPrazoParaOrdem() {
		return prazoParaOrdem;
	}

	public void setPrazoParaOrdem(int prazoParaOrdem) {
		this.prazoParaOrdem = prazoParaOrdem;
	}
}

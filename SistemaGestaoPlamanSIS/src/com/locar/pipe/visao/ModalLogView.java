package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.locar.pipe.filtros.FiltroLog;
import com.locar.pipe.modelos.Usuario;
import com.locar.pipe.service.BackLogApplication;
import com.locar.pipe.service.GestaoPlaman;

@ManagedBean
@ApplicationScoped
public class ModalLogView implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<BackLogApplication> logs;
	private List<Usuario> usuarios;
	private FiltroLog filtro;
	private GestaoPlaman gestao;

	public ModalLogView() {
		recarregar();
	}

	public void recarregar(){
		usuarios = new ArrayList<Usuario>();
		filtro = new FiltroLog();
		gestao = new GestaoPlaman();
		logs = new ArrayList<BackLogApplication>();
		logs = gestao.logsPorUsuario(gestao.colaboradorLogado());
		usuarios = gestao.listarTodosUsers();
	}
	public void gerarLog(){
		logs = gestao.logsPorUsuario(gestao.colaboradorLogado());
	}
	
	public List<BackLogApplication> getLogs() {
		return logs;
	}

	public void setLogs(List<BackLogApplication> logs) {
		this.logs = logs;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public FiltroLog getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroLog filtro) {
		this.filtro = filtro;
	}

}

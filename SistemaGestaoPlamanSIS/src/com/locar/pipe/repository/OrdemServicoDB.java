package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.List;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServicoCorretiva;

public interface OrdemServicoDB extends Serializable {

	void salvar(OrdemServicoCorretiva ordemCorretiva);
	void excluir(OrdemServicoCorretiva ordemCorretiva);
	void editar(OrdemServicoCorretiva ordemCorretiva);
	long qntDeOrdemPorSetorStatus(Departamento setor,Status status);
	List<OrdemServicoCorretiva> listarTodas();
	List<OrdemServicoCorretiva> listarTodasCorretiva();
	List<OrdemServicoCorretiva> listarUltimasCinco();
	List<OrdemServicoCorretiva> listarPorStatus(Departamento setor,Status status);
	List<OrdemServicoCorretiva> pesquisarPorFiltros(FiltrosOrdens filtro);
	OrdemServicoCorretiva buscarPorId(long id);
	boolean jaExiste(OrdemServicoCorretiva ordem);
	
}

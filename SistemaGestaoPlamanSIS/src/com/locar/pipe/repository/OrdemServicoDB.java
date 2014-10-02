package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.List;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;

public interface OrdemServicoDB extends Serializable {

	void salvar(OrdemServico ordemCorretiva);
	void excluir(OrdemServico ordemCorretiva);
	void editar(OrdemServico ordemCorretiva);
	long qntDeOrdemPorSetorStatus(Departamento setor,Status status);
	List<OrdemServico> listarTodas();
	List<OrdemServico> listarTodasCorretiva();
	List<OrdemServico> listarTodasPreventivas();
	List<OrdemServico> listarUltimasCinco(Departamento setor);
	OrdemServico buscarPorId(long id);
	
}

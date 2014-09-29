package com.locar.pipe.interfaces;

import java.io.Serializable;
import java.util.List;

import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;

public interface ColecaoOsInterface extends Serializable {

	void salvar(OrdemServico ordemCorretiva);
	void excluir(OrdemServico ordemCorretiva);
	void editar(OrdemServico ordemCorretiva);
	long qntDeOrdemPorSetor(Departamento setor);
	List<OrdemServico> listarTodas();
	List<OrdemServico> listarTodasCorretiva();
	List<OrdemServico> listarTodasPreventivas();
	OrdemServico buscarPorId(long id);
	
}

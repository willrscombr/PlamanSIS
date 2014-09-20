package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.modelos.OrdemServico;

public interface ColecaoOsInterface {

	void salvar(OrdemServico ordemCorretiva);
	void excluir(OrdemServico ordemCorretiva);
	void editar(OrdemServico ordemCorretiva);
	List<OrdemServico> listarTodasCorretiva();
	List<OrdemServico> listarTodasPreventivas();
	OrdemServico buscarPorId(long id);
	
}

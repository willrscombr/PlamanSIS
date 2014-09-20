package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.modelos.CentroTrabalho;

public interface DepartamentosInterface {
	
	void salvar(CentroTrabalho setor);
	void excluir(CentroTrabalho setor);
	void editar(CentroTrabalho setor);
	List<CentroTrabalho> listarSetor();
	CentroTrabalho buscarSetorPorNome(String nome);
	CentroTrabalho buscarSetorPorSigla(String sigla);
	CentroTrabalho buscarSetorPorId(long id);

}

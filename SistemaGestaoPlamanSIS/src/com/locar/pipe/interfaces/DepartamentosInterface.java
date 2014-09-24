package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.modelos.Departamento;

public interface DepartamentosInterface {
	
	void salvar(Departamento setor);
	void excluir(Departamento setor);
	void editar(Departamento setor);
	List<Departamento> listarSetor();
	Departamento buscarSetorPorNome(String nome);
	Departamento buscarSetorPorSigla(String sigla);
	Departamento buscarSetorPorId(long id);

}

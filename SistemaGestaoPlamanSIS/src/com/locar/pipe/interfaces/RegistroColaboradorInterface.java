package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;

public interface RegistroColaboradorInterface {
	
	void salvar(Colaborador colaborador);
	void excluir(Colaborador colaborador);
	void editar(Colaborador colaborador);
	List<Colaborador> listarTodos();
	List<Colaborador> listarPorSetor(Departamento setor);
	Colaborador buscarPorNome(String nome);
	Colaborador buscarPorMatricula(String matricula);
	
}

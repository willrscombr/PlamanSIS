package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.modelos.Colaborador;

public interface RegistroColaboradorInterface {
	
	void salvar(Colaborador colaborador);
	void excluir(Colaborador colaborador);
	void editar(Colaborador colaborador);
	List<Colaborador> listarTodos();
	List<Colaborador> listarPorSetor(String setor);
	Colaborador buscarPorNome(String nome);
	Colaborador buscarPorMatricula(String matricula);
	
}

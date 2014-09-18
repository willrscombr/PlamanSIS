package com.locar.pipe.repository;

import java.util.ArrayList;
import java.util.List;

import com.locar.pipe.interfaces.RegistroColaboradorInterface;
import com.locar.pipe.modelos.Colaborador;

public class RegistroColaboradorRepository implements
		RegistroColaboradorInterface {

	// Este atributo esta subistituindo o banco de dados
	private List<Colaborador> listaDeColaboradores;

	public RegistroColaboradorRepository() {
		this.listaDeColaboradores = new ArrayList<Colaborador>();
	}

	@Override
	public void salvar(Colaborador colaborador) {
		this.listaDeColaboradores.add(colaborador);

	}

	@Override
	public void excluir(Colaborador colaborador) {
		this.listaDeColaboradores.remove(colaborador);

	}

	@Override
	public void editar(Colaborador colaborador) {
		for (Colaborador colab : this.listaDeColaboradores) {
			if (colab.getId() == colaborador.getId()) {
				colab = colaborador;
			}
		}

	}

	@Override
	public List<Colaborador> listarTodos() {
		return this.listaDeColaboradores;
	}

	@Override
	public List<Colaborador> listarPorSetor(String setor) {
		List<Colaborador> colaboradoresPorSetor = new ArrayList<Colaborador>();
		for (Colaborador colab : this.listaDeColaboradores) {
			if (colab.getSetor().getNome().equalsIgnoreCase(setor)) {
				colaboradoresPorSetor.add(colab);
			}
		}

		if (colaboradoresPorSetor.isEmpty()) {
			return null;
		} else {
			return colaboradoresPorSetor;
		}
	}

	@Override
	public Colaborador buscarPorNome(String nome) {
		Colaborador colabPorNome = null;
		for (Colaborador colab : this.listaDeColaboradores) {
			if (colab.getNome().equalsIgnoreCase(nome)) {
				colabPorNome = colab;
			}
		}
		return colabPorNome;
	}

	@Override
	public Colaborador buscarPorMatricula(String matricula) {
		Colaborador colabPorMatricula = null;
		for (Colaborador colab : this.listaDeColaboradores) {
			if (colab.getNome().equalsIgnoreCase(matricula)) {
				colabPorMatricula = colab;
			}
		}
		return colabPorMatricula;
	}

}

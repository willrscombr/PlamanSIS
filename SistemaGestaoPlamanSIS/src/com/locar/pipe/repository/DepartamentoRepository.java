package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.locar.pipe.interfaces.DepartamentosInterface;
import com.locar.pipe.modelos.Departamento;

public class DepartamentoRepository implements DepartamentosInterface, Serializable {

	private static final long serialVersionUID = 1L;
	private long sentenceId = 1;
	
	// Este atributo será substituido pela implementação do Banco de dados
	private List<Departamento> departamentoCadastrado;

	public DepartamentoRepository() {
		this.departamentoCadastrado = new ArrayList<Departamento>();
	}

	@Override
	public void salvar(Departamento setor) {
		setor.setId(departamentoCadastrado.size()+1);
		this.departamentoCadastrado.add(setor);
	}

	@Override
	public void excluir(Departamento setor) {
		this.departamentoCadastrado.remove(setor);
	}

	@Override
	public List<Departamento> listarSetor() {
		Departamento centro = new Departamento();
		
		centro.setId(sentenceId++);
		centro.setNome("ELÉTRICA");
		centro.setSigla("ELE");
		this.departamentoCadastrado.add(centro);

		centro = new Departamento();

		centro.setId(sentenceId++);
		centro.setNome("MECANICA");
		centro.setSigla("MEC");
		this.departamentoCadastrado.add(centro);

		centro = new Departamento();

		centro.setId(sentenceId++);
		centro.setNome("OPERACIONAL");
		centro.setSigla("OPE");
		this.departamentoCadastrado.add(centro);

		centro = new Departamento();

		centro.setId(sentenceId++);
		centro.setNome("REFRIGERAÇÃO");
		centro.setSigla("REFR");
		this.departamentoCadastrado.add(centro);
		
		centro = new Departamento();

		centro.setId(sentenceId++);
		centro.setNome("MANUTENÇÃO");
		centro.setSigla("MAN");
		this.departamentoCadastrado.add(centro);

		return this.departamentoCadastrado;

	}

	@Override
	public Departamento buscarSetorPorNome(String nome) {
		Departamento sector = null;

		for (Departamento setor : this.departamentoCadastrado) {
			if (setor.getNome().equalsIgnoreCase(nome)) {
				sector = setor;
			}
		}
		return sector;
	}

	@Override
	public Departamento buscarSetorPorSigla(String sigla) {
		Departamento sector = null;

		for (Departamento setor : this.departamentoCadastrado) {
			if (setor.getNome().equalsIgnoreCase(sigla)) {
				sector = setor;
			}
		}
		return sector;
	}

	@Override
	public void editar(Departamento setor) {
		for (Departamento s : this.departamentoCadastrado) {
			if (s.getId() == setor.getId()) {
				s = setor;
			}
		}
	}

	@Override
	public Departamento buscarSetorPorId(long id) {
		Departamento setor = null;
		for(Departamento centro : this.departamentoCadastrado){
			if(centro.getId() == id){
				setor = centro;
			}
		}
		
		return setor;
	}

}

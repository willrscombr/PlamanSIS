package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.locar.pipe.interfaces.DepartamentosInterface;
import com.locar.pipe.modelos.CentroTrabalho;

public class DepartamentoRepository implements DepartamentosInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	// Este atributo será substituido pela implementação do Banco de dados
	private List<CentroTrabalho> departamentoCadastrado;

	public DepartamentoRepository() {
		this.departamentoCadastrado = new ArrayList<CentroTrabalho>();
	}

	@Override
	public void salvar(CentroTrabalho setor) {
		this.departamentoCadastrado.add(setor);
	}

	@Override
	public void excluir(CentroTrabalho setor) {
		this.departamentoCadastrado.remove(setor);
	}

	@Override
	public List<CentroTrabalho> listarSetor() {
		CentroTrabalho centro = new CentroTrabalho();

		centro.setId(1);
		centro.setNome("ELÉTRICA");
		centro.setSigla("ELE");
		this.departamentoCadastrado.add(centro);

		centro = new CentroTrabalho();

		centro.setId(2);
		centro.setNome("MECANICA");
		centro.setSigla("MEC");
		this.departamentoCadastrado.add(centro);

		centro = new CentroTrabalho();

		centro.setId(3);
		centro.setNome("OPERACIONAL");
		centro.setSigla("OPE");
		this.departamentoCadastrado.add(centro);

		centro = new CentroTrabalho();

		centro.setId(4);
		centro.setNome("REFRIGERAÇÃO");
		centro.setSigla("REFR");
		this.departamentoCadastrado.add(centro);
		
		centro = new CentroTrabalho();

		centro.setId(5);
		centro.setNome("MANUTENÇÃO");
		centro.setSigla("MAN");
		this.departamentoCadastrado.add(centro);

		return this.departamentoCadastrado;

	}

	@Override
	public CentroTrabalho buscarSetorPorNome(String nome) {
		CentroTrabalho sector = null;

		for (CentroTrabalho setor : this.departamentoCadastrado) {
			if (setor.getNome().equalsIgnoreCase(nome)) {
				sector = setor;
			}
		}
		return sector;
	}

	@Override
	public CentroTrabalho buscarSetorPorSigla(String sigla) {
		CentroTrabalho sector = null;

		for (CentroTrabalho setor : this.departamentoCadastrado) {
			if (setor.getNome().equalsIgnoreCase(sigla)) {
				sector = setor;
			}
		}
		return sector;
	}

	@Override
	public void editar(CentroTrabalho setor) {
		for (CentroTrabalho s : this.departamentoCadastrado) {
			if (s.getId() == setor.getId()) {
				s = setor;
			}
		}
	}

	@Override
	public CentroTrabalho buscarSetorPorId(long id) {
		CentroTrabalho setor = null;
		for(CentroTrabalho centro : this.departamentoCadastrado){
			if(centro.getId() == id){
				setor = centro;
			}
		}
		
		return setor;
	}

}

package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.locar.pipe.interfaces.DepartamentosInterface;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.util.HibernateUtil;

public class DepartamentoRepository implements DepartamentosInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void salvar(Departamento setor) {
	
	}

	@Override
	public void excluir(Departamento setor) {
	}

	@Override
	public void editar(Departamento setor) {
	
	}
	
	@Override
	public Departamento buscarSetorPorNome(String nome) {

		return null;
	}

	@Override
	public Departamento buscarSetorPorSigla(String sigla) {
	
		return null;
	}

	@Override
	public Departamento buscarSetorPorId(long id) {
		Session session = HibernateUtil.getSession();
		Departamento depart = (Departamento) session.get(Departamento.class, id);
		
		session.close();
		return depart;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> listarSetor() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		Session session = HibernateUtil.getSession();
		departamentos = session.createCriteria(Departamento.class).list();
		session.close();
		return departamentos;
		
	}
}

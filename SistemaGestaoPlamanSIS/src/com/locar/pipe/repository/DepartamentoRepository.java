package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.interfaces.DepartamentosInterface;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.util.HibernateUtil;

public class DepartamentoRepository implements DepartamentosInterface,
		Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.save(setor);
	}

	@Override
	public void excluir(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(setor);

	}

	@Override
	public void editar(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.update(setor);
	}

	@Override
	public Departamento buscarSetorPorNome(String nome) {
		Departamento depart = null;

		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		depart = (Departamento) session.createCriteria(Departamento.class)
				.add(Restrictions.eq("nome", nome)).uniqueResult();

		return depart;
	}

	@Override
	public Departamento buscarSetorPorSigla(String sigla) {

		Departamento depart = null;

		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		depart = (Departamento) session.createCriteria(Departamento.class)
				.add(Restrictions.eq("nome", sigla)).uniqueResult();

		return depart;
	}

	@Override
	public Departamento buscarSetorPorId(long id) {
		Departamento depart = null;

		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		depart = (Departamento) session.get(Departamento.class, id);

		return depart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Departamento> listarSetor() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		departamentos = session.createCriteria(Departamento.class).list();

		return departamentos;

	}
}

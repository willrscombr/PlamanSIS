package com.locar.pipe.repository.infra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.repository.ColaboradorDB;
import com.locar.pipe.util.HibernateUtil;

public class ColaboradorRepository implements
		ColaboradorDB, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Colaborador colaborador) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.save(colaborador);
	}

	@Override
	public void excluir(Colaborador colaborador) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(colaborador);
	}

	@Override
	public void editar(Colaborador colaborador) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.update(colaborador);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarTodos() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		List<Colaborador> colaboradores = session.createCriteria(
				Colaborador.class).list();

		return colaboradores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarPorSetor(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		List<Colaborador> colaboradorPorSetor = new ArrayList<Colaborador>();
		colaboradorPorSetor = session.createCriteria(Colaborador.class)
				.add(Restrictions.eq("setor", setor)).list();

		return colaboradorPorSetor;
	}

	@Override
	public Colaborador buscarPorNome(String nome) {
		Colaborador colabPorNome = null;
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		colabPorNome = (Colaborador) session.createCriteria(Colaborador.class)
				.add(Restrictions.eq("nome", nome)).uniqueResult();

		return colabPorNome;
	}

	@Override
	public Colaborador buscarPorMatricula(String matricula) {
		Colaborador colabPorMatricula = null;
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		colabPorMatricula = (Colaborador) session
				.createCriteria(Colaborador.class)
				.add(Restrictions.eq("matricula", matricula)).uniqueResult();

		return colabPorMatricula;
	}

}

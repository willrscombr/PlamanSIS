package com.locar.pipe.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.interfaces.RegistroColaboradorInterface;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.util.HibernateUtil;

public class RegistroColaboradorRepository implements
		RegistroColaboradorInterface, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Colaborador colaborador) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		
		tx.begin();
		session.save(colaborador);
		tx.commit();
		session.close();
	}

	@Override
	public void excluir(Colaborador colaborador) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.delete(colaborador);
		session.getTransaction().commit();
		
		session.close();
	}

	@Override
	public void editar(Colaborador colaborador) {
		Session session = HibernateUtil.getSession();
		session.getTransaction().begin();
		session.update(colaborador);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarTodos() {
		Session session = HibernateUtil.getSession();
		List<Colaborador> colaboradores = session.createCriteria(
				Colaborador.class).list();
		
		session.close();

		return colaboradores;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarPorSetor(Departamento setor) {
		Session session = HibernateUtil.getSession();

		List<Colaborador> colaboradores = session
				.createCriteria(Colaborador.class)
				.add(Restrictions.eq("setor", setor.getId())).list();
		
		session.close();
		return colaboradores;
	}

	@Override
	public Colaborador buscarPorNome(String nome) {
		Colaborador colabPorNome = null;
		Session session = HibernateUtil.getSession();
		colabPorNome = (Colaborador) session.get(Colaborador.class, nome);
		session.close();
		return colabPorNome;
	}

	@Override
	public Colaborador buscarPorMatricula(String matricula) {
		Colaborador colabPorMatricula = null;

		return colabPorMatricula;
	}

}

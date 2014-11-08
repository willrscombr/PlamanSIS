package com.locar.pipe.repository.infra;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.modelos.Usuario;
import com.locar.pipe.repository.UsuarioDB;
import com.locar.pipe.util.HibernateUtil;

public class UsuarioRepository implements UsuarioDB, Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Usuario user) {
		user.setNomeUsuario(user.getNomeUsuario().toLowerCase());
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.save(user);
		
	}

	@SuppressWarnings("unused")
	@Override
	public void update(Usuario user) {
		user.setNomeUsuario(user.getNomeUsuario().toLowerCase());
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Usuario atualiza = (Usuario) session.get(Usuario.class, user.getNomeUsuario());
		atualiza = user;
	}

	@Override
	public void delete(Usuario user) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.delete(user);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listarTodos() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(Usuario.class);
		crit.addOrder(Order.asc("nomeUsuario"));
		return crit.list();
	}

	@Override
	public Usuario buscarPorNome(String nome) {
		nome = nome.toLowerCase();
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Usuario user = (Usuario)session.createCriteria(Usuario.class).add(Restrictions.eq("nomeUsuario", nome)).uniqueResult();
		return user;
	}

	

}

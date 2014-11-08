package com.locar.pipe.repository.infra;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.repository.BackLogDB;
import com.locar.pipe.service.BackLogApplication;
import com.locar.pipe.util.HibernateUtil;

public class BackLogRepository implements BackLogDB, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(BackLogApplication log) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.saveOrUpdate(log);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BackLogApplication> listarTodos() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(BackLogApplication.class);
		crit.addOrder(Order.asc("dataLog"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BackLogApplication> listarPorColab(Colaborador colab) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(BackLogApplication.class);
		crit.add(Restrictions.eq("colaborador", colab));
		crit.addOrder(Order.asc("dataLog"));
		return crit.list();
	}

}

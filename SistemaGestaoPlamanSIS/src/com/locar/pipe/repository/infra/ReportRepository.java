package com.locar.pipe.repository.infra;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.locar.pipe.modelos.Report;
import com.locar.pipe.repository.ReportDB;
import com.locar.pipe.util.HibernateUtil;

public class ReportRepository implements ReportDB , Serializable{
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(Report report) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.saveOrUpdate(report);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Report> listarTodos() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(Report.class);
		crit.addOrder(Order.asc("dataReport"));
		return crit.list();
	}

}

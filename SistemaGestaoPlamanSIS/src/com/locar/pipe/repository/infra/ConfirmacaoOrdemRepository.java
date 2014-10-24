package com.locar.pipe.repository.infra;

import java.util.Date;
import java.util.List;








import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.ConfirmacaoOrdem;
import com.locar.pipe.repository.ConfirmacaoOrdemDB;
import com.locar.pipe.util.HibernateUtil;

public class ConfirmacaoOrdemRepository implements ConfirmacaoOrdemDB {

	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmacaoOrdem> listarPorColaborador(Colaborador colab) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(ConfirmacaoOrdem.class);
		crit.add(Restrictions.eq("colaborador", colab));
		crit.addOrder(Order.desc("dataInicio"));
		return crit.list();
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ConfirmacaoOrdem> listaPorDataColaborador(Colaborador colab,
			Date data) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(ConfirmacaoOrdem.class);
		crit.add(Restrictions.eq("colaborador", colab));
		crit.add(Restrictions.eq("dataInicio", data));
		return crit.list();
	}

}

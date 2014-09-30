package com.locar.pipe.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.interfaces.SolicitacoesInterface;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.util.HibernateUtil;

public class SolicitacoesRepositorio implements SolicitacoesInterface{

	@Override
	public void salvarSolicitcao(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.save(solicitacao);
	}

	@Override
	public void excluirSolicitacao(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.delete(solicitacao);		
	}

	@SuppressWarnings("unused")
	@Override
	public void editarSolicitacao(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		SolicitacaoServico editar = this.bucarSolicitacao(solicitacao.getId());
		editar = solicitacao;
	}

	@Override
	public SolicitacaoServico bucarSolicitacao(long id) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		SolicitacaoServico solicita = null;
		solicita = (SolicitacaoServico) session.get(SolicitacaoServico.class, id);
		return solicita;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> listarTodas() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.addOrder(Order.asc("dataCriacao"));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> listarPorStatus(Status status) {
		List<SolicitacaoServico> listaPorStatus = null;
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		
		listaPorStatus = session.createCriteria(SolicitacaoServico.class).add(Restrictions.eq("statusSolicitacao", status)).list();
		return listaPorStatus;
	}

	@Override
	public long qntPorSetorStatus(Departamento setor,Status status) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("setor", setor));
		crit.add(Restrictions.eq("statusSolicitacao", status));
		
		return (Long) crit.uniqueResult();
	}

}

package com.locar.pipe.repository.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.SolicitacoesDB;
import com.locar.pipe.util.HibernateUtil;

public class SolicitacoesRepositorio implements SolicitacoesDB{

	@Override
	public void save(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.merge(solicitacao);
	}

	@Override
	public void delete(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		session.delete(solicitacao);		
	}

	@SuppressWarnings("unused")
	@Override
	public void update(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		SolicitacaoServico editar = this.findById(solicitacao.getId());
		editar = solicitacao;
	}

	@Override
	public SolicitacaoServico findById(long id) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		SolicitacaoServico solicita = null;
		solicita = (SolicitacaoServico) session.get(SolicitacaoServico.class, id);
		return solicita;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> getAll() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.addOrder(Order.asc("dataCriacao"));
		return crit.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> listarPorStatusSetor(Departamento setor,Status status) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.add(Restrictions.eq("setor", setor));
		crit.add(Restrictions.eq("status", status));
		return crit.list();
	}

	@Override
	public long qntPorSetor(Departamento setor) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("setor", setor));
		
		return (Long) crit.uniqueResult();
	}

	@Override
	public long qntPorStatus(Status status) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		
		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("status", status));
		
		return (Long) crit.uniqueResult();
	}

	@Override
	public SolicitacaoServico jaExiste(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		SolicitacaoServico retorno = null;
		
		retorno = (SolicitacaoServico) session.createCriteria(SolicitacaoServico.class)
				.add(Restrictions.eq("setor", solicitacao.getSetor()))
				.add(Restrictions.eq("status", solicitacao.getStatus()))
				.add(Restrictions.ilike("equipamento", solicitacao.getEquipamento()))
				.add(Restrictions.ilike("componente", solicitacao.getComponente()))
				.add(Restrictions.ilike("descricaoAcao", solicitacao.getDescricaoAcao()))
				.add(Restrictions.eq("solicitante",solicitacao.getSolicitante()))
				.uniqueResult();
		if(retorno == null){
			System.out.println("Nao EXISTE");
		}
		return retorno;
	}
}

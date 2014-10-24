package com.locar.pipe.repository.infra;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.filtros.FiltrosSolicitacoes;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.SolicitacoesDB;
import com.locar.pipe.util.HibernateUtil;

public class SolicitacoesRepositorio implements SolicitacoesDB {
	private static final long serialVersionUID = 1L;

	@Override
	public void save(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.merge(solicitacao);
	}

	@Override
	public void delete(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(solicitacao);
	}

	@SuppressWarnings("unused")
	@Override
	public void update(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		SolicitacaoServico editar = this.findById(solicitacao.getId());
		editar = solicitacao;
	}

	@Override
	public SolicitacaoServico findById(long id) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		SolicitacaoServico solicita = null;
		solicita = (SolicitacaoServico) session.get(SolicitacaoServico.class,
				id);
		return solicita;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> getAll() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.desc("dataCriacao"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> listarPorStatusSetor(Departamento setor,
			Status status) {
		
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.desc("dataCriacao"));
		
		if (status != null) {
			crit.add(Restrictions.eq("status", status));
		}

		if (setor != null) {
			crit.add(Restrictions.eq("setor", setor));
		}

		return crit.list();
	}

	@Override
	public long qntPorSetor(Departamento setor) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);

		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("setor", setor));

		return (Long) crit.uniqueResult();
	}

	@Override
	public long qntPorStatus(Status status) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);

		crit.setProjection(Projections.rowCount());
		crit.add(Restrictions.eq("status", status));

		return (Long) crit.uniqueResult();
	}

	@Override
	public SolicitacaoServico jaExiste(SolicitacaoServico solicitacao) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		SolicitacaoServico retorno = null;

		retorno = (SolicitacaoServico) session
				.createCriteria(SolicitacaoServico.class)
				.add(Restrictions.eq("setor", solicitacao.getSetor()))
				.add(Restrictions.eq("status", solicitacao.getStatus()))
				.add(Restrictions.ilike("equipamento",
						solicitacao.getEquipamento()))
				.add(Restrictions.ilike("componente",
						solicitacao.getComponente()))
				.add(Restrictions.ilike("descricaoAcao",
						solicitacao.getDescricaoAcao()))
				.add(Restrictions.eq("solicitante",
						solicitacao.getSolicitante())).uniqueResult();
		if (retorno == null) {
			System.out.println("Nao EXISTE");
		}
		return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> listarPorNomeEquipamento(String query) {
		
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.asc("equipamento"));
		crit.add(Restrictions.ilike("equipamento", query, MatchMode.ANYWHERE));
		System.out.println("Valor da QUERY: "+query);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> pesquisarPorFiltros(
			FiltrosSolicitacoes filtro) {
		
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		if(filtro != null){
			if(filtro.getSetor() != null){
				crit.add(Restrictions.eq("setor", filtro.getSetor()));
			}
			
			if(!filtro.getEquipamento().isEmpty() && filtro.getEquipamento() != null ){
				crit.add(Restrictions.ilike("equipamento", filtro.getEquipamento(),MatchMode.ANYWHERE));
			}
			if(!filtro.getComponente().isEmpty()){
				crit.add(Restrictions.ilike("componente", filtro.getComponente(),MatchMode.ANYWHERE));
			}
			
			if(filtro.getStatus() != null){
				crit.add(Restrictions.eq("status", filtro.getStatus()));
			}
			
			if(filtro.getTipoTrabalho() != null){
				crit.add(Restrictions.eq("tipoTrabalho", filtro.getTipoTrabalho()));
			}
		}
		
		crit.addOrder(Order.desc("dataCriacao"));
		return crit.list();		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitacaoServico> pesquisarPorFiltrosSupervisor(
			FiltrosSolicitacoes filtro) {

		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(SolicitacaoServico.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		if(filtro != null){
			if(filtro.getSetor() != null){
				crit.add(Restrictions.eq("setor", filtro.getSetor()));
			}
			
			if(!filtro.getEquipamento().isEmpty() && filtro.getEquipamento() != null ){
				crit.add(Restrictions.ilike("equipamento", filtro.getEquipamento(),MatchMode.ANYWHERE));
			}
			if(!filtro.getComponente().isEmpty()){
				crit.add(Restrictions.ilike("componente", filtro.getComponente(),MatchMode.ANYWHERE));
			}
			
			if(filtro.getStatus() != null){
				crit.add(Restrictions.eq("status", filtro.getStatus()));
			}
			
			if(filtro.getTipoTrabalho() != null){
				crit.add(Restrictions.eq("tipoTrabalho", filtro.getTipoTrabalho()));
			}
		}
		
		crit.addOrder(Order.desc("dataCriacao"));
		return crit.list();
	}

	@Override
	public void trocaStatus(long id,Status status) {
				
		SolicitacaoServico soli = new SolicitacaoServico();
		soli = this.findById(id);
		System.out.println("Solicitacao: "+soli.getDescricaoAcao());
		soli.setStatus(status);		
		
	}
	
	
}

package com.locar.pipe.repository.infra;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.enuns.TipoOrdem;
import com.locar.pipe.filtros.FiltrosOrdens;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.OrdemServico;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.repository.OrdemServicoDB;
import com.locar.pipe.util.HibernateUtil;

public class OrdemServicoRepository implements OrdemServicoDB {
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.save(ordemCorretiva);

	}

	@Override
	public void excluir(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(ordemCorretiva);

	}

	@Override
	public void editar(OrdemServico ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.update(ordemCorretiva);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodasCorretiva() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServico.class);
		crit.add(Restrictions.eq("tipoOrdem",TipoOrdem.CORRETIVA));
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodasPreventivas() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		List<OrdemServico> preventivas = new ArrayList<OrdemServico>();
		preventivas = session.createCriteria(OrdemServico.class)
				.add(Restrictions.eq("tipo", TipoOrdem.PREVENTIVA)).list();

		return preventivas;
	}

	@Override
	public OrdemServico buscarPorId(long id) {
		OrdemServico ordem = null;
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		ordem = (OrdemServico) session.get(OrdemServico.class, id);

		return ordem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarTodas() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		return session.createCriteria(OrdemServico.class)
				.add(Restrictions.eq("tipoOrdem", TipoOrdem.CORRETIVA)).list();
	}

	@Override
	public long qntDeOrdemPorSetorStatus(Departamento setor,Status status) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria criteria = session.createCriteria(OrdemServico.class);
		criteria.add(Restrictions.eq("setor", setor));
		criteria.add(Restrictions.eq("status", status));
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarUltimasCinco() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServico.class);
		crit.setMaxResults(5);
		crit.addOrder(Order.desc("id"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> listarPorStatus(Departamento setor, Status status) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServico.class);
		
		if(setor != null){
			crit.add(Restrictions.eq("setor", setor));
		}
		crit.add(Restrictions.eq("status", status));
		crit.addOrder(Order.desc("dataCriacao"));
		
		return crit.list();
	}

	@Override
	public boolean jaExiste(OrdemServico ordem) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServico.class);
		
		OrdemServico ord = null;
		
		ord = (OrdemServico) crit.add(Restrictions.ilike("equipamento", ordem.getEquipamento()))
				.add(Restrictions.ilike("componente", ordem.getComponente()))
				.add(Restrictions.ilike("descricaoAcao", ordem.getDescricaoAcao()))
				.add(Restrictions.eq("setor", ordem.getSetor()))
				.add(Restrictions.eq("status", ordem.getStatus()))
				.add(Restrictions.eq("dataCriacao", ordem.getDataCriacao()))
				.add(Restrictions.eq("tipoTrabalho", ordem.getTipoTrabalho()))
				.add(Restrictions.eq("tipoOrdem", ordem.getTipoOrdem())).uniqueResult();
		
		return ord == null ? false : true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServico> pesquisarPorFiltros(FiltrosOrdens filtro) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServico.class);
		
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
}

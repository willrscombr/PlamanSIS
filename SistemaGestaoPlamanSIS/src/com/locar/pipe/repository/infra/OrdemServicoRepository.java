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
import com.locar.pipe.modelos.OrdemServicoCorretiva;
import com.locar.pipe.repository.OrdemServicoDB;
import com.locar.pipe.util.HibernateUtil;

public class OrdemServicoRepository implements OrdemServicoDB {
	private static final long serialVersionUID = 1L;

	@Override
	public void salvar(OrdemServicoCorretiva ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.save(ordemCorretiva);

	}

	@Override
	public void excluir(OrdemServicoCorretiva ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.delete(ordemCorretiva);

	}

	@Override
	public void editar(OrdemServicoCorretiva ordemCorretiva) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		session.merge(ordemCorretiva);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServicoCorretiva> listarTodasCorretiva() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServicoCorretiva.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.add(Restrictions.eq("tipoOrdem",TipoOrdem.CORRETIVA));
		
		return crit.list();
	}

	@Override
	public OrdemServicoCorretiva buscarPorId(long id) {
		OrdemServicoCorretiva ordem = null;
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");

		ordem = (OrdemServicoCorretiva) session.get(OrdemServicoCorretiva.class, id);

		return ordem;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServicoCorretiva> listarTodas() {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		return session.createCriteria(OrdemServicoCorretiva.class)
				.add(Restrictions.eq("tipoOrdem", TipoOrdem.CORRETIVA)).list();
	}

	@Override
	public long qntDeOrdemPorSetorStatus(Departamento setor,Status status) {
		Session session = (Session) HibernateUtil
				.getAttributeRequest("session");
		Criteria criteria = session.createCriteria(OrdemServicoCorretiva.class);
		criteria.add(Restrictions.eq("setor", setor));
		criteria.add(Restrictions.eq("status", status));
		criteria.setProjection(Projections.rowCount());
		
		return (Long) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServicoCorretiva> listarUltimasCinco() {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServicoCorretiva.class);
		crit.setMaxResults(5);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		crit.addOrder(Order.desc("id"));
		List<OrdemServicoCorretiva> ultimasOrdens = new ArrayList<OrdemServicoCorretiva>();
		ultimasOrdens = crit.list();
		
		for(OrdemServicoCorretiva ord : ultimasOrdens){
			System.out.println("ID "+ord.getId());
			System.out.println("EQUIPAMENTO "+ord.getEquipamento());
		}
		
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServicoCorretiva> listarPorStatus(Departamento setor, Status status) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServicoCorretiva.class);
		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		if(setor != null){
			crit.add(Restrictions.eq("setor", setor));
		}
		crit.add(Restrictions.eq("status", status));
		crit.addOrder(Order.desc("dataCriacao"));
		
		return crit.list();
	}

	@Override
	public boolean jaExiste(OrdemServicoCorretiva ordem) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServicoCorretiva.class);
		
		OrdemServicoCorretiva ord = null;
		
		ord =  (OrdemServicoCorretiva) crit.add(Restrictions.ilike("equipamento", ordem.getEquipamento()))
				.add(Restrictions.ilike("componente", ordem.getComponente()))
				.add(Restrictions.ilike("descricaoAcao", ordem.getDescricaoAcao()))
				.add(Restrictions.eq("setor", ordem.getSetor()))
				.add(Restrictions.eq("status", ordem.getStatus()))
				.add(Restrictions.eq("dataCriacao", ordem.getDataCriacao()))
				.add(Restrictions.eq("tipoTrabalho", ordem.getTipoTrabalho())).uniqueResult();
		
		return ord == null ? false : true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdemServicoCorretiva> pesquisarPorFiltros(FiltrosOrdens filtro) {
		Session session = (Session) HibernateUtil.getAttributeRequest("session");
		Criteria crit = session.createCriteria(OrdemServicoCorretiva.class);
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
			
			if(filtro.getId() != 0){
				crit.add(Restrictions.eq("id", filtro.getId()));
			}
			
			if(filtro.getTipoTrabalho() != null){
				crit.add(Restrictions.eq("tipoTrabalho", filtro.getTipoTrabalho()));
			}
			if(filtro.getTipoOrdem() != null){
				crit.add(Restrictions.eq("tipoOrdem", filtro.getTipoOrdem()));
			}
			
			if(filtro.getDataInicio() != null && filtro.getDataFinal() != null){
				crit.add(Restrictions.between("dataCriacao", filtro.getDataInicio(), filtro.getDataFinal()));
			}
			
			if(filtro.getDataInicio() != null && filtro.getDataFinal() == null){
				crit.add(Restrictions.eq("dataCriacao", filtro.getDataInicio()));
			}
			
			if(filtro.getDataInicio() == null && filtro.getDataFinal() != null){
				crit.add(Restrictions.eq("dataCriacao", filtro.getDataFinal()));
			}
			
			if(filtro.getCiclo() != 0){
				crit.add(Restrictions.eq("cicloPreventivo", filtro.getCiclo()));
			}
		}
		
		crit.addOrder(Order.asc("status"));
		crit.addOrder(Order.desc("dataCriacao"));
		return crit.list();
	}

	

}

package com.locar.pipe.repository;

import java.util.List;

import org.hibernate.Session;
import com.locar.pipe.interfaces.SolicitacoesOrdem;
import com.locar.pipe.modelos.SolicitacaoServico;
import com.locar.pipe.util.HibernateUtil;

public class SolicitacoesRepositorio implements SolicitacoesOrdem{

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
		return session.createCriteria(SolicitacaoServico.class).list();
	}

}

package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.enuns.StatusSolicitacao;
import com.locar.pipe.modelos.SolicitacaoServico;

public interface SolicitacoesOrdem {

	void salvarSolicitcao(SolicitacaoServico solicitacao);
	void excluirSolicitacao(SolicitacaoServico solicitacao);
	void editarSolicitacao(SolicitacaoServico solicitacao);
	SolicitacaoServico bucarSolicitacao(long id);
	List<SolicitacaoServico> listarTodas();
	List<SolicitacaoServico> listarPorStatus(StatusSolicitacao status);
	
}

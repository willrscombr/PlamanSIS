package com.locar.pipe.interfaces;

import java.util.List;

import com.locar.pipe.enuns.Status;
import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.modelos.SolicitacaoServico;

public interface SolicitacoesInterface {

	void salvarSolicitcao(SolicitacaoServico solicitacao);
	void excluirSolicitacao(SolicitacaoServico solicitacao);
	void editarSolicitacao(SolicitacaoServico solicitacao);
	SolicitacaoServico bucarSolicitacao(long id);
	List<SolicitacaoServico> listarTodas();
	long qntPorSetorStatus(Departamento setor, Status status);
	List<SolicitacaoServico> listarPorStatus(Status status);
	
}

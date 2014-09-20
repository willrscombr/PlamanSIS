package com.locar.pipe.repository;

import java.util.ArrayList;
import java.util.List;

import com.locar.pipe.enuns.TipoDeOrdem;
import com.locar.pipe.interfaces.ColecaoOsInterface;
import com.locar.pipe.modelos.OrdemServico;

public class ColecaoOsRepository implements ColecaoOsInterface {

	// Atributo será substituido pela implementação do banco de dados
	private List<OrdemServico> listaDeOrdem;
	
	public ColecaoOsRepository() {
		this.listaDeOrdem = new ArrayList<OrdemServico>();
	}

	@Override
	public void salvar(OrdemServico ordemCorretiva) {
		this.listaDeOrdem.add(ordemCorretiva);

	}

	@Override
	public void excluir(OrdemServico ordemCorretiva) {
		this.listaDeOrdem.remove(ordemCorretiva);

	}

	@Override
	public void editar(OrdemServico ordemCorretiva) {
		for (OrdemServico ordem : this.listaDeOrdem) {
			if (ordem.getId() == ordemCorretiva.getId()) {
				ordem = ordemCorretiva;
			}
		}

	}

	@Override
	public List<OrdemServico> listarTodasCorretiva() {
		List<OrdemServico> corretivas = new ArrayList<OrdemServico>();
		
		for(OrdemServico os : this.listaDeOrdem){
			if(os.getTipo() == TipoDeOrdem.CORRETIVA){
				corretivas.add(os);
			}
		}
		
		if(corretivas.isEmpty()){
			return null;
		}
		
		return corretivas;
	}
	
	@Override
	public List<OrdemServico> listarTodasPreventivas() {
		List<OrdemServico> preventivas = new ArrayList<OrdemServico>();
		
		for(OrdemServico os : this.listaDeOrdem){
			if(os.getTipo() == TipoDeOrdem.PREVENTIVA){
				preventivas.add(os);
			}
		}
		
		if(preventivas.isEmpty()){
			return null;
		}
		
		return preventivas;
	}

	@Override
	public OrdemServico buscarPorId(long id) {
		OrdemServico ordem = null;

		for (OrdemServico os : this.listaDeOrdem) {
			if (os.getId() == id) {
				ordem = os;
			}
		}

		return ordem;
	}
}

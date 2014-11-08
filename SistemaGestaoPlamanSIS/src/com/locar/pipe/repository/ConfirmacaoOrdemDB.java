package com.locar.pipe.repository;

import java.util.Date;
import java.util.List;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.modelos.ConfirmacaoOrdem;

public interface ConfirmacaoOrdemDB {

	public List<ConfirmacaoOrdem> listarPorColaborador(Colaborador colab);
	public List<ConfirmacaoOrdem> listaPorDataColaborador(Colaborador colab, Date data);
}

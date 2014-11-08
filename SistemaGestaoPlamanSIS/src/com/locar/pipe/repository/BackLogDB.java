package com.locar.pipe.repository;

import java.util.List;
import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.service.BackLogApplication;

public interface BackLogDB {

	void salvar(BackLogApplication log);
	List<BackLogApplication> listarTodos();
	List<BackLogApplication> listarPorColab(Colaborador colab);
}

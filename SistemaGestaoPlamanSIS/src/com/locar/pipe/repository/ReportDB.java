package com.locar.pipe.repository;

import java.util.List;

import com.locar.pipe.modelos.Report;

public interface ReportDB {

	void salvar(Report report);
	List<Report> listarTodos();
}

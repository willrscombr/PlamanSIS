package com.locar.pipe.visao;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import com.locar.pipe.enuns.TipoDoReport;
import com.locar.pipe.modelos.Report;
import com.locar.pipe.repository.ReportDB;
import com.locar.pipe.repository.infra.ReportRepository;
import com.locar.pipe.util.MensagensUtil;


@ManagedBean
public class ReportBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private Report report;
	private ReportDB reportService;
	
	@PostConstruct
	public void init(){
		report = new Report();
		report.setDataReport(Calendar.getInstance().getTime());
		reportService = new ReportRepository();
	}
	
	public void enviarReport(){
			reportService.salvar(report);
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_INFO, "Seu Report foi enviado ao TI e será atendido em breve");
			init();
	}
	
	public boolean validarReport(Report report){
		return !report.getLocal().equals("") && report.getLocal() != null && !report.getDescricao().equals("") && report.getDescricao() != null ? true : false;   
	}
	
	public TipoDoReport[] tiposReport(){
		return TipoDoReport.values();
	}
	
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
	
}

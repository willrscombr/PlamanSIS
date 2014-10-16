package com.locar.pipe.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtil {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void imprimeRelatorio(String relatorioNome,HashMap parametro, List lista){
		
		try {
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			String path = getSContext().getRealPath("/WEB-INF/relatorios/");
			ImageIcon gto = new ImageIcon(getExternalContext().getRealPath("images/logo_locar.jpg")); 
			parametro.put("logo", gto.getImage());
			parametro.put("SUBREPORT_DIR", path + File.separator);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(path+File.separator+relatorioNome+".jasper", parametro,dataSource);
			HttpServletResponse res = (HttpServletResponse) getExternalContext().getResponse();
			res.addHeader("Content-dispositon", "attachment; filename=report.pdf");
			ServletOutputStream outPutStream = res.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outPutStream);
			FacesContext.getCurrentInstance().renderResponse();
			
		} catch (Exception e) {
			MensagensUtil.addMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao imprimir: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static ServletContext getSContext(){
		return (ServletContext) getExternalContext().getContext();
	}
	
	public static ExternalContext getExternalContext(){
		return FacesContext.getCurrentInstance().getExternalContext();
	}
}


package com.locar.pipe.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.locar.pipe.modelos.CentroTrabalho;

public class SetorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		CentroTrabalho setor = null;
		return setor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		CentroTrabalho setor = (CentroTrabalho) value;
		return setor.getNome();
	}

}

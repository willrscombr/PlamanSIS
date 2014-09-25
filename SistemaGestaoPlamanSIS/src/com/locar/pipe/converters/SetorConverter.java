package com.locar.pipe.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.locar.pipe.modelos.Departamento;
import com.locar.pipe.repository.DepartamentoRepository;

@FacesConverter(forClass = Departamento.class)
public class SetorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Departamento setor = null;
		
		DepartamentoRepository repositorio = new DepartamentoRepository();
		setor = repositorio.buscarSetorPorId(Long.parseLong(value));
		
		return setor;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {

		if (value != null) {
			String codigo = String.valueOf(((Departamento) value).getId())
					.toString();

			return codigo;
		}
		return null;
	}

}

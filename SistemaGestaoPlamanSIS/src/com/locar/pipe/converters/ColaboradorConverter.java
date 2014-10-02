package com.locar.pipe.converters;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.locar.pipe.modelos.Colaborador;
import com.locar.pipe.repository.infra.ColaboradorRepository;

@FacesConverter(forClass=Colaborador.class)
public class ColaboradorConverter  implements Converter{

	@Override
	public Object getAsObject(FacesContext contex, UIComponent component, String value) {

		Colaborador colaborador = null;
		ColaboradorRepository repositorio = new ColaboradorRepository();
		colaborador = repositorio.buscarPorNome(value);
		return colaborador;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Colaborador colaborador = (Colaborador) value;
		return colaborador.getNome();
	}

}

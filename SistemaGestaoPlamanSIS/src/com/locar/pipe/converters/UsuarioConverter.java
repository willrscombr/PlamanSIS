package com.locar.pipe.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.locar.pipe.modelos.Usuario;
import com.locar.pipe.repository.infra.UsuarioRepository;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		
		UsuarioRepository userDB = new UsuarioRepository();
		Usuario user = null;
		
		user = userDB.buscarPorNome(value);
		return user;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		Usuario user = (Usuario) value;
		return user.getNomeUsuario();
	}

}

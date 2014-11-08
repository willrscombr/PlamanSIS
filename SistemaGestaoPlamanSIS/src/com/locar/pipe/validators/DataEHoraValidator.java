package com.locar.pipe.validators;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.locar.pipe.DataHoraValidator")
public class DataEHoraValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		Date hora = (Date) value;
		
		if(hora != null){
			
		}
		
	}

}

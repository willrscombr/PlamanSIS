package com.locar.pipe.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MensagensUtil {

	public static void addMensagem(Severity severidade,String sumario ,String mensagem){
		FacesContext.getCurrentInstance().addMessage("Mensagem", new FacesMessage(severidade,sumario,mensagem));
	}
}

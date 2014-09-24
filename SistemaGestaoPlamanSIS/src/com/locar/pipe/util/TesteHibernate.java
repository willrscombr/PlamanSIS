package com.locar.pipe.util;

import java.util.List;

import org.hibernate.Session;
import com.locar.pipe.modelos.Departamento;

public class TesteHibernate {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Session session = HibernateUtil.getSession();
		List<Departamento> departamento = session.createCriteria(Departamento.class).list();

		for(Departamento dep : departamento){
			System.out.println("NOME: "+dep.getSigla());
			System.out.println("SIGLA: "+dep.getNome());
		}

		session.close();
	}
}

package com.locar.pipe.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try{
		Configuration config = new Configuration();
		config.configure();

		ServiceRegistry service = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).build();
		
		sessionFactory = config.buildSessionFactory(service);
		}catch(Throwable ex){
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static Object getAttributeRequest(String attName){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externContext = context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externContext.getRequest();
		
		return request.getAttribute(attName);
	}
}

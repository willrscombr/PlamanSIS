package com.locar.pipe.util;

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
}

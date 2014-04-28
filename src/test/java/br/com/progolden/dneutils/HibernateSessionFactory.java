package br.com.progolden.dneutils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateSessionFactory {

	private static final Logger LOG = LoggerFactory.getLogger(HibernateSessionFactory.class);
	
	/** The factory. */
	private static SessionFactory factory;

	public static SessionFactory getInstance() {
		if (HibernateSessionFactory.factory == null) {
			LOG.debug("Carregando Hibernate pelo arquivo: hibernate.cfg.xml");
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");
			ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(config
				.getProperties());
			HibernateSessionFactory.factory = config.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
		}
		return HibernateSessionFactory.factory;
	}

}

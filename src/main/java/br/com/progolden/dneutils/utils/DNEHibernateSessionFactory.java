/*
   Copyright 2014 ProGolden Soluções Tecnológicas

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package br.com.progolden.dneutils.utils;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DNEHibernateSessionFactory {

	private static final Logger LOG = LoggerFactory.getLogger(DNEHibernateSessionFactory.class);
	
	/** The factory. */
	private static SessionFactory factory;

	public static SessionFactory getInstance() {
		if (DNEHibernateSessionFactory.factory == null) {
			LOG.debug("Carregando Hibernate pelo arquivo: hibernate.cfg.xml");
			Configuration config = new Configuration();
			config
				.configure("dne.hibernate.mappings.xml")
				.configure("hibernate.cfg.xml")
			;
			ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(config
				.getProperties());
			DNEHibernateSessionFactory.factory = config.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
		}
		return DNEHibernateSessionFactory.factory;
	}
	
	private final SessionFactory customFactory;
	
	public DNEHibernateSessionFactory(String configFile) {
		LOG.debug("Carregando Hibernate pelo arquivo: "+configFile);
		Configuration config = new Configuration();
		config
			.configure("dne.hibernate.mappings.xml")
			.configure(configFile)
		;
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder()
			.applySettings(config.getProperties());
		this.customFactory = config.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
	}
	
	public SessionFactory getFactory() {
		return this.customFactory;
	}
	
	@PreDestroy
	public void closeFactory() {
		this.customFactory.close();
	}

}

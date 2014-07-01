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
package br.com.progolden.dneutils.abstractions;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PreDestroy;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract DAO definition. Implements common actions to entities.
 * @author Renato R. R. de Oliveira
 *
 */
@SuppressWarnings("unchecked")
public class GenericDAO implements DAO {
	
	private static final Logger LOG = LoggerFactory.getLogger(GenericDAO.class);
	
	/** Database session. */
	private Session session;
	/** Session factory for the DB. */
	private SessionFactory factory;
	/** Manages the transactions retries. */
	private DatabaseRetryManager retryManager;
	
	protected GenericDAO() {
		this.session = null;
		this.retryManager = new DatabaseRetryManager(this);
	}
	
	public GenericDAO(SessionFactory factory) {
		this.session = null;
		this.factory = factory;
		this.retryManager = new DatabaseRetryManager(this);
	}
	
	@Override
	public Session getSession() {
		if (this.session == null) {
			this.session = this.factory.openSession();
		}
		return this.session;
	}
	
	@Override
	public void returnSession(Session session) {
		if (this.session != session) {
			this.closeSession();
			this.session = session;
		}
	}
	
	@Override
	public void forceSessionClose(Session session) {
		if (session == this.session) {
			this.closeSession();
		} else if (session != null) {
			session.close();
		}
	}
	
	@PreDestroy
	public void closeSession() {
		if (this.session != null) {
			this.session.close();
			this.session = null;
		}
	}
	
	public void persist(EntityIF entity) {
		try {
			this.retryManager.executeInTransaction(new Operation(entity, Operation.PERSIST));
		} catch (RuntimeException ex) {
			LOG.error("An error occurred while trying to persist model.", ex);
			throw ex;
		}
	}
	
	public void delete(EntityIF entity) {
		try {
			this.retryManager.executeInTransaction(new Operation(entity, Operation.DELETE));
		} catch (RuntimeException ex) {
			LOG.error("An error occurred while trying to delete model.", ex);
			throw ex;
		}
	}
	
	public Criteria newCriteria(Class<? extends EntityIF> clazz) {
		return this.getSession().createCriteria(clazz);
	}

	public <E> E uniqueByCriteria(Criteria criteria, Class<E> clazz) {
		return clazz.cast(criteria.uniqueResult());
	}
	
	public <E> List<E> findByCriteria(Criteria criteria, Class<E> clazz) {
		return (List<E>) criteria.list();
	}
	
	public <E> E exists(Class<E> clazz, Serializable id) {
		if (id == null)
			return null;
		return clazz.cast(this.getSession().get(clazz, id));
	}
	
	public SQLQuery newSQLQuery(String sql) {
		return this.getSession().createSQLQuery(sql);
	}
	
	class Operation implements DatabaseOperation {
		
		public static final int PERSIST = 1;
		public static final int BULK_UPDATE = 2;
		public static final int DELETE = 3;
		
		private EntityIF entity;
		private int operation;
		
		public Operation(EntityIF entity, int operation) {
			this.entity = entity;
			this.operation = operation;
		}
		
		@Override
		public void execute(Session session) {
			switch (this.operation) {
			case PERSIST:
				session.persist(this.entity);
				break;
			case DELETE:
				session.delete(this.entity);
				break;
			}
		}
		
	}
}

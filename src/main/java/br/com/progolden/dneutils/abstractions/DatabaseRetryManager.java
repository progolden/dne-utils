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

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;

import br.com.progolden.dneutils.exception.DNEException;

/**
 * Manager para tentar novamente execuções de transações que falharam.
 * @author Renato R. R. de Oliveira
 *
 */
public class DatabaseRetryManager implements DatabaseOperationExecutor {

	private static Set<Class<?>> FRAMEWORK_EXCEPTION_CLASSES = new HashSet<Class<?>>();
	public static long DEFAULT_MAX_RETRY_MILLIS = 2000;

	public static void addFrameworkExceptionClasses(Class<?>... classes) {
		for (Class<?> clazz : classes) {
			FRAMEWORK_EXCEPTION_CLASSES.add(clazz);
		}
	}

	public static void setDefaultMaxRetryTime(long interval, TimeUnit unit) {
		DEFAULT_MAX_RETRY_MILLIS = TimeUnit.MILLISECONDS
				.convert(interval, unit);
	}

	static {
		DatabaseRetryManager.addFrameworkExceptionClasses(
			TransactionException.class,
			JDBCConnectionException.class,
			LockAcquisitionException.class,
			HibernateException.class
		);
	}
	
	private SessionHolder sessionHolder;
	private long maxRetryMillis;

	public DatabaseRetryManager(SessionHolder sessionHolder) {
		this.sessionHolder = sessionHolder;
		maxRetryMillis = DEFAULT_MAX_RETRY_MILLIS;
	}

	@Override
	public void executeInTransaction(DatabaseOperation... databaseOperations) {
		long start = System.currentTimeMillis();

		for (;;) {
			Session session = null;
			try {
				session = this.sessionHolder.getSession();
				executeTransaction(session, databaseOperations);
				this.sessionHolder.returnSession(session);
				return;
			} catch (Exception e) {
				this.sessionHolder.forceSessionClose(session);

				if (!isRetryable(e, databaseOperations)) {
					throw new DNEException("Database exception not retryable.", e);
				}

				if (System.currentTimeMillis() > start + maxRetryMillis) {
					throw new DNEException("Database operation retries timed out.", e);
				}
			}
		}
	}

	private void executeTransaction(Session session,
			DatabaseOperation... databaseOperations) throws Exception {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			for (DatabaseOperation operation : databaseOperations) {
				operation.execute(session);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw e;
		}
	}

	private boolean isRetryable(Exception e,
			DatabaseOperation... databaseOperations) {
		for (Class<?> clazz : FRAMEWORK_EXCEPTION_CLASSES) {
			if (clazz.isAssignableFrom(e.getClass())) {
				return true;
			}
		}

		for (DatabaseOperation operation : databaseOperations) {
			if (operation instanceof RetryableDatabaseOperation) {
				if (((RetryableDatabaseOperation) operation).isRetryable(e)) {
					return true;
				}
			}
		}

		return false;
	}

}
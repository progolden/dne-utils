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

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;

/**
 * Abstract DAO declaration.
 * @author Renato R. R. de Oliveira
 *
 */
public interface DAO extends SessionHolder {
	
	public void persist(EntityIF entity);
	public void delete(EntityIF entity);
	public Criteria newCriteria(Class<? extends EntityIF> clazz);
	public <E> E uniqueByCriteria(Criteria criteria, Class<E> clazz);
	public <E> List<E> findByCriteria(Criteria criteria, Class<E> clazz);
	public <E> E exists(Class<E> clazz, Serializable id);
	public SQLQuery newSQLQuery(String sql);
}

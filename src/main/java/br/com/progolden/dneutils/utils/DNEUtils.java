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

import org.hibernate.SessionFactory;

import br.com.progolden.dneutils.abstractions.GenericDAO;
import br.com.progolden.dneutils.model.Bairro;

public class DNEUtils {

	private final SessionFactory factory;
	
	@Deprecated
	public DNEUtils() {
		this.factory = DNEHibernateSessionFactory.getInstance();
	}

	public DNEUtils(SessionFactory factory) {
		this.factory = factory;
	}
	
	public boolean validarCEP(String cep) {
		if (cep == null)
			return false;
		cep = cep
			.replaceAll("-", "")
			.replaceAll("\\.", "")
			.replaceAll(",", "")
			.replaceAll("/", "")
			.trim()
		;
		if (cep.length() != 8)
			return false;
		
		GenericDAO dao = new GenericDAO(this.factory);
		dao.newCriteria(Bairro.class);
		
		return false;
	}
}

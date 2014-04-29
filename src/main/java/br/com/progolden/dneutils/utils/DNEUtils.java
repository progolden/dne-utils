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

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.progolden.dneutils.abstractions.EntityIF;
import br.com.progolden.dneutils.abstractions.GenericDAO;
import br.com.progolden.dneutils.model.CaixaPostalComunitaria;
import br.com.progolden.dneutils.model.ClienteGrande;
import br.com.progolden.dneutils.model.Localidade;
import br.com.progolden.dneutils.model.Logradouro;
import br.com.progolden.dneutils.model.UnidadeOperacional;

public class DNEUtils {

	private final SessionFactory factory;
	
	public DNEUtils(SessionFactory factory) {
		this.factory = factory;
	}
	
	public CEPStatus validarCEP(String cep) {
		if (cep == null)
			return CEPStatus.INEXISTENTE;
		cep = cep
			.replaceAll("-", "")
			.replaceAll("\\.", "")
			.replaceAll(",", "")
			.replaceAll("/", "")
			.trim()
		;
		if (cep.length() != 8)
			return CEPStatus.INEXISTENTE;
		
		if (this.factory != null) {
			GenericDAO dao = new GenericDAO(this.factory);
			Long count;
			
			// Buscando CEP de Localidade.
			count = this.countByCEP(cep, dao, Localidade.class);
			if (count > 0L)
				return CEPStatus.LOCALIDADE;
	
			// Buscando CEP de Logradouro.
			count = this.countByCEP(cep, dao, Logradouro.class);
			if (count > 0L)
				return CEPStatus.LOGRADOURO;
	
			// Buscando CEP de Grande Usuario.
			count = this.countByCEP(cep, dao, ClienteGrande.class);
			if (count > 0L)
				return CEPStatus.CLIENTE_GRANDE;
	
			// Buscando CEP de Unidade Operacional.
			count = this.countByCEP(cep, dao, UnidadeOperacional.class);
			if (count > 0L)
				return CEPStatus.UNIDADE_OPERACIONAL;
	
			// Buscando CEP de Caixa Postal Comunitaria.
			count = this.countByCEP(cep, dao, CaixaPostalComunitaria.class);
			if (count > 0L)
				return CEPStatus.CAIXA_POSTAL_COMUNITARIA;
	
			// O CEP nao existe.
			return CEPStatus.INEXISTENTE;
		} else {
			// Se a conexão com o banco estiver indisponivel
			return CEPStatus.LOCALIDADE;
		}
	}

	private Long countByCEP(String cep, GenericDAO dao, Class<? extends EntityIF> entityClass) {
		Criteria criteria;
		Long count;
		criteria = dao.newCriteria(entityClass);
		criteria.add(Restrictions.eq("cep", cep));
		criteria.setProjection(Projections.rowCount());
		count = dao.uniqueByCriteria(criteria, Long.class);
		return count;
	}
}

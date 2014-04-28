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
package br.com.progolden.dneutils.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.progolden.dneutils.abstractions.EntityIF;

@Entity(name="dne_faixa_cpc")
@Table(name="dne_faixa_cpc")
public class FaixaCaixaPostalComunitaria implements EntityIF {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name="cpc_nu")
	@ManyToOne(targetEntity=CaixaPostalComunitaria.class, fetch=FetchType.EAGER, optional=false)
	private CaixaPostalComunitaria caixaPostal;

	@Id
	@Column(name="cpc_inicial", length=6, nullable=false)
	private String numeroInicial;

	@Column(name="cpc_final", length=8, nullable=false)
	private String numeroFinal;
	
	public FaixaCaixaPostalComunitaria() {}

	public CaixaPostalComunitaria getCaixaPostal() {
		return caixaPostal;
	}

	public void setCaixaPostal(CaixaPostalComunitaria caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	public String getNumeroInicial() {
		return numeroInicial;
	}

	public void setNumeroInicial(String numeroInicial) {
		this.numeroInicial = numeroInicial;
	}

	public String getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(String numeroFinal) {
		this.numeroFinal = numeroFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((caixaPostal == null) ? 0 : caixaPostal.hashCode());
		result = prime * result
				+ ((numeroInicial == null) ? 0 : numeroInicial.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FaixaCaixaPostalComunitaria other = (FaixaCaixaPostalComunitaria) obj;
		if (caixaPostal == null) {
			if (other.caixaPostal != null)
				return false;
		} else if (!caixaPostal.equals(other.caixaPostal))
			return false;
		if (numeroInicial == null) {
			if (other.numeroInicial != null)
				return false;
		} else if (!numeroInicial.equals(other.numeroInicial))
			return false;
		return true;
	}

}

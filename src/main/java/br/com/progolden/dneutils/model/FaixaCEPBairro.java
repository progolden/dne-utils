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

@Entity(name="dne_faixa_bairro")
@Table(name="dne_faixa_bairro")
public class FaixaCEPBairro implements EntityIF {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name="bai_nu")
	@ManyToOne(targetEntity=Bairro.class, fetch=FetchType.EAGER, optional=false)
	private Bairro bairro;
	
	@Id
	@Column(name="fcb_cep_ini", length=8, nullable=false)
	private String cepInicial;

	@Column(name="fcb_cep_fim", length=8, nullable=true)
	private String cepFinal;
	
	public FaixaCEPBairro() {}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getCepInicial() {
		return cepInicial;
	}

	public void setCepInicial(String cepInicial) {
		this.cepInicial = cepInicial;
	}

	public String getCepFinal() {
		return cepFinal;
	}

	public void setCepFinal(String cepFinal) {
		this.cepFinal = cepFinal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result
				+ ((cepInicial == null) ? 0 : cepInicial.hashCode());
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
		FaixaCEPBairro other = (FaixaCEPBairro) obj;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cepInicial == null) {
			if (other.cepInicial != null)
				return false;
		} else if (!cepInicial.equals(other.cepInicial))
			return false;
		return true;
	}

}

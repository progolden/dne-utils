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

@Entity(name="dne_var_log")
@Table(name="dne_var_log")
public class VariacaoLogradouro implements EntityIF {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name="log_nu")
	@ManyToOne(targetEntity=Logradouro.class, fetch=FetchType.EAGER, optional=false)
	private Logradouro logradouro;

	@Id
	@Column(name="vlo_nu", nullable=false)
	private Long ordem;

	@Column(name="tlo_tx", length=36, nullable=false)
	private String tipo;

	@Column(name="vlo_tx", length=150, nullable=false)
	private String denominacao;
	
	
	public VariacaoLogradouro() {}


	public Logradouro getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}


	public Long getOrdem() {
		return ordem;
	}


	public void setOrdem(Long ordem) {
		this.ordem = ordem;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDenominacao() {
		return denominacao;
	}


	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((denominacao == null) ? 0 : denominacao.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((ordem == null) ? 0 : ordem.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		VariacaoLogradouro other = (VariacaoLogradouro) obj;
		if (denominacao == null) {
			if (other.denominacao != null)
				return false;
		} else if (!denominacao.equals(other.denominacao))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (ordem == null) {
			if (other.ordem != null)
				return false;
		} else if (!ordem.equals(other.ordem))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}

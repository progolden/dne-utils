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

@Entity(name="dne_num_sec")
@Table(name="dne_num_sec")
public class FaixaSeccionamento implements EntityIF {

	private static final long serialVersionUID = 1L;

	public static final char PARIDADE_AMBOS = 'A';
	public static final char PARIDADE_PAR = 'P';
	public static final char PARIDADE_IMPAR = 'I';
	public static final char PARIDADE_DIREITO = 'D';
	public static final char PARIDADE_ESQUERDO = 'E';
	
	@Id
	@JoinColumn(name="log_nu")
	@ManyToOne(targetEntity=Logradouro.class, fetch=FetchType.EAGER, optional=false)
	private Logradouro logradouro;

	@Column(name="sec_nu_ini", length=10, nullable=false)
	private String numeroInicial;

	@Column(name="sec_nu_fim", length=10, nullable=false)
	private String numeroFinal;

	/** Paridade/Lado */
	@Column(name="sec_in_lado", nullable=false)
	private char paridade;
	
	public FaixaSeccionamento() {}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
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

	public char getParidade() {
		return paridade;
	}

	public void setParidade(char paridade) {
		this.paridade = paridade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result
				+ ((numeroFinal == null) ? 0 : numeroFinal.hashCode());
		result = prime * result
				+ ((numeroInicial == null) ? 0 : numeroInicial.hashCode());
		result = prime * result + paridade;
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
		FaixaSeccionamento other = (FaixaSeccionamento) obj;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		if (numeroFinal == null) {
			if (other.numeroFinal != null)
				return false;
		} else if (!numeroFinal.equals(other.numeroFinal))
			return false;
		if (numeroInicial == null) {
			if (other.numeroInicial != null)
				return false;
		} else if (!numeroInicial.equals(other.numeroInicial))
			return false;
		if (paridade != other.paridade)
			return false;
		return true;
	}

}

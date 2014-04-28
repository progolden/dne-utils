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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="dne_faixa_uop")
@Table(name="dne_faixa_uop")
public class FaixaCaixaPostalUOP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@JoinColumn(name="uop_nu")
	@ManyToOne(targetEntity=UnidadeOperacional.class, fetch=FetchType.EAGER, optional=false)
	private UnidadeOperacional unidadeOperacional;

	@Id
	@Column(name="fnc_inicial", nullable=false)
	private Long numeroInicial;

	@Column(name="fnc_final", nullable=false)
	private Long numeroFinal;
	
	public FaixaCaixaPostalUOP() {}

	public UnidadeOperacional getUnidadeOperacional() {
		return unidadeOperacional;
	}

	public void setUnidadeOperacional(UnidadeOperacional unidadeOperacional) {
		this.unidadeOperacional = unidadeOperacional;
	}

	public Long getNumeroInicial() {
		return numeroInicial;
	}

	public void setNumeroInicial(Long numeroInicial) {
		this.numeroInicial = numeroInicial;
	}

	public Long getNumeroFinal() {
		return numeroFinal;
	}

	public void setNumeroFinal(Long numeroFinal) {
		this.numeroFinal = numeroFinal;
	}

}

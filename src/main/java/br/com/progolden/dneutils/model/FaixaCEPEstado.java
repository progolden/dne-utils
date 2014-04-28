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
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="dne_faixa_uf")
@Table(name="dne_faixa_uf")
public class FaixaCEPEstado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ufe_sg", length=2, nullable=false)
	private String estado;

	@Id
	@Column(name="ufe_cep_ini", length=8, nullable=false)
	private String cepInicial;

	@Column(name="ufe_cep_fim", length=8, nullable=false)
	private String cepFinal;
	
	public FaixaCEPEstado() {}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

}

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

@Entity(name="dne_ect_pais")
@Table(name="dne_ect_pais")
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pai_sg", length=2, nullable=false)
	private String sigla;
	
	@Column(name="pai_sg_alternativa", length=3, nullable=false)
	private String siglaAlternativa;

	@Column(name="pai_no_portugues", length=72, nullable=false)
	private String nome;

	@Column(name="pai_no_ingles", length=72, nullable=false)
	private String nomeIngles;

	@Column(name="pai_no_frances", length=72, nullable=false)
	private String nomeFrances;

	@Column(name="pai_abreviatura", length=36, nullable=false)
	private String abreviatura;
	
	public Pais() {}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSiglaAlternativa() {
		return siglaAlternativa;
	}

	public void setSiglaAlternativa(String siglaAlternativa) {
		this.siglaAlternativa = siglaAlternativa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeIngles() {
		return nomeIngles;
	}

	public void setNomeIngles(String nomeIngles) {
		this.nomeIngles = nomeIngles;
	}

	public String getNomeFrances() {
		return nomeFrances;
	}

	public void setNomeFrances(String nomeFrances) {
		this.nomeFrances = nomeFrances;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

}

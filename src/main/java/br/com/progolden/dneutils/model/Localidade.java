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

@Entity(name="dne_localidade")
@Table(name="dne_localidade")
public class Localidade implements EntityIF {

	private static final long serialVersionUID = 1L;

	public static final int SITUACAO_NAO_CODIFICADA = 0;
	public static final int SITUACAO_CODIFICADA = 1;
	public static final int SITUACAO_DISTRITO_CODIFICADO = 2;

	public static final char TIPO_DISTRITO = 'D';
	public static final char TIPO_MUNICIPIO = 'M';
	public static final char TIPO_POVOADO = 'P';
	
	@Id
	@Column(name="loc_nu", nullable=false)
	private Long id;

	@Column(name="ufe_sg", length=2, nullable=false)
	private String estado;

	@Column(name="loc_no", length=72, nullable=false)
	private String nome;

	@Column(name="loc_no_abrev", length=36, nullable=true)
	private String abreviacao;

	@Column(name="cep", length=8, nullable=true)
	private String cep;

	@Column(name="loc_in_sit", length=1, nullable=false)
	private int situacao;

	@Column(name="loc_in_tipo_loc", length=1, nullable=false)
	private char tipo;

	@JoinColumn(name="loc_nu_sub")
	@ManyToOne(targetEntity=Localidade.class, fetch=FetchType.EAGER, optional=true)
	private Localidade subordinacao;

	@Column(name="mun_nu", length=7, nullable=true)
	private String municipio;
	
	public Localidade() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviacao() {
		return abreviacao;
	}

	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public Localidade getSubordinacao() {
		return subordinacao;
	}

	public void setSubordinacao(Localidade subordinacao) {
		this.subordinacao = subordinacao;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((subordinacao == null) ? 0 : subordinacao.hashCode());
		result = prime * result + tipo;
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
		Localidade other = (Localidade) obj;
		if (cep == null) {
			if (other.cep != null)
				return false;
		} else if (!cep.equals(other.cep))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subordinacao == null) {
			if (other.subordinacao != null)
				return false;
		} else if (!subordinacao.equals(other.subordinacao))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}

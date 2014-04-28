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

@Entity(name="dne_grande_usuario")
@Table(name="dne_grande_usuario")
public class ClienteGrande implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="gru_nu", nullable=false)
	private Long id;

	@Column(name="ufe_sg", length=2, nullable=false)
	private String estado;

	@Column(name="gru_no", length=72, nullable=false)
	private String nome;

	@Column(name="gru_endereco", length=100, nullable=false)
	private String endereco;

	@Column(name="cep", length=8, nullable=false)
	private String cep;

	@Column(name="gru_no_abrev", length=72, nullable=true)
	private String abreviatura;

	@JoinColumn(name="loc_nu")
	@ManyToOne(targetEntity=Localidade.class, fetch=FetchType.EAGER, optional=false)
	private Localidade localidade;

	@JoinColumn(name="bai_nu")
	@ManyToOne(targetEntity=Bairro.class, fetch=FetchType.EAGER, optional=false)
	private Bairro bairro;

	@JoinColumn(name="log_nu")
	@ManyToOne(targetEntity=Logradouro.class, fetch=FetchType.EAGER, optional=true)
	private Logradouro logradouro;
	
	public ClienteGrande() {}

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public Localidade getLocalidade() {
		return localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

}

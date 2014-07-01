package br.com.progolden.dneutils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.progolden.dneutils.abstractions.GenericDAO;
import br.com.progolden.dneutils.utils.CEPStatus;
import br.com.progolden.dneutils.utils.DNEUtils;

public class DNEUtilsTest {

	private DNEUtils utils;
	
	@Before
	public void setupTests() {
		this.utils = new DNEUtils(new GenericDAO(HibernateSessionFactory.getInstance()));
	}
	
	@After
	public void finalizeTests() {
		this.utils = null;
		System.gc();
	}
	
	
	@Test
	public void validCEPTest() {
		CEPStatus status;
		status = this.utils.validarCEP("37200-000");
		assertEquals("O CEP de Localidade nao foi encontrado.",
				status, CEPStatus.LOCALIDADE);
	
		status = this.utils.validarCEP("\t37.200-000 ");
		assertEquals("O CEP de Localidade nao foi encontrado.",
				status, CEPStatus.LOCALIDADE);
	
		status = this.utils.validarCEP("01.001-010");
		assertEquals("O CEP de Logradouro nao foi encontrado.",
				status, CEPStatus.LOGRADOURO);

		status = this.utils.validarCEP("69.900-904");
		assertEquals("O CEP de Grande Usuario nao foi encontrado.",
				status, CEPStatus.CLIENTE_GRANDE);

		status = this.utils.validarCEP("69.900-970");
		assertEquals("O CEP de Unidade Operacional nao foi encontrado.",
				status, CEPStatus.UNIDADE_OPERACIONAL);

		status = this.utils.validarCEP("42.800-990");
		assertEquals("O CEP de Caixa Postal Comunitaria nao foi encontrado.",
				status, CEPStatus.CAIXA_POSTAL_COMUNITARIA);
	}
	
	@Test
	public void invalidCEPTest() {
		CEPStatus status;
		
		status = this.utils.validarCEP(null);
		assertEquals("O CEP nulo foi encontrado.", status, CEPStatus.INEXISTENTE);

		status = this.utils.validarCEP("");
		assertEquals("O CEP vazio foi encontrado.", status, CEPStatus.INEXISTENTE);

		status = this.utils.validarCEP("35.999-.00");
		assertEquals("O CEP invalido foi encontrado.", status, CEPStatus.INEXISTENTE);

		status = this.utils.validarCEP("99999-999");
		assertEquals("O CEP nao existente foi encontrado.", status, CEPStatus.INEXISTENTE);
	}

}

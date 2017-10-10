/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import org.junit.Assert;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Financiamento;

/**
 * Teste para o {@link DaoFinanciamento}
 */
public class TesteDaoFinanciamento extends TesteDaoAbstrato<Financiamento> {

	@Override
	protected void alterarObjeto(Financiamento t) {
		t.setParcelasFaltantes(5);
	}

	@Override
	protected void verificarAlteracaoObjeto(Financiamento t, Financiamento t2) {
		Assert.assertEquals(t.getParcelasFaltantes(), t2.getParcelasFaltantes());
	}

}

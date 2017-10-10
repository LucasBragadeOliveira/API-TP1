/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import org.junit.Assert;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Poupanca;

/**
 * Teste para o {@link DaoPoupanca}
 */
public class TesteDaoPoupanca extends TesteDaoAbstrato<Poupanca> {

	@Override
	protected void alterarObjeto(Poupanca t) {
		t.setAniversario(5);
	}

	@Override
	protected void verificarAlteracaoObjeto(Poupanca t, Poupanca t2) {
		Assert.assertEquals(t.getAniversario(), t2.getAniversario());
	}

}

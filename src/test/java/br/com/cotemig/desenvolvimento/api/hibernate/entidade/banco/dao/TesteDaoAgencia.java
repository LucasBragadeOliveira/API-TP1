/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import org.junit.Assert;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;

/**
 * Classe responsável por realizar o teste de unidade
 * na classe {@link DaoAgencia}
 */

public class TesteDaoAgencia extends TesteDaoAbstrato<Agencia> {

	@Override
	protected void alterarObjeto(Agencia t) {
		t.setCodigo(20);
	}

	@Override
	protected void verificarAlteracaoObjeto(Agencia t, Agencia t2) {
		Assert.assertEquals(t.getCodigo(), t2.getCodigo());
	}

}

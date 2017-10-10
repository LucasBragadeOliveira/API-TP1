/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.PovoadorAgencia;

/**
 * Classe responsável por realizar o teste de unidade
 * na classe {@link DaoBanco}
 */

public class TesteDaoBanco extends TesteDaoAbstrato<Banco> {
	
	@Autowired
	private DaoBanco daoBanco;
	
	@Autowired
	private PovoadorAgencia pvAgencia;
	
	@Override
	protected void alterarObjeto(Banco t) {
		t.setNome("outro nome");
	}

	@Override
	protected void verificarAlteracaoObjeto(Banco t, Banco t2) {
		Assert.assertEquals(t.getNome(), t2.getNome());
	}
	
	/**
	 * Método responsável por testar a undide
	 * <code>obterBancoComAgencia</code>
	 */
	@Test
	public void obterBancoComAgencia() {
		//setup
		Agencia a1 = pvAgencia.criarESalvarObjeto();
		Banco b1 = a1.getBanco();
		a1.setCodigo(123);
		daoBanco.salvar(b1);
		
		Agencia a2 = pvAgencia.criarESalvarObjeto();
		Banco b2 = a2.getBanco();
		a2.setCodigo(123);
		daoBanco.salvar(b2);
		
		Agencia a3 = pvAgencia.criarESalvarObjeto();
		Banco b3 = a3.getBanco();
		a3.setCodigo(321);
		daoBanco.salvar(b3);
		
		//execução
		List<Banco> bancos = daoBanco.obterBancoComAgencia(123);
		
		//asserts
		Assert.assertTrue(bancos.contains(b1));
		Assert.assertTrue(bancos.contains(b2));
		Assert.assertFalse(bancos.contains(b3));
	}
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.ContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.PovoadorContaCorrente;

/**
 * Teste para o {@link DaoContaCorrente}
 */
public class TesteDaoContaCorrente extends TesteDaoAbstrato<ContaCorrente> {
	
	@Autowired
	private DaoContaCorrente daoContaCorrente;
	
	@Autowired
	private PovoadorContaCorrente pvContaCorrente;
	
	@Override
	protected void alterarObjeto(ContaCorrente t) {
		t.setDigito("B");
	}

	@Override
	protected void verificarAlteracaoObjeto(ContaCorrente t, ContaCorrente t2) {
		Assert.assertEquals(t.getDigito(), t2.getDigito());
	}
	
	/**
	 * Método responsável por testar a unidade
	 * <code>obterContasValorIgual</code>. 
	 */
	@Test
	public void testarObterContasValorIgual() {
		//setup
		ContaCorrente cc = pvContaCorrente.criarESalvarObjeto();
		cc.setSaldo(15000d);
		
		//execução
		List<ContaCorrente> lista = 
				daoContaCorrente.obterContasValorIgual(15000d);
		
		//asserts
		Assert.assertTrue(lista.contains(cc));
	}
	
	/**
	 * Método responsável por testar a unidade
	 * <code>obterContaComMaiorSaldo</code>
	 */
	@Test
	public void testarObterContaComMaiorSaldo (){
		//setup
		ContaCorrente cc1 = pvContaCorrente.criarESalvarObjeto();
		cc1.setSaldo(1000d);
		ContaCorrente cc2 = pvContaCorrente.criarESalvarObjeto();
		cc2.setSaldo(2000d);
		ContaCorrente cc3 = pvContaCorrente.criarESalvarObjeto();
		cc3.setSaldo(3000d);
		
		//execução
		Double valor = daoContaCorrente.obterContaComMaiorSaldo();
		Assert.assertEquals(3000d, valor, 0);
	}
	
	/**
	 * Método responsável por testar a unidade
	 * <code>obterContaComMaiorSaldo</code>
	 */
	@Test
	public void testarObterContaComMaiorSaldoPorNumero (){
		//setup
		ContaCorrente cc1 = pvContaCorrente.criarESalvarObjeto();
		cc1.setNumero(12345);
		cc1.setSaldo(1000d);
		ContaCorrente cc2 = pvContaCorrente.criarESalvarObjeto();
		cc2.setSaldo(2000d);
		cc2.setNumero(12345);
		
		ContaCorrente cc3 = pvContaCorrente.criarESalvarObjeto();
		cc3.setSaldo(3000d);
		cc3.setNumero(54321);

		
		//execução
		List<Object> lista = daoContaCorrente.obterContaComMaiorSaldoPorNumero();
		
		//asserts
		Object[] o = (Object[])lista.get(0);
		Assert.assertEquals(2000d, o[0]);
		Assert.assertEquals(12345, o[1]);
		
		o = (Object[])lista.get(1);
		Assert.assertEquals(3000d, o[0]);
		Assert.assertEquals(54321, o[1]);
	}

}

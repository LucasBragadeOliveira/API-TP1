/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.TesteDaoAbstrato;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.PovoadorCliente;

/**
 * Classe responsável por realizar o teste de unidade
 * na classe {@link DaoCliente}
 */
public class TesteDaoCliente extends TesteDaoAbstrato<Cliente> {
	
	@Autowired
	private PovoadorCliente pvCliente;
	
	@Autowired
	private DaoCliente daoCliente;
	
	@Override
	protected void alterarObjeto(Cliente t) {
		t.setNome("outro nome");
	}

	@Override
	protected void verificarAlteracaoObjeto(Cliente t, Cliente t2) {
		Assert.assertEquals(t.getNome(), t2.getNome());
	}
	
	/**
	 * Método responsável por testar a unidade
	 * <code>obterOrdenadoPorNome</code>
	 */
	@Test
	public void testarObterOrdenadoPorNome() {
		//execução
		List<Cliente> clientes = daoCliente.obterTodos();
		//assert (verifica que inicialmente não há clientes cadastrados)
		Assert.assertTrue(clientes.isEmpty());
		
		//setup
		Cliente c1 = pvCliente.criarESalvarObjeto();
		c1.setNome("Joel");
		daoCliente.salvar(c1);
		Cliente c2 = pvCliente.criarESalvarObjeto();
		c2.setNome("Maye");
		daoCliente.salvar(c2);
		Cliente c3 = pvCliente.criarESalvarObjeto();
		c3.setNome("Kaylene");
		daoCliente.salvar(c3);
		Cliente c4 = pvCliente.criarESalvarObjeto();
		c4.setNome("Valentin");
		daoCliente.salvar(c4);
		Cliente c5 = pvCliente.criarESalvarObjeto();
		c5.setNome("Margherita");
		daoCliente.salvar(c5);
		
		//execução
		clientes = daoCliente.obterOrdenadoPorNome();
		
		//assert
		Assert.assertEquals(clientes.get(0).getNome(), "Joel");
		Assert.assertEquals(clientes.get(1).getNome(), "Kaylene");
		Assert.assertEquals(clientes.get(2).getNome(), "Margherita");
		Assert.assertEquals(clientes.get(3).getNome(), "Maye");
		Assert.assertEquals(clientes.get(4).getNome(), "Valentin");
	}
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Classe responsável por realizar testes básicos
 * aos daos do projeto.
 */
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
@ComponentScan(basePackages = {"br.com.cotemig.desenvolvimento.api.hibernate.entidade"})
public abstract class TesteDaoAbstrato<T extends ObjetoPersistente> {
	
	@Autowired
	private DaoAbstrato<T> daoAbstrato;
	
	@Autowired
	private PovoadorObjetoPersistente<T> povoador;
	
	/**
	 * Método responsável por realizar testes básicos
	 * aos daos do projeto.
	 */
	@Test
	public void realizarTestesBasicos() {
		T t = povoador.criarObjeto(true);
		
		daoAbstrato.salvar(t);
		Assert.assertNotNull(t.getId());
		
		alterarObjeto(t);
		daoAbstrato.salvar(t);
		
		T t2 = daoAbstrato.procurarPorId(t.getId());
		verificarAlteracaoObjeto(t, t2);
		
		daoAbstrato.excluir(t);
		T t3 = daoAbstrato.procurarPorId(t.getId());
		Assert.assertNull(t3);
	}
	
	/**
	 * Método responsável por alterar um objeto.
	 * @param t T
	 */
	protected abstract void alterarObjeto(T t);
	
	/**
	 * Método responsável por verificar se a propriedade
	 * alterada foi persistida.
	 * @param t T
	 */
	protected abstract void verificarAlteracaoObjeto(T t, T t2);

}

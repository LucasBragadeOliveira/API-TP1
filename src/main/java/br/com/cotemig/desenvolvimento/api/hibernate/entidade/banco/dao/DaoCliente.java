/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;



import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para a entidade {@link Cliente}
 */
@Transactional
@Component
public class DaoCliente extends DaoAbstrato<Cliente> {
	
	/**
	 * Obtém os clientes ordenados pelo nome.
	 * @return {@link List}
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> obterOrdenadoPorNome() {
		Criteria c = obterCriteria();
		c.addOrder(Order.asc("nome"));
		return c.list();
	}
	
	/**
	 * Obtém cliente através de seu CPF
	 * @return {@link Cliente} ou <code>null</code>
	 */
	public Cliente obterClientePorCpf(String cpf) {
		Criteria c = obterCriteria();
		//c.createAlias("cpf", "c");
		c.add(Restrictions.like("cpf.cpf", cpf));
		
		return (Cliente)c.uniqueResult();
	}
}

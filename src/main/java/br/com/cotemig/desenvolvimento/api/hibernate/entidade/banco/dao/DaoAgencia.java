/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para entidade {@link Agencia}
 */
@Transactional
@Component
public class DaoAgencia extends DaoAbstrato<Agencia> {

	/**
	 * Método responsável por obter um banco através de seu código
	 * @param codigo {@link Integer}
	 * @param codigoBanco {@link Integer}
	 * @return {@link Banco} ou <code>null</code> caso não encontre.
	 */
	public Agencia procurarPorCodigoEBanco(Integer codigo, Integer codigoBanco) {
		Criteria c = obterCriteria();
		c.add(Restrictions.eq("codigo", codigo));
		c.createAlias("banco", "b");
		c.add(Restrictions.eq("b.codigo", codigoBanco));
		return (Agencia)c.uniqueResult();
	}
}

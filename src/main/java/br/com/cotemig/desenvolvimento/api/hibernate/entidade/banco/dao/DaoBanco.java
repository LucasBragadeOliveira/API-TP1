/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para entidade {@link Banco}
 */
@Transactional
@Component
public class DaoBanco extends DaoAbstrato<Banco> {
	
	/**
	 * Método responsável por recuperar bancos por um determinado
	 * código de agência.
	 * @param codigo {@link Integer}
	 * @return {@link List}
	 */
	@SuppressWarnings("unchecked")
	public List<Banco> obterBancoComAgencia(Integer codigo) {
		Criteria c = obterCriteria();
		c.createAlias("agencias", "a");
		c.add(Restrictions.eq("a.codigo", codigo));
		return c.list();
	}
	
	/**
	 * Método responsável por obter um banco através de seu código
	 * @param codigo {@link Integer}
	 * @return {@link Banco} ou <code>null</code> caso não encontre.
	 */
	public Banco procurarPorCodigo(Integer codigo) {
		Criteria c = obterCriteria();
		c.add(Restrictions.eq("codigo", codigo));
		return (Banco)c.uniqueResult();
	}
	
}

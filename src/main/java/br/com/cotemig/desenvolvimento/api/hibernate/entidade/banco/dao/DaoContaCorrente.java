/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.ContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para entidade {@link ContaCorrente}
 */
@Transactional
@Component
public class DaoContaCorrente extends DaoAbstrato<ContaCorrente> {
	
	/**
	 * Método responsável por obter as contas que possuem
	 * saldo com o valor igual ao do parâmetro
	 * @param valorIgual {@link Double}
	 * @return {@link List}
	 */
	@SuppressWarnings("unchecked")
	public List<ContaCorrente> obterContasValorIgual(Double valorIgual) {
		Criteria c = obterCriteria();
		c.add(Restrictions.eq("saldo", valorIgual));
		return c.list();
	}
	
	/**
	 * Método responsável por obter a conta com o maior saldo
	 * @return Lista com o a conta que possui maior saldo
	 */
	public Double obterContaComMaiorSaldo() {
		Criteria c = obterCriteria();
		c.setProjection(Projections.max("saldo"));
		return (Double)c.uniqueResult();
	}
	
	/**
	 * Método responsável por obter a conta com o maior saldo por número
	 * @return Lista com a conta que possui maior saldo por número
	 */
	@SuppressWarnings("unchecked")
	public List<Object> obterContaComMaiorSaldoPorNumero() {
		Criteria c = obterCriteria();
		c.setProjection(
			Projections.projectionList().
				add(Projections.alias(Projections.max("saldo"), "saldo")).
				add(Projections.alias(Projections.groupProperty("numero"), "numero")));
		c.addOrder(Order.asc("saldo"));
		
		//A lista será composta por um objeto com 2 campos:
		// [0] o saldo
		// [1] o número da conta
		return c.list();
	}

}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Financiamento;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para a entidade {@link Financiamento}
 */
@Transactional
@Component
public class DaoFinanciamento extends DaoAbstrato<Financiamento> {

}

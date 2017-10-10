/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Poupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para entidade {@link Poupanca}
 */
@Transactional
@Component
public class DaoPoupanca extends DaoAbstrato<Poupanca> {

}

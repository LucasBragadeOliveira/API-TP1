/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Endereco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Dao para entidade {@link Endereco}
 */
@Transactional
@Component
public class DaoEndereco extends DaoAbstrato<Endereco> {

}

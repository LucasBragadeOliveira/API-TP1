/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Dao abstrato para o {@link ObjetoPersistente}.
 */
@Component
@Transactional
public abstract class DaoAbstrato<O extends ObjetoPersistente> implements Dao<O> {

	@Autowired
	private EntityManager entityManager;

	/**
	 * {@inheritDoc}
	 */
	public void excluir(O o) {
		o.prepararParaExcluir();
		o.validarAoExcluir();

		entityManager.remove(o);
	}

	/**
	 * Recupera TODOS os objetos
	 * 
	 * @return {@link List}
	 */
	@SuppressWarnings("unchecked")
	public List<O> obterTodos() {
		Criteria c = obterCriteria();

		return c.list();
	}

	public O procurarPorId(Long id) {
		return entityManager.find(getClassePersistente(), id);
	}

	/**
	 * {@inheritDoc}
	 */
	public void salvar(O o) {
		o.prepararParaSalvar();
		o.validarAoSalvar();

		entityManager.persist(o);
	}

	/**
	 * @return {@link Class} Obtem o tipo do parâmetro
	 */
	@SuppressWarnings("unchecked")
	private Class<O> getClassePersistente() {
		Class<O> clazz = (Class<O>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}

	
	/**
	 * Método responsável por obter a {@link Criteria}
	 * @return {@link Criteria}
	 */
	protected Criteria obterCriteria() {
		Session session = entityManager.unwrap(Session.class);
		Criteria c = session.createCriteria(getClassePersistente());
		return c;
	}
}

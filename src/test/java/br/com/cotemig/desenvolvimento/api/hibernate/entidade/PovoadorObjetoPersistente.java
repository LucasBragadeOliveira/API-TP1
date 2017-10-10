/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao.DaoAbstrato;

/**
 * Povoador para a entidade {@link ObjetoPersistente}
 */
@Component
public abstract class PovoadorObjetoPersistente <T extends ObjetoPersistente> {
	
	@Autowired
	private DaoAbstrato<T> daoAbstrato;
	
	/**
	 * Método responsável por criar e salvar um objeto.
	 * @return T
	 */
	public T criarESalvarObjeto() {
		return criarObjeto(true);
	}
	
	/**
	 * Método responsável por criar um objeto.
	 * @param salvar {@link Boolean}
	 * @return T
	 */
	public T criarObjeto(boolean salvar) {
		T t = instanciarObjeto(salvar);
		
		if(salvar) {
			DaoAbstrato<T> dao = daoAbstrato;
			dao.salvar(t);
		}
		
		return t;
	}
	
	/**
	 * Método responsável por instanciar o objeto.
	 * @return T
	 */
	protected abstract T instanciarObjeto(boolean salvar);
}

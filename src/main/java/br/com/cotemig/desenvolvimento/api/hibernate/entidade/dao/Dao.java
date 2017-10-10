/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.dao;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Interface para Dao
 */
public interface Dao<O extends ObjetoPersistente> {
	
	/**
	 * Método responsável por persistir a entidade.
	 * @param o O
	 */	
	public void salvar(O o);
	
	/**
	 * Método responsável por excluir a entidade. 
	 * @param o O
	 */
	public void excluir(O o);
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.excecao;

/**
 * Exceção para o caso de uma entidade não ser encontrada.
 */
public class EntidadeNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public EntidadeNaoEncontradaException(String nomeEntidade,
			Long idEntidade) {
		super("Entidade [" + nomeEntidade + "] com id=["+
			idEntidade+"] não foi encontrada.");
	}
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para a entidade {@link Endereco}
 */
@Component
public class PovoadorEndereco extends PovoadorObjetoPersistente<Endereco> {

	private static int codigo;
	
	@Override
	protected Endereco instanciarObjeto(boolean salvar) {
		Endereco e = new Endereco();
		e.setRua("Rua " + codigo);
		e.setNumero(codigo);
		e.setComplemento("sem complemento");
		e.setCep("11123-050");
		
		codigo ++;
		
		return e;
	}
	
}

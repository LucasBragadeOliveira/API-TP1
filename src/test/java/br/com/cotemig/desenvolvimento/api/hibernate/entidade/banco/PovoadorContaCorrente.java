/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.stereotype.Component;

/**
 * Povoador para a entidade {@link ContaCorrente}
 */
@Component
public class PovoadorContaCorrente extends PovoadorConta<ContaCorrente> {

	@Override
	protected ContaCorrente criarInstancia() {
		return new ContaCorrente();
	}

}

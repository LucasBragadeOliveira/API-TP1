/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.stereotype.Component;

/**
 * Povoador para a entidade {@link Poupanca}
 */
@Component
public class PovoadorPoupanca extends PovoadorConta<Poupanca> {
	
	@Override
	protected Poupanca instanciarObjeto(boolean salvar) {
		Poupanca p = super.instanciarObjeto(salvar);
		p.setAniversario(15);
		
		return p;
	}
	
	@Override
	protected Poupanca criarInstancia() {
		return new Poupanca();
	}

}

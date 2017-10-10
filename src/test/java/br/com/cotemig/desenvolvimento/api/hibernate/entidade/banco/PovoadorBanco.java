/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para a entidade {@link Banco}
 */
@Component
public class PovoadorBanco extends PovoadorObjetoPersistente<Banco> {
	private static int codigo = 1;
	
	@Override
	protected Banco instanciarObjeto(boolean salvar) {
		Banco b = new Banco();
		
		b.setCodigo(codigo);
		String cnpj = FormatadorCPFCNPJ.formatarCnpj(codigo); 
		b.setCnpj(cnpj);
		b.setNome("Banco" + codigo);
		
		codigo ++;
		
		return b;
	}
}

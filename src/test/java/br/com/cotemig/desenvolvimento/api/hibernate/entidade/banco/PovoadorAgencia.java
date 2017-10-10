/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para {@link Agencia}
 */
@Component
public class PovoadorAgencia extends PovoadorObjetoPersistente<Agencia> {
	
	private static int codigo;

	@Autowired
	private PovoadorEndereco pvEndereco;
	
	@Autowired
	private PovoadorBanco pvBanco;
	
	
	@Override
	protected Agencia instanciarObjeto(boolean salvar) {
		Agencia a = new Agencia();
		a.setBanco(pvBanco.criarObjeto(salvar));
		a.setEndereco(pvEndereco.criarObjeto(salvar));
		a.setCodigo(codigo);
		codigo ++;
		
		return a;
	}

}

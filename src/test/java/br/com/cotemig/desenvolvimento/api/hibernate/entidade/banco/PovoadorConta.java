/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para {@link Conta}
 */
@Component
public abstract class PovoadorConta<T extends Conta> extends PovoadorObjetoPersistente<T>{
	
	private static int codigo;
	
	@Autowired
	private PovoadorCliente pvCliente;
	
	@Autowired
	private PovoadorAgencia pvAgencia;
	
	@Override
	protected T instanciarObjeto(boolean salvar) {
		T t = criarInstancia();
		Cliente cliente = pvCliente.criarObjeto(salvar);
		Agencia agencia = pvAgencia.criarObjeto(salvar);
		
		agencia.adicionarConta(t);
		cliente.adicionarConta(t);
		t.setNumero(codigo);
		t.setDigito("X");
		t.setAtiva(true);
		t.setSaldo(0d);
		
		return t;
	}
	
	/**
	 * Cria a instância concreta.
	 * @return T
	 */
	protected abstract T criarInstancia(); 

}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para a entidade {@link Financiamento}
 */
@Component
public class PovoadorFinanciamento extends PovoadorObjetoPersistente<Financiamento> {
	
	@Autowired
	private PovoadorBanco pvBanco;
	
	@Autowired
	private PovoadorCliente pvCliente;
	
	@Override
	protected Financiamento instanciarObjeto(boolean salvar) {
		Financiamento f = new Financiamento();
		
		Banco b = pvBanco.criarObjeto(salvar);
		b.adicionarFinanciamento(f);
		
		Cliente c1 = pvCliente.criarObjeto(salvar);
		c1.adicionarFinanciamento(f);
		f.adicionarCliente(c1);
		
		Cliente c2 = pvCliente.criarObjeto(salvar);
		c2.adicionarFinanciamento(f);
		f.adicionarCliente(c2);
		
		f.setValorTotal(1000d);
		f.setValorAtual(800d);
		f.setParcelasTotais(10);
		f.setParcelasFaltantes(8);
		
		return f;
	}

		
	
}

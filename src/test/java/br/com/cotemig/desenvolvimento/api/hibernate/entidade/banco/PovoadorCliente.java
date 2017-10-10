/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.PovoadorObjetoPersistente;

/**
 * Povoador para a entidade {@link Cliente}
 */
@Component
public class PovoadorCliente extends PovoadorObjetoPersistente<Cliente> {
	private static int codigo = 1;
	
	@Autowired
	private PovoadorEndereco pvEndereco;
	
	@Override
	protected Cliente instanciarObjeto(boolean salvar) {
		
		Cliente c = new Cliente();
		
		c.setNome("Cliente " + codigo);
		c.setEndereco(pvEndereco.criarObjeto(salvar));
		
		CPF cpf = new CPF();
		cpf.setCpf(FormatadorCPFCNPJ.formatarCpf(codigo));
		cpf.setAtivo(true);
		c.setCpf(cpf);
		
		c.setDataNascimento(Calendar.getInstance());
		
		codigo ++;
		
		return c;
	}

}

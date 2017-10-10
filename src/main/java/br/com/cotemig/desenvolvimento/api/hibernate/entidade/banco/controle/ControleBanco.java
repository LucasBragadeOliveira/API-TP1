/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoBanco;

/**
 * Controle para a entidade {@link Banco}
 */
@Component
public class ControleBanco {

	@Autowired
	private DaoBanco daoBanco;
	
	/**
	 * Método responsável por criar um banco
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link Banco}
	 */
	public Banco criar(Integer codigo, String nome, String cnpj){
		Banco b = new Banco();
		b.setCodigo(codigo);
		b.setNome(nome);
		b.setCnpj(cnpj);
		
		daoBanco.salvar(b);
		return b;
	}
	
	/**
	 * Método responsável por retornar todos os bancos.
	 * @return {@link List}
	 */
	public List<Banco> obterTodos() {
		return daoBanco.obterTodos();
	}
	
	/**
	 * Método responsável por retornar um único
	 * banco através de seu id
	 * @param id {@link Long}
	 * @return {@link Banco}
	 */
	public Banco obterPorId(Long id) {
		return daoBanco.procurarPorId(id);
	}
	
	/**
	 * Método responsável por excluir um banco
	 * @param b {@link Banco}
	 */
	public void excluir(Banco b) {
		daoBanco.excluir(b);
	}
	
	/**
	 * @param banco {@link Banco}
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link Banco}
	 */
	public Banco alterarBanco(Banco banco, Integer codigo, String nome, String cnpj) {
		
		if(codigo != null) {
			banco.setCodigo(codigo);
		}
		
		if(nome != null) {
			banco.setNome(nome);
		}
		
		if(cnpj != null) {
			banco.setCnpj(cnpj);
		}
		
		daoBanco.salvar(banco);
		return banco;
	}
	
}

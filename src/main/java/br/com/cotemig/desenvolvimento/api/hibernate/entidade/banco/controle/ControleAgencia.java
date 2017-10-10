/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Endereco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoAgencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoBanco;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

/**
 * Controle para a entidade {@link Banco}
 */
@Component
public class ControleAgencia {

	@Autowired
	private DaoAgencia daoAgencia;
	
	@Autowired
	private DaoBanco daoBanco;
	
	/**
	 * Método responsável por criar uma agência
	 * @param idBanco {@link Long}
	 * @param codigo {@link Integer}
	 * @param rua {@link String}
	 * @param numero {@link Integer}
	 * @param complemento {@link String}
	 * @param cep {@link String}
	 * @return {@link Agencia}
	 */
	public Agencia criar(Long idBanco, 
			Integer codigo, String rua, 
			Integer numero, String complemento, 
			String cep) {
		Banco banco = daoBanco.procurarPorId(idBanco);
		if(banco == null) {
			throw new EntidadeNaoEncontradaException("Banco", idBanco);
		}
		Agencia agencia = new Agencia();
		banco.adicionarAgencia(agencia);
		
		agencia.setCodigo(codigo);
		
		Endereco e = new Endereco();
		e.setRua(rua);
		e.setNumero(numero);
		e.setComplemento(complemento);
		e.setCep(cep);
		agencia.setEndereco(e);
		
		daoAgencia.salvar(agencia);
		
		return agencia;
	}
	
	/**
	 * Método responsável por retornar todas as agências.
	 * @return {@link List}
	 */
	public List<Agencia> obterTodos() {
		return daoAgencia.obterTodos();
	}
	
	/**
	 * Método responsável por retornar uma única
	 * banco através de seu id
	 * @param id {@link Long}
	 * @return {@link Agencia}
	 */
	public Agencia obterPorId(Long id) {
		return daoAgencia.procurarPorId(id);
	}
	
	/**
	 * Método responsável por excluir uma agencia
	 * @param a {@link Banco}
	 */
	public void excluir(Agencia a) {
		daoAgencia.excluir(a);
	}
	
	public Agencia alterarAgencia(Agencia agencia, 
			Integer codigo, String rua, 
			Integer numero, String complemento, 
			String cep) {
		
		if(codigo != null) {
			agencia.setCodigo(codigo);
		}
		
		if(StringUtils.isNotBlank(rua)) {
			agencia.getEndereco().setRua(rua);
		}
		
		if(numero != null) {
			agencia.getEndereco().setNumero(numero);
		}
		
		// aqui não é necessário verificar se
		// o complemento está preenchido
		agencia.getEndereco().setComplemento(complemento);
		
		if(StringUtils.isNotBlank(cep)) {
			agencia.getEndereco().setCep(cep);
		}
		
		daoAgencia.salvar(agencia);
		
		return agencia;
	}
	
}

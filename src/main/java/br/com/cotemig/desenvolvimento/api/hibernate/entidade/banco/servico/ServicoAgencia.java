/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle.ControleAgencia;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

/**
 * Serviço para o banco
 */
@RestController
public class ServicoAgencia {
	
	@Autowired
	private ControleAgencia controleAgencia;
	
	/**
	 * Método responsável por criar uma agência
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@RequestMapping(path="/agencia", method=RequestMethod.PUT)
	public ResponseEntity<Agencia> criarAgencia(
			@RequestParam(name="idBanco", required=true) Long idBanco,
			@RequestParam(name="codigo", required=true) Integer codigo,
			@RequestParam(name="rua", required=true) String rua,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="complemento", required=false) String complemento,
			@RequestParam(name="cep", required=true) String cep){
		
		Agencia agencia = controleAgencia.criar(idBanco, codigo, rua, 
				numero, complemento, cep);
		return new ResponseEntity<>(agencia, HttpStatus.CREATED);
	}
	
	/**
	 * Método responsável por obter todas as agências cadastradas
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/agencia", method=RequestMethod.GET)
	public ResponseEntity<List<Agencia>> obterTodos(){
		List<Agencia> agencias = controleAgencia.obterTodos();
		ResponseEntity<List<Agencia>> r = new ResponseEntity<>(agencias, HttpStatus.OK);
		return r;
	}
	
	/**
	 * Método responsável por obter um banco pelo seu id
	 * @param id {@link Long}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/agencia/{id}", method=RequestMethod.GET)
	public ResponseEntity<Agencia> obterPorId(
			@PathVariable Long id){
		Agencia a = controleAgencia.obterPorId(id);
		if(a != null) {
			ResponseEntity<Agencia> r = new ResponseEntity<>(a, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Agencia> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	/**
	 * Método responsável por excluir uma agência
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/agencia/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Agencia> excluirAgencia(
			@PathVariable Long id) {
		Agencia b = controleAgencia.obterPorId(id);
		if(b != null) {
			controleAgencia.excluir(b);
			ResponseEntity<Agencia> r = new ResponseEntity<>(HttpStatus.OK);
			return r;
		}
		ResponseEntity<Agencia> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;

	}
	
	/**
	 * Método responsável por alterar uma agência
	 * @param id {@link Long}
	 * @param codigo {@link Integer}
	 * @param rua {@link String}
	 * @param numero {@link Integer}
	 * @param complemento {@link String}
	 * @param cep {@link String}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/agencia/{id}", method=RequestMethod.POST)
	public ResponseEntity<Agencia> alterarAgencia(
			@PathVariable Long id,
			@RequestParam(name="codigo", required=true) Integer codigo,
			@RequestParam(name="rua", required=true) String rua,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="complemento", required=false) String complemento,
			@RequestParam(name="cep", required=true) String cep) {
		
		Agencia a = controleAgencia.obterPorId(id);
		if(a != null) {
			controleAgencia.alterarAgencia(a, codigo, rua, numero, complemento, cep);
			ResponseEntity<Agencia> r = new ResponseEntity<>(a, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Agencia> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
}

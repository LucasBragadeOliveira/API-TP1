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

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.ContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle.ControleContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

/**
 * Serviço para o banco
 */
@RestController
public class ServicoContaCorrente {
	
	@Autowired
	private ControleContaCorrente controleContaCorrente;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@RequestMapping(path="/contacorrente", method=RequestMethod.PUT)
	public ResponseEntity<ContaCorrente> criarCC(
			@RequestParam(name="idAgencia", required=true) Long idAgencia,
			@RequestParam(name="idCliente", required=true) Long idCliente,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="digito", required=true) String digito
			){
		ContaCorrente CC = controleContaCorrente.criar(idAgencia, idCliente, numero, digito);
		return new ResponseEntity<>(CC,HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/contacorrente/{id}", method=RequestMethod.GET)
	public ResponseEntity<ContaCorrente> obterPorId(
			@PathVariable Long id){
		ContaCorrente CC = controleContaCorrente.obterPorId(id);
		if(CC != null) {
			ResponseEntity<ContaCorrente> r = new ResponseEntity<>(CC, HttpStatus.OK);
			return r;
		}
		ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/contacorrente", method=RequestMethod.GET)
	public ResponseEntity<List<ContaCorrente>> obterTodos(){
		List<ContaCorrente> CC = controleContaCorrente.obterTodos();
		ResponseEntity<List<ContaCorrente>> r = new ResponseEntity<>(CC, HttpStatus.OK);
		return r;
	}
	
	@RequestMapping(path="/contacorrente/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<ContaCorrente> excluir(
			@PathVariable Long id){
		ContaCorrente CC = controleContaCorrente.obterPorId(id);
		if(CC != null) {
			controleContaCorrente.excluir(CC);
			ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.OK);
			return r;
		}
		ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/contacorrente/{id}/sacar", method=RequestMethod.POST)
	public ResponseEntity<ContaCorrente> saque(
			@PathVariable Long id,
			@RequestParam(name="valor", required=true) Double valor){
		
		ContaCorrente CC = controleContaCorrente.obterPorId(id);
		if(CC != null) {
			controleContaCorrente.saque(CC, valor);
			ResponseEntity<ContaCorrente> r = new ResponseEntity<>(CC, HttpStatus.OK);
			return r;
		}
		ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/contacorrente/{id}/depositar", method=RequestMethod.POST)
	public ResponseEntity<ContaCorrente> deposito(
			@PathVariable Long id,
			@RequestParam(name="valor", required=true) Double valor){
		
		ContaCorrente CC = controleContaCorrente.obterPorId(id);
		if(CC != null) {
			controleContaCorrente.deposito(CC, valor);
			ResponseEntity<ContaCorrente> r = new ResponseEntity<>(CC, HttpStatus.OK);
			return r;
		}
		ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@RequestMapping(path="/contacorrente/{id}", method=RequestMethod.POST)
	public ResponseEntity<ContaCorrente> alterarCC(
			@PathVariable Long id,
			@RequestParam(name="idAgencia", required=true) Long idAgencia,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="digito", required=true) String digito,
			@RequestParam(name="ativa", required=true) Boolean ativa
			){
		
		ContaCorrente CC = controleContaCorrente.obterPorId(id);
		if(CC != null) {
			controleContaCorrente.alterarCC(CC, idAgencia, numero, digito, ativa);
			ResponseEntity<ContaCorrente> r = new ResponseEntity<>(CC, HttpStatus.OK);
			return r;
		}
		ResponseEntity<ContaCorrente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
}

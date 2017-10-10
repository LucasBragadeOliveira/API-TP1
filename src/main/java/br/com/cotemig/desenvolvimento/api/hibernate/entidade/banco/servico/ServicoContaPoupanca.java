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

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Poupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle.ControleContaPoupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

@RestController
public class ServicoContaPoupanca {
	
	@Autowired
	private ControleContaPoupanca controleContaPoupanca;
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@RequestMapping(path="/Poupanca", method=RequestMethod.PUT)
	public ResponseEntity<Poupanca> criarCP(
			@RequestParam(name="idAgencia", required=true) Long idAgencia,
			@RequestParam(name="idCliente", required=true) Long idCliente,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="digito", required=true) String digito,
			@RequestParam(name="aniversario",  required=true) Integer aniversario
			){
		Poupanca CP = controleContaPoupanca.criar(idAgencia, idCliente, numero, digito, aniversario);
		return new ResponseEntity<>(CP,HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/poupanca/{id}", method=RequestMethod.GET)
	public ResponseEntity<Poupanca> obterPorId(
			@PathVariable Long id){
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			ResponseEntity<Poupanca> r = new ResponseEntity<>(CP, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/poupanca", method=RequestMethod.GET)
	public ResponseEntity<List<Poupanca>> obterTodos(){
		List<Poupanca> CP = controleContaPoupanca.obterTodos();
		ResponseEntity<List<Poupanca>> r = new ResponseEntity<>(CP, HttpStatus.OK);
		return r;
	}
	
	@RequestMapping(path="/poupanca/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Poupanca> excluir(
			@PathVariable Long id){
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			controleContaPoupanca.excluir(CP);
			ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/poupanca/{id}/sacar", method=RequestMethod.POST)
	public ResponseEntity<Poupanca> saque(
			@PathVariable Long id,
			@RequestParam(name="valor", required=true) Double valor){
		
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			controleContaPoupanca.saque(CP, valor);
			ResponseEntity<Poupanca> r = new ResponseEntity<>(CP, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/poupanca/{id}/depositar", method=RequestMethod.POST)
	public ResponseEntity<Poupanca> deposito(
			@PathVariable Long id,
			@RequestParam(name="valor", required=true) Double valor){
		
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			controleContaPoupanca.deposito(CP, valor);
			ResponseEntity<Poupanca> r = new ResponseEntity<>(CP, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@RequestMapping(path="/poupanca/{id}/renderJuros", method=RequestMethod.POST)
	public ResponseEntity<Poupanca> renderJuros(
			@PathVariable Long id,
			@RequestParam(name="juros", required=true) Double juros){
		
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			controleContaPoupanca.renderJuros(CP, juros);
			ResponseEntity<Poupanca> r = new ResponseEntity<>(CP, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	@RequestMapping(path="/poupanca/{id}", method=RequestMethod.POST)
	public ResponseEntity<Poupanca> alterarCP(
			@PathVariable Long id,
			@RequestParam(name="idAgencia", required=true) Long idAgencia,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="digito", required=true) String digito,
			@RequestParam(name="ativa", required=true) Boolean ativa,
			@RequestParam(name="aniversario",  required=true) Integer aniversario
			){
		
		Poupanca CP = controleContaPoupanca.obterPorId(id);
		if(CP != null) {
			controleContaPoupanca.alterarCP(CP, idAgencia, numero, digito, ativa,aniversario);
			ResponseEntity<Poupanca> r = new ResponseEntity<>(CP, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Poupanca> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
}

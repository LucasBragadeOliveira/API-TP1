package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle.ControleCliente;

@RestController
public class ServicoCliente {
	
	@Autowired
	private ControleCliente controleCliente;
	
	@RequestMapping(path="/cliente", method=RequestMethod.PUT)
	public ResponseEntity<Cliente> criarCliente(
			@RequestParam(name="nome", required=true) String nome,
			@RequestParam(name="dataNascimento", required=true) String dataNascimento,
			@RequestParam(name="cpf", required=true) String cpf,
			@RequestParam(name="rua", required=true) String rua,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="complemento", required=false) String complemento,
			@RequestParam(name="cep", required=true) String cep
			){
		
		Cliente c = controleCliente.criar(nome, dataNascimento, cpf, rua, numero, complemento, cep);
		ResponseEntity<Cliente> r = new ResponseEntity<>(c, HttpStatus.CREATED);
		return r;
	}
	
	@RequestMapping(path="/cliente", method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> obterTodos(){
		List<Cliente> c = controleCliente.obterTodos();
		ResponseEntity<List<Cliente>> r = new ResponseEntity<>(c, HttpStatus.OK);
		return r;		
	}
	
	@RequestMapping(path="/cliente/{id}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> obterPorId(
			@PathVariable Long id){
		Cliente c = controleCliente.obterPorId(id);
		if(c != null) {
			ResponseEntity<Cliente> r = new ResponseEntity<>(c, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Cliente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;		
	}
	
	@RequestMapping(path="/cliente/cpf/{string_cpf}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> obterPorCPF(
			@PathVariable String string_cpf){
		Cliente c = controleCliente.obterPorCPF(string_cpf);
		if(c != null) {
			ResponseEntity<Cliente> r = new ResponseEntity<>(c, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Cliente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;		
	}
	
	@RequestMapping(path="/cliente/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Cliente> excluirCliente(
			@PathVariable Long id){
		Cliente c = controleCliente.obterPorId(id);
		if(c != null) {
			controleCliente.excluir(c);
			ResponseEntity<Cliente> r = new ResponseEntity<>(c, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Cliente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;		
	}
	
	@RequestMapping(path="/cliente/{id}", method=RequestMethod.POST)
	public ResponseEntity<Cliente> alterarCliente(
			@PathVariable Long id,
			@RequestParam(name="nome", required=true) String nome,
			@RequestParam(name="dataNascimento", required=true) String dataNascimento,
			@RequestParam(name="cpf", required=true) String cpf,
			@RequestParam(name="rua", required=true) String rua,
			@RequestParam(name="numero", required=true) Integer numero,
			@RequestParam(name="complemento", required=false) String complemento,
			@RequestParam(name="cep", required=true) String cep
			){
		
		Cliente c = controleCliente.obterPorId(id);
		if(c != null) {
			controleCliente.alterarCliente(c, nome, dataNascimento, cpf, rua, numero, complemento, cep);
			ResponseEntity<Cliente> r = new ResponseEntity<>(c, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Cliente> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;		
	}
	
}

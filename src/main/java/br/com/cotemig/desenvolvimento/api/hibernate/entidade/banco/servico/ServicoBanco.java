/**
 * 
 */
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

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle.ControleBanco;

/**
 * Serviço para o banco
 */
@RestController
public class ServicoBanco {
	
	@Autowired
	private ControleBanco controleBanco;
	
	/**
	 * Método responsável por criar um banco
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/banco", method=RequestMethod.PUT)
	public ResponseEntity<Banco> criarBanco(
			@RequestParam(name="codigo", required=true) Integer codigo,
			@RequestParam(name="nome", required=true) String nome,
			@RequestParam(name="cnpj", required=true) String cnpj){
		
		Banco b = controleBanco.criar(codigo, nome, cnpj);
		ResponseEntity<Banco> r = new ResponseEntity<>(b, HttpStatus.CREATED);
		return r;
	}
	
	/**
	 * Método responsável por obter todos os banco cadastrados
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/banco", method=RequestMethod.GET)
	public ResponseEntity<List<Banco>> obterTodos(){
		List<Banco> bancos = controleBanco.obterTodos();
		ResponseEntity<List<Banco>> r = new ResponseEntity<>(bancos, HttpStatus.OK);
		return r;
	}
	
	/**
	 * Método responsável por obter um banco pelo seu id
	 * @param id {@link Long}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/banco/{id}", method=RequestMethod.GET)
	public ResponseEntity<Banco> obterPorId(
			@PathVariable Long id){
		Banco b = controleBanco.obterPorId(id);
		if(b != null) {
			ResponseEntity<Banco> r = new ResponseEntity<>(b, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Banco> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
	/**
	 * Método responsável por criar um banco
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/banco/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Banco> excluirBanco(
			@PathVariable Long id) {
		Banco b = controleBanco.obterPorId(id);
		if(b != null) {
			controleBanco.excluir(b);
			ResponseEntity<Banco> r = new ResponseEntity<>(HttpStatus.OK);
			return r;
		}
		ResponseEntity<Banco> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;

	}
	
	/**
	 * Método responsável por criar um banco
	 * @param codigo {@link Integer}
	 * @param nome {@link String}
	 * @param cnpj {@link String}
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(path="/banco/{id}", method=RequestMethod.POST)
	public ResponseEntity<Banco> alterarBanco(
			@PathVariable Long id,
			@RequestParam(name="codigo", required=false) Integer codigo,
			@RequestParam(name="nome", required=false) String nome,
			@RequestParam(name="cnpj", required=false) String cnpj) {
		Banco b = controleBanco.obterPorId(id);
		if(b != null) {
			controleBanco.alterarBanco(b, codigo, nome, cnpj);
			ResponseEntity<Banco> r = new ResponseEntity<>(b, HttpStatus.OK);
			return r;
		}
		ResponseEntity<Banco> r = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return r;
	}
	
}

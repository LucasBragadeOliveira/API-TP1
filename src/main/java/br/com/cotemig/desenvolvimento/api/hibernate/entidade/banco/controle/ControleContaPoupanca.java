package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Poupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoAgencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoCliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoPoupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

@Component
public class ControleContaPoupanca {
	@Autowired
	private DaoPoupanca daoPoupanca;
	@Autowired
	private DaoCliente daoCliente;
	@Autowired
	private DaoAgencia daoAgencia;
	
	public Poupanca criar(Long idAgencia, Long idCliente,
			Integer numero, String digito, Integer aniversario 
			) {
		
		Cliente cliente = daoCliente.procurarPorId(idCliente);
		if(cliente == null) {
			throw new EntidadeNaoEncontradaException("Cliente", idCliente);
		}
		
		Agencia agencia = daoAgencia.procurarPorId(idAgencia);
		if(agencia == null) {
			throw new EntidadeNaoEncontradaException("Cliente", idCliente);
		}
		Poupanca CP = new Poupanca();
		agencia.adicionarConta(CP);
		cliente.adicionarConta(CP);
		
		CP.setAniversario(aniversario);
		CP.setDigito(digito);
		CP.setNumero(numero);
		CP.setAtiva(true);
		CP.setSaldo(0.0);
		
		daoPoupanca.salvar(CP);
		
		return CP;
	}
	
	public List<Poupanca> obterTodos(){
		return daoPoupanca.obterTodos();
	}
	
	public Poupanca obterPorId(Long id){
		return daoPoupanca.procurarPorId(id);
	}
	
	public void excluir(Poupanca CP) {
		daoPoupanca.excluir(CP);
	}
	
	public Poupanca renderJuros(Poupanca CP, Double juros) {
		if(juros > 0) {
			CP.setSaldo(CP.getSaldo()*(1.0+juros));
			daoPoupanca.salvar(CP);
		}
		return CP;
	}
	
	public Poupanca saque(Poupanca CP, Double valor) {
		Double saldo = CP.getSaldo();
		if(saldo-valor >= 0 && valor > 0) {
			CP.setSaldo(saldo-valor);
			daoPoupanca.salvar(CP);
		}
		return CP;
	}
	
	public Poupanca deposito(Poupanca CP, Double valor) {
		Double saldo = CP.getSaldo();
		if(valor > 0) {
			CP.setSaldo(saldo+valor);
			daoPoupanca.salvar(CP);
		}
		return CP;
	}
	
	public Poupanca alterarCP(Poupanca CP,
			Long idAgencia, Integer numero, String digito, Boolean ativa, Integer aniversario) {
		
		if(idAgencia != null 
				&& idAgencia != CP.getAgencia().getId() 
				&& daoAgencia.procurarPorId(idAgencia) != null) {
			Agencia AgOriginal = daoAgencia.procurarPorId(CP.getAgencia().getId());			
			AgOriginal.removerConta(CP);			
			Agencia AgNova = daoAgencia.procurarPorId(idAgencia);
			AgNova.adicionarConta(CP);
		}
		
		if(aniversario != null) {
			CP.setAniversario(aniversario);
		}

		if(StringUtils.isNotBlank(digito)) {
			CP.setDigito(digito);
		}
		
		if(numero != null && numero > 0) {
			CP.setNumero(numero);
		}
		
		if(ativa != null) {
			CP.setAtiva(ativa);
		}
		daoPoupanca.salvar(CP);
		return CP;
	}
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.ContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoAgencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoCliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.excecao.EntidadeNaoEncontradaException;

@Component
public class ControleContaCorrente {

	@Autowired
	private DaoContaCorrente daoContaCorrente;
	@Autowired
	private DaoCliente daoCliente;
	@Autowired
	private DaoAgencia daoAgencia;
	
	/**
	 * Método responsável por obter as contas que possuem
	 * saldo com o valor igual ao do parâmetro
	 * @param valorIgual {@link Double}
	 * @return {@link List}
	 */
	public List<ContaCorrente> obterContasValorIgual(Double valorIgual) {
		return daoContaCorrente.obterContasValorIgual(valorIgual);
	}
	
	public ContaCorrente criar(Long idAgencia, Long idCliente,
			Integer numero, String digito 
			) {
		
		Cliente cliente = daoCliente.procurarPorId(idCliente);
		if(cliente == null) {
			throw new EntidadeNaoEncontradaException("Cliente", idCliente);
		}
		
		Agencia agencia = daoAgencia.procurarPorId(idAgencia);
		if(agencia == null) {
			throw new EntidadeNaoEncontradaException("Cliente", idCliente);
		}
		ContaCorrente CC = new ContaCorrente();
		agencia.adicionarConta(CC);
		cliente.adicionarConta(CC);
		
		CC.setDigito(digito);
		CC.setNumero(numero);
		CC.setAtiva(true);
		CC.setSaldo(0.0);
		
		daoContaCorrente.salvar(CC);
		
		return CC;
	}
	
	public List<ContaCorrente> obterTodos(){
		return daoContaCorrente.obterTodos();
	}
	
	public ContaCorrente obterPorId(Long id){
		return daoContaCorrente.procurarPorId(id);
	}
	
	public void excluir(ContaCorrente CC) {
		daoContaCorrente.excluir(CC);
	}
	
	public ContaCorrente saque(ContaCorrente CC, Double valor) {
		Double saldo = CC.getSaldo();
		if(saldo-valor >= 0 && valor > 0) {
			CC.setSaldo(saldo-valor);
			daoContaCorrente.salvar(CC);
		}
		return CC;
	}
	
	public ContaCorrente deposito(ContaCorrente CC, Double valor) {
		Double saldo = CC.getSaldo();
		if(valor > 0) {
			CC.setSaldo(saldo+valor);
			daoContaCorrente.salvar(CC);
		}
		return CC;
	}
	
	public ContaCorrente alterarCC(ContaCorrente CC,
			Long idAgencia, Integer numero, String digito, Boolean ativa) {
		
		if(idAgencia != null 
				&& idAgencia != CC.getAgencia().getId() 
				&& daoAgencia.procurarPorId(idAgencia) != null) {
			Agencia AgOriginal = daoAgencia.procurarPorId(CC.getAgencia().getId());
			Agencia AgNova = daoAgencia.procurarPorId(idAgencia);
			AgOriginal.removerConta(CC);
			AgNova.adicionarConta(CC);
		}

		if(StringUtils.isNotBlank(digito)) {
			CC.setDigito(digito);
		}
		
		if(numero != null && numero > 0) {
			CC.setNumero(numero);
		}
		
		if(ativa != null) {
			CC.setAtiva(ativa);
		}
		daoContaCorrente.salvar(CC);
		return CC;
	}
}

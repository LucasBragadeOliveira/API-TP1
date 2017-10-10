/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Agencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Banco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.CPF;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Conta;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.ContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Endereco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Poupanca;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoAgencia;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoBanco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoCliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoConta;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoContaCorrente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoFinanciamento;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoPoupanca;

/**
 * Povoador de banco da aplicação.
 */
@Component
@Transactional
public class PovoadorBD {
	
	@Autowired
	private DaoBanco daoBanco;
	
	@Autowired
	private DaoAgencia daoAgencia;
	
	@Autowired
	private DaoCliente daoCliente;
	
	@Autowired
	private DaoFinanciamento daoFinanciamento;
	
	@Autowired
	private DaoConta daoConta;
	
	/**
	 * Método responsável por povoar o banco.
	 * @throws IOException IOException
	 * @throws FileNotFoundException FileNotFoundException
	 * @throws ParseException ParseException
	 */
	public void povoar() throws FileNotFoundException, IOException, ParseException{
		File file = new File("C:\\Dropbox\\Dropbox\\Cotemig\\API\\desenvolvimento-api-hibernate\\src\\main\\resources\\DadosPovoador.csv");		
		Iterable<CSVRecord> registros = CSVFormat.DEFAULT.newFormat(';').parse(new FileReader(file));
		Iterator<CSVRecord> it = registros.iterator();
		
		//descarta o cabeçalho
		it.next();
		
		// lê as demais linhas
		while(it.hasNext()) {
			CSVRecord record = it.next();
			Banco banco = recuperarOuCriarBanco(record);
			Agencia agencia = recuperarOuCriarAgencia(record, banco);
			Cliente cliente = recuperarOuCriarCliente(record);
			criarContas(record, agencia, cliente);
		}
	}


	private void criarContas(CSVRecord record, Agencia agencia, Cliente cliente) {
		Conta c = null;
		if(record.get(15).equals("P")) {
			c = new Poupanca();
			Poupanca p = (Poupanca)c;
			Integer aniversario = Integer.parseInt(record.get(20)); 
			p.setAniversario(aniversario);
		}else {
			c = new ContaCorrente();
		}
		Integer numero = Integer.parseInt(record.get(16));
		c.setNumero(numero);
		c.setDigito(record.get(17));
		Double saldo = Double.parseDouble(record.get(18));
		c.setSaldo(saldo);
		
		boolean ativa = record.get(19).equals("S");
		c.setAtiva(ativa);
		
		agencia.adicionarConta(c);
		cliente.adicionarConta(c);
		daoConta.salvar(c);
	}


	private Calendar gerarData(String data) throws ParseException {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		cal.setTime(sdf.parse(data));
		
		return cal;
	}


	private Agencia recuperarOuCriarAgencia(CSVRecord record, Banco banco) {
		Integer codigo = Integer.parseInt(record.get(3));
		Agencia agencia = daoAgencia.procurarPorCodigoEBanco(codigo, banco.getCodigo());
		if(agencia == null) {
			agencia = new Agencia();
			banco.adicionarAgencia(agencia);
			agencia.setCodigo(codigo);
			Endereco e = new Endereco();
			e.setRua(record.get(4));
			Integer numero = Integer.parseInt(record.get(5));
			e.setNumero(numero);
			if(StringUtils.isNotBlank(record.get(6))) {
				e.setComplemento(record.get(6));
			}
			e.setCep(record.get(7));
			agencia.setEndereco(e);
			daoAgencia.salvar(agencia);
		}
		return agencia;
	}


	private Banco recuperarOuCriarBanco(CSVRecord record) {
		Integer codigoBanco = Integer.parseInt(record.get(0));
		Banco banco = daoBanco.procurarPorCodigo(codigoBanco);
		if(banco == null) {
			banco = new Banco();
			banco.setCodigo(codigoBanco);
			banco.setNome(record.get(1));
			banco.setCnpj(record.get(2));
			daoBanco.salvar(banco);
		}
		return banco;
	}

	
	private Cliente recuperarOuCriarCliente(CSVRecord record)
			throws ParseException {
		Cliente cliente = daoCliente.obterClientePorCpf(record.get(9));
		if(cliente == null) {
			cliente = new Cliente();
			cliente.setCpf(new CPF(record.get(9)));
			cliente.setNome(record.get(8));
			cliente.setDataNascimento(gerarData(record.get(10)));
			
			Endereco e = new Endereco();
			e.setRua(record.get(11));
			Integer numero = Integer.parseInt(record.get(12));
			e.setNumero(numero);
			if(StringUtils.isNotBlank(record.get(13))) {
				e.setComplemento(record.get(13));
			}
			e.setCep(record.get(14));
			cliente.setEndereco(e);
			daoCliente.salvar(cliente);
		}
		return cliente;
	}
}
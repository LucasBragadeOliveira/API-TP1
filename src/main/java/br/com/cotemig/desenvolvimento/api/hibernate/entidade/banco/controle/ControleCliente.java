package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.CPF;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Cliente;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.Endereco;
import br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco.dao.DaoCliente;

@Component
public class ControleCliente {

	@Autowired
	private DaoCliente daoCliente;
	
	public Cliente criar(String nome, String dataNascimento,
			String cpf,
			String rua, Integer numero, String complemento, String cep){
		Cliente C = new Cliente();
		Endereco E = new Endereco();
		CPF cp = new CPF();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dNasci  = Calendar.getInstance();
		
		try {
			dNasci.setTime(df.parse(dataNascimento));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		E.setCep(cep);
		E.setComplemento(complemento);
		E.setNumero(numero);
		E.setRua(rua);
		
		cp.setAtivo(true);
		cp.setCpf(cpf);		
		
		C.setEndereco(E);
		C.setCpf(cp);
		C.setNome(nome);
		C.setDataNascimento(dNasci);
		
		daoCliente.salvar(C);
		
		return C;		
	}
	
	public List<Cliente> obterTodos(){
		return daoCliente.obterTodos();
	}
	
	public Cliente obterPorId(Long id) {
		return daoCliente.procurarPorId(id);
	}
	
	public Cliente obterPorCPF(String cpf) {
		return daoCliente.obterClientePorCpf(cpf);
	}
	
	public void excluir(Cliente c){
		daoCliente.excluir(c);
	}
	
	public Cliente alterarCliente(Cliente C,
			String nome, String dataNascimento,
			String cpf,
			String rua, Integer numero, String complemento, String cep){
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Calendar dNasci  = Calendar.getInstance();
		
		if(StringUtils.isNotBlank(dataNascimento)) {
			try {
				dNasci.setTime(df.parse(dataNascimento));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			C.setDataNascimento(dNasci);
		}		
		
		if(StringUtils.isNotBlank(nome)) {
			C.setNome(nome);
		}
		
		if(StringUtils.isNotBlank(cpf)) {
			C.getCpf().setCpf(cpf);
		}		
		
		C.getEndereco().setComplemento(complemento);
		
		if(numero != null && numero > 0) {
			C.getEndereco().setNumero(numero);
		}
		
		if(StringUtils.isNotBlank(rua)) {
			C.getEndereco().setRua(rua);
		}
		
		if(StringUtils.isNotBlank(cep)) {
			C.getEndereco().setCep(cep);
		}
		
		return C;	
	}
}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import java.util.Calendar;
import java.util.Collection;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Representa a entidade Cliente.
 */
@Entity
public class Cliente extends ObjetoPersistente {

	private String nome;
	private Calendar dataNascimento;
	private CPF cpf;
	private Endereco endereco;
	private Collection<Conta> contas = new TreeSet<>();
	private Collection<Financiamento> financiamentos = new TreeSet<>();
	
	/**
	 * Método responsável por adicionar uma conta
	 * 
	 * @param conta
	 *            {@link Conta}
	 */
	public void adicionarConta(Conta conta) {
		if (contas.add(conta)) {
			conta.setCliente(this);
		}
	}
	
	/**
	 * Método responsável por adicionar um {@link Financiamento}.
	 * @param financiamento {@link Financiamento}
	 */
	public void adicionarFinanciamento(Financiamento financiamento) {
		financiamentos.add(financiamento);
	}
	
	/**
	 * @return the contas
	 */
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	@Access(AccessType.FIELD)
	public Collection<Conta> getContas() {
		return contas;
	}

	/**
	 * @return the cpf
	 */
	@NotNull
	@AttributeOverrides({ @AttributeOverride(column = @Column(name = "cpfCliente"), name = "cpf") })
	public CPF getCpf() {
		return cpf;
	}

	/**
	 * @return the dataNascimento
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @return the endereco
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_Cliente_Endereco"))
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @return the financiamentos
	 */
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "Cliente_Financiamento", joinColumns = @JoinColumn(name = "Cliente_ID"), foreignKey = @ForeignKey(name = "FK_Cliente_Financiamento"), inverseJoinColumns = @JoinColumn(name = "Financiamento_ID"), inverseForeignKey = @ForeignKey(name = "FK_Financiamento_Cliente"))
	@Access(AccessType.FIELD)
	public Collection<Financiamento> getFinanciamentos() {
		return financiamentos;
	}

	/**
	 * @return the nome
	 */
	@NotEmpty
	@Length(max = 500)
	public String getNome() {
		return nome;
	}

	/**
	 * Método responsável por remover um {@link Financiamento}.
	 * @param financiamento {@link Financiamento}
	 */
	public void removerFinanciamento(Financiamento financiamento) {
		financiamentos.remove(financiamento);
	}

	/**
	 * Método responsável por remover uma conta
	 * 
	 * @param conta
	 *            {@link Conta}
	 */
	public void removerrConta(Conta conta) {
		if (contas.remove(conta)) {
			conta.setCliente(null);
		}
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	/**
	 * @param dataNascimento
	 *            the dataNascimento to set
	 */
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}

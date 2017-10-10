/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Representa a entidade banco.
 */
@Entity
public class Banco extends ObjetoPersistente {

	private Integer codigo;
	private String nome;
	private String cnpj;
	private Collection<Agencia> agencias = new ArrayList<>();
	private Collection<Financiamento> financiamentos = new ArrayList<>();
	
	/**
	 * Método responsável por adicionar uma agência ao banco.
	 * 
	 * @param agencia
	 *            {@link Agencia}
	 */
	public void adicionarAgencia(Agencia agencia) {
		if(agencias.add(agencia)) {
			agencia.setBanco(this);
		}
	}

	/**
	 * Método responsável por adicionar um financiamento.
	 * @param financiamento {@link Financiamento}
	 */
	public void adicionarFinanciamento(Financiamento financiamento) {
		if(financiamentos.add(financiamento)){
			financiamento.setBanco(this);
		}
	}

	/**
	 * @return the agencias
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "banco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Access(AccessType.FIELD)
	public Collection<Agencia> getAgencias() {
		return Collections.unmodifiableCollection(agencias);
	}

	/**
	 * @return the cnpj
	 */
	@NotEmpty()
	@Length(max = 18, min = 18)
	@Pattern(regexp = "([0-9]{2}[.]?[0-9]{3}[.]?[0-9]{3}[/]?[0-9]{4}[-]?[0-9]{2})")
	@Column(unique=true)
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * @return the codigo
	 */
	@NotNull
	@Min(value = 0)
	@Max(value = 9999)
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the financiamentos
	 */
	@JsonIgnore
	@OneToMany(mappedBy="banco", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@Access(AccessType.FIELD)
	public Collection<Financiamento> getFinanciamentos() {
		return Collections.unmodifiableCollection(financiamentos);
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
	 * Método responsável por remover uma agência de um banco
	 * 
	 * @param agencia
	 *            {@link Agencia}
	 */
	public void removerAgencia(Agencia agencia) {
		agencias.remove(agencia);
		agencia.setBanco(null);
	}

	/**
	 * Método responsável por remover um financiamento
	 * @param financiamento {@link Financiamento}
	 */
	public void removerFinanciamento(Financiamento financiamento) {
		financiamentos.remove(financiamento);
		financiamento.setBanco(null);
	}

	/**
	 * @param cnpj
	 *            the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
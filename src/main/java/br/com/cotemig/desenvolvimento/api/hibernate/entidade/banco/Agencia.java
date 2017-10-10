/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Representa a entidade agência.
 */
@Entity
@Table(name = "TabelaAgencias", 
	indexes = @Index(columnList = "codigo", name = "idx_agencia_codigo"))
public class Agencia extends ObjetoPersistente {

	private Banco banco;
	private Integer codigo;
	private Endereco endereco;
	private Collection<Conta> contas = new TreeSet<>();

	/**
	 * Método responsável por adicionar uma conta.
	 * 
	 * @param conta
	 *            {@link Conta}
	 */
	public void adicionarConta(Conta conta) {
		if (contas.add(conta)) {
			conta.setAgencia(this);
		}
	}

	/**
	 * @return the banco
	 */
	@NotNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_Agencia_Banco"))
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @return the codigo
	 */
	@NotNull
	@Min(0)
	@Max(9999)
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * @return the contas
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agencia", fetch = FetchType.LAZY)
	@Access(AccessType.FIELD)
	@JsonIgnore
	public Collection<Conta> getContas() {
		return Collections.unmodifiableCollection(contas);
	}

	/**
	 * @return the endereco
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_Agencia_Endereco"))
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Método responsável por remover uma conta.
	 * 
	 * @param conta
	 *            {@link Conta}
	 */
	public void removerConta(Conta conta) {
		if (contas.remove(conta)) {
			conta.setAgencia(null);
		}
	}

	/**
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
}

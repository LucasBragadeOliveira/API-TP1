/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Representa uma entidade Conta
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Conta extends ObjetoPersistente {

	private Cliente cliente;
	private Agencia agencia;
	private Integer numero;
	private String digito;
	private Double saldo;
	private Boolean ativa;

	/**
	 * @return the agencia
	 */
	@ManyToOne
	@NotNull
	@JoinColumn(foreignKey=@ForeignKey(name="FK_Conta_Agencia"))
	public Agencia getAgencia() {
		return agencia;
	}

	/**
	 * @return the ativa
	 */
	@NotNull
	public Boolean getAtiva() {
		return ativa;
	}

	/**
	 * @return the cliente
	 */
	@ManyToOne
	@NotNull
	@JoinColumn(foreignKey=@ForeignKey(name="FK_Conta_Cliente"))
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @return the digito
	 */
	@NotEmpty
	@Length(min=1, max=1)
	public String getDigito() {
		return digito;
	}

	/**
	 * @return the numero
	 */
	@NotNull
	@Min(0)
	@Max(99999)
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @return the saldo
	 */
	@NotNull
	public Double getSaldo() {
		return saldo;
	}

	/**
	 * @param agencia
	 *            the agencia to set
	 */
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	/**
	 * @param ativa the ativa to set
	 */
	public void setAtiva(Boolean ativa) {
		this.ativa = ativa;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @param digito
	 *            the digito to set
	 */
	public void setDigito(String digito) {
		this.digito = digito;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

}

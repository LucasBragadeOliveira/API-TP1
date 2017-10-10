/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import java.util.Collection;
import java.util.TreeSet;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Representa um financiamento
 */
@Entity
public class Financiamento extends ObjetoPersistente {

	private Banco banco;
	private Collection<Cliente> clientes = new TreeSet<Cliente>();

	private Double valorTotal;
	private Double valorAtual;
	private Integer parcelasTotais;
	private Integer parcelasFaltantes;
	
	/**
	 * Método responsável por adicionar um cliente
	 * @param cliente {@link Cliente}
	 */
	public void adicionarCliente (Cliente cliente) {
		clientes.add(cliente);
	}
	
	/**
	 * @return the banco
	 */
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="FK_Financiamento_Banco"))
	public Banco getBanco() {
		return banco;
	}
	
	/**
	 * @return the clientes
	 */
	@JsonIgnore
	@ManyToMany(mappedBy="financiamentos")
	@Access(AccessType.FIELD)
	public Collection<Cliente> getClientes() {
		return clientes;
	}

	/**
	 * @return the parcelasFaltantes
	 */
	@NotNull
	@Min(0)
	public Integer getParcelasFaltantes() {
		return parcelasFaltantes;
	}

	/**
	 * @return the parcelasTotais
	 */
	@NotNull
	@Min(0)
	public Integer getParcelasTotais() {
		return parcelasTotais;
	}

	/**
	 * @return the valorAtual
	 */
	@NotNull
	@Min(0)
	public Double getValorAtual() {
		return valorAtual;
	}

	/**
	 * Método responsável por calcular
	 * o valor pago.
	 * @return double
	 */
	@Transient
	public Double getValorPago() {
		return valorTotal - valorAtual;
	}

	/**
	 * @return the valorTotal
	 */
	@NotNull
	@Min(0)
	public Double getValorTotal() {
		return valorTotal;
	}

	/**
	 * Método responsável por remover um cliente
	 * @param cliente {@link Cliente}
	 */
	public void removerCliente (Cliente cliente) {
		clientes.remove(cliente);
	}

	/**
	 * @param banco
	 *            the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	/**
	 * @param clientes
	 *            the clientes to set
	 */
	public void setClientes(Collection<Cliente> clientes) {
		this.clientes = clientes;
	}

	/**
	 * @param parcelasFaltantes
	 *            the parcelasFaltantes to set
	 */
	public void setParcelasFaltantes(Integer parcelasFaltantes) {
		this.parcelasFaltantes = parcelasFaltantes;
	}

	/**
	 * @param parcelasTotais
	 *            the parcelasTotais to set
	 */
	public void setParcelasTotais(Integer parcelasTotais) {
		this.parcelasTotais = parcelasTotais;
	}

	/**
	 * @param valorAtual
	 *            the valorAtual to set
	 */
	public void setValorAtual(Double valorAtual) {
		this.valorAtual = valorAtual;
	}
	
	/**
	 * @param valorTotal
	 *            the valorTotal to set
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}

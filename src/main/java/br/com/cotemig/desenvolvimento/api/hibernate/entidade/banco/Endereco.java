/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.cotemig.desenvolvimento.api.hibernate.entidade.ObjetoPersistente;

/**
 * Entidade que representa um endereço.
 */
@Entity
public class Endereco extends ObjetoPersistente {

	private String rua;
	private Integer numero;
	private String complemento;
	private String cep;

	/**
	 * @return the cep
	 */
	@NotEmpty
	@Length(max=10, min=9)
	@Pattern(regexp = "\\d{5}[-]\\d{3}")
	public String getCep() {
		return cep;
	}

	/**
	 * @return the complemento
	 */
	@Length(max=1000)
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @return the numero
	 */
	@NotNull
	@Min(0)
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @return the rua
	 */
	@NotEmpty
	@Length(max=1000)
	public String getRua() {
		return rua;
	}

	/**
	 * @param cep
	 *            the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @param rua
	 *            the rua to set
	 */
	public void setRua(String rua) {
		this.rua = rua;
	}

}

/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Representa a entidade CPF
 */
@Embeddable
public class CPF{
	
	private String cpf;
	private Boolean ativo;
	
	public CPF() {
		super();
	}
	
	public CPF(String cpf) {
		super();
		this.cpf = cpf;
		this.ativo = true;
	}

	/**
	 * @return the cpf
	 */
	@NotEmpty
	@Length(max = 14, min = 14)
	@Pattern(regexp = "([0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}-[0-9]{2})|([0-9]{11})")
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}

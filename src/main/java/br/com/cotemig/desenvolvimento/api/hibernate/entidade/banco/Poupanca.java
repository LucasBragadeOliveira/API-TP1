/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade.banco;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representa uma conta poupança.
 */
@Entity
public class Poupanca extends Conta {
	
	private Integer aniversario;

	/**
	 * @return the aniversario
	 */
	@NotNull
	@Min(0)
	@Max(31)
	public Integer getAniversario() {
		return aniversario;
	}

	/**
	 * @param aniversario the aniversario to set
	 */
	public void setAniversario(Integer aniversario) {
		this.aniversario = aniversario;
	}
}

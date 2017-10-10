/**
 * 
 */
package br.com.cotemig.desenvolvimento.api.hibernate.entidade;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Representa a entidade
 */
@MappedSuperclass
public abstract class ObjetoPersistente implements
		Comparable<ObjetoPersistente> {

	private Long id;
	private Long versao;

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	/**
	 * @return the versao
	 */
	@Version
	public Long getVersao() {
		return versao;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param versao
	 *            the versao to set
	 */
	public void setVersao(Long versao) {
		this.versao = versao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetoPersistente other = (ObjetoPersistente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Método responsável para preparar a entidade para salvar
	 */
	public void prepararParaSalvar() {

	}

	/**
	 * Método chamado ao salvar a entidade.
	 */
	public void validarAoSalvar() {

	}

	/**
	 * Método responsável para preparar a entidade para ser excluida
	 */
	public void prepararParaExcluir() {

	}

	/**
	 * Método chamado ao excluir a entidade.
	 */
	public void validarAoExcluir() {

	}

	@Override
	public int compareTo(ObjetoPersistente o) {
		if (o == null) {
			return 1;
		} else {
			ObjetoPersistente outro = o;
			Long idOutro = outro.getId();
			if (getId() == null && idOutro == null) {
				Integer h1 = new Integer(hashCode());
				Integer h2 = outro.hashCode();
				return h1.compareTo(h2);
			} else {
				Long id1 = (Long) ObjectUtils.defaultIfNull(getId(), 0L);
				Long id2 = (Long) ObjectUtils.defaultIfNull(idOutro, 0L);
				return id1.compareTo(id2);
			}
		}
	}
}

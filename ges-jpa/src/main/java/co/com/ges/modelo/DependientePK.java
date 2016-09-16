package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DEPENDIENTE database table.
 * 
 */
@Embeddable
public class DependientePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idtipopadre;

	private String idpadre;

	private int idtipohijo;

	private String idhijo;

	@Column(insertable=false, updatable=false)
	private String numerocaso;

	public DependientePK() {
	}
	public int getIdtipopadre() {
		return this.idtipopadre;
	}
	public void setIdtipopadre(int idtipopadre) {
		this.idtipopadre = idtipopadre;
	}
	public String getIdpadre() {
		return this.idpadre;
	}
	public void setIdpadre(String idpadre) {
		this.idpadre = idpadre;
	}
	public int getIdtipohijo() {
		return this.idtipohijo;
	}
	public void setIdtipohijo(int idtipohijo) {
		this.idtipohijo = idtipohijo;
	}
	public String getIdhijo() {
		return this.idhijo;
	}
	public void setIdhijo(String idhijo) {
		this.idhijo = idhijo;
	}
	public String getNumerocaso() {
		return this.numerocaso;
	}
	public void setNumerocaso(String numerocaso) {
		this.numerocaso = numerocaso;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DependientePK)) {
			return false;
		}
		DependientePK castOther = (DependientePK)other;
		return 
			(this.idtipopadre == castOther.idtipopadre)
			&& this.idpadre.equals(castOther.idpadre)
			&& (this.idtipohijo == castOther.idtipohijo)
			&& this.idhijo.equals(castOther.idhijo)
			&& this.numerocaso.equals(castOther.numerocaso);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtipopadre;
		hash = hash * prime + this.idpadre.hashCode();
		hash = hash * prime + this.idtipohijo;
		hash = hash * prime + this.idhijo.hashCode();
		hash = hash * prime + this.numerocaso.hashCode();
		
		return hash;
	}
}
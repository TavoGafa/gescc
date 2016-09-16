package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CASOANIO database table.
 * 
 */
@Embeddable
public class CasoanioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String numerocaso;

	private int idanio;

	public CasoanioPK() {
	}
	public String getNumerocaso() {
		return this.numerocaso;
	}
	public void setNumerocaso(String numerocaso) {
		this.numerocaso = numerocaso;
	}
	public int getIdanio() {
		return this.idanio;
	}
	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CasoanioPK)) {
			return false;
		}
		CasoanioPK castOther = (CasoanioPK)other;
		return 
			this.numerocaso.equals(castOther.numerocaso)
			&& (this.idanio == castOther.idanio);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.numerocaso.hashCode();
		hash = hash * prime + this.idanio;
		
		return hash;
	}
}
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PERSONA database table.
 * 
 */
@Embeddable
public class PersonaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idtipo;

	private String ssitin;

	public PersonaPK() {
	}
	public int getIdtipo() {
		return this.idtipo;
	}
	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}
	public String getSsitin() {
		return this.ssitin;
	}
	public void setSsitin(String ssitin) {
		this.ssitin = ssitin;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonaPK)) {
			return false;
		}
		PersonaPK castOther = (PersonaPK)other;
		return 
			(this.idtipo == castOther.idtipo)
			&& this.ssitin.equals(castOther.ssitin);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtipo;
		hash = hash * prime + this.ssitin.hashCode();
		
		return hash;
	}
}
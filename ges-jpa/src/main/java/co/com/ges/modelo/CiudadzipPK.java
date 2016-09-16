package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the CIUDADZIP database table.
 * 
 */
@Embeddable
public class CiudadzipPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idciudad;

	private int idzip;

	public CiudadzipPK() {
	}
	public int getIdciudad() {
		return this.idciudad;
	}
	public void setIdciudad(int idciudad) {
		this.idciudad = idciudad;
	}
	public int getIdzip() {
		return this.idzip;
	}
	public void setIdzip(int idzip) {
		this.idzip = idzip;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CiudadzipPK)) {
			return false;
		}
		CiudadzipPK castOther = (CiudadzipPK)other;
		return 
			(this.idciudad == castOther.idciudad)
			&& (this.idzip == castOther.idzip);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idciudad;
		hash = hash * prime + this.idzip;
		
		return hash;
	}
}
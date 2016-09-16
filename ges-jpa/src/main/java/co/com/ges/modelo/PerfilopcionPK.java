package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PERFILOPCION database table.
 * 
 */
@Embeddable
public class PerfilopcionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idopcionsistema;

	private int idperfil;

	public PerfilopcionPK() {
	}
	public int getIdopcionsistema() {
		return this.idopcionsistema;
	}
	public void setIdopcionsistema(int idopcionsistema) {
		this.idopcionsistema = idopcionsistema;
	}
	public int getIdperfil() {
		return this.idperfil;
	}
	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PerfilopcionPK)) {
			return false;
		}
		PerfilopcionPK castOther = (PerfilopcionPK)other;
		return 
			(this.idopcionsistema == castOther.idopcionsistema)
			&& (this.idperfil == castOther.idperfil);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idopcionsistema;
		hash = hash * prime + this.idperfil;
		
		return hash;
	}
}
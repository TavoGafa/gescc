package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PERFILESTADOCASO database table.
 * 
 */
@Embeddable
public class PerfilestadocasoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idestadocaso;

	private int idperfil;

	public PerfilestadocasoPK() {
	}
	public int getIdestadocaso() {
		return this.idestadocaso;
	}
	public void setIdestadocaso(int idestadocaso) {
		this.idestadocaso = idestadocaso;
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
		if (!(other instanceof PerfilestadocasoPK)) {
			return false;
		}
		PerfilestadocasoPK castOther = (PerfilestadocasoPK)other;
		return 
			(this.idestadocaso == castOther.idestadocaso)
			&& (this.idperfil == castOther.idperfil);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idestadocaso;
		hash = hash * prime + this.idperfil;
		
		return hash;
	}
}
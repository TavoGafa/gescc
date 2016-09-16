package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ANIOCONTRATISTA database table.
 * 
 */
@Embeddable
public class AniocontratistaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idanio;

	private int idcontratista;

	public AniocontratistaPK() {
	}
	public int getIdanio() {
		return this.idanio;
	}
	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}
	public int getIdcontratista() {
		return this.idcontratista;
	}
	public void setIdcontratista(int idcontratista) {
		this.idcontratista = idcontratista;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AniocontratistaPK)) {
			return false;
		}
		AniocontratistaPK castOther = (AniocontratistaPK)other;
		return 
			(this.idanio == castOther.idanio)
			&& (this.idcontratista == castOther.idcontratista);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idanio;
		hash = hash * prime + this.idcontratista;
		
		return hash;
	}
}
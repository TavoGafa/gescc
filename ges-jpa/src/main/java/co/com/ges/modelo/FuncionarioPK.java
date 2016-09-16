package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the FUNCIONARIO database table.
 * 
 */
@Embeddable
public class FuncionarioPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int idtipopersona;

	private String identificacion;

	public FuncionarioPK() {
	}
	public int getIdtipopersona() {
		return this.idtipopersona;
	}
	public void setIdtipopersona(int idtipopersona) {
		this.idtipopersona = idtipopersona;
	}
	public String getIdentificacion() {
		return this.identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FuncionarioPK)) {
			return false;
		}
		FuncionarioPK castOther = (FuncionarioPK)other;
		return 
			(this.idtipopersona == castOther.idtipopersona)
			&& this.identificacion.equals(castOther.identificacion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtipopersona;
		hash = hash * prime + this.identificacion.hashCode();
		
		return hash;
	}
}
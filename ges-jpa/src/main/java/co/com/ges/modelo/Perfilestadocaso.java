package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PERFILESTADOCASO database table.
 * 
 */
@Entity
@Table(name="PERFILESTADOCASO")
public class Perfilestadocaso implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PerfilestadocasoPK id;

	private Date fecha;

	public Perfilestadocaso() {
	}

	public PerfilestadocasoPK getId() {
		return this.id;
	}

	public void setId(PerfilestadocasoPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the ANIOCONTRATISTA database table.
 * 
 */
@Entity
@Table(name="ANIOCONTRATISTA")
public class Aniocontratista implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AniocontratistaPK id;

	private Date fecha;

	public Aniocontratista() {
	}

	public AniocontratistaPK getId() {
		return this.id;
	}

	public void setId(AniocontratistaPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
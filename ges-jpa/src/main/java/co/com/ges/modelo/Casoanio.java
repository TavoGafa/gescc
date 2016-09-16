package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the CASOANIO database table.
 * 
 */
@Entity
@Table(name="CASOANIO")
public class Casoanio implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CasoanioPK id;

	private Date fecha;

	public Casoanio() {
	}

	public CasoanioPK getId() {
		return this.id;
	}

	public void setId(CasoanioPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
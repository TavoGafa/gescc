package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PERFILOPCION database table.
 * 
 */
@Entity
@Table(name="PERFILOPCION")
public class Perfilopcion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String MENU = "Perfilopcion.MENU";

	@EmbeddedId
	private PerfilopcionPK id;

	private Date fecha;

	private String tipo;

	public Perfilopcion() {
	}

	public PerfilopcionPK getId() {
		return this.id;
	}

	public void setId(PerfilopcionPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
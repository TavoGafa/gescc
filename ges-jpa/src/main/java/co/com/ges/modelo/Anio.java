package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ANIO database table.
 * 
 */
@Entity
@Table(name="ANIO")
public class Anio implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TRAER_MAX_ID = "Anio.TRAER_MX_ID";

	public static final String ANIO_X_ID = "Anio.ANIO_X_ID";
	
	public static final String LISTAR_ANIO = "Anio.LISTAR_ANIO";
	
	@Id
	private int id;

	private short nombre;

	public Anio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getNombre() {
		return this.nombre;
	}

	public void setNombre(short nombre) {
		this.nombre = nombre;
	}

}
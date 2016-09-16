package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOCIVIL database table.
 * 
 */
@Entity
@Table(name="ESTADOCIVIL")
public class Estadocivil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TRAER_ECMX_ID = "Estadocivil.TRAER_ECMX_ID";

	public static final String EC_X_ID = "Estadocivil.EC_X_ID";
	
	public static final String LISTAR_EC = "Estadocivil.LISTAR_EC";
	
	@Id
	private int id;

	private String nombre;

	public Estadocivil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
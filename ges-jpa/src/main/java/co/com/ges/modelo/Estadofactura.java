package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOFACTURA database table.
 * 
 */
@Entity
@Table(name="ESTADOFACTURA")
public class Estadofactura implements Serializable {
	private static final long serialVersionUID = 1L;
	
public static final String ID_MAX = "Estadofactura.ID_MAX";
	
	public static final String TRAER_EFA = "Estadofactura.TRAER_EFA";
	
	public static final String TRAER_EFA_ID = "Estadofactura.TRAER_EFA_ID";

	@Id
	private int id;

	private String nombre;

	public Estadofactura() {
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
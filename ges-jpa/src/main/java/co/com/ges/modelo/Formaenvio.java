package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMAENVIO database table.
 * 
 */
@Entity
@Table(name="FORMAENVIO")
public class Formaenvio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_MX_ID = "Formaenvio.TRAER_MX_ID";

	public static final String FE_X_ID = "Formaenvio.FE_X_ID";
	
	public static final String LISTAR_FE = "Formaenvio.LISTAR_FE";


	@Id
	private int id;

	private String nombre;

	public Formaenvio() {
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
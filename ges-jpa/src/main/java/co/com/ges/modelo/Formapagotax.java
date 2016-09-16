package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMAPAGOTAX database table.
 * 
 */
@Entity
@Table(name="FORMAPAGOTAX")
public class Formapagotax implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_MX_ID = "Formapagotax.TRAER_MX_ID";

	public static final String FP_X_ID = "Formapagotax.FP_X_ID";
	
	public static final String LISTAR_FP = "Formapagotax.LISTAR_FP";

	@Id
	private int id;

	private String nombre;

	public Formapagotax() {
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
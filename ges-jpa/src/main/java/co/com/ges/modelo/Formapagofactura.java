package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FORMAPAGOFACTURA database table.
 * 
 */
@Entity
@Table(name="FORMAPAGOFACTURA")
public class Formapagofactura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_FACPAG = "Formapagofactura.LISTAR_FACPAG";
	public static final String LISTAR_FACPAG_POR_ID = "Formapagofactura.LISTAR_FACPAG_POR_ID";
	public static final String MAX_FACPAG = "Formapagofactura.MAX_FACPAG";

	@Id
	private int id;

	private String nombre;

	public Formapagofactura() {
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
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPOIDENTIFICACION database table.
 * 
 */
@Entity
@Table(name="TIPOIDENTIFICACION")
public class Tipoidentificacion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TIPIDEN = "Tipoidentificacion.LISTAR_TIPIDEN";
	public static final String LISTAR_LISTAR_TIPIDEN_POR_ID = "Tipoidentificacion.LISTAR_LISTAR_TIPIDEN_POR_ID";
	public static final String MAX_LISTAR_TIPIDEN = "Tipoidentificacion.MAX_LISTAR_TIPIDEN";

	@Id
	private int id;

	private String nombre;

	public Tipoidentificacion() {
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
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPOPARENTESCO database table.
 * 
 */
@Entity
@Table(name="TIPOPARENTESCO")
public class Tipoparentesco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_TIPPAR = "Tipoparentesco.LISTAR_TIPPAR";
	public static final String LISTAR_LISTAR_TIPPAR_POR_ID = "Tipoparentesco.LISTAR_LISTAR_TIPPAR_POR_ID";
	public static final String MAX_LISTAR_TIPPAR = "Tipoparentesco.MAX_LISTAR_TIPPAR";


	@Id
	private int id;

	private String nombre;

	public Tipoparentesco() {
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
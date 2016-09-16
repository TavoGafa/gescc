package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPOTAREA database table.
 * 
 */
@Entity
@Table(name="TIPOTAREA")
public class Tipotarea implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_MAX = "Tipotarea.ID_MAX";
	
	public static final String TRAER_TT = "Tipotarea.TRAER_TG";
	
	public static final String TRAER_TT_ID = "Tipotarea.TRAER_TG_ID";

	@Id
	private int id;

	private String nombre;

	public Tipotarea() {
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
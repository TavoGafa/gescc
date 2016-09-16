package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPOGASTO database table.
 * 
 */
@Entity
@Table(name="TIPOGASTO")
public class Tipogasto implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ID_MAX = "Tipogasto.ID_MAX";
	
	public static final String TRAER_TG = "Tipogasto.TRAER_TG";
	
	public static final String TRAER_TG_ID = "Tipogasto.TRAER_TG_ID";

	
	@Id
	private int id;

	private String nombre;

	public Tipogasto() {
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
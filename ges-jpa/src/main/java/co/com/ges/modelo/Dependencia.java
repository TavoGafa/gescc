package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DEPENDENCIA database table.
 * 
 */
@Entity
@Table(name="DEPENDENCIA")
public class Dependencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_MAX = "Dependencia.ID_MAX";
	
	public static final String TRAER_DEP = "Dependencia.TRAER_DEP";
	
	public static final String TRAER_DEP_ID = "Dependencia.TRAER_DEP_ID";

	@Id
	private int id;

	private String nombre;

	public Dependencia() {
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
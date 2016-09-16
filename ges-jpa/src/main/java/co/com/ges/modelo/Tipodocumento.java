package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TIPODOCUMENTO database table.
 * 
 */
@Entity
@Table(name="TIPODOCUMENTO")
public class Tipodocumento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_MAX = "Tipodocumento.ID_MAX";
	
	public static final String TRAER_TP = "Tipodocumento.TRAER_TP";
	
	public static final String TRAER_TP_ID = "Tipodocumento.TRAER_TP_ID";


	@Id
	private int id;

	private String nombre;

	public Tipodocumento() {
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
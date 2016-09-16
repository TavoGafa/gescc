package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SEXO database table.
 * 
 */
@Entity
@Table(name="SEXO")
public class Sexo implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TRAER_SEXO ="Sexo.TRAER_SEXO"; 
	
	@Id
	private int id;

	private String nombre;

	public Sexo() {
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
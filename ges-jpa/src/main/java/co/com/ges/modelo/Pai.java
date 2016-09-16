package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PAIS database table.
 * 
 */
@Entity
@Table(name="PAIS")
public class Pai implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LISTAR_PAIS = "Pai.LISTAR_PAIS";
	public static final String LISTAR_PAIS_POR_ID = "Pai.LISTAR_PAIS_POR_ID";
	public static final String EDITAR_PAIS = "Pai.EDITAR_PAIS";
	public static final String MAX_PAIS = "Pai.MAX_PAIS";

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nombre;

	public Pai() {
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
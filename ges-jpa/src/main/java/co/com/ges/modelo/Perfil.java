package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PERFIL database table.
 * 
 */
@Entity
@Table(name="PERFIL")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TRAER_PMX_ID = "Perfil.TRAER_PMX_ID";

	public static final String PERF_X_ID = "Perfil.PERF_X_ID";
	
	public static final String LISTAR_PERF = "Perfil.LISTAR_PERF";

	
	@Id
	private int id;

	private String descripcion;

	private String nombre;

	public Perfil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADOCASO database table.
 * 
 */
@Entity
@Table(name="ESTADOCASO")
public class Estadocaso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_MAX = "Estadocaso.ID_MAX";
	
	public static final String TRAER_EST_CAS = "Estadocaso.TRAER_EST_CAS";
	
	public static final String TRAER_EST_CAS_ID = "Estadocaso.TRAER_EST_CAS_ID";
	
	public static final String EDITAR_EST_CAS = "Estadocaso.EDITAR_EST_CAS";

	@Id
	private int id;

	private int asignacion;

	private int estado;

	private String nombre;

	public Estadocaso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAsignacion() {
		return this.asignacion;
	}

	public void setAsignacion(int asignacion) {
		this.asignacion = asignacion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
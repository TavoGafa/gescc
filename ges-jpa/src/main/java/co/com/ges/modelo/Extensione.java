package co.com.ges.modelo;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the EXTENSIONES database table.
 * 
 */
@Entity
@Table(name="EXTENSIONES")
public class Extensione implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_MX_ID = "Extensione.TRAER_MX_ID";

	public static final String EX_X_ID = "Extensione.EX_X_ID";
	
	public static final String LISTAR_EX = "Extensione.LISTAR_EX";

	@Id
	private String extension;

	private String descripcion;

	private String directorio;

	private String ejecutable;

	private short estado;

	private String fecha;

	public Extensione() {
	}

	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDirectorio() {
		return this.directorio;
	}

	public void setDirectorio(String directorio) {
		this.directorio = directorio;
	}

	public String getEjecutable() {
		return this.ejecutable;
	}

	public void setEjecutable(String ejecutable) {
		this.ejecutable = ejecutable;
	}

	public short getEstado() {
		return this.estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public String getFecha() {
		return this.fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
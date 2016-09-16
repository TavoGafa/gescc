package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ESTADO database table.
 * 
 */
@Entity
@Table(name="ESTADO")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_ESTADO = "Estado.TRAER_ESTADO";
	
	public static final String TRAER_ESTADO_ID = "Estado.TRAER_ESTADO_ID";
	
	public static final String TRAER_ESTADO_ID_MAX = "Estado.TRAER_ESTADO_ID_MAX";
	
	public static final String EDITA_ESTADO = "Estado.EDITA_ESTADO";
	
	public static final String ESTADO_PAIS = "Estado.ESTADO_PAIS";

	@Id
	private int id;

	private String abreviatura;

	private int idpais;

	private String nombre;

	public Estado() {
	}

	public Estado(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getIdpais() {
		return this.idpais;
	}

	public void setIdpais(int idpais) {
		this.idpais = idpais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
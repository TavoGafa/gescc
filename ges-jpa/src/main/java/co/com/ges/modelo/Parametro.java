package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PARAMETROS database table.
 * 
 */
@Entity
@Table(name="PARAMETROS")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_MAX_ID = "Parametro.TRAER_MX_ID";

	public static final String PAR_X_ID = "Parametro.PAR_X_ID";
	
	public static final String LISTAR_PAR = "Parametro.LISTAR_PAR";

	@Id
	private int id;

	private String direccion;

	private int estado;

	private int idciudadpobox;

	private int idzippobox;

	public Parametro() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getIdciudadpobox() {
		return this.idciudadpobox;
	}

	public void setIdciudadpobox(int idciudadpobox) {
		this.idciudadpobox = idciudadpobox;
	}

	public int getIdzippobox() {
		return this.idzippobox;
	}

	public void setIdzippobox(int idzippobox) {
		this.idzippobox = idzippobox;
	}

}
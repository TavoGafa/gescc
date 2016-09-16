package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the CIUDADZIP database table.
 * 
 */
@Entity
@Table(name="CIUDADZIP")
public class Ciudadzip implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String DELETE_ZIP = "Ciudadzip.DELETE_ZIP";
	
	public static final String LIST_ZIP_CIU = "Ciudadzip.LIST_ZIP_CIU";
	
	public static final String EDIT_ZIP_CIU = "Ciudadzip.EDIT_ZIP_CIU";
	
	public static final String ZIP_CIU_ID = "Ciudadzip.ZIP_CIU_ID";
	
	public static final String ZIP_CIU = "Ciudadzip.ZIP_CIU";
	
//	@EmbeddedId
//	private CiudadzipPK id;

	private int idciudad;

	@Id
	private int idzip;
	
	private Date fecha;

	private String idfuncionario;

	private int idtipofuncionario;

	public Ciudadzip() {
	}
	
//	public CiudadzipPK getId() {
//		return this.id;
//	}
//
//	public void setId(CiudadzipPK id) {
//		this.id = id;
//	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getIdfuncionario() {
		return this.idfuncionario;
	}

	public void setIdfuncionario(String idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public int getIdtipofuncionario() {
		return this.idtipofuncionario;
	}

	public void setIdtipofuncionario(int idtipofuncionario) {
		this.idtipofuncionario = idtipofuncionario;
	}
	
	public int getIdciudad() {
		return this.idciudad;
	}
	public void setIdciudad(int idciudad) {
		this.idciudad = idciudad;
	}
	public int getIdzip() {
		return this.idzip;
	}
	public void setIdzip(int idzip) {
		this.idzip = idzip;
	}


}
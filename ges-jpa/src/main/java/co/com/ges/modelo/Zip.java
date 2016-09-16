package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the ZIP database table.
 * 
 */
@Entity
@Table(name="ZIP")
public class Zip implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_ZMX_ID = "Zip.TRAER_ZMX_ID";

	public static final String EDITAR_ZIP = "Zip.EDITAR_ZIP";
	
	public static final String ZIP_X_ID = "Zip.ZIP_X_ID";
	
	public static final String LISTAR_ZIP = "Zip.LISTAR_ZIP";
	
	public static final String DELETE_ZIP = "Zip.DELETE_ZIP";
	
	public static final String ZIP_CIU = "Zip.ZIP_CIU";

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private String idfuncionario;

	private int idtipofuncionario;

	private String zipcode;

	public Zip() {
	}
	
	public Zip(int id, String zipcode) {
		super();
		this.id = id;
		this.zipcode = zipcode;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
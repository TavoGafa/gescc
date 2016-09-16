package co.com.ges.dto;

import java.io.Serializable;

public class CiudZipDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int idCiudad;
	
	private int idZip;
	
	private String nombreCiudad;
	
	private String codZip;

	public CiudZipDTO() {
		super();
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public int getIdZip() {
		return idZip;
	}

	public void setIdZip(int idZip) {
		this.idZip = idZip;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getCodZip() {
		return codZip;
	}

	public void setCodZip(String codZip) {
		this.codZip = codZip;
	}

	

}

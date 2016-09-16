package co.com.ges.dto;

import java.io.Serializable;

public class CiudadDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int codigoCiu;
	
	private String nombreCiu;
	
	public CiudadDTO(int codigoCiu, String nombreCiu) {
		super();
		this.codigoCiu = codigoCiu;
		this.nombreCiu = nombreCiu;
	}

	public int getCodigoCiu() {
		return codigoCiu;
	}

	public void setCodigoCiu(int codigoCiu) {
		this.codigoCiu = codigoCiu;
	}

	public String getNombreCiu() {
		return nombreCiu;
	}

	public void setNombreCiu(String nombreCiu) {
		this.nombreCiu = nombreCiu;
	}

	
	

}

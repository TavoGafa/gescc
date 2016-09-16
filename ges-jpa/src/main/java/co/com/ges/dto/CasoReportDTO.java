package co.com.ges.dto;

import java.io.Serializable;
import java.util.Date;

public class CasoReportDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String caso;
	
	private String numero;
	
	private String nombre;
	
	private String usuario;
	
	private String dependencia;
	
	private Date fecha;
	
	private String observaciones;
	
	public CasoReportDTO() {
		super();
	}

	public String getCaso() {
		return caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	
	

}

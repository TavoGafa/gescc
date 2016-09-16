package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the NOVEDADCASO database table.
 * 
 */
@Entity
@Table(name="NOVEDADCASO")
public class Novedadcaso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LIST_NOVE = "Novedadcaso.LIST_NOVE";
	
	public static final String LIST_NOVE_DTO = "Novedadcaso.LIST_NOVE_DTO";

	@Id
	private int id;

	private Timestamp fecha;

	private String idasignado;

	private String idcaso;

	private int idestado;

	private String idfuncionario;

	private int idtipoasignado;

	private int idtipofuncionario;

	private String observaciones;

	private boolean tiponovedad;

	public Novedadcaso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFecha() {
		return this.fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getIdasignado() {
		return this.idasignado;
	}

	public void setIdasignado(String idasignado) {
		this.idasignado = idasignado;
	}

	public String getIdcaso() {
		return this.idcaso;
	}

	public void setIdcaso(String idcaso) {
		this.idcaso = idcaso;
	}

	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	public String getIdfuncionario() {
		return this.idfuncionario;
	}

	public void setIdfuncionario(String idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public int getIdtipoasignado() {
		return this.idtipoasignado;
	}

	public void setIdtipoasignado(int idtipoasignado) {
		this.idtipoasignado = idtipoasignado;
	}

	public int getIdtipofuncionario() {
		return this.idtipofuncionario;
	}

	public void setIdtipofuncionario(int idtipofuncionario) {
		this.idtipofuncionario = idtipofuncionario;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean getTiponovedad() {
		return this.tiponovedad;
	}

	public void setTiponovedad(boolean tiponovedad) {
		this.tiponovedad = tiponovedad;
	}

}
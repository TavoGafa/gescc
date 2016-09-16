package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CONTRATISTA database table.
 * 
 */
@Entity
@Table(name="CONTRATISTA")
public class Contratista implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private short estado;

	private String idcaso;

	private String nombre;

	private String ssitin;

	public Contratista() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getEstado() {
		return this.estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public String getIdcaso() {
		return this.idcaso;
	}

	public void setIdcaso(String idcaso) {
		this.idcaso = idcaso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSsitin() {
		return this.ssitin;
	}

	public void setSsitin(String ssitin) {
		this.ssitin = ssitin;
	}

}
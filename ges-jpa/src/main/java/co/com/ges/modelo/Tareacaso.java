package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TAREACASO database table.
 * 
 */
@Entity
@Table(name="TAREACASO")
public class Tareacaso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descripcion;

	private short estado;

	private int idanio;

	private String idcaso;

	private String idfuncionario;

	private int idtipofuncionario;

	private int idtipotarea;

	public Tareacaso() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public short getEstado() {
		return this.estado;
	}

	public void setEstado(short estado) {
		this.estado = estado;
	}

	public int getIdanio() {
		return this.idanio;
	}

	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}

	public String getIdcaso() {
		return this.idcaso;
	}

	public void setIdcaso(String idcaso) {
		this.idcaso = idcaso;
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

	public int getIdtipotarea() {
		return this.idtipotarea;
	}

	public void setIdtipotarea(int idtipotarea) {
		this.idtipotarea = idtipotarea;
	}

}
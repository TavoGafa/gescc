package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the NOVEDADTAREA database table.
 * 
 */
@Entity
@Table(name="NOVEDADTAREA")
public class Novedadtarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean estado;

	private Date fecha;

	private String idfuncionario;

	private int idtarea;

	private int idtipofuncionario;

	public Novedadtarea() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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

	public int getIdtarea() {
		return this.idtarea;
	}

	public void setIdtarea(int idtarea) {
		this.idtarea = idtarea;
	}

	public int getIdtipofuncionario() {
		return this.idtipofuncionario;
	}

	public void setIdtipofuncionario(int idtipofuncionario) {
		this.idtipofuncionario = idtipofuncionario;
	}

}
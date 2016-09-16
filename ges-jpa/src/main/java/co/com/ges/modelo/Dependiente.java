package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DEPENDIENTE database table.
 * 
 */
@Entity
@Table(name="DEPENDIENTE")
public class Dependiente implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DependientePK id;

	private int idanio;

	private int idtipoparentesco;

	private String nombremama;

	public Dependiente() {
	}

	public DependientePK getId() {
		return this.id;
	}

	public void setId(DependientePK id) {
		this.id = id;
	}

	public int getIdanio() {
		return this.idanio;
	}

	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}

	public int getIdtipoparentesco() {
		return this.idtipoparentesco;
	}

	public void setIdtipoparentesco(int idtipoparentesco) {
		this.idtipoparentesco = idtipoparentesco;
	}

	public String getNombremama() {
		return this.nombremama;
	}

	public void setNombremama(String nombremama) {
		this.nombremama = nombremama;
	}

}
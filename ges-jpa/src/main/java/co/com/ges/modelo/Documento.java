package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the DOCUMENTOS database table.
 * 
 */
@Entity
@Table(name="DOCUMENTOS")
public class Documento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Timestamp fecha;

	private String idcaso;

	private int idtipodoc;

	private String nombre;

	private String ruta;

	public Documento() {
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

	public String getIdcaso() {
		return this.idcaso;
	}

	public void setIdcaso(String idcaso) {
		this.idcaso = idcaso;
	}

	public int getIdtipodoc() {
		return this.idtipodoc;
	}

	public void setIdtipodoc(int idtipodoc) {
		this.idtipodoc = idtipodoc;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRuta() {
		return this.ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

}
package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the VEHICULO database table.
 * 
 */
@Entity
@Table(name="VEHICULO")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int anio;

	private Date fecha;

	private int idanio;

	private int idcontratista;

	private String marca;

	private double millas;

	public Vehiculo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnio() {
		return this.anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdanio() {
		return this.idanio;
	}

	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}

	public int getIdcontratista() {
		return this.idcontratista;
	}

	public void setIdcontratista(int idcontratista) {
		this.idcontratista = idcontratista;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public double getMillas() {
		return this.millas;
	}

	public void setMillas(double millas) {
		this.millas = millas;
	}

}
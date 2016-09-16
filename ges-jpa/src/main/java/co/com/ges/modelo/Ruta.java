package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the RUTAS database table.
 * 
 */
@Entity
@Table(name="RUTAS")
public class Ruta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String ID_MAX = "Ruta.ID_MAX";
	
	public static final String TRAER_RUT = "Ruta.TRAER_RUT";
	
	public static final String TRAER_RUT_ID = "Ruta.TRAER_RUT_ID";
	
	@Id
	private int id;

	private String nombrebanco;

	private String numero;

	public Ruta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombrebanco() {
		return this.nombrebanco;
	}

	public void setNombrebanco(String nombrebanco) {
		this.nombrebanco = nombrebanco;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
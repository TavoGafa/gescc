package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the PREGUNTA database table.
 * 
 */
@Entity
@Table(name="PREGUNTA")
public class Pregunta implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String TRAER_MAX_ID = "Pregunta.TRAER_MX_ID";

	public static final String PREG_X_ID = "Pregunta.PREG_X_ID";
	
	public static final String LISTAR_PREG = "Pregunta.LISTAR_PREG";
	
	@Id
	private int id;

	private String pregunta;

	public Pregunta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

}
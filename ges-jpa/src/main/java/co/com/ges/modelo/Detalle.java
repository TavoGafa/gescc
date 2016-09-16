package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the DETALLE database table.
 * 
 */
@Entity
@Table(name="DETALLE")
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String ID_MAX = "Detalle.ID_MAX";
	
	public static final String TRAER_DET = "Detalle.TRAER_DET";
	
	public static final String TRAER_DET_ID = "Detalle.TRAER_DET_ID";
	
	public static final String EDITAR_DET_CAS = "Detalle.EDITAR_DET";

	
	@Id
	private int id;

	private String descripcion;

	private BigDecimal valor;

	public Detalle() {
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GASTO database table.
 * 
 */
@Entity
@Table(name="GASTO")
public class Gasto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int idanio;

	private int idcontratista;

	private int idtipogasto;

	private BigDecimal valor;

	public Gasto() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getIdtipogasto() {
		return this.idtipogasto;
	}

	public void setIdtipogasto(int idtipogasto) {
		this.idtipogasto = idtipogasto;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
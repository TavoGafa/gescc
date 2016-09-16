package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the EMPLEADO database table.
 * 
 */
@Entity
@Table(name="EMPLEADO")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int idanio;

	private int idcontratista;

	private String idpersona;

	private int idtipopersona;

	private BigDecimal valor;

	public Empleado() {
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

	public String getIdpersona() {
		return this.idpersona;
	}

	public void setIdpersona(String idpersona) {
		this.idpersona = idpersona;
	}

	public int getIdtipopersona() {
		return this.idtipopersona;
	}

	public void setIdtipopersona(int idtipopersona) {
		this.idtipopersona = idtipopersona;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
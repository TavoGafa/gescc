package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the PAGO database table.
 * 
 */
@Entity
@Table(name="PAGO")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Timestamp fecha;

	private String idcaso;

	private int idformapago;

	private String idfuncionario;

	private int idtipofuncionario;

	private BigDecimal valor;

	public Pago() {
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

	public int getIdformapago() {
		return this.idformapago;
	}

	public void setIdformapago(int idformapago) {
		this.idformapago = idformapago;
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

	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
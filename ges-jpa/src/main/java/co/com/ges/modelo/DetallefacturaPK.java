package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DETALLEFACTURA database table.
 * 
 */
@Embeddable
public class DetallefacturaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String idfactura;

	private int idarticulo;

	public DetallefacturaPK() {
	}
	public String getIdfactura() {
		return this.idfactura;
	}
	public void setIdfactura(String idfactura) {
		this.idfactura = idfactura;
	}
	public int getIdarticulo() {
		return this.idarticulo;
	}
	public void setIdarticulo(int idarticulo) {
		this.idarticulo = idarticulo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DetallefacturaPK)) {
			return false;
		}
		DetallefacturaPK castOther = (DetallefacturaPK)other;
		return 
			this.idfactura.equals(castOther.idfactura)
			&& (this.idarticulo == castOther.idarticulo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idfactura.hashCode();
		hash = hash * prime + this.idarticulo;
		
		return hash;
	}
}
package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DETALLEFACTURA database table.
 * 
 */
@Entity
@Table(name="DETALLEFACTURA")
public class Detallefactura implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@EmbeddedId
	private DetallefacturaPK id;

	private int cantidad;

	public Detallefactura() {
	}

	public DetallefacturaPK getId() {
		return this.id;
	}

	public void setId(DetallefacturaPK id) {
		this.id = id;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

}
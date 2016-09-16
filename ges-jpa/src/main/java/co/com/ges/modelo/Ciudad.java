package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@Table(name="CIUDAD")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_AMX_ID = "Ciudad.TRAER_AMX_ID";

	public static final String EDITAR_CIUDAD = "Ciudad.EDITAR_CIUDAD";
	
	public static final String CIUDAD_X_ID = "Ciudad.CIUDAD_X_ID";
	
	public static final String LISTAR_CIUDAD = "Ciudad.LISTAR_CIUDAD";
	
	public static final String TAER_CIUDAD_EST = "Ciudad.TAER_CIUDAD_EST";
	
	public static final String DELETE_CIUDAD = "Ciudad.DELETE_CIUDAD";
	
	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private Integer idestado;

	private String idfuncionario;

	private int idtipofuncionario;

	private String nombre;

	public Ciudad() {
	}
	
	public Ciudad(int id, String nombre, int idEstado, Date fecha) {
		this.id = id;
		this.nombre = nombre;
		this.idestado = idEstado;
		this.fecha = fecha;
	}

	public Ciudad(int id, String nombre, int idEstado ) {
		this.id = id;
		this.nombre = nombre;
		this.idestado = idEstado;
	}
	
	public Ciudad(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdestado() {
		return this.idestado;
	}

	public void setIdestado(Integer idestado) {
		this.idestado = idestado;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
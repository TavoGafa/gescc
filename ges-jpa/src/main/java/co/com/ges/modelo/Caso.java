package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the CASO database table.
 * 
 */
@Entity
@Table(name="CASO")
public class Caso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String LIST_NOVE_DTO ="Caso.LIST_NOVE_DTO";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String numerocaso;

	private String aptogiro;

	private int ciudadgiro;

	private boolean contratista;

	private String cuenta;

	private String dirgiro;

	private Date fecha;

	private Timestamp fechanov;

	private int idanio;

	private int idenvio;

	private int idestado;

	private String idfuncionario;

	private String idfuncionario1;

	private int idpago;

	private String idpersona;

	private int idrutabanco;

	private int idtipofuncionario;

	private int idtipofuncionario1;

	private int idtipopersona;

	private String observacion;

	private int zipgiro;

	public Caso() {
	}

	public String getNumerocaso() {
		return this.numerocaso;
	}

	public void setNumerocaso(String numerocaso) {
		this.numerocaso = numerocaso;
	}

	public String getAptogiro() {
		return this.aptogiro;
	}

	public void setAptogiro(String aptogiro) {
		this.aptogiro = aptogiro;
	}

	public int getCiudadgiro() {
		return this.ciudadgiro;
	}

	public void setCiudadgiro(int ciudadgiro) {
		this.ciudadgiro = ciudadgiro;
	}

	public boolean getContratista() {
		return this.contratista;
	}

	public void setContratista(boolean contratista) {
		this.contratista = contratista;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDirgiro() {
		return this.dirgiro;
	}

	public void setDirgiro(String dirgiro) {
		this.dirgiro = dirgiro;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Timestamp getFechanov() {
		return this.fechanov;
	}

	public void setFechanov(Timestamp fechanov) {
		this.fechanov = fechanov;
	}

	public int getIdanio() {
		return this.idanio;
	}

	public void setIdanio(int idanio) {
		this.idanio = idanio;
	}

	public int getIdenvio() {
		return this.idenvio;
	}

	public void setIdenvio(int idenvio) {
		this.idenvio = idenvio;
	}

	public int getIdestado() {
		return this.idestado;
	}

	public void setIdestado(int idestado) {
		this.idestado = idestado;
	}

	public String getIdfuncionario() {
		return this.idfuncionario;
	}

	public void setIdfuncionario(String idfuncionario) {
		this.idfuncionario = idfuncionario;
	}

	public String getIdfuncionario1() {
		return this.idfuncionario1;
	}

	public void setIdfuncionario1(String idfuncionario1) {
		this.idfuncionario1 = idfuncionario1;
	}

	public int getIdpago() {
		return this.idpago;
	}

	public void setIdpago(int idpago) {
		this.idpago = idpago;
	}

	public String getIdpersona() {
		return this.idpersona;
	}

	public void setIdpersona(String idpersona) {
		this.idpersona = idpersona;
	}

	public int getIdrutabanco() {
		return this.idrutabanco;
	}

	public void setIdrutabanco(int idrutabanco) {
		this.idrutabanco = idrutabanco;
	}

	public int getIdtipofuncionario() {
		return this.idtipofuncionario;
	}

	public void setIdtipofuncionario(int idtipofuncionario) {
		this.idtipofuncionario = idtipofuncionario;
	}

	public int getIdtipofuncionario1() {
		return this.idtipofuncionario1;
	}

	public void setIdtipofuncionario1(int idtipofuncionario1) {
		this.idtipofuncionario1 = idtipofuncionario1;
	}

	public int getIdtipopersona() {
		return this.idtipopersona;
	}

	public void setIdtipopersona(int idtipopersona) {
		this.idtipopersona = idtipopersona;
	}

	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public int getZipgiro() {
		return this.zipgiro;
	}

	public void setZipgiro(int zipgiro) {
		this.zipgiro = zipgiro;
	}

}
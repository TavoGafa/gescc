package co.com.ges.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the PERSONA database table.
 * 
 */
@Entity
@Table(name="PERSONA")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String CONSULTAR_EMPRESAS = "Persona.CONSULTAR_EMPRESAS";
	public static final String CONSULTAR_EMPRESAS_USUARIO = "Persona.CONSULTAR_EMPRESAS_USUARIO";
	public static final String CONSULTAR_EMPRESAS_USUARIO_D = "Persona.CONSULTAR_EMPRESAS_USUARIO_D";
	public static final String UPDATE_EMPRESAS = "Persona.UPDATE_EMPRESAS";

	@EmbeddedId
	private PersonaPK id;

	private String apartamento;

	private String apellido1;

	private String apellido2;

	private int ciudadnacimiento;

	private int ciudadorigen;

	private int ciudadusa;

	private String direccionorigen;

	private String direccionusa;

	private String email;

	private String expiracionpasaporte;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaentrada;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechanacimiento;

	private int idestadocivil;

	private int idsexo;

	private String nombre1;

	private String nombre2;

	private String pasaporte;

	private String telefono1;

	private String telefono2;

	private int zipusa;
	
	private String tipo;
	
	private String obama;

	public Persona() {
	}
	
	public Persona(PersonaPK id, String nombre1) {
		super();
		this.id = id;
		this.nombre1 = nombre1;
	}

	public PersonaPK getId() {
		return this.id;
	}

	public void setId(PersonaPK id) {
		this.id = id;
	}

	public String getApartamento() {
		return this.apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getCiudadnacimiento() {
		return this.ciudadnacimiento;
	}

	public void setCiudadnacimiento(int ciudadnacimiento) {
		this.ciudadnacimiento = ciudadnacimiento;
	}

	public int getCiudadorigen() {
		return this.ciudadorigen;
	}

	public void setCiudadorigen(int ciudadorigen) {
		this.ciudadorigen = ciudadorigen;
	}

	public int getCiudadusa() {
		return this.ciudadusa;
	}

	public void setCiudadusa(int ciudadusa) {
		this.ciudadusa = ciudadusa;
	}

	public String getDireccionorigen() {
		return this.direccionorigen;
	}

	public void setDireccionorigen(String direccionorigen) {
		this.direccionorigen = direccionorigen;
	}

	public String getDireccionusa() {
		return this.direccionusa;
	}

	public void setDireccionusa(String direccionusa) {
		this.direccionusa = direccionusa;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getExpiracionpasaporte() {
		return this.expiracionpasaporte;
	}

	public void setExpiracionpasaporte(String expiracionpasaporte) {
		this.expiracionpasaporte = expiracionpasaporte;
	}

	public Date getFechaentrada() {
		return this.fechaentrada;
	}

	public void setFechaentrada(Date fechaentrada) {
		this.fechaentrada = fechaentrada;
	}

	public Date getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(Date fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
	}

	public int getIdestadocivil() {
		return this.idestadocivil;
	}

	public void setIdestadocivil(int idestadocivil) {
		this.idestadocivil = idestadocivil;
	}

	public int getIdsexo() {
		return this.idsexo;
	}

	public void setIdsexo(int idsexo) {
		this.idsexo = idsexo;
	}

	public String getNombre1() {
		return this.nombre1;
	}

	public void setNombre1(String nombre1) {
		this.nombre1 = nombre1;
	}

	public String getNombre2() {
		return this.nombre2;
	}

	public void setNombre2(String nombre2) {
		this.nombre2 = nombre2;
	}

	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public int getZipusa() {
		return this.zipusa;
	}

	public void setZipusa(int zipusa) {
		this.zipusa = zipusa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObama() {
		return obama;
	}

	public void setObama(String obama) {
		this.obama = obama;
	}

}

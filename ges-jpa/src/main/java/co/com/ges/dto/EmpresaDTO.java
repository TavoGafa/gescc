package co.com.ges.dto;

import java.io.Serializable;

public class EmpresaDTO implements  Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombreEmpresa;
	private String industria;
	private String tipoNego;
	private String ein;
	private String direccion;
	private String ciudad;
	private String estado;
	private String zip;
	private String pais;
	private String telf;
	private String usuario;
	private String email;
	private String fax;
	private String pass;
	private String apt; 
	
	public EmpresaDTO() {
		super();
	}
	
	
	public EmpresaDTO(String nombreEmpresa, String industria, String tipoNego, String ein, String direccion, String ciudad,
			String estado, String zip, String pais, String telf, String apt) {
		super();
		this.nombreEmpresa = nombreEmpresa;
		this.industria = industria;
		this.tipoNego = tipoNego;
		this.ein = ein;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.estado = estado;
		this.zip = zip;
		this.pais = pais;
		this.telf = telf;
		this.apt = apt;
	}


	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getIndustria() {
		return industria;
	}
	public void setIndustria(String industria) {
		this.industria = industria;
	}
	public String getTipoNego() {
		return tipoNego;
	}
	public void setTipoNego(String tipoNego) {
		this.tipoNego = tipoNego;
	}
	public String getEin() {
		return ein;
	}
	public void setEin(String ein) {
		this.ein = ein;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTelf() {
		return telf;
	}
	public void setTelf(String telf) {
		this.telf = telf;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}


	public String getApt() {
		return apt;
	}


	public void setApt(String apt) {
		this.apt = apt;
	}
	
	

}

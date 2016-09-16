package co.com.ges.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Ciudad;
import co.com.ges.modelo.Estadocaso;
import co.com.ges.modelo.Funcionario;
import co.com.ges.servicio.GesServicio;

public class CiudadControl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int codC;
	private String nomCiud;
	private List<Ciudad> ciud;
	private List<Ciudad> contFilter;
	private int codEsta;
	private DataTable dtActu;
	private String user;
	private Properties prop;
	private Funcionario f;
	private String paisc;
	@EJB
	GesServicio servicio;
	
	 public CiudadControl()
	{
		codC = 0;
		ciud = new ArrayList<Ciudad>();
		codEsta = 0;
		prop = new Properties();
		f = new Funcionario();
		codC = 0;
		codEsta = 0;
		cargarPropiedades();
	}
	 
	public void cargarPropiedades() {
		prop = new Properties();
		try {
			prop.load(CiudadControl.class.getResourceAsStream("mensajes.properties"));
			user = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name");
			f = (Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void cargarPermisos()
	{
		@SuppressWarnings("unchecked")
		List<OpcionesMenuUsuario> opc = (List<OpcionesMenuUsuario>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try {
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargarDatos() {
		try {
			dtActu = new DataTable();
			contFilter = new ArrayList<Ciudad>();
			codC = servicio.maxCodCiu().intValue();
			ciud = servicio.listarCiudad();
			dtActu.setValue(ciud);
			contFilter = ciud;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crearCiudad() {
		try {
			if (nomCiud != null && !nomCiud.equals("") && codEsta > 0) {
				Ciudad c = new Ciudad();
				c.setId(codC);
				c.setFecha(new Date());
				c.setNombre(nomCiud);
				c.setIdestado(Integer.valueOf(codEsta));
				c.setIdfuncionario(user);
				c.setIdtipofuncionario(f.getIdperfil());
				servicio.crearCiudad(c);
				limpiarDatos();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuardadoCiu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreaCiudad")));
		}
	}
	
	public void editCiudad(Ciudad p) {
		try {
			if (p != null) {
				p.setIdfuncionario(user);
				p.setFecha(new Date());
				p.setIdtipofuncionario(f.getIdperfil());
				servicio.editarCiudad(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditCiudad")));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorEditarCiu")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorEditarPais")));
		}
	}


	public void deleteCiudad(Ciudad p) {
		try {
			servicio.delete(p);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> busquedaCiu(String nombre) {
		List<String> p = new ArrayList<String>();
		for (Ciudad pa : ciud) {
			if (pa.getNombre().contains(nombre)) {
				p.add(pa.getNombre());
			}

		}
		return p;
	}


	public void limpiarDatos() {
		nomCiud = null;
		codEsta = 0;
		cargarDatos();
	}
	
	public int getCodC() {
		return codC;
	}

	public void setCodC(int codC) {
		this.codC = codC;
	}

	public String getNomCiud() {
		return nomCiud;
	}

	public void setNomCiud(String nomCiud) {
		this.nomCiud = nomCiud;
	}

	public List<Ciudad> getCiud() {
		return ciud;
	}

	public void setCiud(List<Ciudad> ciud) {
		this.ciud = ciud;
	}

	public List<Ciudad> getContFilter() {
		return contFilter;
	}

	public void setContFilter(List<Ciudad> contFilter) {
		this.contFilter = contFilter;
	}

	public int getCodEsta() {
		return codEsta;
	}

	public void setCodEsta(int codEsta) {
		this.codEsta = codEsta;
	}

	public DataTable getDtActu() {
		return dtActu;
	}

	public void setDtActu(DataTable dtActu) {
		this.dtActu = dtActu;
	}

	public String getPaisc() {
		return paisc;
	}

	public void setPaisc(String paisc) {
		this.paisc = paisc;
	}


}

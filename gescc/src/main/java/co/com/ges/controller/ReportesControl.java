package co.com.ges.controller;

import co.com.ges.dto.CasoReportDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Funcionario;
import co.com.ges.servicio.GesServicio;
import co.com.ges.util.Reports;
import co.com.ges.vistas.VFuncionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "repor")
@SessionScoped
public class ReportesControl implements Serializable {
	private static final long serialVersionUID = 1L;
	private Properties prop = new Properties();
	private String user;
	private Funcionario f = new Funcionario();
	private String caso = null;
	private Integer estado = Integer.valueOf(0);
	private String funcio = null;
	private List<VFuncionario> vfun = new ArrayList();
	private List<CasoReportDTO> detalle = new ArrayList();
	private boolean hc;
	private boolean sc;
	private boolean oc;
	private boolean thc = true;
	private boolean tscb = true;
	private boolean tocb = true;
	@EJB
	GesServicio servicio;
	private Map parametros;

	public ReportesControl() {
		cargarPropiedades();
	}

	public void cargarPropiedades() {
		prop = new Properties();
		try {
			prop.load(AdministracionControl.class.getResourceAsStream("mensajes.properties"));
			user = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name"));
			f = ((Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void cargarPermisos() {
		List<OpcionesMenuUsuario> opc = (List) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try {
			vfun = servicio.traerFuncionario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void consultarReporte() {
		try {
			if ((this.caso != null) && (!this.caso.equals(""))) {
				detalle = this.servicio.listarNovedad(this.caso);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errocampoB")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errorMensa")));
		}
	}

	public void generarRepote() {
		Reports r = new Reports();
		if ((caso != null) && (!caso.equals(""))) {
			r.crearReporte(1, this.caso);
		} else {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errocampoB")));
		}
	}

	public void activeHc() {
		if (this.hc == true) {
			this.thc = false;
			this.tscb = true;
			this.tocb = true;
		} else {
			this.thc = true;
			this.tscb = true;
			this.tocb = true;
		}
	}

	public void activeSc() {
		if (this.sc == true) {
			this.thc = false;
			this.tscb = true;
			this.tocb = true;
		} else {
			this.thc = true;
			this.tscb = true;
			this.tocb = true;
		}
	}

	public void activeOc() {
		if (this.oc == true) {
			this.thc = false;
			this.tscb = true;
			this.tocb = true;
		} else {
			this.thc = true;
			this.tscb = true;
			this.tocb = true;
		}
	}

	public void consultarEstado() {
	}

	public void consultarFunciona() {
	}

	public String getCaso() {
		return this.caso;
	}

	public void setCaso(String caso) {
		this.caso = caso;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getFuncio() {
		return this.funcio;
	}

	public void setFuncio(String funcio) {
		this.funcio = funcio;
	}

	public List<VFuncionario> getVfun() {
		return this.vfun;
	}

	public void setVfun(List<VFuncionario> vfun) {
		this.vfun = vfun;
	}

	public List<CasoReportDTO> getDetalle() {
		return this.detalle;
	}

	public void setDetalle(List<CasoReportDTO> detalle) {
		this.detalle = detalle;
	}

	public boolean isHc() {
		return this.hc;
	}

	public void setHc(boolean hc) {
		this.hc = hc;
	}

	public boolean isThc() {
		return this.thc;
	}

	public void setThc(boolean thc) {
		this.thc = thc;
	}

	public boolean isTscb() {
		return this.tscb;
	}

	public void setTscb(boolean tscb) {
		this.tscb = tscb;
	}

	public boolean isTocb() {
		return this.tocb;
	}

	public void setTocb(boolean tocb) {
		this.tocb = tocb;
	}

	public boolean isSc() {
		return this.sc;
	}

	public void setSc(boolean sc) {
		this.sc = sc;
	}

	public boolean isOc() {
		return this.oc;
	}

	public void setOc(boolean oc) {
		this.oc = oc;
	}
}

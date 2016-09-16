package co.com.ges.controller;

import co.com.ges.dto.CiudadDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.enums.AsignacionEnum;
import co.com.ges.modelo.Ciudad;
import co.com.ges.modelo.Dependiente;
import co.com.ges.modelo.Estado;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.FuncionarioPK;
import co.com.ges.modelo.Persona;
import co.com.ges.modelo.PersonaPK;
import co.com.ges.modelo.Sexo;
import co.com.ges.modelo.Zip;
import co.com.ges.servicio.GesServicio;
import co.com.ges.vistas.VFuncionario;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name = "cust")
@SessionScoped
public class CustumersControl implements Serializable {
	private static final long serialVersionUID = 1L;
	private Properties prop = new Properties();
	private String user;
	private Funcionario f = new Funcionario();
	private List<VFuncionario> lisVf = new ArrayList<VFuncionario>();
	private VFuncionario vf = new VFuncionario();
	private int totolReg = 0;
	private DataTable dtActuOff;
	private List<VFuncionario> offFilter;
	private boolean ventVer = false;
	private boolean vPrin = true;
	private Funcionario fun = new Funcionario();
	private Persona per = new Persona();
	private List<Sexo> lisSex = new ArrayList<Sexo>();
	private List<Ciudad> lisCiuE = new ArrayList<Ciudad>();
	private List<Ciudad> lisCiuEC = new ArrayList<Ciudad>();
	private List<CiudadDTO> lisCiuEO = new ArrayList<CiudadDTO>();
	private List<Estado> lisEstP = new ArrayList<Estado>();
	private List<Estado> lisEstPa = new ArrayList<Estado>();
	private List<Zip> liszipc = new ArrayList<Zip>();
	private int idEstado = 0;
	private int idEstadoO = 0;
	private int idzipciu = 0;
	private boolean obm;
	private List<Dependiente> depen = new ArrayList<Dependiente>();
	private Date fechaNa = new Date();
	private Date feDateV = new Date();
	private int idtipo = 0;
	private String nit = null;
	private boolean tabU = true;
	private int paisd;
	private int paise;
	private int paisn;
	private int estadon;
	private String nombre1;
	private String nombre2;
	private String apellido1;
	private String apellido2;
	private int idestadocivil;
	private int idsexo;
	private int ciudadnacimiento;
	private String pasaporte;
	private String direccionusa;
	private int ciudadusa;
	private int zipusa;
	private int ciudadorigen;
	private String telefono1;
	private String telefono2;
	private String email;
	private Pattern pattern;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@EJB
	GesServicio servicio;


	public CustumersControl() {
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
		@SuppressWarnings("unchecked")
		List<OpcionesMenuUsuario> opc = (List) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try {
			dtActuOff = new DataTable();
			cargarDatos();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void activarTab() {
		if ((nit != null && !nit.equals("")) && (idtipo > 0) && (per.getNombre1() != null)
				&& (!per.getNombre1().equals(""))) {
			PersonaPK perpk = new PersonaPK();
			perpk.setIdtipo(idtipo);
			perpk.setSsitin(nit);
			per.setId(perpk);
			tabU = false;
		} else {
			tabU = true;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faltan Campos Obligatorios"));
		}
	}

	public void cargarDatos() {
		try {
			lisVf = servicio.traerFuncionario();
			lisSex = servicio.listarSexo();
			lisEstPa = servicio.traerEstadoPais(1);
			if (lisVf.size() > 0) {
				totolReg = lisVf.size();
				offFilter = lisVf;
				dtActuOff.setValue(lisVf);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorTraerDat")));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCarga")));
		}
	}

	public void abrirPanel() {
		ventVer = true;
		vPrin = false;
		per = new Persona();
		lisCiuE = new ArrayList<Ciudad>();
		lisCiuEO = new ArrayList<CiudadDTO>();
	}

	public void crearFunciorio() {
		try {
			Persona p = new Persona();
			PersonaPK pk = new PersonaPK();
			pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher m = pattern.matcher(email);
			if (m.matches()) {
				per.setNombre1(nombre1);
				per.setNombre2(nombre2);
				per.setApellido1(apellido1);
				per.setApellido2(apellido2);
				per.setFechanacimiento(fechaNa);
				per.setIdestadocivil(idestadocivil);
				per.setIdsexo(idsexo);
				per.setCiudadnacimiento(ciudadnacimiento);
				per.setPasaporte(pasaporte);
				per.setDireccionusa(direccionusa);
				per.setCiudadusa(ciudadusa);
				per.setZipusa(zipusa);
				per.setCiudadorigen(ciudadnacimiento);
				per.setTelefono1(telefono1);
				per.setTelefono2(telefono2);
				per.setEmail(email);
				SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
				String fec = f.format(feDateV);
				per.setExpiracionpasaporte(fec);
				per.setApartamento("");
				per.setFechaentrada(new Date());
				if(obm != false) {
					per.setObama(AsignacionEnum.SI.getNombre());
				} else {
					per.setObama(AsignacionEnum.NO.getNombre());
				}
				pk.setIdtipo(idtipo);
				pk.setSsitin(nit);
				per.setId(pk);
				per.setTipo("C");

				servicio.crearEmpresa(per);

				FuncionarioPK fpk = new FuncionarioPK();
				fpk.setIdentificacion(nit);
				fpk.setIdtipopersona(idtipo);
				fun.setEstadodel((short) 1);
				fun.setId(fpk);
				servicio.crearFuncionario(fun);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "", "OFFICER CREATE"));
				limpiar();
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "INVALID MAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroFUnci")));
		}
	}

	public void editarOfficer(VFuncionario vfe) {
	}

	public void activarCampos(ValueChangeEvent e) {
		try {
			List<CiudadDTO> c = new ArrayList<CiudadDTO>();
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idEstado = Integer.valueOf((String) e.getNewValue()).intValue();
				if (idEstado > 0) {
					lisCiuE = new ArrayList<Ciudad>();
					c = servicio.ciudXEstad(idEstado);
					for (CiudadDTO cd : c) {
						Ciudad lc = new Ciudad(cd.getCodigoCiu(), cd.getNombreCiu());
						lisCiuE.add(lc);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarCamposP(ValueChangeEvent e) {
		try {
			List<CiudadDTO> c = new ArrayList<CiudadDTO>();
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idEstado = ((Integer) e.getNewValue()).intValue();
				if (idEstado > 0) {
					lisCiuEC = new ArrayList<Ciudad>();
					c = servicio.ciudXEstad(idEstado);
					for (CiudadDTO cd : c) {
						Ciudad lc = new Ciudad(cd.getCodigoCiu(), cd.getNombreCiu());
						lisCiuEC.add(lc);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarCamposD(ValueChangeEvent e) {
		try {
			List<CiudadDTO> c = new ArrayList<CiudadDTO>();
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idEstado = ((Integer) e.getNewValue()).intValue();
				if (idEstado > 0) {
					lisCiuE = new ArrayList<Ciudad>();
					c = servicio.ciudXEstad(idEstado);
					for (CiudadDTO cd : c) {
						Ciudad lc = new Ciudad(cd.getCodigoCiu(), cd.getNombreCiu());
						lisCiuE.add(lc);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarCamposE(ValueChangeEvent e) {
		try {
			List<CiudadDTO> c = new ArrayList<CiudadDTO>();
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idEstado = ((Integer) e.getNewValue()).intValue();
				if (idEstado > 0) {
					lisCiuE = new ArrayList<Ciudad>();
					c = servicio.ciudXEstad(idEstado);
					for (CiudadDTO cd : c) {
						Ciudad lc = new Ciudad(cd.getCodigoCiu(), cd.getNombreCiu());
						lisCiuE.add(lc);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarCamposO(ValueChangeEvent e) {
		try {
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idzipciu = Integer.valueOf((String) e.getNewValue()).intValue();
				if (idzipciu > 0) {
					liszipc = new ArrayList<Zip>();
					liszipc = servicio.traerCiudadZip(idzipciu);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarCamposOs(ValueChangeEvent e) {
		try {
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				idzipciu = ((Integer) e.getNewValue()).intValue();
				if (idzipciu > 0) {
					liszipc = new ArrayList<Zip>();
					liszipc = servicio.traerCiudadZip(idzipciu);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarEstado(ValueChangeEvent e) {
		try {
			int pais = 0;
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				pais = Integer.valueOf((String) e.getNewValue()).intValue();
				if (pais > 0) {
					lisEstP = new ArrayList<Estado>();
					lisEstP = servicio.traerEstadoPais(pais);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarEstadoP(ValueChangeEvent e) {
		try {
			int pais = 0;
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				pais = ((Integer) e.getNewValue()).intValue();
				if (pais > 0) {
					lisEstP = new ArrayList<Estado>();
					lisEstP = servicio.traerEstadoPais(pais);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarEstadoE(ValueChangeEvent e) {
		try {
			int pais = 0;
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				pais = ((Integer) e.getNewValue()).intValue();
				if (pais > 0) {
					lisEstP = new ArrayList<Estado>();
					lisEstP = servicio.traerEstadoPais(pais);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void activarEstadoD(ValueChangeEvent e) {
		try {
			int pais = 0;
			if ((e.getNewValue() != null) && (!e.getNewValue().equals(""))) {
				pais = ((Integer) e.getNewValue()).intValue();
				if (pais > 0) {
					lisEstP = new ArrayList<Estado>();
					lisEstP = servicio.traerEstadoPais(pais);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void guardarEmpresa() {
		try {
			if ((per.getId().getIdtipo() > 0) && (per.getId().getSsitin() != null)
					&& (!per.getId().getSsitin().equals(""))) {
				servicio.crearEmpresa(per);
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faltan Campos Obligatorios"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Error al Crear Empresa"));
		}
	}

	public void limpiar() {
		nombre1 = null;
		nombre2 = null;
		apellido1 = null;
		apellido2 = null;
		idestadocivil = 0;
		idsexo = 0;
		ciudadnacimiento = 0;
		pasaporte = null;
		direccionusa = null;
		ciudadusa = 0;
		zipusa = 0;
		ciudadorigen = 0;
		telefono1 = null;
		telefono2 = null;
		email = null;
		nit = null;
		idtipo = 0;
		fun = new Funcionario();
	}
	
	public List<VFuncionario> getLisVf() {
		return this.lisVf;
	}

	public void setLisVf(List<VFuncionario> lisVf) {
		this.lisVf = lisVf;
	}

	public VFuncionario getVf() {
		return this.vf;
	}

	public void setVf(VFuncionario vf) {
		this.vf = vf;
	}

	public int getTotolReg() {
		return this.totolReg;
	}

	public void setTotolReg(int totolReg) {
		this.totolReg = totolReg;
	}

	public DataTable getDtActuOff() {
		return this.dtActuOff;
	}

	public void setDtActuOff(DataTable dtActuOff) {
		this.dtActuOff = dtActuOff;
	}

	public List<VFuncionario> getOffFilter() {
		return this.offFilter;
	}

	public void setOffFilter(List<VFuncionario> offFilter) {
		this.offFilter = offFilter;
	}

	public boolean isVentVer() {
		return this.ventVer;
	}

	public void setVentVer(boolean ventVer) {
		this.ventVer = ventVer;
	}

	public boolean isvPrin() {
		return this.vPrin;
	}

	public void setvPrin(boolean vPrin) {
		this.vPrin = vPrin;
	}

	public Funcionario getFun() {
		return this.fun;
	}

	public void setFun(Funcionario fun) {
		this.fun = fun;
	}

	public Persona getPer() {
		return this.per;
	}

	public void setPer(Persona per) {
		this.per = per;
	}

	public List<Sexo> getLisSex() {
		return this.lisSex;
	}

	public void setLisSex(List<Sexo> lisSex) {
		this.lisSex = lisSex;
	}

	public List<Ciudad> getLisCiuE() {
		return this.lisCiuE;
	}

	public void setLisCiuE(List<Ciudad> lisCiuE) {
		this.lisCiuE = lisCiuE;
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public List<CiudadDTO> getLisCiuEO() {
		return this.lisCiuEO;
	}

	public void setLisCiuEO(List<CiudadDTO> lisCiuEO) {
		this.lisCiuEO = lisCiuEO;
	}

	public int getIdtipo() {
		return this.idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public Date getFechaNa() {
		return this.fechaNa;
	}

	public void setFechaNa(Date fechaNa) {
		this.fechaNa = fechaNa;
	}

	public Date getFeDateV() {
		return this.feDateV;
	}

	public void setFeDateV(Date feDateV) {
		this.feDateV = feDateV;
	}

	public boolean isTabU() {
		return this.tabU;
	}

	public void setTabU(boolean tabU) {
		this.tabU = tabU;
	}

	public List<Estado> getLisEstP() {
		return this.lisEstP;
	}

	public void setLisEstP(List<Estado> lisEstP) {
		this.lisEstP = lisEstP;
	}

	public List<Zip> getLiszipc() {
		return this.liszipc;
	}

	public void setLiszipc(List<Zip> liszipc) {
		this.liszipc = liszipc;
	}

	public int getIdEstadoO() {
		return this.idEstadoO;
	}

	public void setIdEstadoO(int idEstadoO) {
		this.idEstadoO = idEstadoO;
	}

	public int getPaisd() {
		return this.paisd;
	}

	public void setPaisd(int paisd) {
		this.paisd = paisd;
	}

	public int getPaise() {
		return this.paise;
	}

	public void setPaise(int paise) {
		this.paise = paise;
	}

	public int getPaisn() {
		return this.paisn;
	}

	public void setPaisn(int paisn) {
		this.paisn = paisn;
	}

	public int getEstadon() {
		return this.estadon;
	}

	public void setEstadon(int estadon) {
		this.estadon = estadon;
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

	public int getCiudadnacimiento() {
		return this.ciudadnacimiento;
	}

	public void setCiudadnacimiento(int ciudadnacimiento) {
		this.ciudadnacimiento = ciudadnacimiento;
	}

	public int getCiudadusa() {
		return this.ciudadusa;
	}

	public void setCiudadusa(int ciudadusa) {
		this.ciudadusa = ciudadusa;
	}

	public int getZipusa() {
		return this.zipusa;
	}

	public void setZipusa(int zipusa) {
		this.zipusa = zipusa;
	}

	public int getCiudadorigen() {
		return this.ciudadorigen;
	}

	public void setCiudadorigen(int ciudadorigen) {
		this.ciudadorigen = ciudadorigen;
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Estado> getLisEstPa() {
		return this.lisEstPa;
	}

	public void setLisEstPa(List<Estado> lisEstPa) {
		this.lisEstPa = lisEstPa;
	}

	public List<Ciudad> getLisCiuEC() {
		return this.lisCiuEC;
	}

	public void setLisCiuEC(List<Ciudad> lisCiuEC) {
		this.lisCiuEC = lisCiuEC;
	}

	public boolean isObm() {
		return this.obm;
	}

	public void setObm(boolean obm) {
		this.obm = obm;
	}

	public List<Dependiente> getDepen() {
		return this.depen;
	}

	public void setDepen(List<Dependiente> depen) {
		this.depen = depen;
	}
	
	public String getPasaporte() {
		return this.pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public String getDireccionusa() {
		return this.direccionusa;
	}

	public void setDireccionusa(String direccionusa) {
		this.direccionusa = direccionusa;
	}

	
}

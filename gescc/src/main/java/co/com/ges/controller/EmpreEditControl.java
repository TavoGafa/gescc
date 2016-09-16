package co.com.ges.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import co.com.ges.dto.EmpresaDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Persona;
import co.com.ges.modelo.PersonaPK;
import co.com.ges.servicio.GesServicio;

@ManagedBean(name="emed")
@SessionScoped
public class EmpreEditControl implements Serializable{

	private static final long serialVersionUID = 1L;
	private Properties prop = new Properties();
	private String user;
	private Funcionario f = new Funcionario();
	Funcionario usuario= new Funcionario();
	private Pattern pattern;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private boolean activeCuatro = false;
	private List<Persona> emprsaP = new ArrayList<Persona>();
	private EmpresaDTO empreEdit = new EmpresaDTO();
	private  boolean editu = false;
	private  boolean editd = false;
	private  boolean editt = false;
	private  boolean create = false;
	private String pa;
	private String est;
	private String ciud;
	private String zi;
	private EmpresaDTO per = new EmpresaDTO();

	@EJB
	GesServicio servicio;
	
	public EmpreEditControl() {
		super();
		this.cargarPropiedades();
	}
	
	public void cargarPropiedades(){
		prop = new Properties();
		try{
			prop.load(EmpreEditControl.class.getResourceAsStream("mensajes.properties"));
			user=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.AUTH_KEY);
			usuario =(Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.USERS);
			per = (EmpresaDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(PrincipalControl.EMPRESADTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void cargarPermisos(){
		@SuppressWarnings("unchecked")
		List<OpcionesMenuUsuario> opc = (List<OpcionesMenuUsuario>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try{
			limpiarDatos();
			mostrarEmpresa();
//			for(OpcionesMenuUsuario o: opc){
//				if((o.getIdMenu().compareTo(ComponentesMenu.CONTRATO.getIdComponente())==0) 
//						&& (o.getPermisoCodigo().compareTo(PermisosMenu.ADMCONTRATRO.getIdPermiso().intValue())==0)){
//					if((o.getIdMenu().compareTo(ComponentesMenu.CONTRATO.getIdComponente())==0) 
//						&& (o.getPermisoCodigo().compareTo(PermisosMenu.SUMINISTROS.getIdPermiso().intValue())==0)){
//						
//					}
//				}
//			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editarEmpresa() {
		try{
			empreEdit = new EmpresaDTO();
			
			empreEdit = servicio.traerEmpresa(usuario.getUsuario(),per.getNombreEmpresa());
			activeCuatro = true;
			editu = true;
		}catch (Exception e) {
				
		}
	}
	
	public void limpiarDatos(){
		create = false;
	}
	
	public void mostrarEmpresa(){
		try{
			empreEdit = (EmpresaDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(PrincipalControl.EMPRESADTO); 
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void activeEdits(Long iden){
		switch (iden.intValue()) {
		case 1:
				editu = true;
				editd = false;
				editt = false;
			break;
		case 2:
				editd = true;
				editu = false;
				editt = false;
			break;
		case 3:
				editt = true;
				editu = false;
				editd = false;
			break;

		}
	}
	
	public void empEita(EmpresaDTO emp){
		try {
			if(emp!=null && !emp.equals("")){
				Persona p = new Persona();
				PersonaPK pk = new PersonaPK();
				p.setEmail(emp.getEmail());
				p.setNombre2(emp.getUsuario());
				
				p.setApartamento("");
				p.setApellido1(emp.getIndustria());
				p.setApellido2("");
				p.setNombre1(emp.getNombreEmpresa());
				if(ciud!=null && !ciud.equals("")){
					p.setCiudadnacimiento(Integer.valueOf(ciud));
					p.setCiudadorigen(Integer.valueOf(ciud));
					p.setCiudadusa(Integer.valueOf(ciud));
				}else{
					p.setCiudadnacimiento(Integer.valueOf(emp.getCiudad()));
					p.setCiudadorigen(Integer.valueOf(emp.getCiudad()));
					p.setCiudadusa(Integer.valueOf(emp.getCiudad()));
				}
				
				p.setDireccionorigen(emp.getDireccion());
				p.setDireccionusa(emp.getDireccion());
				p.setExpiracionpasaporte("");
				p.setFechaentrada(new Date());
				p.setFechanacimiento(new Date());
				p.setIdestadocivil(2);
				p.setIdsexo(2);
				
				p.setPasaporte("");
				p.setTelefono1(emp.getTelf());
				p.setTelefono2(emp.getTelf());
				p.setTipo(emp.getTipoNego());
				if(zi!=null && !zi.equals("")){
					p.setZipusa(Integer.valueOf(zi));
				}else{
					p.setZipusa(Integer.valueOf(emp.getZip()));
				}
				
				pk.setIdtipo(7);
				pk.setSsitin(emp.getEin());
				p.setId(pk);
				p.setTipo("E");
				servicio.editarEmpresa(p);
				activeCuatro = false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","EDIT COMPANY"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			limpiarDatos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"","EDIT THE COMPANY ERRO"));
		}
		
	}

	public boolean isActiveCuatro() {
		return activeCuatro;
	}

	public void setActiveCuatro(boolean activeCuatro) {
		this.activeCuatro = activeCuatro;
	}

	public List<Persona> getEmprsaP() {
		return emprsaP;
	}

	public void setEmprsaP(List<Persona> emprsaP) {
		this.emprsaP = emprsaP;
	}

	public EmpresaDTO getEmpreEdit() {
		return empreEdit;
	}

	public void setEmpreEdit(EmpresaDTO empreEdit) {
		this.empreEdit = empreEdit;
	}

	public boolean isEditu() {
		return editu;
	}

	public void setEditu(boolean editu) {
		this.editu = editu;
	}

	public boolean isEditd() {
		return editd;
	}

	public void setEditd(boolean editd) {
		this.editd = editd;
	}

	public boolean isEditt() {
		return editt;
	}

	public void setEditt(boolean editt) {
		this.editt = editt;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

	public String getEst() {
		return est;
	}

	public void setEst(String est) {
		this.est = est;
	}

	public String getCiud() {
		return ciud;
	}

	public void setCiud(String ciud) {
		this.ciud = ciud;
	}

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
	}

	public String getZi() {
		return zi;
	}

	public void setZi(String zi) {
		this.zi = zi;
	}
	
}

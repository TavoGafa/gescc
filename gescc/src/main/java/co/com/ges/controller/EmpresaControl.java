package co.com.ges.controller;

import java.io.Serializable;
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

import co.com.ges.dto.EmpresaDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.FuncionarioPK;
import co.com.ges.modelo.Persona;
import co.com.ges.modelo.PersonaPK;
import co.com.ges.servicio.AutenticacionServicio;
import co.com.ges.servicio.GesServicio;
import co.com.ges.servicio.MenuServicio;

@ManagedBean(name="empre")
@SessionScoped
public class EmpresaControl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Properties prop = new Properties();
	private String user;
	private Funcionario f = new Funcionario();
	Funcionario usuario= new Funcionario();
	public static final String func = "Funcionario";
	public static final String AUTH_KEY="app.user.name";
	public static final String USERS="app.user";
	public static final String EMPRESA="empre";
	public static final String PERSONA="persona";
	
	private Pattern pattern;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private String emailempre;
    private boolean activeDos = true;
    private boolean activeCuatro = false;
	private boolean activeTres = true;
    private EmpresaDTO emdto = new EmpresaDTO();
	private boolean diaempr=false;
	private String pass;
	private List<Persona> emprsaP = new ArrayList<Persona>();
	private EmpresaDTO empreDTO = new EmpresaDTO();
	private EmpresaDTO empreEdit = new EmpresaDTO();
	private  boolean editu = false;
	private  boolean editd = false;
	private  boolean editt = false;
	private  boolean create = false;
	private String pa;
	private String est;
	private String ciud;
	private String zi;
	private Persona per = new Persona();
    
	@EJB
	GesServicio servicio;
	
	@EJB 
	private MenuServicio serviciom;
	
	@EJB
	private AutenticacionServicio autenticacionServicio;
	
	public EmpresaControl() {
		super();
		this.cargarPropiedades();
	}
	
	public void cargarPropiedades(){
		prop = new Properties();
		try{
			prop.load(EmpresaControl.class.getResourceAsStream("mensajes.properties"));
			user=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.AUTH_KEY);
			f =(Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.USERS);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostConstruct
	public void cargarPermisos(){
		@SuppressWarnings("unchecked")
		List<OpcionesMenuUsuario> opc = (List<OpcionesMenuUsuario>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try{
			diaempr = true;
			limpiarDatos();
			//mostrarEmpresa();
			emailempre = "admin";
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
	
	public void validarMail(){
		try {
			//usuario = (Funcionario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.USERS);
			//emailempre = usuario.getUsuario();
			//pass = usuario.getClave();
			if((emailempre!=null && !emailempre.equals("")) && 
					(pass!=null && !pass.equals(""))){
				pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher m  = pattern.matcher(emailempre);
				if(m.matches()){
					usuario = autenticacionServicio.usuarioConsultado(emailempre);
					if(usuario.getUsuario()==null || usuario.getUsuario().equals("")){
						emdto.setEmail(emailempre);
						emdto.setPass(pass);
						activeDos = false;
					}else{
						if(usuario!=null && !usuario.equals("")){
							List<OpcionesMenuUsuario>opc=new ArrayList<OpcionesMenuUsuario>();
							opc = serviciom.consultarPermisos(usuario.getUsuario());
							if(opc != null && ! opc.isEmpty()){
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY,user);
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USERS, usuario);
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("menu",opc);
//								FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/comun/principal.faces");
								diaempr = false;
	//							login="/pages/comun/principal";
							}else {
								FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/comun/principal.faces");
								diaempr = false;
							}
						}else{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("usarioVencido")));
							FacesContext.getCurrentInstance().getExternalContext().redirect("/login/loginFail.faces");
							diaempr = false;
	//						login="/login/loginFail.faces";
						}
					}
					
				}else{
						usuario = autenticacionServicio.usuarioConsultado(emailempre);
						if(usuario.getUsuario()==null || usuario.getUsuario().equals("")){
							emdto.setPass(pass);
							activeDos = false;
						}else{
							if(usuario!=null && !usuario.equals("")){
								List<OpcionesMenuUsuario>opc=new ArrayList<OpcionesMenuUsuario>();
								opc = serviciom.consultarPermisos(usuario.getUsuario());
								if(opc != null && ! opc.isEmpty()){
									emdto.setEmail(emailempre);
									emdto.setPass(pass);
									activeDos = false;
									FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY,emailempre);
									FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USERS, usuario);
									FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("menu",opc);
									//FacesContext.getCurrentInstance().getExternalContext().redirect(".../../pages/comun/principal.faces");
//									diaempr = false;
		//							login="/pages/comun/principal";
								}else {
									FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/comun/principal.faces");
									diaempr = false;
								}
							}else{
								FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("usarioVencido")));
								FacesContext.getCurrentInstance().getExternalContext().redirect("/login/loginFail.faces");
								diaempr = false;
		//						login="/login/loginFail.faces";
							}
						}
					
				}
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"","REQUIRED FILEDS"));
				activeDos = true;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"USUARIO YA LOGUEADO","USUARIO YA LOGUEADO"));
		}
	}
	
	public void crearEmpresa(){
		try {
			if(emdto.getNombreEmpresa()!=null && !emdto.getNombreEmpresa().equals("")){
				Persona p = new Persona();
				PersonaPK pk = new PersonaPK();
				pattern = Pattern.compile(EMAIL_PATTERN);
				Matcher m  = pattern.matcher(emailempre);
				if(m.matches()){
					p.setEmail(emailempre);
					p.setNombre2(emailempre);
				}else{
					p.setEmail(emdto.getEmail());
					p.setNombre2(emailempre);
				}
				
				
				p.setApartamento(emdto.getApt());
				p.setApellido1(emdto.getIndustria());
				p.setApellido2("");
				p.setNombre1(emdto.getNombreEmpresa());
				
				p.setCiudadnacimiento(Integer.valueOf(emdto.getCiudad()));
				p.setCiudadorigen(Integer.valueOf(emdto.getCiudad()));
				p.setCiudadusa(Integer.valueOf(emdto.getCiudad()));
				p.setDireccionorigen(emdto.getDireccion());
				p.setDireccionusa(emdto.getDireccion());
				p.setExpiracionpasaporte("");
				p.setFechaentrada(new Date());
				p.setFechanacimiento(new Date());
				p.setIdestadocivil(2);
				p.setIdsexo(2);
				
				p.setPasaporte("");
				p.setTelefono1(emdto.getTelf());
				p.setTelefono2(emdto.getTelf());
				p.setTipo(emdto.getTipoNego());
				p.setZipusa(Integer.valueOf(emdto.getZip()));
				pk.setIdtipo(7);
				pk.setSsitin(emdto.getEin());
				p.setId(pk);
				p.setTipo("E");
				servicio.crearEmpresa(p);
				
				Funcionario funcionario = new Funcionario();
				funcionario = autenticacionServicio.usuarioConsultado(emailempre);
				Funcionario fun = new Funcionario();
				FuncionarioPK fpk = new FuncionarioPK();
				fpk.setIdentificacion(emdto.getEin());
				fpk.setIdtipopersona(7);
				fun.setClave(emdto.getPass());
				fun.setEstadodel((short) 1);
				fun.setUsuario(emailempre);
				fun.setIddependencia(-1);
				fun.setIdperfil(7);
				fun.setIdpregunta(1);
				fun.setRespuesta("EMPRE");
				fun.setId(fpk);
				if(funcionario.getUsuario()==null || funcionario.getUsuario().equals("")){
					servicio.crearFuncionario(fun);
				}
				per = p;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(func, fun);				
				
				activeTres = false;
				create = true;
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"","REQUIRED FILEDS"));
				activeTres = true;
				create = false;
				limpiarDatos();
			}
		} catch (Exception e) {
			e.printStackTrace();
			limpiarDatos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"","CREATING THE COMPANY ERRO"));
		}
	}
	
	public void limpiarDatos(){
		emdto = new EmpresaDTO();
		emailempre = null;
		pass = null;
		create = false;
	}
	
	public void start(){
		try{
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY,emailempre);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USERS, usuario);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(EMPRESA, per.getNombre1());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(PERSONA, per);
			limpiarDatos();
			diaempr = false;
			create = false;
			 FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/comun/prin.faces");
			
		}catch (Exception e) {
				
		}	
	}
	
//	public void editarEmpresa() {
//		try{
//			empreEdit = new EmpresaDTO();
//			empreEdit = servicio.traerEmpresa(usuario.getUsuario(),per.getNombre1());
//			activeCuatro = true;
//		}catch (Exception e) {
//				
//		}
//	}
	
//	public void empEita(EmpresaDTO emp){
//		try {
//			if(emp!=null && !emp.equals("")){
//				Persona p = new Persona();
//				PersonaPK pk = new PersonaPK();
//				p.setEmail(emp.getEmail());
//				p.setNombre2(emp.getUsuario());
//				
//				p.setApartamento("");
//				p.setApellido1(emp.getIndustria());
//				p.setApellido2("");
//				p.setNombre1(emp.getNombreEmpresa());
//				if(ciud!=null && !ciud.equals("")){
//					p.setCiudadnacimiento(Integer.valueOf(ciud));
//					p.setCiudadorigen(Integer.valueOf(ciud));
//					p.setCiudadusa(Integer.valueOf(ciud));
//				}else{
//					p.setCiudadnacimiento(Integer.valueOf(emp.getCiudad()));
//					p.setCiudadorigen(Integer.valueOf(emp.getCiudad()));
//					p.setCiudadusa(Integer.valueOf(emp.getCiudad()));
//				}
//				
//				p.setDireccionorigen(emp.getDireccion());
//				p.setDireccionusa(emp.getDireccion());
//				p.setExpiracionpasaporte("");
//				p.setFechaentrada(new Date());
//				p.setFechanacimiento(new Date());
//				p.setIdestadocivil(2);
//				p.setIdsexo(2);
//				
//				p.setPasaporte("");
//				p.setTelefono1(emp.getTelf());
//				p.setTelefono2(emp.getTelf());
//				p.setTipo(emp.getTipoNego());
//				if(zi!=null && !zi.equals("")){
//					p.setZipusa(Integer.valueOf(zi));s
//				}else{
//					p.setZipusa(Integer.valueOf(emp.getZip()));
//				}
//				
//				pk.setIdtipo(7);
//				pk.setSsitin(emp.getEin());
//				p.setId(pk);
//				p.setTipo("E");
//				servicio.editarEmpresa(p);
//				activeCuatro = false;
//				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"","EDIT COMPANY"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			limpiarDatos();
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"","EDIT THE COMPANY ERRO"));
//		}
//		
//	}
	
	
//	public void mostrarEmpresa(){
//		try{
//			empreDTO = new EmpresaDTO();
//			empreDTO=(EmpresaDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(PrincipalControl.EMPRESADTO); 
//			empreEdit = empreDTO;
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	public void activeEdits(Long iden){
//		switch (iden.intValue()) {
//		case 1:
//				editu = true;
//				editd = false;
//				editt = false;
//			break;
//		case 2:
//				editd = true;
//				editu = false;
//				editt = false;
//			break;
//		case 3:
//				editt = true;
//				editu = false;
//				editd = false;
//			break;
//
//		}
//	}

	public boolean isActiveDos() {
		return activeDos;
	}

	public void setActiveDos(boolean activeDos) {
		this.activeDos = activeDos;
	}

	public EmpresaDTO getEmdto() {
		return emdto;
	}

	public void setEmdto(EmpresaDTO emdto) {
		this.emdto = emdto;
	}

	public boolean isActiveTres() {
		return activeTres;
	}

	public void setActiveTres(boolean activeTres) {
		this.activeTres = activeTres;
	}

	public String getEmailempre() {
		return emailempre;
	}

	public void setEmailempre(String emailempre) {
		this.emailempre = emailempre;
	}
	
	public boolean isDiaempr() {
		return diaempr;
	}
	public void setDiaempr(boolean diaempr) {
		this.diaempr = diaempr;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public List<Persona> getEmprsaP() {
		return emprsaP;
	}

	public void setEmprsaP(List<Persona> emprsaP) {
		this.emprsaP = emprsaP;
	}

	public EmpresaDTO getEmpreDTO() {
		return empreDTO;
	}

	public void setEmpreDTO(EmpresaDTO empreDTO) {
		this.empreDTO = empreDTO;
	}
	
	public boolean isActiveCuatro() {
		return activeCuatro;
	}

	public void setActiveCuatro(boolean activeCuatro) {
		this.activeCuatro = activeCuatro;
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

	public String getPa() {
		return pa;
	}

	public void setPa(String pa) {
		this.pa = pa;
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

	public String getZi() {
		return zi;
	}

	public void setZi(String zi) {
		this.zi = zi;
	}

	public boolean isCreate() {
		return create;
	}

	public void setCreate(boolean create) {
		this.create = create;
	}

}



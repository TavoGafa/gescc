package co.com.ges.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

import co.com.comfaboy.Ldap.LoginLDAP;
import co.com.ges.dto.EmpresaDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Formapagofactura;
//import co.com.comfaboy.contratacion.dto.OpcionesMenuUsuario;
//import co.com.comfaboy.contratacion.managedbean.AutenticacionServicio;
//import co.com.comfaboy.contratacion.managedbean.MenuServicio;
//import co.com.comfaboy.contratacion.modelo.Tctmlogi;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Persona;
import co.com.ges.servicio.AutenticacionServicio;
import co.com.ges.servicio.GesServicio;
import co.com.ges.servicio.MenuServicio;



@ManagedBean(name="log")
@SessionScoped
@SuppressWarnings(value = { "all" })
@Singleton
public class LoginControl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@EJB 
	private MenuServicio servicio;
	
	@EJB
	private AutenticacionServicio autenticacionServicio;
	
	@EJB
	private GesServicio gesServicio;

	public static final String AUTH_KEY="app.user.name";
	public static final String USERS="app.user";
	public static final String EMPRESA="empre";
	public static final String PERSONA="persona";
	
	public String cadena="cadena del login control";
	Properties prop;
	private String user;
	private String pass;
	private boolean show=false;
	private List<EmpresaDTO> empresa = new ArrayList<EmpresaDTO>();
	private DataTable dtEmpr;
	private List<EmpresaDTO> empreFilter;
	private boolean panLog = false;
	private boolean panPri = true;
	private String empre;
	
	
	public LoginControl(){
		prop=new Properties();
		try {
			//prop.load(LoginControl.class.getResourceAsStream("ldap2.properties"));
			prop.load(LoginControl.class.getResourceAsStream("mensajes.properties"));
			dtEmpr = new DataTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@PostConstruct
	public void cargar(){
		traerEmpresas();
	}
	
	public String accessCheck(){
		String login=null;
		Funcionario usuario= new Funcionario();
//		LoginLDAP ldap=new LoginLDAP();
//		login=ldap.validarUsuarioLDAP(user, pass, prop.getProperty("host"), prop.getProperty("base"));
//		String useri = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(EmpresaControl.USERS);
//		if(useri!=null){
//			user = useri;
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("empre");
//		}
		
		if(user!=null){
			String usaux=(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(AUTH_KEY);
			//Funcionario fun = (Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(EmpresaControl.func);
			
			if(usaux==null||usaux.compareTo("")==0){
				try{
//					if(fun!=null && !fun.equals("")){
//						usuario = fun;
//					}else{
//						usuario = autenticacionServicio.usuarioConsultado(user);
//					}
					usuario = autenticacionServicio.usuarioConsultado(user);	
					if(usuario!=null && !usuario.equals("")){
							List<OpcionesMenuUsuario>opc=new ArrayList<OpcionesMenuUsuario>();
							opc = servicio.consultarPermisos(usuario.getUsuario());
							if(opc != null && ! opc.isEmpty()){
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY,user);
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USERS, usuario);
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("menu",opc);
								//login="../principal.facess";
								login="/pages/comun/principal.faces";
							}else {
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY,user);
								FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(USERS, usuario);
								login="/pages/comun/principal.faces";
								//login="/login/loginFail.faces";
							}
						}else{
							FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("usarioVencido")));
							login="/login/loginFail.faces";
							
						}
						
				}catch (Exception e) {
					e.printStackTrace();
					login="/login/loginFail";
				}
			}else{
				//show=true;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"USUARIO YA LOGUEADO","USUARIO YA LOGUEADO"));
				login="/login/login.faces";
			}
		}
		return login;
	}
	
	public String metodo(){
		System.out.println("llega hasta el metodo------->");
		return "/login/login.faces";
		//return "/pages/empresas.faces";
	}
	
	public void traerEmpresas(){
		try {
			empresa = new ArrayList<EmpresaDTO>();
			empresa = gesServicio.traerEmprsas();
			if(empresa.size()>0){
				empreFilter = empresa;
				dtEmpr.setValue(empresa);
				for(EmpresaDTO e : empresa){
					user = e.getEstado();
				}
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(PERSONA,empre);
			}
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR EMPRESAS","NO SE PUEDE CONSULTAR EMPRESAS"));
		}
	}
	
	public void createEmpre(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../empresa/empresa/empresa.faces");
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERROR EMPRESAS","NO SE PUEDE CONSULTAR EMPRESAS"));
			
		}
		
	}
	
	public void cargarEmpresa(EmpresaDTO emp){
		if(emp!=null && !emp.equals("")){
			user = emp.getUsuario();
			empre = emp.getNombreEmpresa();
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(EMPRESA,empre);
			panLog = true;
			panPri = false;
		}
	}
	
	public void editarEmpres(EmpresaDTO em){
		if(em!=null && !em.equals("")){
			panLog = true;
			panPri = false;
		}
	}
	
	public void volver(){
		panLog = false;
		panPri = true;
	}
	
	
	
	public void closeDlg(){
		show=false;
	} 
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isShow() {
		return show;
	}

	public List<EmpresaDTO> getEmpresa() {
		return empresa;
	}

	public void setEmpresa(List<EmpresaDTO> empresa) {
		this.empresa = empresa;
	}

	public DataTable getDtEmpr() {
		return dtEmpr;
	}

	public void setDtEmpr(DataTable dtEmpr) {
		this.dtEmpr = dtEmpr;
	}

	public List<EmpresaDTO> getEmpreFilter() {
		return empreFilter;
	}

	public void setEmpreFilter(List<EmpresaDTO> empreFilter) {
		this.empreFilter = empreFilter;
	}

	public boolean isPanLog() {
		return panLog;
	}

	public void setPanLog(boolean panLog) {
		this.panLog = panLog;
	}


	public boolean isPanPri() {
		return panPri;
	}


	public void setPanPri(boolean panPri) {
		this.panPri = panPri;
	}
}

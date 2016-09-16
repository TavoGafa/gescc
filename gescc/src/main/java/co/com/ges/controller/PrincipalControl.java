package co.com.ges.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.MenuItem;

import co.com.ges.dto.EmpresaDTO;
import co.com.ges.modelo.Funcionario;
import co.com.ges.servicio.GesServicio;



@ManagedBean(name="prin")
@SessionScoped
public class PrincipalControl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String usLoged;
	private List<Integer> listYears;
	private int periodo;
	private int cont=0;
	private int pagi=0;
	private Funcionario us;
	private boolean actp=false;
	private boolean actpd=false;
	private String nombre;
	public static final String EMPRESADTO="dtoEmp";
	public static final String PERSONA="persona";
	private EmpresaDTO empreDTO = new EmpresaDTO();
	private String empre = null;
	
	@EJB
	GesServicio servicio;
	
	public PrincipalControl() {
		listYears=new ArrayList<Integer>();
		usLoged = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.AUTH_KEY);
		us =(Funcionario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.USERS);
	}
	
	 public void cambio(ValueChangeEvent e){ 
		 Calendar c=Calendar.getInstance();
		 int perdiodoActual = 0;
		 int year=c.get(Calendar.YEAR);
		 if((Integer)e.getNewValue()==0){
		 }else{
			 if(year==(Integer)e.getNewValue()){
				 c.setTime(new Date());
					perdiodoActual=c.get(Calendar.MONTH);
			 }
		 }
	 }
	 
	 public void opcionMenu(ActionEvent event){
		 try{
			 MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
			 String url=menuItem.getParams().get("url").get(0);
			 if(cont==0){
				 FacesContext.getCurrentInstance().getExternalContext().redirect(".."+url);
				 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repor");
				 cont=1;
			 }else{
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../"+".."+url);
					 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repo");
					 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rots");
			 }
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void logout(){
		 try{
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(LoginControl.AUTH_KEY);
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(EmpresaControl.AUTH_KEY);
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("log");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("prin");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("admin");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("estc");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ad");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("off");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repor");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ciud");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("zip");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("empre");
			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cust");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("intcon");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("intor");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("obracon");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("obraor");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sercon");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("serord");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("sumcon");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("suor");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("pol");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("repo");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("edtc");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("rots");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ots");
//			 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("admUser");
			 //FacesContext.getCurrentInstance().getExternalContext().redirect("login/login.faces");
			 FacesContext.getCurrentInstance().getExternalContext().redirect("../../login/login.faces");
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void llamarPantallas(Long a){
		 try{
			 if(pagi==0){
				 if(a==1){
					 
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/admin/admin.faces");
				 }else if(a==2){
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../pages/reportes/reportes.faces");
				 }
				 
				  pagi=1;
			 }else{
				 if(a==1){
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../"+".."+"/pages/admin/admin.faces");
				 }else if(a==2){
					 FacesContext.getCurrentInstance().getExternalContext().redirect("../"+".."+"/pages/reportes/reportes.faces");
				 }
					 
			 }
			 
			 
		 }catch (Exception e) {
			e.printStackTrace();
		}
	 }
	 
	 public void crerEmpresa() throws IOException{
		 if(usLoged.equals("admin")){
			 FacesContext.getCurrentInstance().getExternalContext().redirect("/empresa.faces");
			 RequestContext context = RequestContext.getCurrentInstance();
			 context.execute("PF('empre').show();");
		 }else{
			 FacesContext.getCurrentInstance().getExternalContext().redirect("/principal.faces");
		 }
	 }
	 
	 public void panelActivarBotones(){
		 
	 }
	 
	 public void abrirPagina(Long p,Long em){
		 try {
			 switch (p.intValue()) {
				case 1:
					String empre = (String)  FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginControl.EMPRESA);
					empreDTO = new EmpresaDTO();
					empreDTO = servicio.traerEmpresa(usLoged,empre);
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(EMPRESADTO,empreDTO);
					if(em==0){
						FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/empre.faces");
					}else{
						FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/admin/empre.faces");
					}
					
				break;
				case 2:
					if(em==0){
						FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/admin.faces");
					}else{
						FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/admin/admin.faces");
					}
					
				break;
				case 3:
					if(em==0){
						FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/officer.faces");
					}else{
						FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/admin/officer.faces");
					}
				break;
				case 4:
					if(em==0){
						FacesContext.getCurrentInstance().getExternalContext().redirect("../admin/custumers.faces");
					}else{
						FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/admin/custumers.faces");
					}
				break;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	 }

	
	public int getPeriodo() {
		return periodo;
	}
	public String getUsLoged() {
		return usLoged;
	}

	public List<Integer> getListYears() {
		return listYears;
	}

	public void setListYears(List<Integer> listYears) {
		this.listYears = listYears;
	}

	public boolean isActp() {
		return actp;
	}

	public void setActp(boolean actp) {
		this.actp = actp;
	}

	public boolean isActpd() {
		return actpd;
	}

	public void setActpd(boolean actpd) {
		this.actpd = actpd;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EmpresaDTO getEmpreDTO() {
		return empreDTO;
	}

	public void setEmpreDTO(EmpresaDTO empreDTO) {
		this.empreDTO = empreDTO;
	}

	public String getEmpre() {
		return empre;
	}

	public void setEmpre(String empre) {
		this.empre = empre;
	}
	
	
}


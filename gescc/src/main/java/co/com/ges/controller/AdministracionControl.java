package co.com.ges.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import co.com.ges.dto.CiudZipDTO;
import co.com.ges.dto.CiudadDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Anio;
import co.com.ges.modelo.Ciudad;
import co.com.ges.modelo.Ciudadzip;
import co.com.ges.modelo.CiudadzipPK;
import co.com.ges.modelo.Estado;
import co.com.ges.modelo.Estadocivil;
import co.com.ges.modelo.Estadofactura;
import co.com.ges.modelo.Formaenvio;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Pai;
import co.com.ges.modelo.Parametro;
import co.com.ges.modelo.Perfil;
import co.com.ges.modelo.Pregunta;
import co.com.ges.modelo.Tipoidentificacion;
import co.com.ges.modelo.Zip;
import co.com.ges.servicio.GesServicio;

@ManagedBean(name="ad")
@SessionScoped
public class AdministracionControl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Pai> listPais = new ArrayList<Pai>();
	private Properties prop = new Properties();
	private String user;
	private boolean permisosCreate = false;
	private Pai paisNew = new Pai();
	private int num = 0;
	private String nombre;
	
	
	private List<Estado> listEstado = new ArrayList<Estado>();
	private Funcionario f = new Funcionario();
	

	private DataTable dtActuE;
	
	private DataTable dtActuAn;
	private DataTable dtActuPr;
	private DataTable dtActuPm;
	private DataTable dtActuPf;
	private DataTable dtActuEstC;
	private DataTable dtActuTi;
	private DataTable dtActuFe;
	private DataTable dtActuEfc;
	
	private Estado estado = new Estado();
	private int codEst=0;
	private List<Estado> estFilter;
	
	private int codAnio = 0;
	private Anio an = new Anio();
	private List<Anio> lisAnio = new ArrayList<Anio>();
	private List<Anio> anioFilter;
	private int codPreg = 0;
	private Pregunta pr = new Pregunta();
	private List<Pregunta> lisPreg = new ArrayList<Pregunta>();
	private List<Pregunta> preFilter;
	private int codParam = 0;
	private Parametro param = new Parametro();
	private List<Parametro> lisParam = new ArrayList<Parametro>();
	private List<Parametro> paramFilter;
	private int codPerf = 0;
	private Perfil perf = new Perfil();
	private List<Perfil> lisPerf = new ArrayList<Perfil>();
	private List<Perfil> perfFilter;
	private int codEstCiv = 0;
	private Estadocivil ec = new Estadocivil();
	private List<Estadocivil> lisEstCi = new ArrayList<Estadocivil>();
	private List<Estadocivil> estCiFilter;
	private int codTi = 0;
	private Tipoidentificacion ti = new Tipoidentificacion();
	private List<Tipoidentificacion> lisTi = new ArrayList<Tipoidentificacion>();
	private List<Tipoidentificacion> tiFilter;
	private int codFe = 0;
	private Formaenvio fe = new Formaenvio();
	private List<Formaenvio> lisFe = new ArrayList<Formaenvio>();
	private List<Formaenvio> feFilter;
	private int codEfc = 0;
	private Estadofactura efc = new Estadofactura();
	private List<Estadofactura> lisefc = new ArrayList<Estadofactura>();
	private List<Estadofactura> efcFilter;
	private String pa;
	private String est;
	private boolean adminsh=false;
	private boolean actp=false;
	private boolean actpd=false;
	private boolean actpes=false;
	private boolean actpzip=false;
	private boolean actpanio=false;
	private boolean actppr=false;
	private boolean actppam=false;
	private boolean actpperf=false;
	private boolean actpec=false;
	private boolean actptid=false;
	private boolean actpfe=false;
	private boolean actpef=false;
	
	private boolean actpesc=false;
	private boolean actpecos=false;
	private boolean actpbak=false;
	private boolean actpdep=false;
	private boolean actptidoc=false;
	private boolean actpgas=false;
	private boolean actptar=false;
	private boolean actpfpta=false;
	private boolean actptia=false;
	private boolean actpfpf=false;
	private boolean actptip=false;
	
	private boolean diP = false;
	private boolean de=false;
	private boolean dc=false;
	private boolean dz=false;
	private boolean dy=false;
	private boolean da=false;
	private boolean dp=false;
	private boolean dpr=false;
	private boolean deci=false;
	private boolean dti=false;
	private boolean dfe=false;
	private boolean def=false;
	private boolean dec=false;
	private boolean dco=false;
	private boolean db=false;
	private boolean dd=false;
	private boolean dtd=false;
	private boolean dg=false;
	private boolean dt=false;
	private boolean dtx=false;
	private boolean dar=false;
	private boolean df=false;
	private boolean dpar=false;
	
	
	@EJB
	GesServicio servicio;
	
	public AdministracionControl() {
		super();
		this.cargarPropiedades();
	}
	
	public void cargarPropiedades(){
		prop = new Properties();
		try{
			prop.load(AdministracionControl.class.getResourceAsStream("mensajes.properties"));
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
			adminsh = true;
			cargarDatos();
			diP = false;
			de=false;
			dc=false;
			dz=false;
			dy=false;
			da=false;
			dp=false;
			dpr=false;
			deci=false;
			dti=false;
			dfe=false;
			def=false;
			dec=false;
			dco=false;
			db=false;
			dd=false;
			dtd=false;
			dg=false;
			dt=false;
			dtx=false;
			dar=false;
			df=false;
			dpar=false;
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
	
	public void cargarDatos(){
		try{
			dtActuE = new DataTable();
	
			dtActuAn = new DataTable();
			dtActuPr = new DataTable();
			dtActuPm = new DataTable();
			dtActuPf = new DataTable();
			dtActuEstC = new DataTable();
			dtActuTi = new DataTable();
			dtActuFe = new DataTable();
			dtActuEfc = new DataTable();
			listPais = servicio.listarPais();
			num = servicio.maxId();
			listEstado = servicio.traerEstado();
			dtActuE.setValue(listEstado);
			codEst = servicio.maxCodEst();
			estFilter = listEstado;
	
			codAnio = servicio.maxCodAnio();
			lisAnio = servicio.listarAnio();
			anioFilter = lisAnio;
			dtActuAn.setValue(lisAnio);
			codPreg = servicio.maxCodPreg();
			lisPreg = servicio.listarPregunta();
			preFilter  =lisPreg;
			dtActuPr.setValue(lisPreg);
			codParam = servicio.maxCodParam();
			lisParam = servicio.listarParametros();
			paramFilter = lisParam;
			dtActuPm.setValue(lisParam);
			codPerf = servicio.maxCodPerf();
			lisPerf = servicio.listarPerfil();
			perfFilter = lisPerf;
			dtActuPf.setValue(lisPerf);
			codEstCiv = servicio.maxCodEstadoCivil();
			lisEstCi = servicio.listarEstadoCivil();
			estCiFilter = lisEstCi;
			dtActuEstC.setValue(lisEstCi);
			codTi = servicio.maxCodTipoIdentifiacion();
			lisTi = servicio.listarTipoIdentifiacion();
			tiFilter = lisTi;
			dtActuTi.setValue(lisTi);
			codFe = servicio.maxCodFormaEnvio();
			lisFe = servicio.listarFormaEnvio();
			feFilter = lisFe;
			dtActuFe.setValue(lisFe);
			codEfc = servicio.maxCodEstadoFactura();
			lisefc = servicio.listarEstadoFactura();
			efcFilter = lisefc;
			dtActuEfc.setValue(lisefc);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/*manejo de parte visual de paneles*/
	public void panelAct(){
		actp = true;
		actpd = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	
	 public void panDosAct(){
		actpd = true;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void pantAct(){
		actpd = false;
		actp = false;
		actpes = true;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void panCuaAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = true;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void panCinAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = true;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }

	 public void panSeiAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = true;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	
	 public void panSeitAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = true;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void panocAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = true;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void pannuAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = true;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void pandieAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = true;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void panonAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = true;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	 public void pandocAct(){
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = true;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	 }
	 
	public void pantrAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = true;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	 
	public void pancatAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = true;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void panquiAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = true;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}

	
	public void pandecAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = true;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void pandiecAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = true;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void pandioAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = true;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void pandiecnAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = true;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void panveinAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = true;
		actptia = false;
		actpfpf = false;
		actptip = false;
	}
	
	public void panveinuAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = true;
		actpfpf = false;
		actptip = false;
	}
	
	public void panveindAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = true;
		actptip = false;
	}
	
	public void panveintAct() {
		actpd = false;
		actp = false;
		actpes = false;
		actpzip = false;
		actpanio = false;
		actppr = false;
		actppam = false;
		actpperf = false;
		actpec = false;
		actptid = false;
		actpfe = false;
		actpef = false;
		actpesc = false;
		actpecos = false;
		actpbak = false;
		actpdep = false;
		actptidoc = false;
		actpgas = false;
		actptar = false;
		actpfpta = false;
		actptia = false;
		actpfpf = false;
		actptip = true;
	}
	 
	public void edit(Pai p) {
		try{
			if(p!=null){
				servicio.editarPais(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditPais")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorEditarPais")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorEditarPais")));
		}
    }
	
	public void delete(Pai p){
		try{
			servicio.delete(p);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoDelete")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void limpiarDatos(){
		nombre = null;
		estado = new Estado();
		
		an = new Anio();
		param = new Parametro();
		perf = new Perfil();
		ec = new Estadocivil();
		ti = new Tipoidentificacion();
		fe = new Formaenvio();
		efc = new Estadofactura();
		cargarDatos();
	}
	
	public void active(){
		permisosCreate = true;
	}
	
	public void cerrar(){
		permisosCreate = false;
	}
	
    public void create() {
    	try{
    		if(nombre!=null && !nombre.equals("")){
    			paisNew.setId(num);
    			paisNew.setNombre(nombre);
    			servicio.crearPais(paisNew);
    			limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuardadoPais")));
    		}else{
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreaPais")));
    	}
    }
    
  
    public void crearEstado(){
    	try{
    		if((estado.getNombre()!=null && !estado.getNombre().equals("")) && (estado.getIdpais()>0)){
    			estado.setId(codEst);
    			servicio.crearEstado(estado);
    			limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuardadoEst")));
    		}else{
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorEstado")));
    	}
    }
    
    public void editEstado(Estado es) {
		try{
			if(es!=null){
				servicio.editarEstado(es);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditEst")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorEditarEst")));
			}
			
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorEditarEst")));
		}
    }
    
    public void deleteEstado(Estado es){
		try{
			servicio.delete(es);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoDelete")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
     
    public void crearAnio(){
		try{
			if(an.getNombre()>0){
				an.setId(codAnio);
				servicio.crearAnio(an);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaAn")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreAn")));
		}
	}
	
	public void editarAnio(Anio a){
		try{
			if(a.getNombre()>0){
				servicio.editarAnio(a);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditAn")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiAn")));
		}
	}
	
	public void deleteAnio(Anio a){
		try{
			servicio.delete(a);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoDelete")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	 public void crearPregunta(){
			try{
				if(pr.getPregunta()!=null && !pr.getPregunta().equals("")){
					pr.setId(codPreg);
					servicio.crearPregunta(pr);
					limpiarDatos();
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaPR")));
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
				}
			}catch(Exception e){
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCrePR")));
			}
		}
		
		public void editarPregunta(Pregunta p){
			try{
				if(p.getPregunta()!=null && !p.getPregunta().equals("")){
					servicio.editarPregunta(p);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditPR")));
				}else{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
				}
			}catch(Exception e){
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiPR")));
			}
		}
		
		public void deletePregunta(Pregunta p){
			try{
				servicio.delete(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoDelete")));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	public void crearParam(){
		try{
			if(param.getEstado()>0 && (param.getDireccion()!=null && !param.getDireccion().equals(""))
					&& param.getIdciudadpobox()>0 && param.getIdzippobox()>0){
				param.setId(codParam);
				servicio.crearParamero(param);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaPar")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCrePar")));
		}
	}
	
	public void editarParam(Parametro p){
		try{
			if(p.getEstado()>0 || (p.getDireccion()!=null && !p.getDireccion().equals(""))
					|| p.getIdciudadpobox()>0 || p.getIdzippobox()>0){
				servicio.editarParametro(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditPar")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiPar")));
		}
	}
	
	public void deleteParam(Parametro p){
		try{
			servicio.delete(p);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoDelete")));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void crearPerfil(){
		try{
			if((perf.getDescripcion()!=null && !perf.getDescripcion().equals("")) 
					&& (perf.getNombre()!=null && !perf.getNombre().equals(""))){
				perf.setId(codPerf);
				servicio.crearPerfil(perf);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaPf")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCrePF")));
		}
	}
	
	public void editarPerfil(Perfil p){
		try{
			if((p.getDescripcion()!=null && !p.getDescripcion().equals("")) 
					|| (p.getNombre()!=null && !p.getNombre().equals(""))){
				servicio.editarPerfil(p);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaEstCiv")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreEstCiv")));
		}
	}

	 public void deletePerfil(Perfil p)
	{
		try {
			servicio.delete(p);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearEstadoCivil(){
		try{
			if(ec.getNombre()!=null && !ec.getNombre().equals("")){
				ec.setId(codEstCiv);
				servicio.crearEstadoCivil(ec);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditEstCiv")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreEstCiv")));
		}
	}
	
	public void editarEstadoCivil(Estadocivil esc){
		try{
			if((esc.getNombre()!=null && !esc.getNombre().equals(""))){
				servicio.editarEstadoCivil(esc);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("errorCreEstCiv")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiEstCiv")));
		}
	}
	
	 public void deleteEstadoCivil(Estadocivil esc)
	{
		try {
			servicio.delete(esc);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void crearTipoIden(){
		try{
			if(ti.getNombre()!=null && !ti.getNombre().equals("")){
				ti.setId(codTi);
				servicio.crearTipoIdentifiacion(ti);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaTI")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreTi")));
		}
	}
	
	public void editarTipoIden(Tipoidentificacion tid){
		try{
			if((tid.getNombre()!=null && !tid.getNombre().equals(""))){
				servicio.editarTipoIdentifiacion(tid);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditTI")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiTi")));
		}
	}
	
	public void deleteTipoIden(Tipoidentificacion tid)
	{
		try {
			servicio.delete(tid);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crearFormaEnvio(){
		try{
			if(fe.getNombre()!=null && !fe.getNombre().equals("")){
				fe.setId(codFe);
				servicio.crearFormaEnvio(fe);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaFe")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCrefe")));
		}
	}
	
	public void editarFormaEnvio(Formaenvio foen){
		try{
			if((foen.getNombre()!=null && !foen.getNombre().equals(""))){
				servicio.editarFormaEnvio(foen);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEditFe")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdife")));
		}
	}
	
	 public void deleteFormaEnvio(Formaenvio foen)
	{
		try {
			servicio.delete(foen);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void crearEstadoFacrura(){
		try{
			if(efc.getNombre()!=null && !efc.getNombre().equals("")){
				efc.setId(codEfc);
				servicio.crearEstadoFactura(efc);
				limpiarDatos();
    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoGuaEf")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCreEf")));
		}
	}
	
	public void editarEstadoFacrura(Estadofactura esf){
		try{
			if((esf.getNombre()!=null && !esf.getNombre().equals(""))){
				servicio.editarEstadoFactura(esf);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"",prop.getProperty("exitoEdiEFe")));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("errorCamposObli")));
			}
		}catch(Exception e){
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"",prop.getProperty("erroEdiEf")));
		}
	}
	
	public void deleteEstadoFacrura(Estadofactura esf) {
		try {
			servicio.delete(esf);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void home(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("../../pages/comun/principal.faces");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> busqueda(String nombre) {
		List<String> p = new ArrayList<String>();
		for (Pai pa : listPais) {
			if (pa.getNombre().contains(nombre.toUpperCase())) {
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaEst(String nombre){
		List<String> p = new ArrayList<String>();
		for(Estado pa : listEstado){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaPer(String nombre){
		List<String> p = new ArrayList<String>();
		for(Perfil pa : lisPerf){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaEC(String nombre){
		List<String> p = new ArrayList<String>();
		for(Estadocivil pa : lisEstCi){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaTI(String nombre){
		List<String> p = new ArrayList<String>();
		for(Tipoidentificacion pa : lisTi){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaFe(String nombre){
		List<String> p = new ArrayList<String>();
		for(Formaenvio pa : lisFe){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public List<String> busquedaEF(String nombre){
		List<String> p = new ArrayList<String>();
		for(Estadofactura pa : lisefc){
			if(pa.getNombre().contains(nombre.toUpperCase())){
				p.add(pa.getNombre());
			}
		}
		return p;
	}
	
	public void setListPais(List<Pai> listPais) {
		this.listPais = listPais;
	}

	public void activarDP(){
		diP = true;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDE(){
		diP = false;
		de=true;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDC(){
		diP = false;
		de=false;
		dc=true;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDZ(){
		diP = false;
		de=false;
		dc=false;
		dz=true;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDY(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=true;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDA(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=true;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDPA(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=true;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDPr(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=true;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDEC(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=true;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDTi(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=true;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDfe(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=true;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDef(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=true;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDec(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=true;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDco(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=true;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDb(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=true;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDd(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=true;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDtd(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=true;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDg(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=true;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDt(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=true;
		dtx=false;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDtx(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=true;
		dar=false;
		df=false;
		dpar=false;
	}
	
	public void activarDar(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=true;
		df=false;
		dpar=false;
	}
	
	public void activarDf(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=true;
		dpar=false;
	}
	
	public void activarDpar(){
		diP = false;
		de=false;
		dc=false;
		dz=false;
		dy=false;
		da=false;
		dp=false;
		dpr=false;
		deci=false;
		dti=false;
		dfe=false;
		def=false;
		dec=false;
		dco=false;
		db=false;
		dd=false;
		dtd=false;
		dg=false;
		dt=false;
		dtx=false;
		dar=false;
		df=false;
		dpar=true;
	}
	
	public boolean isPermisosCreate() {
		return permisosCreate;
	}

	public void setPermisosCreate(boolean permisosCreate) {
		this.permisosCreate = permisosCreate;
	}

	public Pai getPaisNew() {
		return paisNew;
	}

	public void setPaisNew(Pai paisNew) {
		this.paisNew = paisNew;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Estado> getListEstado() {
		return listEstado;
	}

	public void setListEstado(List<Estado> listEstado) {
		this.listEstado = listEstado;
	}


	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getCodEst() {
		return codEst;
	}

	public void setCodEst(int codEst) {
		this.codEst = codEst;
	}

	public List<Estado> getEstFilter() {
		return estFilter;
	}

	public void setEstFilter(List<Estado> estFilter) {
		this.estFilter = estFilter;
	}

	public DataTable getDtActuE() {
		return dtActuE;
	}

	public void setDtActuE(DataTable dtActuE) {
		this.dtActuE = dtActuE;
	}


	public DataTable getDtActuAn() {
		return dtActuAn;
	}

	public void setDtActuAn(DataTable dtActuAn) {
		this.dtActuAn = dtActuAn;
	}

	public int getCodAnio() {
		return codAnio;
	}

	public void setCodAnio(int codAnio) {
		this.codAnio = codAnio;
	}

	public Anio getAn() {
		return an;
	}

	public void setAn(Anio an) {
		this.an = an;
	}

	public List<Anio> getLisAnio() {
		return lisAnio;
	}

	public void setLisAnio(List<Anio> lisAnio) {
		this.lisAnio = lisAnio;
	}

	public List<Anio> getAnioFilter() {
		return anioFilter;
	}

	public void setAnioFilter(List<Anio> anioFilter) {
		this.anioFilter = anioFilter;
	}

	public DataTable getDtActuPr() {
		return dtActuPr;
	}

	public void setDtActuPr(DataTable dtActuPr) {
		this.dtActuPr = dtActuPr;
	}

	public int getCodPreg() {
		return codPreg;
	}

	public void setCodPreg(int codPreg) {
		this.codPreg = codPreg;
	}

	public Pregunta getPr() {
		return pr;
	}

	public void setPr(Pregunta pr) {
		this.pr = pr;
	}

	public List<Pregunta> getLisPreg() {
		return lisPreg;
	}

	public void setLisPreg(List<Pregunta> lisPreg) {
		this.lisPreg = lisPreg;
	}

	public List<Pregunta> getPreFilter() {
		return preFilter;
	}

	public void setPreFilter(List<Pregunta> preFilter) {
		this.preFilter = preFilter;
	}

	public DataTable getDtActuPm() {
		return dtActuPm;
	}

	public void setDtActuPm(DataTable dtActuPm) {
		this.dtActuPm = dtActuPm;
	}

	public int getCodParam() {
		return codParam;
	}

	public void setCodParam(int codParam) {
		this.codParam = codParam;
	}

	public Parametro getParam() {
		return param;
	}

	public void setParam(Parametro param) {
		this.param = param;
	}

	public List<Parametro> getLisParam() {
		return lisParam;
	}

	public void setLisParam(List<Parametro> lisParam) {
		this.lisParam = lisParam;
	}

	public List<Parametro> getParamFilter() {
		return paramFilter;
	}

	public void setParamFilter(List<Parametro> paramFilter) {
		this.paramFilter = paramFilter;
	}

	public DataTable getDtActuPf() {
		return dtActuPf;
	}

	public void setDtActuPf(DataTable dtActuPf) {
		this.dtActuPf = dtActuPf;
	}

	public Perfil getPerf() {
		return perf;
	}

	public void setPerf(Perfil perf) {
		this.perf = perf;
	}

	public List<Perfil> getLisPerf() {
		return lisPerf;
	}

	public void setLisPerf(List<Perfil> lisPerf) {
		this.lisPerf = lisPerf;
	}

	public List<Perfil> getPerfFilter() {
		return perfFilter;
	}

	public void setPerfFilter(List<Perfil> perfFilter) {
		this.perfFilter = perfFilter;
	}

	public int getCodPerf() {
		return codPerf;
	}

	public void setCodPerf(int codPerf) {
		this.codPerf = codPerf;
	}

	public DataTable getDtActuEstC() {
		return dtActuEstC;
	}

	public void setDtActuEstC(DataTable dtActuEstC) {
		this.dtActuEstC = dtActuEstC;
	}

	public int getCodEstCiv() {
		return codEstCiv;
	}

	public void setCodEstCiv(int codEstCiv) {
		this.codEstCiv = codEstCiv;
	}

	public Estadocivil getEc() {
		return ec;
	}

	public void setEc(Estadocivil ec) {
		this.ec = ec;
	}

	public List<Estadocivil> getLisEstCi() {
		return lisEstCi;
	}

	public void setLisEstCi(List<Estadocivil> lisEstCi) {
		this.lisEstCi = lisEstCi;
	}

	public List<Estadocivil> getEstCiFilter() {
		return estCiFilter;
	}

	public void setEstCiFilter(List<Estadocivil> estCiFilter) {
		this.estCiFilter = estCiFilter;
	}

	public DataTable getDtActuTi() {
		return dtActuTi;
	}

	public void setDtActuTi(DataTable dtActuTi) {
		this.dtActuTi = dtActuTi;
	}

	public int getCodTi() {
		return codTi;
	}

	public void setCodTi(int codTi) {
		this.codTi = codTi;
	}

	public Tipoidentificacion getTi() {
		return ti;
	}

	public void setTi(Tipoidentificacion ti) {
		this.ti = ti;
	}

	public List<Tipoidentificacion> getLisTi() {
		return lisTi;
	}

	public void setLisTi(List<Tipoidentificacion> lisTi) {
		this.lisTi = lisTi;
	}

	public List<Tipoidentificacion> getTiFilter() {
		return tiFilter;
	}

	public void setTiFilter(List<Tipoidentificacion> tiFilter) {
		this.tiFilter = tiFilter;
	}

	public List<Pai> getListPais() {
		return listPais;
	}

	public DataTable getDtActuFe() {
		return dtActuFe;
	}

	public void setDtActuFe(DataTable dtActuFe) {
		this.dtActuFe = dtActuFe;
	}

	public int getCodFe() {
		return codFe;
	}

	public void setCodFe(int codFe) {
		this.codFe = codFe;
	}

	public Formaenvio getFe() {
		return fe;
	}

	public void setFe(Formaenvio fe) {
		this.fe = fe;
	}

	public List<Formaenvio> getLisFe() {
		return lisFe;
	}

	public void setLisFe(List<Formaenvio> lisFe) {
		this.lisFe = lisFe;
	}

	public List<Formaenvio> getFeFilter() {
		return feFilter;
	}

	public void setFeFilter(List<Formaenvio> feFilter) {
		this.feFilter = feFilter;
	}

	public DataTable getDtActuEfc() {
		return dtActuEfc;
	}

	public void setDtActuEfc(DataTable dtActuEfc) {
		this.dtActuEfc = dtActuEfc;
	}

	public int getCodEfc() {
		return codEfc;
	}

	public void setCodEfc(int codEfc) {
		this.codEfc = codEfc;
	}

	public Estadofactura getEfc() {
		return efc;
	}

	public void setEfc(Estadofactura efc) {
		this.efc = efc;
	}

	public List<Estadofactura> getLisefc() {
		return lisefc;
	}

	public void setLisefc(List<Estadofactura> lisefc) {
		this.lisefc = lisefc;
	}

	public List<Estadofactura> getEfcFilter() {
		return efcFilter;
	}

	public void setEfcFilter(List<Estadofactura> efcFilter) {
		this.efcFilter = efcFilter;
	}

	public boolean isAdminsh() {
		return adminsh;
	}

	public void setAdminsh(boolean adminsh) {
		this.adminsh = adminsh;
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

	public boolean isActpes() {
		return actpes;
	}

	public void setActpes(boolean actpes) {
		this.actpes = actpes;
	}

	public boolean isActpzip() {
		return actpzip;
	}

	public void setActpzip(boolean actpzip) {
		this.actpzip = actpzip;
	}

	public boolean isActpanio() {
		return actpanio;
	}

	public void setActpanio(boolean actpanio) {
		this.actpanio = actpanio;
	}

	public boolean isActppr() {
		return actppr;
	}

	public void setActppr(boolean actppr) {
		this.actppr = actppr;
	}

	public boolean isActppam() {
		return actppam;
	}

	public void setActppam(boolean actppam) {
		this.actppam = actppam;
	}

	public boolean isActpperf() {
		return actpperf;
	}

	public void setActpperf(boolean actpperf) {
		this.actpperf = actpperf;
	}

	public boolean isActpec() {
		return actpec;
	}

	public void setActpec(boolean actpec) {
		this.actpec = actpec;
	}

	public boolean isActptid() {
		return actptid;
	}

	public void setActptid(boolean actptid) {
		this.actptid = actptid;
	}

	public boolean isActpfe() {
		return actpfe;
	}

	public void setActpfe(boolean actpfe) {
		this.actpfe = actpfe;
	}

	public boolean isActpef() {
		return actpef;
	}

	public void setActpef(boolean actpef) {
		this.actpef = actpef;
	}

	public boolean isActpesc() {
		return actpesc;
	}

	public void setActpesc(boolean actpesc) {
		this.actpesc = actpesc;
	}

	public boolean isActpecos() {
		return actpecos;
	}

	public void setActpecos(boolean actpecos) {
		this.actpecos = actpecos;
	}

	public boolean isActpbak() {
		return actpbak;
	}

	public void setActpbak(boolean actpbak) {
		this.actpbak = actpbak;
	}

	public boolean isActpdep() {
		return actpdep;
	}

	public void setActpdep(boolean actpdep) {
		this.actpdep = actpdep;
	}

	public boolean isActptidoc() {
		return actptidoc;
	}

	public void setActptidoc(boolean actptidoc) {
		this.actptidoc = actptidoc;
	}

	public boolean isActpgas() {
		return actpgas;
	}

	public void setActpgas(boolean actpgas) {
		this.actpgas = actpgas;
	}

	public boolean isActptar() {
		return actptar;
	}

	public void setActptar(boolean actptar) {
		this.actptar = actptar;
	}

	public boolean isActpfpta() {
		return actpfpta;
	}

	public void setActpfpta(boolean actpfpta) {
		this.actpfpta = actpfpta;
	}

	public boolean isActptia() {
		return actptia;
	}

	public void setActptia(boolean actptia) {
		this.actptia = actptia;
	}

	public boolean isActpfpf() {
		return actpfpf;
	}

	public void setActpfpf(boolean actpfpf) {
		this.actpfpf = actpfpf;
	}

	public boolean isActptip() {
		return actptip;
	}

	public void setActptip(boolean actptip) {
		this.actptip = actptip;
	}

	public boolean isDiP() {
		return diP;
	}

	public void setDiP(boolean diP) {
		this.diP = diP;
	}

	public boolean isDe() {
		return de;
	}

	public void setDe(boolean de) {
		this.de = de;
	}

	public boolean isDc() {
		return dc;
	}

	public void setDc(boolean dc) {
		this.dc = dc;
	}

	public boolean isDz() {
		return dz;
	}

	public void setDz(boolean dz) {
		this.dz = dz;
	}

	public boolean isDy() {
		return dy;
	}

	public void setDy(boolean dy) {
		this.dy = dy;
	}

	public boolean isDa() {
		return da;
	}

	public void setDa(boolean da) {
		this.da = da;
	}

	public boolean isDp() {
		return dp;
	}

	public void setDp(boolean dp) {
		this.dp = dp;
	}

	public boolean isDpr() {
		return dpr;
	}

	public void setDpr(boolean dpr) {
		this.dpr = dpr;
	}

	public boolean isDeci() {
		return deci;
	}

	public void setDeci(boolean deci) {
		this.deci = deci;
	}

	public boolean isDti() {
		return dti;
	}

	public void setDti(boolean dti) {
		this.dti = dti;
	}

	public boolean isDfe() {
		return dfe;
	}

	public void setDfe(boolean dfe) {
		this.dfe = dfe;
	}

	public boolean isDef() {
		return def;
	}

	public void setDef(boolean def) {
		this.def = def;
	}

	public boolean isDec() {
		return dec;
	}

	public void setDec(boolean dec) {
		this.dec = dec;
	}

	public boolean isDco() {
		return dco;
	}

	public void setDco(boolean dco) {
		this.dco = dco;
	}

	public boolean isDb() {
		return db;
	}

	public void setDb(boolean db) {
		this.db = db;
	}

	public boolean isDd() {
		return dd;
	}

	public void setDd(boolean dd) {
		this.dd = dd;
	}

	public boolean isDtd() {
		return dtd;
	}

	public void setDtd(boolean dtd) {
		this.dtd = dtd;
	}

	public boolean isDg() {
		return dg;
	}

	public void setDg(boolean dg) {
		this.dg = dg;
	}

	public boolean isDt() {
		return dt;
	}

	public void setDt(boolean dt) {
		this.dt = dt;
	}

	public boolean isDtx() {
		return dtx;
	}

	public void setDtx(boolean dtx) {
		this.dtx = dtx;
	}

	public boolean isDar() {
		return dar;
	}

	public void setDar(boolean dar) {
		this.dar = dar;
	}

	public boolean isDf() {
		return df;
	}

	public void setDf(boolean df) {
		this.df = df;
	}

	public boolean isDpar() {
		return dpar;
	}

	public void setDpar(boolean dpar) {
		this.dpar = dpar;
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
}

		
			

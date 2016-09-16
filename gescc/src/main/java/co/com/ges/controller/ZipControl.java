package co.com.ges.controller;

import co.com.ges.dto.CiudZipDTO;
import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Ciudadzip;
import co.com.ges.modelo.CiudadzipPK;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Zip;
import co.com.ges.servicio.GesServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.primefaces.component.datatable.DataTable;

@ManagedBean(name="zip")
@SessionScoped
public class ZipControl
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String user;
  private Properties prop = new Properties();
  private Funcionario f = new Funcionario();
  private Zip zip = new Zip();
  private int zipCod = 0;
  private List<Zip> lisZip = new ArrayList();
  private List<Zip> zipFilter;
  private List<CiudZipDTO> lisCZip = new ArrayList();
  private List<CiudZipDTO> zipCFilter;
  private CiudZipDTO cpz = new CiudZipDTO();
  private Ciudadzip cz = new Ciudadzip();
  private Integer ciuz = null;
  private DataTable dtActuZ;
  private String pac;
  private String estc;
  @EJB
  GesServicio servicio;
  
  public ZipControl()
  {
    cargarPropiedades();
  }
  
  public void cargarPropiedades()
  {
    this.prop = new Properties();
    try
    {
      this.prop.load(AdministracionControl.class.getResourceAsStream("mensajes.properties"));
      this.user = ((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name"));
      this.f = ((Funcionario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user"));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  @PostConstruct
  public void cargarPermisos()
  {
    List<OpcionesMenuUsuario> opc = (List)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
    try
    {
      cargarDatos();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void cargarDatos()
  {
    try
    {
      this.dtActuZ = new DataTable();
      this.zipCod = this.servicio.maxCodZip().intValue();
      
      this.zipFilter = this.lisZip;
      
      this.zipCFilter = this.lisCZip;
      this.dtActuZ.setValue(this.lisCZip);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void limpiarDatos()
  {
    this.zip = new Zip();
    cargarDatos();
  }
  
  public void crearZip()
  {
    try
    {
      if ((this.zip.getZipcode() != null) && (!this.zip.getZipcode().equals("")) && (this.ciuz.intValue() > 0))
      {
        this.zip.setId(this.zipCod);
        this.zip.setIdfuncionario(this.user);
        this.zip.setFecha(new Date());
        this.zip.setIdtipofuncionario(this.f.getIdperfil());
        
        Ciudadzip zp = new Ciudadzip();
        CiudadzipPK zpk = new CiudadzipPK();
        zp.setIdciudad(this.ciuz.intValue());
        zp.setIdzip(this.zipCod);
        zp.setFecha(new Date());
        
        zp.setIdfuncionario(this.user);
        zp.setIdtipofuncionario(this.f.getIdperfil());
        
        this.servicio.crearZip(this.zip);
        this.servicio.crearCiudadzip(zp);
        limpiarDatos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", this.prop.getProperty("exitoGuarZip")));
      }
      else
      {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errorCamposObli")));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("erroCreaZip")));
    }
  }
  
  public void editarZip(CiudZipDTO cp)
  {
    try
    {
      if (cp != null)
      {
        Zip z = new Zip();
        z.setId(cp.getIdZip());
        z.setZipcode(cp.getCodZip());
        z.setFecha(new Date());
        z.setIdfuncionario(this.user);
        z.setIdtipofuncionario(this.f.getIdperfil());
        
        Ciudadzip cz = new Ciudadzip();
        
        cz.setIdciudad(cp.getIdCiudad());
        cz.setIdzip(cp.getIdZip());
        cz.setFecha(new Date());
        cz.setIdfuncionario(this.user);
        cz.setIdtipofuncionario(this.f.getIdperfil());
        
        this.servicio.editZip(z);
        this.servicio.ediatrCiudadzip(cz);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", this.prop.getProperty("exitoEditZip")));
      }
      else
      {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errorCamposObli")));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", this.prop.getProperty("errorEdiZip")));
    }
  }
  
  public void deleteZip(CiudZipDTO z)
  {
    try
    {
      this.servicio.delete(z);
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", this.prop.getProperty("exitoDelete")));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public List<String> busquedaZip(String nombre)
  {
    List<String> p = new ArrayList();
    for (Zip pa : this.lisZip) {
      if (pa.getZipcode().contains(nombre)) {
        p.add(pa.getZipcode());
      }
    }
    return p;
  }
  
  public DataTable getDtActuZ()
  {
    return this.dtActuZ;
  }
  
  public void setDtActuZ(DataTable dtActuZ)
  {
    this.dtActuZ = dtActuZ;
  }
  
  public Zip getZip()
  {
    return this.zip;
  }
  
  public void setZip(Zip zip)
  {
    this.zip = zip;
  }
  
  public int getZipCod()
  {
    return this.zipCod;
  }
  
  public void setZipCod(int zipCod)
  {
    this.zipCod = zipCod;
  }
  
  public List<Zip> getLisZip()
  {
    return this.lisZip;
  }
  
  public void setLisZip(List<Zip> lisZip)
  {
    this.lisZip = lisZip;
  }
  
  public List<Zip> getZipFilter()
  {
    return this.zipFilter;
  }
  
  public void setZipFilter(List<Zip> zipFilter)
  {
    this.zipFilter = zipFilter;
  }
  
  public Ciudadzip getCz()
  {
    return this.cz;
  }
  
  public void setCz(Ciudadzip cz)
  {
    this.cz = cz;
  }
  
  public Integer getCiuz()
  {
    return this.ciuz;
  }
  
  public void setCiuz(Integer ciuz)
  {
    this.ciuz = ciuz;
  }
  
  public List<CiudZipDTO> getLisCZip()
  {
    return this.lisCZip;
  }
  
  public void setLisCZip(List<CiudZipDTO> lisCZip)
  {
    this.lisCZip = lisCZip;
  }
  
  public List<CiudZipDTO> getZipCFilter()
  {
    return this.zipCFilter;
  }
  
  public void setZipCFilter(List<CiudZipDTO> zipCFilter)
  {
    this.zipCFilter = zipCFilter;
  }
  
  public CiudZipDTO getCpz()
  {
    return this.cpz;
  }
  
  public void setCpz(CiudZipDTO cpz)
  {
    this.cpz = cpz;
  }
  
  public String getPac()
  {
    return this.pac;
  }
  
  public void setPac(String pac)
  {
    this.pac = pac;
  }
  
  public String getEstc()
  {
    return this.estc;
  }
  
  public void setEstc(String estc)
  {
    this.estc = estc;
  }
}

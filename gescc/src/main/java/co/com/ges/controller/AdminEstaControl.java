package co.com.ges.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.faces.application.FacesMessage;

//import java.math.BigDecimal;
//import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.enums.AsignacionEnum;
import co.com.ges.enums.EstadosEnum;
import co.com.ges.modelo.Dependencia;
import co.com.ges.modelo.Detalle;
import co.com.ges.modelo.Estadocaso;
import co.com.ges.modelo.Extensione;
import co.com.ges.modelo.Formapagofactura;
import co.com.ges.modelo.Formapagotax;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Ruta;
import co.com.ges.modelo.Tipodocumento;
import co.com.ges.modelo.Tipogasto;
import co.com.ges.modelo.Tipoparentesco;
import co.com.ges.modelo.Tipotarea;
import co.com.ges.servicio.GesServicio;

import javax.ejb.EJB;
import javax.annotation.PostConstruct;

@ManagedBean(name="estc")
@SessionScoped
public class AdminEstaControl implements Serializable{


 private static final long serialVersionUID = 1L;
    private Properties prop;
    private String user;
    private Funcionario f;
    private DataTable dtActEC;
    private DataTable dtActuR;
    private DataTable dtActuD;
    private DataTable dtActuDep;
    private DataTable dtActuTico;
    private DataTable dtActuTg;
    private DataTable dtActuTt;
    private DataTable dtActuFtp;
    private DataTable dtActuExten;
    private DataTable dtActufpf;
    private DataTable dtActuTp;
    private List<EstadosEnum> lienu;
    private Estadocaso esc;
    private List<AsignacionEnum> as;
    private Integer codec;
    private List<Estadocaso> estl;
    private int codDet;
    private Detalle deta;
    private List<Detalle> lisdte;
    private int codRuta;
	private Ruta ruta = new Ruta();
	private List<Ruta> lisRut = new ArrayList<Ruta>();
	private List<Estadocaso> estCFilter;
	private List<Detalle> detFilter;
	private List<Ruta> rutFilter;
    private int codDepe;
    private Dependencia dep;
    private List<Dependencia> lisDep = new ArrayList<Dependencia>();
	private List<Detalle> depeFilter;
	private List<Dependencia> depenFilter;
    private int codTico;
    private Tipodocumento tico;
	private List<Tipodocumento> lisTico = new ArrayList<Tipodocumento>();
	private List<Tipodocumento> ticoFilter;
    private int codTigas;
    private Tipogasto tg;
	private List<Tipogasto> lisTg = new ArrayList<Tipogasto>();
	private List<Tipogasto> tgFilter;
    private int codTt;
    private Tipotarea tt;
	private List<Tipotarea> lisTt = new ArrayList<Tipotarea>();
	private List<Tipotarea> ttFilter;
    private int codFtp;
    private Formapagotax ftp;
	private List<Formapagotax> lisFtp = new ArrayList<Formapagotax>();
	private List<Formapagotax> ftpFilter;
    private Extensione exten;
	private List<Extensione> lisExten = new ArrayList<Extensione>();
	private List<Extensione> extenFilter;
    private int codFpf;
    private Formapagofactura fpf;
	private List<Formapagofactura> lisFpf = new ArrayList<Formapagofactura>();
	private List<Formapagofactura> fpfFilter;
    private int codTp;
    private Tipoparentesco tp;
	private List<Tipoparentesco> listp = new ArrayList<Tipoparentesco>();
	private List<Tipoparentesco> tpFilter;
    @EJB
    GesServicio servicio;

    public AdminEstaControl()
    {
        prop = new Properties();
        f = new Funcionario();
        lienu = new ArrayList<EstadosEnum>();
        esc = new Estadocaso();
        as = new ArrayList<AsignacionEnum>();
        codec = Integer.valueOf(0);
        estl = new ArrayList<Estadocaso>();
        codDet = 0;
        deta = new Detalle();
        lisdte = new ArrayList<Detalle>();
        codRuta = 0;
        ruta = new Ruta();
        lisRut = new ArrayList<Ruta>();
        codDepe = 0;
        dep = new Dependencia();
        lisDep = new ArrayList<Dependencia>();
        codTico = 0;
        tico = new Tipodocumento();
        lisTico = new ArrayList<Tipodocumento>();
        codTigas = 0;
        tg = new Tipogasto();
        lisTg = new ArrayList<Tipogasto>();
        codTt = 0;
        tt = new Tipotarea();
        lisTt = new ArrayList<Tipotarea>();
        codFtp = 0;
        ftp = new Formapagotax();
        lisFtp = new ArrayList<Formapagotax>();
        exten = new Extensione();
        lisExten = new ArrayList<Extensione>();
        codFpf = 0;
        fpf = new Formapagofactura();
        lisFpf = new ArrayList<Formapagofactura>();
        codTp = 0;
        tp = new Tipoparentesco();
        listp = new ArrayList<Tipoparentesco>();
        cargarPropiedades();
    }

    public void cargarPropiedades()
    {
        prop = new Properties();
        try
        {
            prop.load(AdminEstaControl.class.getResourceAsStream("mensajes.properties"));
            user = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user.name");
            f = (Funcionario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("app.user");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void cargarPermisos()
    {
    	@SuppressWarnings("unchecked")
		List<OpcionesMenuUsuario> opc = (List<OpcionesMenuUsuario>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
        try
        {
            lienu.add(EstadosEnum.ACTIVO);
            lienu.add(EstadosEnum.INACTIVO);
            as.add(AsignacionEnum.SI);
            as.add(AsignacionEnum.NO);
            cargarDatos();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cargarDatos()
    {
        try
        {
            dtActEC = new DataTable();
            dtActuR = new DataTable();
            dtActuD = new DataTable();
            dtActuDep = new DataTable();
            dtActuTico = new DataTable();
            dtActuTg = new DataTable();
            dtActuTt = new DataTable();
            dtActuFtp = new DataTable();
            dtActuExten = new DataTable();
            dtActufpf = new DataTable();
            dtActuTp = new DataTable();
            codec = servicio.maxCodEC();
            estl = servicio.listarEC();
            codDet = servicio.maxCodDetalle().intValue();
            lisdte = servicio.listarDetalle();
            codRuta = servicio.maxCodRuta().intValue();
            lisRut = servicio.listarRuta();
            codDepe = servicio.maxCodDep().intValue();
            lisDep = servicio.listarDependencia();
            lisTico = servicio.listarTico();
            codTico = servicio.maxCodTico().intValue();
            codTigas = servicio.maxCodTig().intValue();
            lisTg = servicio.listarTg();
            codTt = servicio.maxCodTt().intValue();
            lisTt = servicio.listarTt();
            ticoFilter = lisTico;
            estCFilter = estl;
            detFilter = lisdte;
            rutFilter = lisRut;
            tgFilter = lisTg;
            ttFilter = lisTt;
            depenFilter = lisDep;
            dtActEC.setValue(estl);
            dtActuD.setValue(lisdte);
            dtActuR.setValue(lisRut);
            dtActuDep.setValue(lisDep);
            dtActuTico.setValue(lisTico);
            dtActuTg.setValue(lisTg);
            dtActuTt.setValue(lisTt);
            codFtp = servicio.maxCodFPT().intValue();
            lisFtp = servicio.listarFPT();
            ftpFilter = lisFtp;
            dtActuFtp.setValue(lisFtp);
            lisExten = servicio.listarExtecion();
            extenFilter = lisExten;
            dtActuExten.setValue(lisExten);
            codFpf = servicio.maxCodFormaPagoFactura().intValue();
            lisFpf = servicio.listarFormaPagoFactura();
            fpfFilter = lisFpf;
            dtActufpf.setValue(lisFpf);
            codTp = servicio.maxCodTipoParentesco().intValue();
            listp = servicio.listarTipoParentesco();
            tpFilter = listp;
            dtActuTp.setValue(listp);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void limpiarDatos()
    {
        esc = new Estadocaso();
        deta = new Detalle();
        ruta = new Ruta();
        dep = new Dependencia();
        tico = new Tipodocumento();
        tg = new Tipogasto();
        tt = new Tipotarea();
        ftp = new Formapagotax();
        exten = new Extensione();
        fpf = new Formapagofactura();
        tp = new Tipoparentesco();
        cargarDatos();
    }

    public void crearEstaCaso()
    {
        try
        {
            if(esc != null)
            {
                esc.setId(codec.intValue());
                servicio.crearEC(esc);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaEC")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroCreaed")));
        }
    }

    public void editarEC(Estadocaso ec)
    {
        try
        {
            if(ec.getNombre() != null)
            {
                servicio.editarEC(ec);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditEC")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroCreaed")));
        }
    }

    public void deleteEC(Estadocaso ec)
    {
        try
        {
            servicio.delete(ec);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearDetalle()
    {
        try
        {
            if(deta.getDescripcion() != null && !deta.getDescripcion().equals("") && !deta.getValor().equals(BigDecimal.ZERO))
            {
                deta.setId(codDet);
                servicio.crearDetalle(deta);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaDet")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreDEt")));
        }
    }

    public void editarDetalle(Detalle d)
    {
        try
        {
            if(d.getDescripcion() != null && !d.getDescripcion().equals("") && !d.getValor().equals(BigDecimal.ZERO))
            {
                servicio.editarDetalle(d);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditEC")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiDEt")));
        }
    }

    public void deleteDetalle(Detalle d)
    {
        try
        {
            servicio.delete(d);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearRuta()
    {
        try
        {
            if(ruta.getNombrebanco() != null && !ruta.getNombrebanco().equals("") && ruta.getNumero() != null && !ruta.getNumero().equals(""))
            {
                ruta.setId(codRuta);
                servicio.crearRuta(ruta);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaBan")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreBan")));
        }
    }

    public void editarRuta(Ruta r)
    {
        try
        {
            if(r.getNombrebanco() != null && !r.getNombrebanco().equals("") && r.getNumero() != null && !r.getNumero().equals(""))
            {
                servicio.editarRuta(r);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditBan")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiBan")));
        }
    }

    public void deleteRuta(Ruta r)
    {
        try
        {
            servicio.delete(r);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearDepen()
    {
        try
        {
            if(dep.getNombre() != null && !dep.getNombre().equals(""))
            {
                dep.setId(codDepe);
                servicio.crearDependencia(dep);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaDep")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreDep")));
        }
    }

    public void editarDependencia(Dependencia d)
    {
        try
        {
            if(d.getNombre() != null && !d.getNombre().equals(""))
            {
                servicio.editarDependencia(d);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditDep")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiDep")));
        }
    }

    public void deleteDependencia(Dependencia d)
    {
        try
        {
            servicio.delete(d);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearTico()
    {
        try
        {
            if(tico.getNombre() != null && !tico.getNombre().equals(""))
            {
                tico.setId(codTico);
                servicio.crearTico(tico);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaTico")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreTico")));
        }
    }

    public void editarTico(Tipodocumento tp)
    {
        try
        {
            if(tp.getNombre() != null && !tp.getNombre().equals(""))
            {
                servicio.editarTico(tp);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditTico")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiTico")));
        }
    }

    public void deleteTico(Tipodocumento tp)
    {
        try
        {
            servicio.delete(tp);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearTipoGas()
    {
        try
        {
            if(tg.getNombre() != null && !tg.getNombre().equals(""))
            {
                tg.setId(codTigas);
                servicio.crearTg(tg);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaTg")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCregG")));
        }
    }

    public void editarTipGas(Tipogasto tg)
    {
        try
        {
            if(tg.getNombre() != null && !tg.getNombre().equals(""))
            {
                servicio.editarTig(tg);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditTG")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiTg")));
        }
    }

    public void deleteTipGas(Tipogasto tg)
    {
        try
        {
            servicio.delete(tg);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearTt()
    {
        try
        {
            if(tt.getNombre() != null && !tt.getNombre().equals(""))
            {
                tt.setId(codTt);
                servicio.crearTt(tt);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaTt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreTt")));
        }
    }

    public void editarTt(Tipotarea tw)
    {
        try
        {
            if(tw.getNombre() != null && !tw.getNombre().equals(""))
            {
                servicio.editarTt(tw);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditTt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiTt")));
        }
    }

    public void deleteTt(Tipotarea tw)
    {
        try
        {
            servicio.delete(tw);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearFtp()
    {
        try
        {
            if(ftp.getNombre() != null && !ftp.getNombre().equals(""))
            {
                ftp.setId(codFtp);
                servicio.crearFTP(ftp);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaEstFpt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreEstFpt")));
        }
    }

    public void editarftp(Formapagotax fpt)
    {
        try
        {
            if(fpt.getNombre() != null && !fpt.getNombre().equals(""))
            {
                servicio.editarFTP(fpt);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditEstFpt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiEstFpt")));
        }
    }

    public void deleteftp(Formapagotax fpt)
    {
        try
        {
            servicio.delete(fpt);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearExtension()
    {
        try
        {
            if(exten.getExtension() != null && !exten.getExtension().equals("") && exten.getDescripcion() != null && !exten.getDescripcion().equals("") && exten.getDirectorio() != null && !exten.getDirectorio().equals("") && exten.getEjecutable() != null && !exten.getEjecutable().equals("") && exten.getEstado() > 0)
            {
                servicio.crearExtencion(exten);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaExt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreExt")));
        }
    }

    public void editarExtension(Extensione ex)
    {
        try
        {
            if(ex.getExtension() != null && !ex.getExtension().equals("") || ex.getDescripcion() != null && !ex.getDescripcion().equals("") || ex.getDirectorio() != null && !ex.getDirectorio().equals("") || ex.getEjecutable() != null && !ex.getEjecutable().equals("") || ex.getEstado() > 0)
            {
                servicio.editarExtenciones(ex);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditExt")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiExt")));
        }
    }

    public void deleteExtension(Extensione ex)
    {
        try
        {
            servicio.delete(ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearFormaPagoFactura()
    {
        try
        {
            if(fpf.getNombre() != null && !fpf.getNombre().equals(""))
            {
                fpf.setId(codFpf);
                servicio.crearFormaPagoFactura(fpf);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaTp")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiFpf")));
        }
    }

    public void editarFormaPagoFactura(Formapagofactura fpfa)
    {
        try
        {
            if(fpfa.getNombre() != null && !fpfa.getNombre().equals(""))
            {
                servicio.editarFormaPagoFactura(fpfa);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditTP")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiTp")));
        }
    }

    public void deleteFormaPagoFactura(Formapagofactura fpfa)
    {
        try
        {
            servicio.delete(fpfa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void crearTipoParentesco()
    {
        try
        {
            if(tp.getNombre() != null && !tp.getNombre().equals(""))
            {
                tp.setId(codTp);
                servicio.crearTipoParentesco(tp);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoGuaFpf")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("errorCreFpf")));
        }
    }

    public void editarTipoParentesco(Tipoparentesco tpe)
    {
        try
        {
            if(tpe.getNombre() != null && !tpe.getNombre().equals(""))
            {
                servicio.editarTipoParentesco(tpe);
                limpiarDatos();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoEditFpf")));
            } else
            {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", prop.getProperty("errorCamposObli")));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", prop.getProperty("erroEdiFpf")));
        }
    }

    public void deleteTipoParentesco(Tipoparentesco tpe)
    {
        try
        {
            servicio.delete(tpe);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", prop.getProperty("exitoDelete")));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public List<String> busquedaEstC(String nombre)
    {
    	List<String> p = new ArrayList<String>();
    	for(Estadocaso pa: estl){
    		if(pa.getNombre().contains(nombre)){
    			p.add(pa.getNombre());	
    		}
                
    	}
        return p;
    }

    public List<String> busquedaCos(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Detalle pa: lisdte){
        	if(pa.getDescripcion().contains(nombre)){
        		p.add(pa.getDescripcion());
        	}
        }
        return p;
    }

    public List<String> busquedaBan(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Ruta pa: lisRut){
        	if(pa.getNombrebanco().contains(nombre)){
        		p.add(pa.getNombrebanco());
        	}
        }
        return p;
    }

    public List<String> busquedaDep(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Dependencia pa: lisDep){
        	if(pa.getNombre().contains(nombre)){
        		p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<String> busquedaTico(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Tipodocumento pa: lisTico){
        	 if(pa.getNombre().contains(nombre)){
                 p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<String> busquedaTg(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Tipogasto pa: lisTg){
        	if(pa.getNombre().contains(nombre)){
                p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<String> busquedaTt(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Tipotarea pa: lisTt){
        	if(pa.getNombre().contains(nombre)){
                p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<String> busquedaFpf(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Formapagofactura pa: lisFpf){
        	if(pa.getNombre().contains(nombre)){
                p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<String> busquedaTp(String nombre)
    {
        List<String> p = new ArrayList<String>();
        for(Tipoparentesco pa: listp){
        	if(pa.getNombre().contains(nombre)){
                p.add(pa.getNombre());
        	}
        }
        return p;
    }

    public List<EstadosEnum> getLienu()
    {
        return lienu;
    }

    public void setLienu(List<EstadosEnum> lienu)
    {
        this.lienu = lienu;
    }

    public Estadocaso getEsc()
    {
        return esc;
    }

    public void setEsc(Estadocaso esc)
    {
        this.esc = esc;
    }

    public List<AsignacionEnum> getAs()
    {
        return as;
    }

    public void setAs(List<AsignacionEnum> as)
    {
        this.as = as;
    }

    public Integer getCodec()
    {
        return codec;
    }

    public void setCodec(Integer codec)
    {
        this.codec = codec;
    }

    public List<Estadocaso> getEstl()
    {
        return estl;
    }

    public void setEstl(List<Estadocaso> estl)
    {
        this.estl = estl;
    }

    public int getCodDet()
    {
        return codDet;
    }

    public void setCodDet(int codDet)
    {
        this.codDet = codDet;
    }

    public Detalle getDeta()
    {
        return deta;
    }

    public void setDeta(Detalle deta)
    {
        this.deta = deta;
    }

    public List<Detalle> getLisdte()
    {
        return lisdte;
    }

    public void setLisdte(List<Detalle> lisdte)
    {
        this.lisdte = lisdte;
    }

    public int getCodRuta()
    {
        return codRuta;
    }

    public void setCodRuta(int codRuta)
    {
        this.codRuta = codRuta;
    }

    public Ruta getRuta()
    {
        return ruta;
    }

    public void setRuta(Ruta ruta)
    {
        this.ruta = ruta;
    }

    public List<Ruta> getLisRut()
    {
        return lisRut;
    }

    public DataTable getDtActEC()
    {
        return dtActEC;
    }

    public void setDtActEC(DataTable dtActEC)
    {
        this.dtActEC = dtActEC;
    }

    public void setLisRut(List<Ruta> lisRut)
    {
        this.lisRut = lisRut;
    }

    public List<Estadocaso> getEstCFilter()
    {
        return estCFilter;
    }

    public void setEstCFilter(List<Estadocaso> estCFilter)
    {
        this.estCFilter = estCFilter;
    }

    public DataTable getDtActuR()
    {
        return dtActuR;
    }

    public void setDtActuR(DataTable dtActuR)
    {
        this.dtActuR = dtActuR;
    }

    public List<Detalle> getDetFilter()
    {
        return detFilter;
    }

    public void setDetFilter(List<Detalle> detFilter)
    {
        this.detFilter = detFilter;
    }

    public DataTable getDtActuD()
    {
        return dtActuD;
    }

    public void setDtActuD(DataTable dtActuD)
    {
        this.dtActuD = dtActuD;
    }

    public List<Ruta> getRutFilter()
    {
        return rutFilter;
    }

    public void setRutFilter(List<Ruta> rutFilter)
    {
        this.rutFilter = rutFilter;
    }

    public Dependencia getDep()
    {
        return dep;
    }

    public void setDep(Dependencia dep)
    {
        this.dep = dep;
    }

    public List<Dependencia> getLisDep()
    {
        return lisDep;
    }

    public void setLisDep(List<Dependencia> lisDep)
    {
        this.lisDep = lisDep;
    }

    public List<Detalle> getDepeFilter()
    {
        return depeFilter;
    }

    public void setDepeFilter(List<Detalle> depeFilter)
    {
        this.depeFilter = depeFilter;
    }

    public List<Dependencia> getDepenFilter()
    {
        return depenFilter;
    }

    public void setDepenFilter(List<Dependencia> depenFilter)
    {
        this.depenFilter = depenFilter;
    }

    public int getCodDepe()
    {
        return codDepe;
    }

    public void setCodDepe(int codDepe)
    {
        this.codDepe = codDepe;
    }

    public DataTable getDtActuDep()
    {
        return dtActuDep;
    }

    public void setDtActuDep(DataTable dtActuDep)
    {
        this.dtActuDep = dtActuDep;
    }

    public DataTable getDtActuTico()
    {
        return dtActuTico;
    }

    public void setDtActuTico(DataTable dtActuTico)
    {
        this.dtActuTico = dtActuTico;
    }

    public int getCodTico()
    {
        return codTico;
    }

    public void setCodTico(int codTico)
    {
        this.codTico = codTico;
    }

    public Tipodocumento getTico()
    {
        return tico;
    }

    public void setTico(Tipodocumento tico)
    {
        this.tico = tico;
    }

    public List<Tipodocumento> getLisTico()
    {
        return lisTico;
    }

    public void setLisTico(List<Tipodocumento> lisTico)
    {
        this.lisTico = lisTico;
    }

    public List<Tipodocumento> getTicoFilter()
    {
        return ticoFilter;
    }

    public void setTicoFilter(List<Tipodocumento> ticoFilter)
    {
        this.ticoFilter = ticoFilter;
    }

    public int getCodTigas()
    {
        return codTigas;
    }

    public void setCodTigas(int codTigas)
    {
        this.codTigas = codTigas;
    }

    public Tipogasto getTg()
    {
        return tg;
    }

    public void setTg(Tipogasto tg)
    {
        this.tg = tg;
    }

    public List<Tipogasto> getLisTg()
    {
        return lisTg;
    }

    public void setLisTg(List<Tipogasto> lisTg)
    {
        this.lisTg = lisTg;
    }

    public List<Tipogasto> getTgFilter()
    {
        return tgFilter;
    }

    public void setTgFilter(List<Tipogasto> tgFilter)
    {
        this.tgFilter = tgFilter;
    }

    public DataTable getDtActuTg()
    {
        return dtActuTg;
    }

    public void setDtActuTg(DataTable dtActuTg)
    {
        this.dtActuTg = dtActuTg;
    }

    public DataTable getDtActuTt()
    {
        return dtActuTt;
    }

    public void setDtActuTt(DataTable dtActuTt)
    {
        this.dtActuTt = dtActuTt;
    }

    public int getCodTt()
    {
        return codTt;
    }

    public void setCodTt(int codTt)
    {
        this.codTt = codTt;
    }

    public Tipotarea getTt()
    {
        return tt;
    }

    public void setTt(Tipotarea tt)
    {
        this.tt = tt;
    }

    public List<Tipotarea> getLisTt()
    {
        return lisTt;
    }

    public void setLisTt(List<Tipotarea> lisTt)
    {
        this.lisTt = lisTt;
    }

    public List<Tipotarea> getTtFilter()
    {
        return ttFilter;
    }

    public void setTtFilter(List<Tipotarea> ttFilter)
    {
        this.ttFilter = ttFilter;
    }

    public DataTable getDtActuFtp()
    {
        return dtActuFtp;
    }

    public void setDtActuFtp(DataTable dtActuFtp)
    {
        this.dtActuFtp = dtActuFtp;
    }

    public int getCodFtp()
    {
        return codFtp;
    }

    public void setCodFtp(int codFtp)
    {
        this.codFtp = codFtp;
    }

    public Formapagotax getFtp()
    {
        return ftp;
    }

    public void setFtp(Formapagotax ftp)
    {
        this.ftp = ftp;
    }

    public List<Formapagotax> getLisFtp()
    {
        return lisFtp;
    }

    public void setLisFtp(List<Formapagotax> lisFtp)
    {
        this.lisFtp = lisFtp;
    }

    public List<Formapagotax> getFtpFilter()
    {
        return ftpFilter;
    }

    public void setFtpFilter(List<Formapagotax> ftpFilter)
    {
        this.ftpFilter = ftpFilter;
    }

    public DataTable getDtActuExten()
    {
        return dtActuExten;
    }

    public void setDtActuExten(DataTable dtActuExten)
    {
        this.dtActuExten = dtActuExten;
    }

    public Extensione getExten()
    {
        return exten;
    }

    public void setExten(Extensione exten)
    {
        this.exten = exten;
    }

    public List<Extensione> getLisExten()
    {
        return lisExten;
    }

    public void setLisExten(List<Extensione> lisExten)
    {
        this.lisExten = lisExten;
    }

    public List<Extensione> getExtenFilter()
    {
        return extenFilter;
    }

    public void setExtenFilter(List<Extensione> extenFilter)
    {
        this.extenFilter = extenFilter;
    }

    public DataTable getDtActufpf()
    {
        return dtActufpf;
    }

    public void setDtActufpf(DataTable dtActufpf)
    {
        this.dtActufpf = dtActufpf;
    }

    public int getCodFpf()
    {
        return codFpf;
    }

    public void setCodFpf(int codFpf)
    {
        this.codFpf = codFpf;
    }

    public Formapagofactura getFpf()
    {
        return fpf;
    }

    public void setFpf(Formapagofactura fpf)
    {
        this.fpf = fpf;
    }

    public List<Formapagofactura> getLisFpf()
    {
        return lisFpf;
    }

    public void setLisFpf(List<Formapagofactura> lisFpf)
    {
        this.lisFpf = lisFpf;
    }

    public List<Formapagofactura> getFpfFilter()
    {
        return fpfFilter;
    }

    public void setFpfFilter(List<Formapagofactura> fpfFilter)
    {
        this.fpfFilter = fpfFilter;
    }

    public DataTable getDtActuTp()
    {
        return dtActuTp;
    }

    public void setDtActuTp(DataTable dtActuTp)
    {
        this.dtActuTp = dtActuTp;
    }

    public int getCodTp()
    {
        return codTp;
    }

    public void setCodTp(int codTp)
    {
        this.codTp = codTp;
    }

    public List<Tipoparentesco> getListp()
    {
        return listp;
    }

    public void setListp(List<Tipoparentesco> listp)
    {
        this.listp = listp;
    }

    public List<Tipoparentesco> getTpFilter()
    {
        return tpFilter;
    }

    public void setTpFilter(List<Tipoparentesco> tpFilter)
    {
        this.tpFilter = tpFilter;
    }

    public Tipoparentesco getTp()
    {
        return tp;
    }

    public void setTp(Tipoparentesco tp)
    {
        this.tp = tp;
    }

}

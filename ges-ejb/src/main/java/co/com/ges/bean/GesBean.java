package co.com.ges.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.ges.dto.CasoReportDTO;
import co.com.ges.dto.CiudZipDTO;
import co.com.ges.dto.CiudadDTO;
import co.com.ges.dto.EmpresaDTO;
import co.com.ges.fachada.GesFachada;
import co.com.ges.modelo.Anio;
import co.com.ges.modelo.Caso;
import co.com.ges.modelo.Ciudad;
import co.com.ges.modelo.Ciudadzip;
import co.com.ges.modelo.CiudadzipPK;
import co.com.ges.modelo.Dependencia;
import co.com.ges.modelo.Detalle;
import co.com.ges.modelo.Estado;
import co.com.ges.modelo.Estadocaso;
import co.com.ges.modelo.Estadocivil;
import co.com.ges.modelo.Estadofactura;
import co.com.ges.modelo.Extensione;
import co.com.ges.modelo.Formaenvio;
import co.com.ges.modelo.Formapagofactura;
import co.com.ges.modelo.Formapagotax;
import co.com.ges.modelo.Funcionario;
import co.com.ges.modelo.Novedadcaso;
import co.com.ges.modelo.Pai;
import co.com.ges.modelo.Parametro;
import co.com.ges.modelo.Perfil;
import co.com.ges.modelo.Persona;
import co.com.ges.modelo.Pregunta;
import co.com.ges.modelo.Ruta;
import co.com.ges.modelo.Sexo;
import co.com.ges.modelo.Tipodocumento;
import co.com.ges.modelo.Tipogasto;
import co.com.ges.modelo.Tipoidentificacion;
import co.com.ges.modelo.Tipoparentesco;
import co.com.ges.modelo.Tipotarea;
import co.com.ges.modelo.Zip;
import co.com.ges.vistas.VFuncionario;

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GesBean implements GesFachada{

	@PersistenceContext(unitName="jpa-ges")
	private EntityManager em;
	
	public GesBean() {
		super();
	}

	@Override
	public List<Pai> listarPais() throws Exception {
		List<Pai> pais = new ArrayList<Pai>();
		 
		Query q = em.createNamedQuery(Pai.LISTAR_PAIS);
		pais = q.getResultList();
		
		return pais;
	}

	@Override
	public void crearPais(Pai pais) throws Exception {
		if(pais!=null){
			em.persist(pais);
			em.flush();
		}
	}

	@Override
	public void editarPais(Pai pais) throws Exception {
		Pai p = new Pai();
		if(pais!=null){
			p = listarPaisPorId(pais.getId());
			if(p.getId()>0){
				em.merge(pais);
				em.flush();
			}
		}
		
	}

	@Override
	public Pai listarPaisPorId(int id) throws Exception {
		List<Pai> obj = new ArrayList<Pai>();
		Pai pais = new Pai();
		if(id>0){
			Query q = em.createNamedQuery(Pai.LISTAR_PAIS_POR_ID).setParameter("id", id);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Pai o: obj){
					pais = new Pai();
					pais.setId(o.getId());
					pais.setNombre(o.getNombre());
				}
			}
		}
		
		return pais;
	}

	@Override
	public Integer maxid() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Pai.MAX_PAIS);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Estado> traerEstado() throws Exception {
		List<Estado> esta = new ArrayList<Estado>();
		
		Query q = em.createNamedQuery(Estado.TRAER_ESTADO);
		esta = q.getResultList();
		
		return esta;
	}

	@Override
	public Integer maxidCiud() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Ciudad.TRAER_AMX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public void crearCiudad(Ciudad ciud) throws Exception {
		if(ciud!=null){
			em.persist(ciud);
			em.flush();
		}
		
	}

	@Override
	public void editarCiudad(Ciudad ciud) throws Exception {
		Ciudad p = new Ciudad();
		String formato = "yyyy-MM-dd";
		if(ciud!=null){
			p = listarCiudadPorId(ciud.getId());
			if(p.getId()>0){
				Calendar c = Calendar.getInstance();
				Date d = c.getTime();
				Timestamp t = new Timestamp(d.getTime());
				ciud.setFecha(t);
				Query q = em.createNamedQuery(Ciudad.EDITAR_CIUDAD);
				q.setParameter("nombre", ciud.getNombre());
				q.setParameter("funcionario", ciud.getIdfuncionario());
				q.setParameter("estado", ciud.getIdestado());
				q.setParameter("fecha", ciud.getFecha());
				q.setParameter("id", ciud.getId());
				q.executeUpdate();
				em.flush();
			}
		}
		
	}

	private Ciudad mergeCiudad(Ciudad ciud) throws Exception {
		Ciudad p = new Ciudad();
		String formato = "yyyy-MM-dd";
		if(ciud!=null){
			p = listarCiudadPorId(ciud.getId());
			if(p.getId()>0){
				Calendar c = Calendar.getInstance();
				Date d = c.getTime();
				Timestamp t = new Timestamp(d.getTime());
				ciud.setFecha(t);
				Query q = em.createNamedQuery(Ciudad.EDITAR_CIUDAD);
				q.setParameter("nombre", ciud.getNombre());
				q.setParameter("funcionario", ciud.getIdfuncionario());
				q.setParameter("estado", ciud.getIdestado());
				q.setParameter("fecha", ciud.getFecha());
				q.setParameter("id", ciud.getId());
				q.executeUpdate();
				em.flush();
			}
		}
		
		return ciud;
		
	}

	@Override
	public Ciudad listarCiudadPorId(int id) throws Exception {
		List<Ciudad> obj = new ArrayList<Ciudad>();
		Ciudad ciudad = new Ciudad();
		if(id>0){
			Query q = em.createNamedQuery(Ciudad.CIUDAD_X_ID).setParameter("id", id);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Ciudad o: obj){
					ciudad = new Ciudad();
					ciudad.setId(o.getId());
					ciudad.setNombre(o.getNombre());
					ciudad.setIdestado(o.getIdestado());
					ciudad.setFecha(new Date());
					ciudad.setIdtipofuncionario(o.getIdtipofuncionario());
				}
			}
		}
		
		return ciudad;
	}

	@Override
	public List<Ciudad> listarCiudades() throws Exception {
		List<Ciudad> ciudad = new ArrayList<Ciudad>();
		 
		Query q = em.createNamedQuery(Ciudad.LISTAR_CIUDAD);
		ciudad = q.getResultList();
		
		return ciudad;
	}

	@Override
	public Estado traerEstadoId(int id) throws Exception {
		List<Estado> obj = new ArrayList<Estado>();
		Estado estado = new Estado();
		if(id>0){
			Query q = em.createNamedQuery(Estado.TRAER_ESTADO_ID).setParameter("id", id);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Estado o: obj){
					estado = new Estado();
					estado.setId(o.getId());
					estado.setNombre(o.getNombre());
					estado.setAbreviatura(o.getAbreviatura());
					estado.setIdpais(o.getIdpais());
				}
			}
		}
		
		return estado;
	}

	@Override
	public Integer maxidEstado() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Estado.TRAER_ESTADO_ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public void crearEstado(Estado e) throws Exception {
		if(e.getId()>0){
			em.persist(e);
			em.flush();
		}
		
	}

	@Override
	public void crearEditaEstado(Estado e) throws Exception {
		Estado p = new Estado();
		List<Object[]> obj = new ArrayList<Object[]>();
		if(e!=null){
			p = traerEstadoId(e.getId());
			if(p.getId()>0){
				em.merge(e);
				em.flush();
			}
		}
		
	}

	@Override
	public Integer maxidZip() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Zip.TRAER_ZMX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Zip> listarZip() throws Exception {
		List<Zip> pais = new ArrayList<Zip>();
		 
		Query q = em.createNamedQuery(Zip.LISTAR_ZIP);
		pais = q.getResultList();
		
		return pais;
	}

	@Override
	public void crearZip(Zip z) throws Exception {
		if(z.getId()>0){
			em.persist(z);
			em.flush();
		}
	}

	@Override
	public void editaZip(Zip z) throws Exception {
		Zip p = new Zip();
		String formato = "yyyy-MM-dd";
		if(z!=null){
			p = traerZipId(z.getId());
			if(p.getId()>0){
				Calendar c = Calendar.getInstance();
				Date d = c.getTime();
				Timestamp t = new Timestamp(d.getTime());
				z.setFecha(t);
				Query q = em.createNamedQuery(Zip.EDITAR_ZIP);
				q.setParameter("codigo", z.getZipcode());
				q.setParameter("fecha", z.getFecha());
				q.setParameter("tipfun", z.getIdtipofuncionario());
				q.setParameter("funcio", z.getIdfuncionario());
				q.setParameter("id", z.getId());
				q.executeUpdate();
				em.flush();
			}
		}
	}
	
	public Zip mergeZip(Zip z) throws Exception {
		Zip p = new Zip();
		String formato = "yyyy-MM-dd";
		if(z!=null){
			p = traerZipId(z.getId());
			if(p.getId()>0){
				Calendar c = Calendar.getInstance();
				Date d = c.getTime();
				Timestamp t = new Timestamp(d.getTime());
				z.setFecha(t);
				Query q = em.createNamedQuery(Zip.EDITAR_ZIP);
				q.setParameter("codigo", z.getZipcode());
				q.setParameter("fecha", z.getFecha());
				q.setParameter("tipfun", z.getIdtipofuncionario());
				q.setParameter("funcio", z.getIdfuncionario());
				q.setParameter("id", z.getId());
				q.executeUpdate();
				em.flush();
			}
		}
		
		return z;
	}

	@Override
	public Zip traerZipId(int id) throws Exception {
		List<Zip> obj = new ArrayList<Zip>();
		Zip zip = new Zip();
		if(id>0){
			Query q = em.createNamedQuery(Zip.ZIP_X_ID).setParameter("id", id);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Zip o: obj){
					zip = new Zip();
					zip.setId(o.getId());
					zip.setFecha(o.getFecha());
					zip.setIdfuncionario(o.getIdfuncionario());
					zip.setIdtipofuncionario(o.getIdtipofuncionario());
					zip.setZipcode(o.getZipcode());
				}
			}
		}
		
		return zip;
	}

	@Override
	public Integer maxidEC() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Estadocaso.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Estadocaso> traerListEsC() throws Exception {
		List<Estadocaso> ec = new ArrayList<Estadocaso>();
		 
		Query q = em.createNamedQuery(Estadocaso.TRAER_EST_CAS);
		ec = q.getResultList();
		
		return ec;
	}

	@Override
	public void crearEC(Estadocaso ec) throws Exception {
		if(ec!=null){
			em.persist(ec);
			em.flush();
		}
		
	}

	@Override
	public void editarEC(Estadocaso ec) throws Exception {
		Estadocaso p = new Estadocaso();
		if(ec!=null){
			p = traerECId(ec.getId());
			if(p.getId()>0){
				em.merge(ec);
				em.flush();
			}
		}
		
	}

	@Override
	public Estadocaso traerECId(int ec) throws Exception {
		List<Estadocaso> obj = new ArrayList<Estadocaso>();
		Estadocaso est = new Estadocaso();
		if(ec>0){
			Query q = em.createNamedQuery(Estadocaso.TRAER_EST_CAS_ID).setParameter("id", ec);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Estadocaso o: obj){
					est = new Estadocaso();
					est.setId(o.getId());
					est.setNombre(o.getNombre());
					est.setAsignacion(o.getAsignacion());
					est.setEstado(o.getEstado());
				}
			}
		}
		
		return est;
		
	}

	@Override
	public Integer maxidDet() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Detalle.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Detalle> traerListDet() throws Exception {
		List<Detalle> ec = new ArrayList<Detalle>();
		 
		Query q = em.createNamedQuery(Detalle.TRAER_DET);
		ec = q.getResultList();
		
		return ec;
	}

	@Override
	public void crearDetalle(Detalle d) throws Exception {
		if(d.getId()>0){
			em.persist(d);
			em.flush();
		}
	}

	@Override
	public void editarDetalle(Detalle d) throws Exception {
		Detalle de = new Detalle();
		if(d!=null){
			de = traerDetId(d.getId());
			if(de.getId()>0){
				em.merge(d);
				em.flush();
			}
		}
	}

	@Override
	public Detalle traerDetId(int d) throws Exception {
		List<Detalle> obj = new ArrayList<Detalle>();
		Detalle det = new Detalle();
		if(d>0){
			Query q = em.createNamedQuery(Detalle.TRAER_DET_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Detalle o: obj){
					det = new Detalle();
					det.setId(o.getId());
					det.setDescripcion(o.getDescripcion());
					det.setValor(o.getValor());
				}
			}
		}
		
		return det;
	}

	@Override
	public Integer maxidRut() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Ruta.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Ruta> traerListRut() throws Exception {
		List<Ruta> rut = new ArrayList<Ruta>();
		 
		Query q = em.createNamedQuery(Ruta.TRAER_RUT);
		rut = q.getResultList();
		
		return rut;
	}

	@Override
	public void crearRuta(Ruta r) throws Exception {
		if(r.getId()>0){
			em.persist(r);
			em.flush();
		}
		
	}

	@Override
	public void editarRuta(Ruta r) throws Exception {
		Ruta de = new Ruta();
		if(r!=null){
			de = traerRutId(r.getId());
			if(de.getId()>0){
				em.merge(r);
				em.flush();
			}
		}
	}

	@Override
	public Ruta traerRutId(int d) throws Exception {
		List<Ruta> obj = new ArrayList<Ruta>();
		Ruta det = new Ruta();
		if(d>0){
			Query q = em.createNamedQuery(Ruta.TRAER_RUT_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Ruta o: obj){
					det = new Ruta();
					det.setId(o.getId());
					det.setNombrebanco(o.getNombrebanco());
					det.setNumero(o.getNumero());
				}
			}
		}
		
		return det;
	}

	@Override
	public Integer maxidDep() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Dependencia.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Dependencia> traerListDep() throws Exception {
		List<Dependencia> rut = new ArrayList<Dependencia>();
		 
		Query q = em.createNamedQuery(Dependencia.TRAER_DEP);
		rut = q.getResultList();
		
		return rut;
	}

	@Override
	public void crearDep(Dependencia d) throws Exception {
		if(d.getId()>0){
			em.persist(d);
			em.flush();
		}
	}

	@Override
	public void editarDep(Dependencia d) throws Exception {
		Dependencia de = new Dependencia();
		if(d!=null){
			de = traerDepId(d.getId());
			if(de.getId()>0){
				em.merge(d);
				em.flush();
			}
		}
	}

	@Override
	public Dependencia traerDepId(int d) throws Exception {
		List<Dependencia> obj = new ArrayList<Dependencia>();
		Dependencia dep = new Dependencia();
		if(d>0){
			Query q = em.createNamedQuery(Dependencia.TRAER_DEP_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Dependencia o: obj){
					dep = new Dependencia();
					dep.setId(o.getId());
					dep.setNombre(o.getNombre());
				}
			}
		}
		
		return dep;
	}

	@Override
	public Integer maxidTiDoc() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Tipodocumento.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Tipodocumento> traerListTidoc() throws Exception {
		List<Tipodocumento> tp = new ArrayList<Tipodocumento>();
		 
		Query q = em.createNamedQuery(Tipodocumento.TRAER_TP);
		tp = q.getResultList();
		
		return tp;
	}

	@Override
	public void crearTidoc(Tipodocumento tp) throws Exception {
		if(tp.getId()>0){
			em.persist(tp);
			em.flush();
		}
		
	}

	@Override
	public void editarTidoc(Tipodocumento tp) throws Exception {
		Tipodocumento de = new Tipodocumento();
		if(tp!=null){
			de = traerTicoId(tp.getId());
			if(de.getId()>0){
				em.merge(tp);
				em.flush();
			}
		}		
	}

	@Override
	public Tipodocumento traerTicoId(int d) throws Exception {
		List<Tipodocumento> obj = new ArrayList<Tipodocumento>();
		Tipodocumento tico = new Tipodocumento();
		if(d>0){
			Query q = em.createNamedQuery(Tipodocumento.TRAER_TP_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Tipodocumento o: obj){
					tico = new Tipodocumento();
					tico.setId(o.getId());
					tico.setNombre(o.getNombre());
				}
			}
		}
		return tico;
	}

	@Override
	public Integer maxidTg() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Tipogasto.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Tipogasto> traerListTg() throws Exception {
		List<Tipogasto> tp = new ArrayList<Tipogasto>();
		 
		Query q = em.createNamedQuery(Tipogasto.TRAER_TG);
		tp = q.getResultList();
		
		return tp;
	}

	@Override
	public void crearTg(Tipogasto tg) throws Exception {
		if(tg.getId()>0){
			em.persist(tg);
			em.flush();
		}
		
	}

	@Override
	public void editarTg(Tipogasto tg) throws Exception {
		Tipogasto de = new Tipogasto();
		if(tg!=null){
			de = traerTgId(tg.getId());
			if(de.getId()>0){
				em.merge(tg);
				em.flush();
			}
		}
		
	}

	@Override
	public Tipogasto traerTgId(int d) throws Exception {
		List<Tipogasto> obj = new ArrayList<Tipogasto>();
		Tipogasto tig = new Tipogasto();
		if(d>0){
			Query q = em.createNamedQuery(Tipogasto.TRAER_TG_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Tipogasto o: obj){
					tig = new Tipogasto();
					tig.setId(o.getId());
					tig.setNombre(o.getNombre());
				}
			}
		}
		return tig;
	}

	@Override
	public Integer maxidTt() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Tipotarea.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Tipotarea> traerListTt() throws Exception {
		List<Tipotarea> tp = new ArrayList<Tipotarea>();
		 
		Query q = em.createNamedQuery(Tipotarea.TRAER_TT);
		tp = q.getResultList();
		
		return tp;
	}

	@Override
	public void crearTt(Tipotarea tt) throws Exception {
		if(tt.getId()>0){
			em.persist(tt);
			em.flush();
		}
	}

	@Override
	public void editarTt(Tipotarea tt) throws Exception {
		Tipotarea de = new Tipotarea();
		if(tt!=null){
			de = traerTtId(tt.getId());
			if(de.getId()>0){
				em.merge(tt);
				em.flush();
			}
		}
		
	}

	@Override
	public Tipotarea traerTtId(int d) throws Exception {
		List<Tipotarea> obj = new ArrayList<Tipotarea>();
		Tipotarea tt = new Tipotarea();
		if(d>0){
			Query q = em.createNamedQuery(Tipotarea.TRAER_TT_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Tipotarea o: obj){
					tt = new Tipotarea();
					tt.setId(o.getId());
					tt.setNombre(o.getNombre());
				}
			}
		}
		return tt;
	}

	@Override
	public Integer maxidAnio() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Anio.TRAER_MAX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;

	}

	@Override
	public List<Anio> traerListAnio() throws Exception {
		List<Anio> a = new ArrayList<Anio>();
		 
		Query q = em.createNamedQuery(Anio.LISTAR_ANIO);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearAnio(Anio a) throws Exception {
		if(a.getId()>0){
			em.persist(a);
			em.flush();
		}
		
	}

	@Override
	public void editarAnio(Anio a) throws Exception {
		Anio an = new Anio();
		if(a!=null){
			an = traerAnioId(a.getId());
			if(an.getId()>0){
				em.merge(a);
				em.flush();
			}
		}
		
	}

	@Override
	public Anio traerAnioId(int d) throws Exception {
		List<Anio> obj = new ArrayList<Anio>();
		Anio tt = new Anio();
		if(d>0){
			Query q = em.createNamedQuery(Anio.ANIO_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Anio o: obj){
					tt = new Anio();
					tt.setId(o.getId());
					tt.setNombre(o.getNombre());
				}
			}
		}
		return tt;
	}

	@Override
	public Integer maxidPreg() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Pregunta.TRAER_MAX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Pregunta> traerListPreg() throws Exception {
		List<Pregunta> a = new ArrayList<Pregunta>();
		 
		Query q = em.createNamedQuery(Pregunta.LISTAR_PREG);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearPregunta(Pregunta p) throws Exception {
		if(p.getId()>0){
			em.persist(p);
			em.flush();
		}
	}

	@Override
	public void editarPregunta(Pregunta p) throws Exception {
		Pregunta pr = new Pregunta();
		if(p!=null){
			pr = traerPregId(p.getId());
			if(pr.getId()>0){
				em.merge(p);
				em.flush();
			}
		}
	}

	@Override
	public Pregunta traerPregId(int d) throws Exception {
		List<Pregunta> obj = new ArrayList<Pregunta>();
		Pregunta pr = new Pregunta();
		if(d>0){
			Query q = em.createNamedQuery(Pregunta.PREG_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Pregunta o: obj){
					pr = new Pregunta();
					pr.setId(o.getId());
					pr.setPregunta(o.getPregunta());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidParam() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Parametro.TRAER_MAX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Parametro> traerListParam() throws Exception {
		List<Parametro> a = new ArrayList<Parametro>();
		 
		Query q = em.createNamedQuery(Parametro.LISTAR_PAR);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearParametro(Parametro p) throws Exception {
		if(p.getId()>0){
			em.persist(p);
			em.flush();
		}
		
	}

	@Override
	public void editarParametro(Parametro p) throws Exception {
		Parametro pr = new Parametro();
		if(p!=null){
			pr = traerParamId(p.getId());
			if(pr.getId()>0){
				em.merge(p);
				em.flush();
			}
		}
	}

	@Override
	public Parametro traerParamId(int d) throws Exception {
		List<Parametro> obj = new ArrayList<Parametro>();
		Parametro pr = new Parametro();
		if(d>0){
			Query q = em.createNamedQuery(Parametro.PAR_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Parametro o: obj){
					pr = new Parametro();
					pr.setId(o.getId());
					pr.setEstado(o.getEstado());
					pr.setDireccion(o.getDireccion());
					pr.setIdciudadpobox(o.getIdciudadpobox());
					pr.setIdzippobox(o.getIdzippobox());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidPerfil() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Perfil.TRAER_PMX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Perfil> traerListPerf() throws Exception {
		List<Perfil> a = new ArrayList<Perfil>();
		 
		Query q = em.createNamedQuery(Perfil.LISTAR_PERF);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearPerfil(Perfil p) throws Exception {
		if(p.getId()>0){
			em.persist(p);
			em.flush();
		}
		
	}

	@Override
	public void editarPerfil(Perfil p) throws Exception {
		Perfil pr = new Perfil();
		if(p!=null){
			pr = traerPerfId(p.getId());
			if(pr.getId()>0){
				em.merge(p);
				em.flush();
			}
		}
		
	}

	@Override
	public Perfil traerPerfId(int d) throws Exception {
		List<Perfil> obj = new ArrayList<Perfil>();
		Perfil pr = new Perfil();
		if(d>0){
			Query q = em.createNamedQuery(Perfil.PERF_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Perfil o: obj){
					pr = new Perfil();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
					pr.setDescripcion(o.getDescripcion());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidEstadocivil() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Estadocivil.TRAER_ECMX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Estadocivil> traerListEestadoCivil() throws Exception {
		List<Estadocivil> a = new ArrayList<Estadocivil>();
		 
		Query q = em.createNamedQuery(Estadocivil.LISTAR_EC);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearEstadoCivil(Estadocivil ec) throws Exception {
		if(ec.getId()>0){
			em.persist(ec);
			em.flush();
		}
		
	}

	@Override
	public void editarEstadoCivil(Estadocivil ec) throws Exception {
		Estadocivil pr = new Estadocivil();
		if(ec!=null){
			pr = traerEstaCId(ec.getId());
			if(pr.getId()>0){
				em.merge(ec);
				em.flush();
			}
		}
		
	}

	@Override
	public Estadocivil traerEstaCId(int d) throws Exception {
		List<Estadocivil> obj = new ArrayList<Estadocivil>();
		Estadocivil pr = new Estadocivil();
		if(d>0){
			Query q = em.createNamedQuery(Estadocivil.EC_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Estadocivil o: obj){
					pr = new Estadocivil();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidFPT() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Formapagotax.TRAER_MX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Formapagotax> traerListFPT() throws Exception {
		List<Formapagotax> a = new ArrayList<Formapagotax>();
		 
		Query q = em.createNamedQuery(Formapagotax.LISTAR_FP);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearFPT(Formapagotax fp) throws Exception {
		if(fp.getId()>0){
			em.persist(fp);
			em.flush();
		}
		
	}

	@Override
	public void editarFPT(Formapagotax fp) throws Exception {
		Formapagotax pr = new Formapagotax();
		if(fp!=null){
			pr = traerFPTCId(fp.getId());
			if(pr.getId()>0){
				em.merge(fp);
				em.flush();
			}
		}
	}

	@Override
	public Formapagotax traerFPTCId(int d) throws Exception {
		List<Formapagotax> obj = new ArrayList<Formapagotax>();
		Formapagotax pr = new Formapagotax();
		if(d>0){
			Query q = em.createNamedQuery(Formapagotax.FP_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Formapagotax o: obj){
					pr = new Formapagotax();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public List<Extensione> traerListExte() throws Exception {
		List<Extensione> a = new ArrayList<Extensione>();
		 
		Query q = em.createNamedQuery(Extensione.LISTAR_EX);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearExt(Extensione ex) throws Exception {
		if(ex.getExtension()!=null && !ex.getExtension().equals("")){
			String formato = "yyyy-MM-dd";
			SimpleDateFormat f = new SimpleDateFormat(formato);
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();
			String fecha = f.format(d);
			ex.setFecha(fecha);
			em.persist(ex);
			em.flush();
		}
		
	}

	@Override
	public void editarExt(Extensione ex) throws Exception {
		if(ex.getExtension()!=null && !ex.getExtension().equals("")){
			em.merge(ex);
			em.flush();
		}
		
	}

	@Override
	public Integer maxidFormaPagoFactura() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Formapagofactura.MAX_FACPAG);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Formapagofactura> traerListFormaPagoFactura() throws Exception {
		List<Formapagofactura> a = new ArrayList<Formapagofactura>();
		 
		Query q = em.createNamedQuery(Formapagofactura.LISTAR_FACPAG);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearFormaPagoFactura(Formapagofactura fpf) throws Exception {
		if(fpf.getNombre()!=null && !fpf.getNombre().equals("")){
			em.persist(fpf);
			em.flush();
		}
	}

	@Override
	public void editarFormaPagoFacutura(Formapagofactura fpf) throws Exception {
		Formapagofactura pr = new Formapagofactura();
		if(fpf!=null){
			pr = traerFpfCId(fpf.getId());
			if(pr.getId()>0){
				em.merge(fpf);
				em.flush();
			}
		}
	}

	@Override
	public Formapagofactura traerFpfCId(int d) throws Exception {
		List<Formapagofactura> obj = new ArrayList<Formapagofactura>();
		Formapagofactura pr = new Formapagofactura();
		if(d>0){
			Query q = em.createNamedQuery(Formapagofactura.LISTAR_FACPAG_POR_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Formapagofactura o: obj){
					pr = new Formapagofactura();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidTipoParentesco() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Tipoparentesco.MAX_LISTAR_TIPPAR);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Tipoparentesco> traerListTipoParentesco() throws Exception {
		List<Tipoparentesco> a = new ArrayList<Tipoparentesco>();
		 
		Query q = em.createNamedQuery(Tipoparentesco.LISTAR_TIPPAR);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearTipoParentesco(Tipoparentesco tp) throws Exception {
		if(tp.getNombre()!=null && !tp.getNombre().equals("")){
			em.persist(tp);
			em.flush();
		}
		
	}

	@Override
	public void editarTipoParentesco(Tipoparentesco tp) throws Exception {
		Tipoparentesco pr = new Tipoparentesco();
		if(tp!=null){
			pr = traerTPId(tp.getId());
			if(pr.getId()>0){
				em.merge(tp);
				em.flush();
			}
		}
		
	}

	@Override
	public Tipoparentesco traerTPId(int d) throws Exception {
		List<Tipoparentesco> obj = new ArrayList<Tipoparentesco>();
		Tipoparentesco pr = new Tipoparentesco();
		if(d>0){
			Query q = em.createNamedQuery(Tipoparentesco.LISTAR_LISTAR_TIPPAR_POR_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Tipoparentesco o: obj){
					pr = new Tipoparentesco();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidTipoIdentificacion() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Tipoidentificacion.MAX_LISTAR_TIPIDEN);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Tipoidentificacion> traerTipoIdentificacion() throws Exception {
		List<Tipoidentificacion> a = new ArrayList<Tipoidentificacion>();
		 
		Query q = em.createNamedQuery(Tipoidentificacion.LISTAR_TIPIDEN);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearTipoIdentificacion(Tipoidentificacion ti) throws Exception {
		if(ti!=null && !ti.equals("")){
			em.persist(ti);
			em.flush();
		}
		
	}

	@Override
	public void editarTipoIdentificacion(Tipoidentificacion ti) throws Exception {
		Tipoidentificacion pr = new Tipoidentificacion();
		if(ti!=null){
			pr = traerTIId(ti.getId());
			if(pr.getId()>0){
				em.merge(ti);
				em.flush();
			}
		}
		
	}

	@Override
	public Tipoidentificacion traerTIId(int d) throws Exception {
		List<Tipoidentificacion> obj = new ArrayList<Tipoidentificacion>();
		Tipoidentificacion pr = new Tipoidentificacion();
		if(d>0){
			Query q = em.createNamedQuery(Tipoidentificacion.LISTAR_LISTAR_TIPIDEN_POR_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Tipoidentificacion o: obj){
					pr = new Tipoidentificacion();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidFormaEnvio() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Formaenvio.TRAER_MX_ID);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Formaenvio> traerFormaEnvio() throws Exception {
		List<Formaenvio> a = new ArrayList<Formaenvio>();
		 
		Query q = em.createNamedQuery(Formaenvio.LISTAR_FE);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearFormaEnvio(Formaenvio fe) throws Exception {
		if(fe!=null && !fe.equals("")){
			em.persist(fe);
			em.flush();
		}
		
	}

	@Override
	public void editarFormaEnvio(Formaenvio fe) throws Exception {
		Formaenvio pr = new Formaenvio();
		if(fe!=null){
			pr = traerFeId(fe.getId());
			if(pr.getId()>0){
				em.merge(fe);
				em.flush();
			}
		}
		
	}

	@Override
	public Formaenvio traerFeId(int d) throws Exception {
		List<Formaenvio> obj = new ArrayList<Formaenvio>();
		Formaenvio pr = new Formaenvio();
		if(d>0){
			Query q = em.createNamedQuery(Formaenvio.FE_X_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Formaenvio o: obj){
					pr = new Formaenvio();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public Integer maxidEstadoFactura() throws Exception {
		Integer p = new Integer(0);
		Query q = em.createNamedQuery(Estadofactura.ID_MAX);
		
		p =(Integer) q.getSingleResult();
		
		return p;
	}

	@Override
	public List<Estadofactura> traerEstadoFactura() throws Exception {
		List<Estadofactura> a = new ArrayList<Estadofactura>();
		 
		Query q = em.createNamedQuery(Estadofactura.TRAER_EFA);
		a = q.getResultList();
		
		return a;
	}

	@Override
	public void crearEstadoFactura(Estadofactura ef) throws Exception {
		if(ef!=null && !ef.equals("")){
			em.persist(ef);
			em.flush();
		}
		
	}

	@Override
	public void editarEstadoFactura(Estadofactura ef) throws Exception {
		Estadofactura pr = new Estadofactura();
		if(ef!=null){
			pr = traerEFId(ef.getId());
			if(pr.getId()>0){
				em.merge(ef);
				em.flush();
			}
		}
		
	}

	@Override
	public Estadofactura traerEFId(int d) throws Exception {
		List<Estadofactura> obj = new ArrayList<Estadofactura>();
		Estadofactura pr = new Estadofactura();
		if(d>0){
			Query q = em.createNamedQuery(Estadofactura.TRAER_EFA_ID).setParameter("id", d);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Estadofactura o: obj){
					pr = new Estadofactura();
					pr.setId(o.getId());
					pr.setNombre(o.getNombre());
				}
			}
		}
		return pr;
	}

	@Override
	public List<VFuncionario> traerFuncionarios() throws Exception {
		List<VFuncionario> listF = new ArrayList<VFuncionario>();
		
		Query q = em.createNamedQuery(VFuncionario.TRAER_OFFICER);
		
		listF = q.getResultList();
		
		return listF;
	}

	@Override
	public List<Sexo> listarSexo() throws Exception {
		List<Sexo> s = new ArrayList<Sexo>();
		Query q = em.createNamedQuery(Sexo.TRAER_SEXO);
		
		s = q.getResultList();
		
		return s;
	}

	@Override
	public List<CiudadDTO> ciudadEstado(int idestado) throws Exception {
		List<CiudadDTO> s = new ArrayList<CiudadDTO>();
		List<Object[]> er = new ArrayList<Object[]>();
		
		if(idestado>0){
			Query q = em.createNamedQuery(Ciudad.TAER_CIUDAD_EST).setParameter("idestado", idestado);
			
			er = q.getResultList();
			
			if(er.size()>0){
				for(Object[] a : er){
					CiudadDTO c = new CiudadDTO((int)a[0],(String)a[1]);
					s.add(c);
				}
			}
		}
		
		
		return s;
	}

	@Override
	public void delete(Object o) throws Exception {
		if(o instanceof Pai){
			Pai p =  (Pai) o;
			em.remove(em.contains(p) ? p : em.merge(p));
		}else if(o instanceof Ciudad){
			Ciudad c = (Ciudad) o;
			Query q = em.createNamedQuery(Ciudad.DELETE_CIUDAD).setParameter("id", c.getId());
			q.executeUpdate();
		}else if(o instanceof Estado){
			Estado e = (Estado) o;
			em.remove(em.contains(e) ? e : em.merge(e));
		}else if(o instanceof CiudZipDTO){
			CiudZipDTO c =	(CiudZipDTO) o;
			//cz.setIdciudad(z.getId().);
			Query qc = em.createNamedQuery(Ciudadzip.DELETE_ZIP);
			qc.setParameter("idc", c.getIdCiudad());
			qc.setParameter("idz", c.getIdZip());
			qc.executeUpdate();
			Query q = em.createNamedQuery(Zip.DELETE_ZIP).setParameter("id", c.getIdZip());
			q.executeUpdate();
		}else if(o instanceof Anio){
			Anio a = (Anio)o;
			em.remove(em.contains(a) ? a : em.merge(a));
		}else if(o instanceof Pregunta){
			Pregunta p = (Pregunta)o;
			em.remove(em.contains(p) ? p : em.merge(p));;
		}else if(o instanceof Parametro){
			Parametro pa = (Parametro)o;
			em.remove(em.contains(pa) ? pa : em.merge(pa));
		}else if(o instanceof Perfil){
			Perfil pe= (Perfil)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Estadocivil){
			Estadocivil pe= (Estadocivil)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Tipoidentificacion){
			Tipoidentificacion pe= (Tipoidentificacion)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Formaenvio){
			Formaenvio pe= (Formaenvio)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Estadofactura){
			Estadofactura pe= (Estadofactura)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Estadocaso){
			Estadocaso pe= (Estadocaso)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Detalle){
			Detalle pe= (Detalle)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Ruta){
			Ruta pe= (Ruta)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Dependencia){
			Dependencia pe= (Dependencia)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Tipodocumento){
			Tipodocumento pe= (Tipodocumento)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Tipogasto){
			Tipogasto pe= (Tipogasto)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Tipotarea){
			Tipotarea pe= (Tipotarea)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Formapagotax){
			Formapagotax pe= (Formapagotax)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Extensione){
			Extensione pe= (Extensione)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Formapagofactura){
			Formapagofactura pe= (Formapagofactura)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}else if(o instanceof Tipoparentesco){
			Tipoparentesco pe= (Tipoparentesco)o;
			em.remove(em.contains(pe) ? pe : em.merge(pe));
		}
		
	}

	@Override
	public void crearCiudadZip(Ciudadzip cz) throws Exception {
		if(cz!=null){
			em.persist(cz);
		}
		
	}

	@Override
	public List<CiudZipDTO> listarCiudadZip() throws Exception {
		List<Object[]> cp = new ArrayList<Object[]>();
		List<CiudZipDTO> cz = new ArrayList<CiudZipDTO>();
		
		Query q = em.createNamedQuery(Ciudadzip.LIST_ZIP_CIU);
		cp = q.getResultList();
		
		if(cp.size()>0){
			for(Object[] c : cp){
				CiudZipDTO ct = new CiudZipDTO();
				ct.setIdZip((Integer)c[1]);
				ct.setIdCiudad((Integer)c[0]);
				ct.setCodZip(traerZipId((Integer)c[1]).getZipcode());
				ct.setNombreCiudad(listarCiudadPorId((Integer)c[0]).getNombre());
				cz.add(ct);
			}
		}
		
		return cz;
	}

	@Override
	public void editarCiudadZip(Ciudadzip cz) throws Exception {
		Ciudadzip p = new Ciudadzip();
		String formato = "yyyy-MM-dd";
		if(cz!=null){
			p = traerCd(cz.getIdzip(), cz.getIdciudad());
			if(p.getIdzip()==0){
				Calendar c = Calendar.getInstance();
				Date d = c.getTime();
				Timestamp t = new Timestamp(d.getTime());
				cz.setFecha(t);
				Query q = em.createNamedQuery(Ciudadzip.EDIT_ZIP_CIU);
				q.setParameter("idciu", cz.getIdciudad());
				q.setParameter("fech", cz.getFecha());
				q.setParameter("fun", cz.getIdfuncionario());
				q.setParameter("tipf", cz.getIdtipofuncionario());
				q.setParameter("idzip", cz.getIdzip());
				q.executeUpdate();
				em.flush();
			}
		}
		
	}

	@Override
	public Ciudadzip traerCd(int d, int c) throws Exception {
		List<Object[]> obj = new ArrayList<Object[]>();
		Ciudadzip zip = new Ciudadzip();
		if(d>0 && c>0){
			Query q = em.createNamedQuery(Ciudadzip.ZIP_CIU_ID);
			q.setParameter("zip", d);
			q.setParameter("ciud", c);
			obj = q.getResultList();
			if(obj.size()>0){
				for(Object[] o: obj){
					zip= new Ciudadzip();
					zip.setIdciudad((Integer)o[0]);
					zip.setIdzip((Integer)o[1]);
					
				}
			}
		}
		
		return zip;	
	}

	@Override
	public List<CasoReportDTO> listNovedad(String nove) throws Exception {
		List<Object[]> objeto = new ArrayList<Object[]>();
		List<CasoReportDTO> nc = new ArrayList<CasoReportDTO>();
		Query query= em.createNamedQuery(Caso.LIST_NOVE_DTO);
		query.setParameter("caso", nove);
		
		objeto = query.getResultList();
		
		if(objeto.size()>0){
			for(Object[] o : objeto){
				CasoReportDTO repor = new CasoReportDTO();
				repor.setCaso(((String) o[0]).trim());
				repor.setNumero(((String) o[1]).trim());
				repor.setNombre(((String) o[2]).trim()+" "+((String) o[3]).trim()+" "+((String) o[4]).trim()+" "+((String) o[5]).trim());
				repor.setUsuario((String)o[6]);
				repor.setDependencia(((String) o[7]).trim());
				repor.setFecha((Date)o[8]);
				repor.setObservaciones(((String) o[9]).trim());
				nc.add(repor);
			}
		}
		return nc;
	}

	@Override
	public void crearEmpresa(Persona p) throws Exception {
		if(p.getId().getSsitin()!=null && !p.getId().getSsitin().equals("")){
			em.persist(p);
			em.flush();
		}
		
	}

	@Override
	public List<EmpresaDTO> traerEmpresa() throws Exception {
		List<EmpresaDTO> rsul = new ArrayList<EmpresaDTO>();
		List<Object[]> perso = new ArrayList<Object[]>();
		Query q = em.createNamedQuery(Persona.CONSULTAR_EMPRESAS);
		
		perso = q.getResultList();
		if(perso.size()>0){
			for(Object[] p : perso){
				EmpresaDTO em = new EmpresaDTO();
				em.setCiudad(String.valueOf((Integer)p[0]));
				em.setDireccion((String)p[1]);
				em.setEin((String)p[2]);
				em.setNombreEmpresa((String)p[3]);
				if(((String)p[5])!=null && !((String)p[5]).equals("")){
					em.setUsuario((String)p[5]);
					em.setEmail((String)p[4]);
				}else{
					em.setUsuario((String)p[4]);
					em.setEmail((String)p[4]);
				}
				
				
				rsul.add(em);
			}
		}
		
		return rsul;
	}

	@Override
	public List<Estado> traerEstadoPais(int idPais) throws Exception {
		List<Estado> espa = new ArrayList<Estado>();
		Query q = em.createNamedQuery(Estado.ESTADO_PAIS).setParameter("pais", idPais);
		espa = q.getResultList();
		return espa;
	}

	@Override
	public List<Zip> traerCiudadZip(int idciudad) throws Exception {
		List<Object[]> espa = new ArrayList<Object[]>();
		List<Zip> zl = new ArrayList<Zip>();
		Query q = em.createNamedQuery(Zip.ZIP_CIU);
		q.setParameter("1", idciudad);
		espa = q.getResultList();
		if(espa.size()>0){
			for(Object[] o : espa){
				Zip z = new Zip();
				z.setId((Integer)o[0]);
				z.setZipcode(((String)o[1]).trim());
				zl.add(z);
			}
			
		}
		return zl;
	}

	@Override
	public void crearFuncionario(Funcionario f) throws Exception {
		if(f.getUsuario()!=null && !f.getUsuario().equals("")){
			em.persist(f);
		}
	}

	@Override
	public EmpresaDTO traerEmpresaUsuario(String user, String empre) throws Exception {
		EmpresaDTO rsul = new EmpresaDTO();
		List<Object[]> perso = new ArrayList<Object[]>();
		Pattern pattern;
	    String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	    pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher m  = pattern.matcher(user);
		
		if(m.matches()){
			Query q = em.createNamedQuery(Persona.CONSULTAR_EMPRESAS_USUARIO);
			q.setParameter("user", user);
			q.setParameter("nombre", empre);
			perso = q.getResultList();
		}else{
			Query q = em.createNamedQuery(Persona.CONSULTAR_EMPRESAS_USUARIO_D);
			q.setParameter("user", user);
			q.setParameter("nombre", empre);
			perso = q.getResultList();
		}
		
				if(perso.size()>0){
			for(Object[] p : perso){
				rsul = new EmpresaDTO();
				rsul.setCiudad(String.valueOf((Integer)p[0]));
				rsul.setDireccion((String)p[1]);
				rsul.setEin((String)p[2]);
				rsul.setNombreEmpresa((String)p[3]);
				if(((String)p[8])!=null && !((String)p[8]).equals("")){
					rsul.setUsuario((String)p[8]);
					rsul.setEmail((String)p[4]);
				}else{
					rsul.setEmail((String)p[4]);
					rsul.setUsuario((String)p[4]);
				}
				
				rsul.setIndustria((String)p[5]);
				rsul.setTelf((String)p[6]);
				rsul.setFax((String)p[7]);
				rsul.setZip(((Integer)p[9]).toString());
				
			}
		}
		return rsul;
	}

	@Override
	public void editarEmpresa(Persona p) throws Exception {
		if(p.getId().getSsitin()!=null && !p.getId().getSsitin().equals("")){
			Calendar c = Calendar.getInstance();
			Date d = c.getTime();
			Timestamp t = new Timestamp(d.getTime());
			Timestamp ti = new Timestamp(d.getTime());
			p.setFechaentrada(t);
			p.setFechanacimiento(ti);
			Query q = em.createNamedQuery(Persona.UPDATE_EMPRESAS);
			q.setParameter("ciudad", p.getCiudadnacimiento());
			q.setParameter("dirusa", p.getDireccionorigen());
			q.setParameter("nombre", p.getNombre1());
			q.setParameter("email", p.getEmail());
			q.setParameter("tel", p.getTelefono1());
			q.setParameter("fax", p.getTelefono2());
			q.setParameter("zip", p.getZipusa());
			q.setParameter("nit", p.getId().getSsitin());
			q.executeUpdate();
			em.flush();
		}
	}

	@Override
	public Funcionario traerFuncionario(String user) throws Exception {
		Funcionario f = new Funcionario();
		List<Object[]> o = new ArrayList<Object[]>();
		Query q = em.createNamedQuery(Funcionario.USUARIO_PERMITIDO);
		q.setParameter("user", user);
		
		o = q.getResultList();
		if(o.size()>0){
			for(Object[] ob : o){
				f.setUsuario((String)ob[0]);
			}
		}
		
		return f;
	}

}



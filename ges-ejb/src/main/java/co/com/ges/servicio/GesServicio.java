package co.com.ges.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import co.com.ges.dto.CasoReportDTO;
import co.com.ges.dto.CiudZipDTO;
import co.com.ges.dto.CiudadDTO;
import co.com.ges.dto.EmpresaDTO;
import co.com.ges.fachada.GesFachada;
import co.com.ges.modelo.Anio;
import co.com.ges.modelo.Ciudad;
import co.com.ges.modelo.Ciudadzip;
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
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GesServicio {
	
	@EJB
	GesFachada fachada;
	
	public List<Pai> listarPais() throws Exception{
		return fachada.listarPais();
	}
	
	public void crearPais(Pai pais) throws Exception{
		fachada.crearPais(pais);
	}
	
	public void editarPais(Pai paisNombre) throws Exception{
		fachada.editarPais(paisNombre);
	}
	
	public Integer maxId() throws Exception{
		return fachada.maxid();
	}
	
	public List<Estado> traerEstado() throws Exception{
		return fachada.traerEstado();
	}
	
	public Integer maxCodCiu() throws Exception{
		return fachada.maxidCiud();
	}
	
	public void crearCiudad(Ciudad ciud) throws Exception{
		fachada.crearCiudad(ciud);
	}

	public void editarCiudad(Ciudad ciu) throws Exception{
		fachada.editarCiudad(ciu);
	}
	
	public List<Ciudad> listarCiudad() throws Exception{
		return fachada.listarCiudades();
	}

	public Integer maxCodEst() throws Exception{
		return fachada.maxidEstado();
	}
	
	public void crearEstado(Estado e) throws Exception{
		fachada.crearEstado(e);
	}
	
	public void editarEstado(Estado e) throws Exception{
		fachada.crearEditaEstado(e);
	}
	
	public Integer maxCodZip() throws Exception{
		return fachada.maxidZip();
	}
	
	public List<Zip> listarZip() throws Exception{
		return fachada.listarZip();
	}
	
	public void crearZip(Zip z) throws Exception{
		fachada.crearZip(z);
	}
	
	public void editZip(Zip z) throws Exception{
		fachada.editaZip(z);
	}
	
	public Integer maxCodEC() throws Exception{
		return fachada.maxidEC();
	}
	
	public List<Estadocaso> listarEC() throws Exception{
		return fachada.traerListEsC();
	}
	
	public void crearEC(Estadocaso ec) throws Exception{
		fachada.crearEC(ec);
	}
	
	public void editarEC(Estadocaso ec) throws Exception{
		fachada.editarEC(ec);
	}
	
	public Integer maxCodDetalle() throws Exception{
		return fachada.maxidDet();
	}
	
	public List<Detalle> listarDetalle() throws Exception{
		return fachada.traerListDet();
	}
	
	public void crearDetalle(Detalle d) throws Exception{
		fachada.crearDetalle(d);
	}
	
	public void editarDetalle(Detalle d) throws Exception{
		fachada.editarDetalle(d);
	}
	
	public Integer maxCodRuta() throws Exception{
		return fachada.maxidRut();
	}
	
	public List<Ruta> listarRuta() throws Exception{
		return fachada.traerListRut();
	}
	
	public void crearRuta(Ruta r) throws Exception{
		fachada.crearRuta(r);
	}
	
	public void editarRuta(Ruta r) throws Exception{
		fachada.editarRuta(r);
	}
	
	public Integer maxCodDep() throws Exception{
		return fachada.maxidDep();
	}
	
	public List<Dependencia> listarDependencia() throws Exception{
		return fachada.traerListDep();
	}
	
	public void crearDependencia(Dependencia d) throws Exception{
		fachada.crearDep(d);
	}
	
	public void editarDependencia(Dependencia d) throws Exception{
		fachada.editarDep(d);
	}
	
	public Integer maxCodTico() throws Exception{
		return fachada.maxidTiDoc();
	}
	
	public List<Tipodocumento> listarTico() throws Exception{
		return fachada.traerListTidoc();
	}
	
	public void crearTico(Tipodocumento tp) throws Exception{
		fachada.crearTidoc(tp);
	}
	
	public void editarTico(Tipodocumento tp) throws Exception{
		fachada.editarTidoc(tp);
	}
	
	public void crearTg(Tipogasto tg) throws Exception{
		fachada.crearTg(tg);
	}
	
	public void editarTig(Tipogasto tg) throws Exception{
		fachada.editarTg(tg);
	}
	
	public Integer maxCodTig() throws Exception{
		return fachada.maxidTg();
	}
	
	public List<Tipogasto> listarTg() throws Exception{
		return fachada.traerListTg();
	}
	
	public void crearTt(Tipotarea tt) throws Exception{
		fachada.crearTt(tt);
	}
	
	public void editarTt(Tipotarea tt) throws Exception{
		fachada.editarTt(tt);
	}
	
	public Integer maxCodTt() throws Exception{
		return fachada.maxidTt();
	}
	
	public List<Tipotarea> listarTt() throws Exception{
		return fachada.traerListTt();
	}
	
	public Integer maxCodAnio() throws Exception{
		return fachada.maxidAnio();
	}
	
	public List<Anio> listarAnio() throws Exception{
		return fachada.traerListAnio();
	}
	
	public void crearAnio(Anio a) throws Exception{
		fachada.crearAnio(a);
	}
	
	public void editarAnio(Anio a) throws Exception{
		fachada.editarAnio(a);
	}
	
	public Integer maxCodPreg() throws Exception{
		return fachada.maxidPreg();
	}
	
	public List<Pregunta> listarPregunta() throws Exception{
		return fachada.traerListPreg();
	}
	
	public void crearPregunta(Pregunta p) throws Exception{
		fachada.crearPregunta(p);
	}
	
	public void editarPregunta(Pregunta p) throws Exception{
		fachada.editarPregunta(p);
	}
	
	public Integer maxCodParam() throws Exception{
		return fachada.maxidParam();
	}
	
	public List<Parametro> listarParametros() throws Exception{
		return fachada.traerListParam();
	}
	
	public void crearParamero(Parametro p) throws Exception{
		fachada.crearParametro(p);
	}
	
	public void editarParametro(Parametro p) throws Exception{
		fachada.editarParametro(p);
	}
	
	public Integer maxCodPerf() throws Exception{
		return fachada.maxidPerfil();
	}
	
	public List<Perfil> listarPerfil() throws Exception{
		return fachada.traerListPerf();
	}
	
	public void crearPerfil(Perfil p) throws Exception{
		fachada.crearPerfil(p);
	}
	
	public void editarPerfil(Perfil p) throws Exception{
		fachada.editarPerfil(p);
	}
	
	public Integer maxCodEstadoCivil() throws Exception{
		return fachada.maxidEstadocivil();
	}
	
	public List<Estadocivil> listarEstadoCivil() throws Exception{
		return fachada.traerListEestadoCivil();
	}
	
	public void crearEstadoCivil(Estadocivil ec) throws Exception{
		fachada.crearEstadoCivil(ec);
	}
	
	public void editarEstadoCivil(Estadocivil ec) throws Exception{
		fachada.editarEstadoCivil(ec);
	}
	
	public Integer maxCodFPT() throws Exception{
		return fachada.maxidFPT();
	}
	
	public List<Formapagotax> listarFPT() throws Exception{
		return fachada.traerListFPT();
	}
	
	public void crearFTP(Formapagotax fp) throws Exception{
		fachada.crearFPT(fp);
	}
	
	public void editarFTP(Formapagotax fp) throws Exception{
		fachada.editarFPT(fp);
	}
	
	public List<Extensione> listarExtecion() throws Exception{
		return fachada.traerListExte();
	}
	
	public void crearExtencion(Extensione ex) throws Exception{
		fachada.crearExt(ex);
	}
	
	public void editarExtenciones(Extensione ex) throws Exception{
		fachada.editarExt(ex);
	}
	
	public Integer maxCodFormaPagoFactura() throws Exception{
		return fachada.maxidFormaPagoFactura();
	}
	
	public List<Formapagofactura> listarFormaPagoFactura() throws Exception{
		return fachada.traerListFormaPagoFactura();
	}
	
	public void crearFormaPagoFactura(Formapagofactura fpf) throws Exception{
		fachada.crearFormaPagoFactura(fpf);
	}
	
	public void editarFormaPagoFactura(Formapagofactura fpf) throws Exception{
		fachada.editarFormaPagoFacutura(fpf);
	}
	
	public Integer maxCodTipoParentesco() throws Exception{
		return fachada.maxidTipoParentesco();
	}
	
	public List<Tipoparentesco> listarTipoParentesco() throws Exception{
		return fachada.traerListTipoParentesco();
	}
	
	public void crearTipoParentesco(Tipoparentesco tp) throws Exception{
		fachada.crearTipoParentesco(tp);
	}
	
	public void editarTipoParentesco(Tipoparentesco tp) throws Exception{
		fachada.editarTipoParentesco(tp);
	}
	
	public Integer maxCodTipoIdentifiacion() throws Exception{
		return fachada.maxidTipoIdentificacion();
	}
	
	public List<Tipoidentificacion> listarTipoIdentifiacion() throws Exception{
		return fachada.traerTipoIdentificacion();
	}
	
	public void crearTipoIdentifiacion(Tipoidentificacion ti) throws Exception{
		fachada.crearTipoIdentificacion(ti);
	}
	
	public void editarTipoIdentifiacion(Tipoidentificacion ti) throws Exception{
		fachada.editarTipoIdentificacion(ti);
	}
	
	public Integer maxCodFormaEnvio() throws Exception{
		return fachada.maxidFormaEnvio();
	}
	
	public List<Formaenvio> listarFormaEnvio() throws Exception{
		return fachada.traerFormaEnvio();
	}
	
	public void crearFormaEnvio(Formaenvio fe) throws Exception{
		fachada.crearFormaEnvio(fe);
	}
	
	public void editarFormaEnvio(Formaenvio fe) throws Exception{
		fachada.editarFormaEnvio(fe);
	}
	
	public Integer maxCodEstadoFactura() throws Exception{
		return fachada.maxidEstadoFactura();
	}
	
	public List<Estadofactura> listarEstadoFactura() throws Exception{
		return fachada.traerEstadoFactura();
	}
	
	public void crearEstadoFactura(Estadofactura ef) throws Exception{
		fachada.crearEstadoFactura(ef);
	}
	
	public void editarEstadoFactura(Estadofactura ef) throws Exception{
		fachada.editarEstadoFactura(ef);
	}
	
	public List<VFuncionario> traerFuncionario() throws Exception{
		return fachada.traerFuncionarios();
	}
	
	public List<Sexo> listarSexo() throws Exception{
		return fachada.listarSexo();
	}
	
	public List<CiudadDTO> ciudXEstad(int idestado) throws Exception{
		return fachada.ciudadEstado(idestado);
	}
	
	public void delete(Object o) throws Exception{
		fachada.delete(o);
	}
	
	public void crearCiudadzip(Ciudadzip cz) throws Exception{
		fachada.crearCiudadZip(cz);
	}
	
	public List<CiudZipDTO> ciudadZipLis() throws Exception{
		return fachada.listarCiudadZip();
	}
	
	public void ediatrCiudadzip(Ciudadzip cz) throws Exception{
		fachada.editarCiudadZip(cz);
	}
	
	public List<CasoReportDTO> listarNovedad(String  nove) throws Exception{
		return fachada.listNovedad(nove);
	}
	
	public void crearEmpresa(Persona p) throws Exception{
		fachada.crearEmpresa(p);
	}
	
	public List<EmpresaDTO> traerEmprsas() throws Exception{
		return fachada.traerEmpresa();
	}
	
	public List<Estado> traerEstadoPais(int idPais) throws Exception {
		return fachada.traerEstadoPais(idPais);
	}
	
	public List<Zip> traerCiudadZip(int idciudad) throws Exception {
		return fachada.traerCiudadZip(idciudad);
	}
	
	public void crearFuncionario(Funcionario f) throws Exception{
		fachada.crearFuncionario(f);
	}
	
	public EmpresaDTO traerEmpresa(String user,String empre) throws Exception{
		return fachada.traerEmpresaUsuario(user, empre);
	}
	
	public void editarEmpresa(Persona p) throws Exception {
		fachada.editarEmpresa(p);
	}
	
	public Funcionario traerfuncionari(String user) throws Exception{
		return fachada.traerFuncionario(user);
	}
	
}

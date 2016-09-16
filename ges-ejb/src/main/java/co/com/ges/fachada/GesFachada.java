package co.com.ges.fachada;

import java.security.cert.Extension;
import java.util.List;

import javax.ejb.Remote;

import co.com.ges.dto.CasoReportDTO;
import co.com.ges.dto.CiudZipDTO;
import co.com.ges.dto.CiudadDTO;
import co.com.ges.dto.EmpresaDTO;
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

@Remote
public interface GesFachada {
	
	public List<Pai> listarPais() throws Exception;
	
	public Pai listarPaisPorId(int id) throws Exception;
	
	public void crearPais(Pai pais) throws Exception;
	
	public void editarPais(Pai pais) throws Exception;
	
	public Integer maxid() throws Exception;
	
	public List<Estado> traerEstado() throws Exception;
	
	public Integer maxidCiud() throws Exception;
	
	public void crearCiudad(Ciudad ciud) throws Exception;
	
	public void editarCiudad(Ciudad ciud) throws Exception;
	
	public Ciudad listarCiudadPorId(int id) throws Exception;
	
	public List<Ciudad> listarCiudades() throws Exception;
	
	public Estado traerEstadoId(int id) throws Exception;
	
	public Integer maxidEstado() throws Exception;
	
	public void crearEstado(Estado e) throws Exception;
	
	public void crearEditaEstado(Estado e) throws Exception;
	
	public Integer maxidZip() throws Exception;
	
	public List<Zip> listarZip() throws Exception;
	
	public void crearZip(Zip z) throws Exception;
	
	public void editaZip(Zip z) throws Exception; 
	
	public Zip traerZipId(int id) throws Exception;
	
	public Integer maxidEC() throws Exception;
	
	public List<Estadocaso> traerListEsC() throws Exception;
	
	public void crearEC(Estadocaso ec) throws Exception;
	
	public void editarEC(Estadocaso ec) throws Exception;
	
	public Estadocaso traerECId(int ec) throws Exception;
	
	public Integer maxidDet() throws Exception;
	
	public List<Detalle> traerListDet() throws Exception;
	
	public void crearDetalle(Detalle d) throws Exception;
	
	public void editarDetalle(Detalle d) throws Exception;
	
	public Detalle traerDetId(int d) throws Exception;
	
	public Integer maxidRut() throws Exception;
	
	public List<Ruta> traerListRut() throws Exception;
	
	public void crearRuta(Ruta r) throws Exception;
	
	public void editarRuta(Ruta r) throws Exception;
	
	public Ruta traerRutId(int d) throws Exception;

	public Integer maxidDep() throws Exception;
	
	public List<Dependencia> traerListDep() throws Exception;
	
	public void crearDep(Dependencia d) throws Exception;
	
	public void editarDep(Dependencia d) throws Exception;
	
	public Dependencia traerDepId(int d) throws Exception;
	
	public Integer maxidTiDoc() throws Exception;
	
	public List<Tipodocumento> traerListTidoc() throws Exception;
	
	public void crearTidoc(Tipodocumento tp) throws Exception;
	
	public void editarTidoc(Tipodocumento tp) throws Exception;
	
	public Tipodocumento traerTicoId(int d) throws Exception;

    public Integer maxidTg() throws Exception;
	
	public List<Tipogasto> traerListTg() throws Exception;
	
	public void crearTg(Tipogasto tg) throws Exception;
	
	public void editarTg(Tipogasto tg) throws Exception;
	
	public Tipogasto traerTgId(int d) throws Exception;
	
	public Integer maxidTt() throws Exception;
	
	public List<Tipotarea> traerListTt() throws Exception;
	
	public void crearTt(Tipotarea tt) throws Exception;
	
	public void editarTt(Tipotarea tt) throws Exception;
	
	public Tipotarea traerTtId(int d) throws Exception;
	
	public Integer maxidAnio() throws Exception;
	
	public List<Anio> traerListAnio() throws Exception;
	
	public void crearAnio(Anio a) throws Exception;
	
	public void editarAnio(Anio a) throws Exception;
	
	public Anio traerAnioId(int d) throws Exception;
	
	public Integer maxidPreg() throws Exception;
	
	public List<Pregunta> traerListPreg() throws Exception;
	
	public void crearPregunta(Pregunta p) throws Exception;
	
	public void editarPregunta(Pregunta p) throws Exception;
	
	public Pregunta traerPregId(int d) throws Exception;
	
    public Integer maxidParam() throws Exception;
	
	public List<Parametro> traerListParam() throws Exception;
	
	public void crearParametro(Parametro p) throws Exception;
	
	public void editarParametro(Parametro p) throws Exception;
	
	public Parametro traerParamId(int d) throws Exception;
	
    public Integer maxidPerfil() throws Exception;
	
	public List<Perfil> traerListPerf() throws Exception;
	
	public void crearPerfil(Perfil p) throws Exception;
	
	public void editarPerfil(Perfil p) throws Exception;
	
	public Perfil traerPerfId(int d) throws Exception;
	
    public Integer maxidEstadocivil() throws Exception;
	
	public List<Estadocivil> traerListEestadoCivil() throws Exception;
	
	public void crearEstadoCivil(Estadocivil ec) throws Exception;
	
	public void editarEstadoCivil(Estadocivil ec) throws Exception;
	
	public Estadocivil traerEstaCId(int d) throws Exception;
	
	public Integer maxidFPT() throws Exception;
		
	public List<Formapagotax> traerListFPT() throws Exception;
		
	public void crearFPT(Formapagotax fp) throws Exception;
		
	public void editarFPT(Formapagotax fp) throws Exception;
		
	public Formapagotax traerFPTCId(int d) throws Exception;
	
	public List<Extensione> traerListExte() throws Exception;
	
	public void crearExt(Extensione ex) throws Exception;
		
	public void editarExt(Extensione ex) throws Exception;
	
	public Integer maxidFormaPagoFactura() throws Exception;
	
	public List<Formapagofactura> traerListFormaPagoFactura() throws Exception;
	
	public void crearFormaPagoFactura(Formapagofactura fpf) throws Exception;
	
	public void editarFormaPagoFacutura(Formapagofactura fpf) throws Exception;
	
	public Formapagofactura traerFpfCId(int d) throws Exception;
	
	public Integer maxidTipoParentesco() throws Exception;
	
	public List<Tipoparentesco> traerListTipoParentesco() throws Exception;
	
	public void crearTipoParentesco(Tipoparentesco tp) throws Exception;
	
	public void editarTipoParentesco(Tipoparentesco tp) throws Exception;
	
	public Tipoparentesco traerTPId(int d) throws Exception;
	
	public Integer maxidTipoIdentificacion() throws Exception;
	
	public List<Tipoidentificacion> traerTipoIdentificacion() throws Exception;
	
	public void crearTipoIdentificacion(Tipoidentificacion ti) throws Exception;
	
	public void editarTipoIdentificacion(Tipoidentificacion ti) throws Exception;
	
	public Tipoidentificacion traerTIId(int d) throws Exception;

	public Integer maxidFormaEnvio() throws Exception;
	
	public List<Formaenvio> traerFormaEnvio() throws Exception;
	
	public void crearFormaEnvio(Formaenvio fe) throws Exception;
	
	public void editarFormaEnvio(Formaenvio fe) throws Exception;
	
	public Formaenvio traerFeId(int d) throws Exception;
	
	public Integer maxidEstadoFactura() throws Exception;
	
	public List<Estadofactura> traerEstadoFactura() throws Exception;
	
	public void crearEstadoFactura(Estadofactura ef) throws Exception;
	
	public void editarEstadoFactura(Estadofactura ef) throws Exception;
	
	public Estadofactura traerEFId(int d) throws Exception;
	
	public List<VFuncionario> traerFuncionarios() throws Exception;
	
	public List<Sexo> listarSexo() throws Exception;
	
	public List<CiudadDTO> ciudadEstado(int idestado) throws Exception;
	
	public void delete(Object o) throws Exception;
	
	public void crearCiudadZip (Ciudadzip cz) throws Exception;
	
	public List<CiudZipDTO> listarCiudadZip() throws Exception;
	
	public void editarCiudadZip (Ciudadzip cz) throws Exception;
	
	public Ciudadzip traerCd(int d, int c) throws Exception;
	
	public List<CasoReportDTO> listNovedad(String nove) throws Exception;
	
	public void crearEmpresa(Persona p) throws Exception;
	
	public List<EmpresaDTO> traerEmpresa() throws Exception;
	
	public List<Estado> traerEstadoPais(int idPais) throws Exception;
	
	public List<Zip> traerCiudadZip(int idciudad) throws Exception;
	
	public void crearFuncionario(Funcionario f) throws Exception;
	
	public EmpresaDTO traerEmpresaUsuario(String user, String empre) throws Exception;
	
	public void editarEmpresa(Persona p) throws Exception;
	
	public Funcionario traerFuncionario (String user) throws Exception;

}

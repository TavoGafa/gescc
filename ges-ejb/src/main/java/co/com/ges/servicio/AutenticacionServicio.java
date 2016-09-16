package co.com.ges.servicio;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.fachada.AutenticacionFachada;
import co.com.ges.modelo.Funcionario;


@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AutenticacionServicio{

	@EJB
	AutenticacionFachada autenticacionBean;

	public AutenticacionServicio() {
		super();
	}
	
	 public List<OpcionesMenuUsuario> consultaPermisosOpcionesUser() throws Exception{
 		autenticacionBean.consultaPermisosOpcionesUser();
 	return null;
 }
 
	 
 public Funcionario usuarioConsultado(String user) throws Exception{
	 Funcionario logUser = new Funcionario();
 	logUser = autenticacionBean.consultarUsuario(user);
 	if(logUser==null || logUser.equals("")){
 		logUser = null;
 	}
 	
 	return logUser;
 }
}

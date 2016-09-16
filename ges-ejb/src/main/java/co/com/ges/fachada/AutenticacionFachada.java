package co.com.ges.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.modelo.Funcionario;


@Remote
public interface AutenticacionFachada{
	
	public List<OpcionesMenuUsuario>consultaPermisosOpcionesUser()throws Exception;
	
	public Funcionario consultarUsuario(String usuario) throws Exception;

}

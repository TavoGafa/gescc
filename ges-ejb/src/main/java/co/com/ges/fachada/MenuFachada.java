package co.com.ges.fachada;

import java.util.List;

import javax.ejb.Remote;

import co.com.ges.dto.OpcionesMenuUsuario;


@Remote
public interface MenuFachada {
	
	public List<OpcionesMenuUsuario> opcionesMenu(String user) throws Exception;

}

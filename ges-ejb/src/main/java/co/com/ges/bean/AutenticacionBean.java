package co.com.ges.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.fachada.AutenticacionFachada;
import co.com.ges.modelo.Funcionario;


@Stateless
@LocalBean
public class AutenticacionBean implements AutenticacionFachada{

	@PersistenceContext(unitName="jpa-ges")
	private EntityManager em;
	
	public AutenticacionBean() {
		super();
	}

	@Override
	public List<OpcionesMenuUsuario> consultaPermisosOpcionesUser()
			throws Exception {
		return null;
	}

	@Override
	public Funcionario consultarUsuario(String usuario) throws Exception {
		List<Funcionario> listLog = new ArrayList<Funcionario>();
		Funcionario user = new Funcionario();
		Query q = em.createNamedQuery(Funcionario.USUARIO_PERMITIDO);
		q.setParameter("user", usuario);
		listLog = q.getResultList();
		
		if(listLog.size()>0){
			for(Funcionario o : listLog){
				user = new Funcionario();
				user.setClave(o.getClave());
				user.setEstadodel(o.getEstadodel());
				user.setId(o.getId());
				user.setIddependencia(o.getIddependencia());
				user.setIdperfil(o.getIdperfil());
				user.setIdpregunta(o.getIdpregunta());
				user.setRespuesta(o.getRespuesta());
				user.setUsuario(o.getUsuario());
			}
		}
		
		em.clear();
		return user;
	}

}

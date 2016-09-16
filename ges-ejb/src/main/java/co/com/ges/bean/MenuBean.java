package co.com.ges.bean;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.ges.dto.OpcionesMenuUsuario;
import co.com.ges.fachada.MenuFachada;
import co.com.ges.modelo.Perfilopcion;


/**
 * Session Bean implementation class MenuBean
 */
@Stateless
@LocalBean
public class MenuBean implements MenuFachada {

	@PersistenceContext(unitName="jpa-ges")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public MenuBean() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OpcionesMenuUsuario> opcionesMenu(String user) throws Exception {
		List<OpcionesMenuUsuario> usuarios = new ArrayList<OpcionesMenuUsuario>();
		List<Object[]> resultado = new ArrayList<Object[]>();
		Query query = em.createNamedQuery(Perfilopcion.MENU);
		query.setParameter("usuario", user);
		resultado = query.getResultList();
		if (resultado != null && !resultado.isEmpty()) {
			for (Object[] opc : resultado) {
				OpcionesMenuUsuario opcMenu = new OpcionesMenuUsuario();
				opcMenu.setUsuario(((String)opc[0]).trim());
				opcMenu.setIdMenu(Long.valueOf((Integer)opc[2]));
				opcMenu.setNombreMenu(((String)opc[1]).trim());
				opcMenu.setUrlOpcion(((String)opc[4]).trim());
				opcMenu.setIdOpc(Long.valueOf((Integer)opc[3]));
				usuarios.add(opcMenu);
			}

			return usuarios;

		} else {
			return null;
		}

	}

}

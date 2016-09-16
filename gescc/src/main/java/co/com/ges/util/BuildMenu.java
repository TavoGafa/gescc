package co.com.ges.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import co.com.ges.dto.OpcionesMenuUsuario;

public class BuildMenu implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private MenuModel menuModel;
	private List<MenuDto> opcMenu;

	@PostConstruct
	public void men() {
		menuModel = new DefaultMenuModel();
		opcMenu = new ArrayList<MenuDto>();
		List<OpcionesMenuUsuario> opciones = (List) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menu");
		try {
			armarListaOpcMenu(opciones);
			armaMenu(this.opcMenu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void armarListaOpcMenu(List<OpcionesMenuUsuario> opc) throws Exception {
		for (OpcionesMenuUsuario ob : opc) {
			if (ob.getIdPadre() == null) {
				MenuDto m = new MenuDto();
				m.setId(ob.getIdMenu());
				m.setNombre(ob.getNombreMenu());
				m.setMenusSegNivel(new ArrayList<MenuDto>());
				this.opcMenu.add(m);
			} else {
				MenuDto padre = new MenuDto();
				padre.setId(Long.valueOf(ob.getIdPadre().toString()));
				int indexPadre = this.opcMenu.indexOf(padre);
				if (indexPadre >= 0) {
					MenuDto hijo = new MenuDto();
					hijo.setId(ob.getIdMenu());
					hijo.setNombre(ob.getNombreMenu());
					hijo.setMenuItems(new ArrayList<MenuItemDto>());
					int indexHijo = ((MenuDto) this.opcMenu.get(indexPadre)).getMenusSegNivel().indexOf(hijo);
					if (indexHijo >= 0) {
						if ((ob.getUrlOpcion() != null) && (!ob.getUrlOpcion().equals(""))) {
							MenuItemDto nieto = new MenuItemDto(ob.getNombreOpc(), ob.getUrlOpcion(), ob.getIdOpc());
							((MenuDto) ((MenuDto) this.opcMenu.get(indexPadre)).getMenusSegNivel().get(indexHijo))
									.getMenuItems().add(nieto);
						}
					} else {
						((MenuDto) this.opcMenu.get(indexPadre)).getMenusSegNivel().add(hijo);
						int indexHijo2 = ((MenuDto) this.opcMenu.get(indexPadre)).getMenusSegNivel().indexOf(hijo);
						if ((ob.getUrlOpcion() != null) && (!ob.getUrlOpcion().equals(""))) {
							MenuItemDto nieto = new MenuItemDto(ob.getNombreOpc(), ob.getUrlOpcion(), ob.getIdOpc());
							((MenuDto) ((MenuDto) this.opcMenu.get(indexPadre)).getMenusSegNivel().get(indexHijo2))
									.getMenuItems().add(nieto);
						}
					}
				}
			}
		}
	}

	public void armaMenu(List<MenuDto> opcionesMenu) throws Exception {
		for (MenuDto menu : opcionesMenu) {
			DefaultSubMenu submenub = new DefaultSubMenu();
			submenub.setLabel(menu.getNombre());
			for (MenuDto menuseg : menu.getMenusSegNivel()) {
				DefaultSubMenu submenSeg = new DefaultSubMenu();
				submenSeg.setLabel(menuseg.getNombre());
				for (MenuItemDto hijos : menuseg.getMenuItems()) {
					DefaultMenuItem mhij = new DefaultMenuItem();
					mhij.setValue(hijos.getNombre());

					mhij.setCommand("#{prin.opcionMenu}");
					mhij.setParam("url", hijos.getUrl());
					submenSeg.getElements().add(mhij);
				}
				submenub.getElements().add(submenSeg);
			}
			this.menuModel.addElement(submenub);
		}
	}

	public MenuModel getMenuModel() {
		return this.menuModel;
	}

}

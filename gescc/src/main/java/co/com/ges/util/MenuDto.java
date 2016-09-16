package co.com.ges.util;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {
	
	private Long id;
	private String nombre;
	private String urlIcono;
	private List<MenuItemDto> menuItems;
	private List<MenuDto> menusSegNivel;
	private String action;

	public MenuDto() {
		this.menuItems = new ArrayList<MenuItemDto>();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlIcono() {
		return this.urlIcono;
	}

	public void setUrlIcono(String urlIcono) {
		this.urlIcono = urlIcono;
	}

	public List<MenuItemDto> getMenuItems() {
		return this.menuItems;
	}

	public void setMenuItems(List<MenuItemDto> menuItems) {
		this.menuItems = menuItems;
	}

	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = 31 * result + (this.id == null ? 0 : this.id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		MenuDto other = (MenuDto) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public List<MenuDto> getMenusSegNivel() {
		return this.menusSegNivel;
	}

	public void setMenusSegNivel(List<MenuDto> menusSegNivel) {
		this.menusSegNivel = menusSegNivel;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

package co.com.ges.dto;

import java.io.Serializable;

public class PermisosDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String permiso;
	private int codigo;
	
	public PermisosDto(String permiso, int codigo) {
		this.permiso = permiso;
		this.codigo = codigo;
	}
	public String getPermiso() {
		return permiso;
	}
	public void setPermiso(String permiso) {
		this.permiso = permiso;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}

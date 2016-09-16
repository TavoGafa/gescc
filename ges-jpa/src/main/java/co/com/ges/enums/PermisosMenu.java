package co.com.ges.enums;

public enum PermisosMenu {

	ADMCONTRATRO(1L,"ADMCONTRATRO"),
	ADMORDENSERVIC(2L,"ADMORDENSERVIC"),
	ADMCONVENIO(3L,"ADMCONVENIO"),
	SUMINISTROS(4L,"SUMINISTROS"),
	SERVICIOS(5L,"SERVICIOS"),
	OBRA(6L,"OBRA"),
	CONSULTORIA(7L,"CONSULTORIA"),
	INTERVENTORIA(8L,"INTERVENTORIA"),
	EDITARCONTRATO(9L,"EDITARCONTRATO");
	
	private final Long idPermiso;
	private final String nombre;
	
	private PermisosMenu(Long idPermiso, String nombre) {
		this.idPermiso = idPermiso;
		this.nombre = nombre;
	}

	public Long getIdPermiso() {
		return idPermiso;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return idPermiso.toString();
	}
	
	public static PermisosMenu get(String nombre) {
		for (PermisosMenu estado : PermisosMenu.values()) {
			if (estado.getNombre().equals(nombre)) {
				return estado;
			}
		}
		return null;
	}
}

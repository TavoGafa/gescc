package co.com.ges.enums;

public enum ComponentesMenu {
	CONTRATO(2L,"CONTRATO"),
	ORDENSERVICIO(3L,"ORDENSERVICIO"),
	CONVENIO(4L,"CONVENIO"),
	POLIZAS(5L,"POLIZAS"),
	REPORTES(7L,"REPORTES"),
	OTROSI(8L,"OTROSI"),
	EDITACONT(9L,"EDITACONTRATO"),
	REPORTOTROSI(10L,"REPORTE OTRO SI");
	
	private final Long idComponente;
	private final String nombre;
	
	private ComponentesMenu(Long idComponente, String nombre) {
		this.idComponente = idComponente;
		this.nombre = nombre;
	}

	public Long getIdComponente() {
		return idComponente;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return idComponente.toString();
	}
	
	public static ComponentesMenu get(String nombre) {
		for (ComponentesMenu estado : ComponentesMenu.values()) {
			if (estado.getNombre().equals(nombre)) {
				return estado;
			}
		}
		return null;
	}
	
}

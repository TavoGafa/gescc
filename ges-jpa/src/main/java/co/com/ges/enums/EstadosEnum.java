package co.com.ges.enums;

public enum EstadosEnum {

	ACTIVO(1L,"ACTIVO"),
	INACTIVO(0L,"INACTIVO");
	
	private final Long idComponente;
	private final String nombre;
	
	private EstadosEnum(Long idComponente, String nombre) {
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

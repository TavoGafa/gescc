package co.com.ges.enums;

public enum AsignacionEnum {

	SI(1L,"SI"),
	NO(0L,"NO");
	
	private final Long id;
	private final String nombre;
	
	private AsignacionEnum(Long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return id.toString();
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

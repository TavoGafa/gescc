package co.com.ges.util;

public class MenuItemDto {

	private String nombre;
	private String url;
	private String urlIcono;
	private Long id;
	private String action;

	public MenuItemDto() {
	}

	public MenuItemDto(String nombre, String url, Long id) {
		this.nombre = nombre;
		this.url = url;
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlIcono() {
		return this.urlIcono;
	}

	public void setUrlIcono(String urlIcono) {
		this.urlIcono = urlIcono;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

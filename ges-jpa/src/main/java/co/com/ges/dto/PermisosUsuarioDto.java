package co.com.ges.dto;

import java.io.Serializable;

public class PermisosUsuarioDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private int pelocomSerial;
	private String reporte;
	private String permiso;
	private String url;
	private int pelocomCompId;
	
	public PermisosUsuarioDto(int pelocomSerial, String reporte,
			String permiso, String url, int pelocomCompId) {
		this.pelocomSerial = pelocomSerial;
		this.reporte = reporte;
		this.permiso = permiso;
		this.url = url;
		this.pelocomCompId=pelocomCompId;
	}

	public int getPelocomSerial() {
		return pelocomSerial;
	}

	public String getReporte() {
		return reporte;
	}

	public String getPermiso() {
		return permiso;
	}

	public String getUrl() {
		return url;
	}

	public int getPelocomCompId() {
		return pelocomCompId;
	}

	public void setPelocomCompId(int pelocomCompId) {
		this.pelocomCompId = pelocomCompId;
	}
	
 
}

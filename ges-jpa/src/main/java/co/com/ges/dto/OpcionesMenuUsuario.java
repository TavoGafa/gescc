package co.com.ges.dto;

import java.io.Serializable;

public class OpcionesMenuUsuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
		private String usuario;
		private Long idMenu;
		private Integer idPadre;
		private Long idOpc;
		private String nombreMenu;
		private String nombreOpc;
		private String urlOpcion;
		private Integer permisoCodigo;
		
		
		
		public OpcionesMenuUsuario() {
			super();
		}


		public OpcionesMenuUsuario(String usuario, Long idMenu,
				Integer idPadre, Long idOpc, String nombreMenu,
				String nombreOpc, String urlOpcion, Integer permisoCodigo) {
			super();
			this.usuario = usuario;
			this.idMenu = idMenu;
			this.idPadre = idPadre;
			this.idOpc = idOpc;
			this.nombreMenu = nombreMenu;
			this.nombreOpc = nombreOpc;
			this.urlOpcion = urlOpcion;
			this.permisoCodigo = permisoCodigo;
		}


		public String getUsuario() {
			return usuario;
		}


		public void setUsuario(String usuario) {
			this.usuario = usuario;
		}


		public Long getIdMenu() {
			return idMenu;
		}


		public void setIdMenu(Long idMenu) {
			this.idMenu = idMenu;
		}


		public Integer getIdPadre() {
			return idPadre;
		}


		public void setIdPadre(Integer idPadre) {
			this.idPadre = idPadre;
		}


		public Long getIdOpc() {
			return idOpc;
		}


		public void setIdOpc(Long idOpc) {
			this.idOpc = idOpc;
		}


		public String getNombreMenu() {
			return nombreMenu;
		}


		public void setNombreMenu(String nombreMenu) {
			this.nombreMenu = nombreMenu;
		}


		public String getNombreOpc() {
			return nombreOpc;
		}


		public void setNombreOpc(String nombreOpc) {
			this.nombreOpc = nombreOpc;
		}


		public String getUrlOpcion() {
			return urlOpcion;
		}


		public void setUrlOpcion(String urlOpcion) {
			this.urlOpcion = urlOpcion;
		}


		public Integer getPermisoCodigo() {
			return permisoCodigo;
		}


		public void setPermisoCodigo(Integer permisoCodigo) {
			this.permisoCodigo = permisoCodigo;
		}
		
		
}

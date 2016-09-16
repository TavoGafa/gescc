package co.com.ges.vistas;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the VFuncionario database table.
 * 
 */
@Entity
public class VFuncionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String TRAER_OFFICER = "VFuncionario.TRAER_OFFICER";

	@Column(name="Dependencia")
	private String dependencia;

	@Column(name="Funcionario")
	private String funcionario;

	@Id
	private int id;

	private String identificacion;

	@Column(name="Perfil")
	private String perfil;

	@Column(name="TipoIdentificacion")
	private String tipoIdentificacion;

	private String usuario;

	public VFuncionario() {
	}
	
	

	public VFuncionario(String dependencia, String funcionario, int id, String identificacion, String perfil,
			String tipoIdentificacion, String usuario) {
		super();
		this.dependencia = dependencia;
		this.funcionario = funcionario;
		this.id = id;
		this.identificacion = identificacion;
		this.perfil = perfil;
		this.tipoIdentificacion = tipoIdentificacion;
		this.usuario = usuario;
	}



	public String getDependencia() {
		return this.dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}

	public String getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificacion() {
		return this.identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getTipoIdentificacion() {
		return this.tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
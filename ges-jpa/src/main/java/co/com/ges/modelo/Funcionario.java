package co.com.ges.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FUNCIONARIO database table.
 * 
 */
@Entity
@Table(name="FUNCIONARIO")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String USUARIO_PERMITIDO = "Funcionario.USUARIO_PERMITIDO";

	@EmbeddedId
	private FuncionarioPK id;

	private String clave;

	private short estadodel;

	private int iddependencia;

	private int idperfil;

	private int idpregunta;

	private String respuesta;

	private String usuario;

	public Funcionario() {
	}

	public FuncionarioPK getId() {
		return this.id;
	}

	public void setId(FuncionarioPK id) {
		this.id = id;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public short getEstadodel() {
		return this.estadodel;
	}

	public void setEstadodel(short estadodel) {
		this.estadodel = estadodel;
	}

	public int getIddependencia() {
		return this.iddependencia;
	}

	public void setIddependencia(int iddependencia) {
		this.iddependencia = iddependencia;
	}

	public int getIdperfil() {
		return this.idperfil;
	}

	public void setIdperfil(int idperfil) {
		this.idperfil = idperfil;
	}

	public int getIdpregunta() {
		return this.idpregunta;
	}

	public void setIdpregunta(int idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
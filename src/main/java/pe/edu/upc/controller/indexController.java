package pe.edu.upc.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IUsuarioService;

import java.io.Serializable;

@Named
@ViewScoped
public class indexController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUsuarioService uService;
	private Usuario usuario;
	private String mensajeError;
	private String claseError;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	public String getClaseError() {
		return claseError;
	}

	public void setClaseError(String claseError) {
		this.claseError = claseError;
	}

	public String iniciarSesion() {
		Usuario us;
		String redireccion = null;
		try {
			//le asignamos el valor que retorne nuestra consulta que realizamos en nuestro daoimpl
			us = uService.iniciarSesion(usuario);
			
				if(us != null) {
					//almacenar en las sesion de JSF
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
					//lo redireccionamos dependiendo el tipo de usuario que sea
					if(us.getTipo().equals("C")) {
						redireccion = "home?faces-redirect=true";
					}else {
						redireccion = "homeAdmin?faces-redirect=true";
					}
					
					this.mensajeError = "";
					this.claseError = "";
				}else {
						this.mensajeError = "Error en la contraseña o usuario";
						this.claseError = "login-mensaje-error";
						
			}
		}catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
		}
		return redireccion;
	}
	
	
	
}

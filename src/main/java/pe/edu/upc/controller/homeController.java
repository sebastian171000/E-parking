package pe.edu.upc.controller;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

import pe.edu.upc.entity.Usuario;


import java.io.Serializable;

@Named
@ViewScoped
public class homeController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreUsuario;
		
	public String imprimirNombre() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		this.nombreUsuario = us.getPersona().getNombres();
		return this.nombreUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
	
}

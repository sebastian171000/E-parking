package pe.edu.upc.controller;


import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import pe.edu.upc.entity.Usuario;

import java.io.Serializable;

@Named
@ViewScoped
public class plantillaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String url;
	private String uri;
	public String  someMethod() {
	    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	   // url = request.getRequestURL().toString();
	    uri = request.getRequestURI();
	    String pageName = uri.substring(uri.lastIndexOf("/")+1);
	    
	    //String sSubCadena = uri.substring(10,20);
	    return pageName.replaceAll(".xhtml", "");
	}
	
	public void verificarSesion() {
		try {
			Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			if(us == null) {
				FacesContext.getCurrentInstance().getExternalContext().redirect("permisos.xhtml");
			}
		}catch(Exception e) {
			//loop para guardar algun registro de un error
			
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	

	
	
	
	
}

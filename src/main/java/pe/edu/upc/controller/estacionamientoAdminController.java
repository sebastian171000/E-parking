package pe.edu.upc.controller;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Estacionamiento;
import pe.edu.upc.service.IEstacionamientoService;

import java.io.Serializable;

@Named
@ViewScoped
public class estacionamientoAdminController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEstacionamientoService uService;
	private Estacionamiento estacionamiento;
	private String mensajeError;
	private String claseError;
	
	@PostConstruct
	public void init() {
		estacionamiento = new Estacionamiento();
		esDeAdmin();
	}
	public void esDeAdmin() {
		this.estacionamiento = uService.esDeAdmin();
	}

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
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

	
	
	
}

package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Estacionamiento;
import pe.edu.upc.service.IEstacionamientoService;

@Named
@RequestScoped
public class EstacionamientoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEstacionamientoService eService;
	private Estacionamiento estacionamiento;
	List<Estacionamiento> listaEstacionamientos;
	
	@PostConstruct
	public void init() {
		this.listaEstacionamientos = new ArrayList<Estacionamiento>();
		this.estacionamiento = new Estacionamiento();
		this.listar();
	}
	
	
	
	public String nuevoEstacionamiento() {
		this.setEstacionamiento(new Estacionamiento());
		return "estacionamiento.xhtml";
	}
	
	public void insertar() {
		try {
			eService.insertar(estacionamiento);
			limpiarEstacionamiento();
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaEstacionamientos = eService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void limpiarEstacionamiento() {
		this.init();
	}
	
	public void eliminar(Estacionamiento mo) {
		try {
			eService.eliminar(mo.getCodigo());
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}
	

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
	}

	public List<Estacionamiento> getListaEstacionamientos() {
		return listaEstacionamientos;
	}

	public void setListaEstacionamientos(List<Estacionamiento> listaEstacionamientos) {
		this.listaEstacionamientos = listaEstacionamientos;
	}
	
	
	

}

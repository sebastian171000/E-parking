package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Usuario;
import pe.edu.upc.entity.Vehiculo;
import pe.edu.upc.service.IVehiculoService;

@Named
@RequestScoped
public class VehiculoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IVehiculoService mService;
	private Vehiculo vehiculo;
	List<Vehiculo> listaVehiculos;
	
	@PostConstruct
	public void init() {
		this.listaVehiculos = new ArrayList<Vehiculo>();
		this.vehiculo = new Vehiculo();
		this.listar();
	}
	
	public String nuevoVehiculo() {
		this.setVehiculo(new Vehiculo());
		return "nuevoVehiculo.xhtml";
	}
	
	public void insertar() {
		try {
			Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			this.vehiculo.setPersona(us.getPersona());
			mService.insertar(vehiculo);
			limpiarVehiculo();
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaVehiculos = mService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void limpiarVehiculo() {
		this.init();
	}
	
	public void eliminar(Vehiculo mo) {
		try {
			mService.eliminar(mo.getCodigo());
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}
	
	public String goUpdate(Vehiculo veh) {
		this.setVehiculo(veh);
		return "updateVehiculo.xhtml";
	}
	public void update() {
		try {
			Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			this.vehiculo.setPersona(us.getPersona());
			mService.edit(vehiculo);
			limpiarVehiculo();
			this.listar();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Vehiculo> getListaVehiculos() {
		return listaVehiculos;
	}

	public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
		this.listaVehiculos = listaVehiculos;
	}
	
	
	

}

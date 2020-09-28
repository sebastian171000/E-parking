package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Estacionamiento;
import pe.edu.upc.entity.Reserva;
import pe.edu.upc.entity.Vehiculo;
import pe.edu.upc.service.IEstacionamientoService;
import pe.edu.upc.service.IReservaService;
import pe.edu.upc.service.IVehiculoService;

@Named
@RequestScoped
public class ReservaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEstacionamientoService eService;
	@Inject
	private IVehiculoService vService;
	@Inject
	private IReservaService rService;
	private Reserva reserva;
	private Estacionamiento estacionamiento;
	private Vehiculo vehiculo;
	private List<Vehiculo> listaVehiculos;
	private List<Estacionamiento> listaEstacionamientos;
	List<Reserva> listaReservas;
	List<Reserva> listaReservasAdmin;
	List<Reserva> listarhistorial;
	List<Reserva> listarhistorialAdmin;
	
	@PostConstruct
	public void init() {
		this.listaReservas = new ArrayList<Reserva>();
		this.listaReservasAdmin = new ArrayList<Reserva>();
		this.listarhistorial = new ArrayList<Reserva>();
		this.listarhistorialAdmin = new ArrayList<Reserva>();
		this.reserva = new Reserva();
		this.vehiculo = new Vehiculo();
		this.estacionamiento = new Estacionamiento();
		
		listaVehiculos = vService.listar();
		listaEstacionamientos = eService.listar();
		
		this.listar();
		this.listarReservasPersona();
		this.listarReservasHistorial();
		this.listarReservasHistorialAdmin();
	}
	public void aprobarReserva(Reserva res) {
		try {
			res.setEstado("Aprobado");
			rService.edit(res);
			limpiarReserva();
			this.listar();
			this.listarReservasPersona();
			this.listarReservasHistorial();
			this.listarReservasHistorialAdmin();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	public void rechazarReserva(Reserva res) {
		try {
			res.setEstado("Rechazado");
			rService.edit(res);
			limpiarReserva();
			this.listar();
			this.listarReservasPersona();
			this.listarReservasHistorial();
			this.listarReservasHistorialAdmin();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
	}
	public void finalizarReserva(Reserva res) {
		res.setEstado("Finalizado");
		rService.edit(res);
		limpiarReserva();
		this.listar();
		this.listarReservasPersona();
		this.listarReservasHistorial();
		this.listarReservasHistorialAdmin();
	}
	
	public String goUpdate(Reserva res) {
		this.setReserva(res);
		return "updateReserva.xhtml";
	}
	
	public void update() {
		try {
			this.reserva.setVehiculo(vehiculo);
			this.reserva.setEstacionamiento(estacionamiento);
			reserva.setEstado("Pendiente");
			rService.edit(reserva);
			
			limpiarReserva();
			this.listar();
			this.listarReservasPersona();
			this.listarReservasHistorial();
			this.listarReservasHistorialAdmin();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String nuevoReserva() {
		this.setReserva(new Reserva());
		return "nuevaReserva.xhtml";
	}
	public void asignar(Estacionamiento es) {
		this.estacionamiento = es;
	}
	public void insertar() {
		try {
			this.reserva.setVehiculo(vehiculo);
			this.reserva.setEstacionamiento(estacionamiento);
			rService.insertar(reserva);
			limpiarReserva();
			this.listar();
			this.listarReservasPersona();
			this.listarReservasHistorial();
			this.listarReservasHistorialAdmin();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaReservasAdmin = rService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void listarReservasPersona() {
		try {
			listaReservas = rService.listarReservasPersona();
			
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void listarReservasHistorial() {
		try {
			listarhistorial = rService.listarReservasHistorial();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void listarReservasHistorialAdmin() {
		try {
			listarhistorialAdmin = rService.listarReservasHistorialAdmin();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void limpiarReserva() {
		this.init();
	}
	
	public void eliminar(Reserva mo) {
		try {
			rService.eliminar(mo.getCodigo());
			this.listarReservasPersona();
			this.listar();
			this.listarReservasHistorial();
			this.listarReservasHistorialAdmin();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}
	

	public List<Reserva> getListaReservasAdmin() {
		return listaReservasAdmin;
	}

	public void setListaReservasAdmin(List<Reserva> listaReservasAdmin) {
		this.listaReservasAdmin = listaReservasAdmin;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getListaReservas() {
		return listaReservas;
	}

	public void setListaReservas(List<Reserva> listaReservas) {
		this.listaReservas = listaReservas;
	}

	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}

	public void setEstacionamiento(Estacionamiento estacionamiento) {
		this.estacionamiento = estacionamiento;
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

	public List<Estacionamiento> getListaEstacionamientos() {
		return listaEstacionamientos;
	}

	public void setListaEstacionamientos(List<Estacionamiento> listaEstacionamientos) {
		this.listaEstacionamientos = listaEstacionamientos;
	}
	public List<Reserva> getListarhistorial() {
		return listarhistorial;
	}
	public void setListarhistorial(List<Reserva> listarhistorial) {
		this.listarhistorial = listarhistorial;
	}
	public List<Reserva> getListarhistorialAdmin() {
		return listarhistorialAdmin;
	}
	public void setListarhistorialAdmin(List<Reserva> listarhistorialAdmin) {
		this.listarhistorialAdmin = listarhistorialAdmin;
	}
	
	
	
	

}

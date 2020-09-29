package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IReservaDao;
import pe.edu.upc.entity.Reserva;
import pe.edu.upc.service.IReservaService;

@Named
@RequestScoped

public class ReservaServiceImpl implements IReservaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IReservaDao uD;

	@Override
	public void insertar(Reserva reserva) {
		uD.insertar(reserva);		
	}
	@Override
	public void edit(Reserva reserva) {
		uD.edit(reserva);
	}

	@Override
	public List<Reserva> listar() {
		return uD.listar();
	}
	
	@Override
	public List<Reserva> listarReservasPersona(){
		return uD.listarReservasPersona();
	}
	
	@Override
	public List<Reserva> listarReservasHistorial(){
		return uD.listarReservasHistorial();
	}
	
	@Override
	public List<Reserva> listarReservasHistorialAdmin(){
		return uD.listarReservasHistorialAdmin();
	}

	@Override
	public void eliminar(int idReserva) {
		uD.eliminar(idReserva);		
	}

}

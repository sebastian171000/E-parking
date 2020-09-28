package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IVehiculoDao;
import pe.edu.upc.entity.Vehiculo;
import pe.edu.upc.service.IVehiculoService;

@Named
@RequestScoped

public class VehiculoServiceImpl implements IVehiculoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IVehiculoDao uD;

	@Override
	public void insertar(Vehiculo vehiculo) {
		uD.insertar(vehiculo);		
	}

	@Override
	public List<Vehiculo> listar() {
		return uD.listar();
	}

	@Override
	public void eliminar(int idVehiculo) {
		uD.eliminar(idVehiculo);		
	}
	@Override
	public void edit(Vehiculo vehiculo) {
		uD.edit(vehiculo);
	}

}

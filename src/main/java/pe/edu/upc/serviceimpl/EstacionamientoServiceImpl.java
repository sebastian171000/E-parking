package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IEstacionamientoDao;
import pe.edu.upc.entity.Estacionamiento;
import pe.edu.upc.service.IEstacionamientoService;

@Named
@RequestScoped

public class EstacionamientoServiceImpl implements IEstacionamientoService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IEstacionamientoDao uD;

	@Override
	public void insertar(Estacionamiento estacionamiento) {
		uD.insertar(estacionamiento);		
	}

	@Override
	public List<Estacionamiento> listar() {
		return uD.listar();
	}

	@Override
	public void eliminar(int idEstacionamiento) {
		uD.eliminar(idEstacionamiento);		
	}
	@Override
	public Estacionamiento esDeAdmin() {
		return uD.esDeAdmin();
	}
	
	

}

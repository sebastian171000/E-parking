package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IPersonaDao;
import pe.edu.upc.entity.Persona;
import pe.edu.upc.service.IPersonaService;

@Named
@RequestScoped

public class PersonaServiceImpl implements IPersonaService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPersonaDao cD;

	@Override
	public void insertar(Persona persona) {
		cD.insertar(persona);		
	}

	@Override
	public List<Persona> listar() {
		return cD.listar();
	}

	@Override
	public void eliminar(int idPersona) {
		cD.eliminar(idPersona);		
	}

}

package pe.edu.upc.service;

import java.util.List;
import pe.edu.upc.entity.Persona;

public interface IPersonaService {
	public void insertar(Persona persona);
	public List<Persona> listar();
	public void eliminar(int idPersona);
}


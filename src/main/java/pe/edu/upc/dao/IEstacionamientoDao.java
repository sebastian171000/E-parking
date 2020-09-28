package pe.edu.upc.dao;

import java.util.List;
import pe.edu.upc.entity.Estacionamiento;

public interface IEstacionamientoDao {
	public void insertar(Estacionamiento estacionamiento);
	public List<Estacionamiento> listar();
	public void eliminar(int idEstacionamiento);
	public Estacionamiento esDeAdmin();
}

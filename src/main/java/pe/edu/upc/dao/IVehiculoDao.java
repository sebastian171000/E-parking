package pe.edu.upc.dao;

import java.util.List;
import pe.edu.upc.entity.Vehiculo;

public interface IVehiculoDao {
	public void insertar(Vehiculo vehiculo);
	public List<Vehiculo> listar();
	public void eliminar(int idVehiculo);
	public void edit(Vehiculo vehiculo);
}

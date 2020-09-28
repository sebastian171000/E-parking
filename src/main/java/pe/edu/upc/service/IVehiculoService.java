package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Vehiculo;

public interface IVehiculoService {
	public void insertar(Vehiculo vehiculo);
	public List<Vehiculo> listar();
	public void eliminar(int idVehiculo);
	public void edit(Vehiculo vehiculo);
}

package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Estacionamiento;

public interface IEstacionamientoService {
	public void insertar(Estacionamiento estacionamiento);
	public List<Estacionamiento> listar();
	public void eliminar(int idEstacionamiento);
	public Estacionamiento esDeAdmin();
}

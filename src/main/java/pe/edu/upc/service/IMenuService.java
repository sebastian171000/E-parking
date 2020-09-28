package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Menu;

public interface IMenuService {
	public void insertar(Menu menu);
	public List<Menu> listar();
	public void eliminar(int idMenu);
}

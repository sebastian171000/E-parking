package pe.edu.upc.dao;

import java.util.List;
import pe.edu.upc.entity.Menu;

public interface IMenuDao {
	public void insertar(Menu menu);
	public List<Menu> listar();
	public void eliminar(int idMenu);
}

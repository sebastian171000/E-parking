package pe.edu.upc.dao;

import java.util.List;
import pe.edu.upc.entity.Usuario;

public interface IUsuarioDao {
	public void insertar(Usuario usuario);
	public List<Usuario> listar();
	public void eliminar(int idUsuario);
	public Usuario iniciarSesion(Usuario us);
	public void edit(Usuario usuario);

}

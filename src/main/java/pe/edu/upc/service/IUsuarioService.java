package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Usuario;

public interface IUsuarioService {
	public void insertar(Usuario usuario);
	public List<Usuario> listar();
	public void eliminar(int idUsuario);
	public Usuario iniciarSesion(Usuario us);
	public void edit(Usuario usuario);

}

package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IUsuarioDao;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IUsuarioService;

@Named
@RequestScoped

public class UsuarioServiceImpl implements IUsuarioService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUsuarioDao uD;

	@Override
	public void insertar(Usuario usuario) {
		uD.insertar(usuario);		
	}

	@Override
	public List<Usuario> listar() {
		return uD.listar();
	}

	@Override
	public void eliminar(int idUsuario) {
		uD.eliminar(idUsuario);		
	}
	@Override
	public Usuario iniciarSesion(Usuario us) {
		return uD.iniciarSesion(us);
	}
	@Override
	public void edit(Usuario usuario) {
		uD.edit(usuario);
	}

}

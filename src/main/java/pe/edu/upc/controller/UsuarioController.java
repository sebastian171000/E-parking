package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Persona;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IUsuarioService;

@Named
@RequestScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IUsuarioService mService;
	private Usuario usuario;
	private Persona persona;
	private Usuario perilUsuario;
	List<Usuario> listaUsuarios;
	
	@PostConstruct
	public void init() {
		this.listaUsuarios = new ArrayList<Usuario>();
		this.persona = new Persona();
		this.usuario = new Usuario();
		this.listar();
		getDatosUsuario();
	}
	public void getDatosUsuario() {
		this.perilUsuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
	}
	public String nuevoUsuario() {
		this.setUsuario(new Usuario());
		return "usuario.xhtml";
	}
	public String goUpdate() {
		this.setUsuario(perilUsuario);
		return "updatePerfil.xhtml";
	}
	public void update() {
		try {
			this.usuario.setPersona(persona);
			mService.edit(usuario);
			limpiarUsuario();
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void insertar() {
		try {
			this.usuario.setPersona(persona);
			mService.insertar(usuario);
			limpiarUsuario();
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaUsuarios = mService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void limpiarUsuario() {
		this.init();
	}
	
	public void eliminar(Usuario mo) {
		try {
			mService.eliminar(mo.getCodigo());
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Usuario getPerilUsuario() {
		return perilUsuario;
	}
	public void setPerilUsuario(Usuario perilUsuario) {
		this.perilUsuario = perilUsuario;
	}
	
	
	

}

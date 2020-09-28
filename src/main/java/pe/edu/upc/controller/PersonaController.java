package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.entity.Persona;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IPersonaService;

@Named
@RequestScoped
public class PersonaController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPersonaService cService;
	private Persona persona;
	private Usuario usuario;
	List<Persona> listaPersonas;
	
	@PostConstruct
	public void init() {
		this.listaPersonas = new ArrayList<Persona>();
		this.persona = new Persona();
		this.usuario = new Usuario();
		this.listar();
	}
	
	public String nuevoPersona() {
		this.setPersona(new Persona());
		return "persona.xhtml";
	}
	
	public void insertar() {
		try {
			this.usuario.setPersona(persona);

			cService.insertar(persona);
			limpiarPersona();
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaPersonas = cService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void limpiarPersona() {
		this.init();
	}
	
	public void eliminar(Persona mo) {
		try {
			cService.eliminar(mo.getCodigo());
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}
	

	
	
	
	

}

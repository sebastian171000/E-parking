package pe.edu.upc.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import pe.edu.upc.entity.Menu;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.service.IMenuService;

@Named
@SessionScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IMenuService mService;
	private Menu menu;
	List<Menu> listaMenues;
	private MenuModel model;
	
	@PostConstruct
	public void init() {
		this.listaMenues = new ArrayList<Menu>();
		model = new DefaultMenuModel();
		this.menu = new Menu();
		this.listar();
		this.establecesPermisos();
	}
	
	public void establecesPermisos() {
		
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		
		for(Menu m : listaMenues) {
			if(m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo())) {
				DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
				for(Menu i : listaMenues) {
					Menu submenu = i.getSubmenu();
					if(submenu != null) {
						if(submenu.getCodigo() == m.getCodigo()) {
							DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
							item.setUrl(i.getUrl());
							firstSubmenu.addElement(item);
						}
					}
				}
				 model.addElement(firstSubmenu);
			}else {
				if(m.getSubmenu() == null && m.getTipoUsuario().equals(us.getTipo())) {
					DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
					item.setUrl(m.getUrl());
					model.addElement(item);
				}
				
			}
		}
	}
	
	public void cerrarSesion() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	public String nuevoMenu() {
		this.setMenu(new Menu());
		return "menu.xhtml";
	}
	
	public void insertar() {
		try {
			mService.insertar(menu);
			limpiarMenu();
			//this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
		
	public void listar() {
		try {
			listaMenues = mService.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}		
	}
	
	public void limpiarMenu() {
		this.init();
	}
	
	public void eliminar(Menu mo) {
		try {
			mService.eliminar(mo.getCodigo());
			this.listar();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}			
	}
	

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getListaMenues() {
		return listaMenues;
	}

	public void setListaMenues(List<Menu> listaMenues) {
		this.listaMenues = listaMenues;
	}

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}
	
	
	

}

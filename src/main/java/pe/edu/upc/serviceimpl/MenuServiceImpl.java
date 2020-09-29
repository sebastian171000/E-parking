package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pe.edu.upc.dao.IMenuDao;
import pe.edu.upc.entity.Menu;
import pe.edu.upc.service.IMenuService;

@Named
@RequestScoped

public class MenuServiceImpl implements IMenuService, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IMenuDao mD;

	@Override
	public void insertar(Menu menu) {
		mD.insertar(menu);		
	}

	@Override
	public List<Menu> listar() {
		return mD.listar();
	}

	@Override
	public void eliminar(int idMenu) {
		mD.eliminar(idMenu);		
	}

}

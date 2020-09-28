package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IMenuDao;
import pe.edu.upc.entity.Menu;

public class MenuDaoImpl implements IMenuDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void insertar(Menu menu) {
		try {
			em.persist(menu);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> listar() {
		List<Menu> lista = new ArrayList<Menu>();
		try {
			Query q = em.createQuery("select m from Menu m");
			lista = (List<Menu>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	@Transactional
	@Override
	public void eliminar(int idMenu) {
		Menu mot = new Menu();
		try {
			mot = em.getReference(Menu.class,idMenu);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}

}

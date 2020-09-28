package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IVehiculoDao;
import pe.edu.upc.entity.Usuario;
import pe.edu.upc.entity.Vehiculo;

public class VehiculoDaoImpl implements IVehiculoDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void insertar(Vehiculo vehiculo) {
		try {
			em.persist(vehiculo);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void edit(Vehiculo vehiculo) {
		try {
			em.merge(vehiculo);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> listar() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Vehiculo> lista = new ArrayList<Vehiculo>();
		try {
			Query q = em.createQuery("FROM Vehiculo v WHERE v.persona = ?1");
			//Query q = em.createQuery("FROM Vehiculo v");
			q.setParameter(1, us.getPersona());
			
			lista = (List<Vehiculo>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	@Transactional
	@Override
	public void eliminar(int idVehiculo) {
		Vehiculo mot = new Vehiculo();
		try {
			mot = em.getReference(Vehiculo.class,idVehiculo);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}

}

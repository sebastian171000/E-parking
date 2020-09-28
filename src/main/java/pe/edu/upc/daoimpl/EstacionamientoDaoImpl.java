package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IEstacionamientoDao;
import pe.edu.upc.entity.Estacionamiento;
import pe.edu.upc.entity.Usuario;

public class EstacionamientoDaoImpl implements IEstacionamientoDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void insertar(Estacionamiento estacionamiento) {
		try {
			em.persist(estacionamiento);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Estacionamiento> listar() {
		List<Estacionamiento> lista = new ArrayList<Estacionamiento>();
		try {
			Query q = em.createQuery("select e from Estacionamiento e");
			lista = (List<Estacionamiento>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Estacionamiento esDeAdmin() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		Estacionamiento estacionamiento = null;
		String consulta;
		try {
			consulta = "FROM Estacionamiento e WHERE e.persona = ?1";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getPersona());
		
			
			List<Estacionamiento> lista = query.getResultList();
			if(!lista.isEmpty()) {
				estacionamiento = lista.get(0);
			}
		}catch(Exception e) {
			throw e;
		}
		return estacionamiento;
	}


	@Transactional
	@Override
	public void eliminar(int idEstacionamiento) {
		Estacionamiento mot = new Estacionamiento();
		try {
			mot = em.getReference(Estacionamiento.class,idEstacionamiento);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}
	
	
	

}

package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IPersonaDao;
import pe.edu.upc.entity.Persona;

public class PersonaDaoImpl implements IPersonaDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void insertar(Persona persona) {
		try {
			em.persist(persona);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Persona> listar() {
		List<Persona> lista = new ArrayList<Persona>();
		try {
			Query q = em.createQuery("select c from Persona c");
			lista = (List<Persona>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	@Transactional
	@Override
	public void eliminar(int idPersona) {
		Persona mot = new Persona();
		try {
			mot = em.getReference(Persona.class,idPersona);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}
	
	

}

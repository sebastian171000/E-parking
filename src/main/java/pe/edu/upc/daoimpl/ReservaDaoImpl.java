package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IReservaDao;
import pe.edu.upc.entity.Reserva;
import pe.edu.upc.entity.Usuario;

public class ReservaDaoImpl implements IReservaDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void edit(Reserva reserva) {
		try {
			em.merge(reserva);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void insertar(Reserva reserva) {
		try {
			em.persist(reserva);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> listar() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reserva> lista = new ArrayList<Reserva>();
		try {
			Query q = em.createQuery("SELECT r FROM Reserva r WHERE r.estacionamiento.persona = ?1 AND r.estado != ?2 AND r.estado != ?3");
			q.setParameter(1, us.getPersona());
			q.setParameter(2, "Rechazado");
			q.setParameter(3, "Finalizado");
			lista = (List<Reserva>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> listarReservasPersona() {
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reserva> lista = new ArrayList<Reserva>();
		try {
			Query q = em.createQuery("SELECT r FROM Reserva r WHERE r.vehiculo.persona = ?1 AND r.estado != ?2 AND r.estado != ?3 ");
			
			q.setParameter(1, us.getPersona());
			q.setParameter(2, "Rechazado");
			q.setParameter(3, "Finalizado");
			lista = (List<Reserva>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> listarReservasHistorial(){
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reserva> lista = new ArrayList<Reserva>();
		try {
			Query q = em.createQuery("SELECT r FROM Reserva r WHERE r.vehiculo.persona = ?1 AND r.estado != ?2 AND r.estado != ?3 ");
			
			q.setParameter(1, us.getPersona());
			q.setParameter(2, "Pendiente");
			q.setParameter(3, "Aprobado");
			lista = (List<Reserva>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reserva> listarReservasHistorialAdmin(){
		Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		List<Reserva> lista = new ArrayList<Reserva>();
		try {
			Query q = em.createQuery("SELECT r FROM Reserva r WHERE r.estacionamiento.persona = ?1 AND r.estado != ?2 AND r.estado != ?3 ");
			
			q.setParameter(1, us.getPersona());
			q.setParameter(2, "Pendiente");
			q.setParameter(3, "Aprobado");
			lista = (List<Reserva>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	@Transactional
	@Override
	public void eliminar(int idReserva) {
		Reserva mot = new Reserva();
		try {
			mot = em.getReference(Reserva.class,idReserva);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}

}

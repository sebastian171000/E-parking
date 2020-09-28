package pe.edu.upc.daoimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import pe.edu.upc.dao.IUsuarioDao;
import pe.edu.upc.entity.Usuario;

public class UsuarioDaoImpl implements IUsuarioDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "a")
	private EntityManager em;

	@Transactional
	@Override
	public void insertar(Usuario usuario) {
		try {
			em.persist(usuario);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	@Transactional
	@Override
	public void edit(Usuario usuario) {
		try {
			em.merge(usuario);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> listar() {
		List<Usuario> lista = new ArrayList<Usuario>();
		try {
			Query q = em.createQuery("select u from Usuario u");
			lista = (List<Usuario>) q.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return lista;
	}

	@Transactional
	@Override
	public void eliminar(int idUsuario) {
		Usuario mot = new Usuario();
		try {
			mot = em.getReference(Usuario.class,idUsuario);
			em.remove(mot);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}				
	}
	@SuppressWarnings("unchecked")
	@Override
	public Usuario iniciarSesion(Usuario us) {
		Usuario usuario = null;
		String consulta;
		try {
			//vemos si el nombre y la clave de usuario que colocamos en el login coinciden con algunos de los usuarios que 
			//tenemos registrados en nuestra bd
			consulta = "FROM Usuario u WHERE u.usuario = ?1 and u.clave = ?2";
			Query query = em.createQuery(consulta);
			//no es recomendable utilizar la concatenacion en las consultas por lo que usamos esta otra forma
			query.setParameter(1, us.getUsuario());
			query.setParameter(2, us.getClave());
			
			List<Usuario> lista = query.getResultList();
			//comprobamos si la lista esta vacia
			if(!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		}catch(Exception e) {
			throw e;
		}
		return usuario;
	}

}

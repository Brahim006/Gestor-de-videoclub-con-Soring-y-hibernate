/*
 * Implementación para Hibernate del DAO para la tabla Cliente en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.ClienteDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;
import javax.transaction.Transactional;
// Imports para el uso de los frameworks
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.JDBCException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Brahim
 */
public class ClienteDAOHibernateImple implements ClienteDAO {

    @Autowired
    private SessionFactory sessionFactory; 
    
    @Override
    @Transactional
    public Cliente obtenerClientePorId(int id) throws SQLException {
    
        Session session = null;
        
        try{
            // consulta
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Cliente c WHERE c.idCliente = :id";
            Query q = session.createQuery(hql).setInteger("id", id);
            // Obtengo el único resultado (o null)
            Cliente ret = (Cliente)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerClientePorID

    @Override
    @Transactional
    public Collection<Cliente> obtenerClientePorNombre(String nombre) 
                                                        throws SQLException {
    
        Session session = null;
        
        try{ // consulta
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Cliente c WHERE c.nombre LIKE :nombre";
            Query q = session.createQuery(hql)
                             .setString("nombre", nombre + "%");

            Collection<Cliente> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin ObtenerClientePorNombre

    @Override
    @Transactional
    public Collection<Cliente> obtenerClientesPorPelicula(Pelicula pelicula) 
                                                        throws SQLException {
    
        Session session = null;
        
        try{
            // consulta
            session = sessionFactory.getCurrentSession();
            
            Pelicula p = (Pelicula)session.get(Pelicula.class, 
                                               pelicula.getIdPelicula());
            Collection<Cliente> ret =  p.getClientes();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerClientesPorPelicula

    @Override
    @Transactional
    public Collection<Cliente> obtenerClientesPorPromocion(Promocion promocion) 
                                                        throws SQLException {
 
        Session session = null;
        
        try {
            // consulta
            session = sessionFactory.getCurrentSession();
            
            Promocion p = (Promocion)session.get(Promocion.class, 
                                                 promocion.getIdPromocion());

            Collection<Cliente> ret = p.getClientes();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerClientesPorPromocion
    
    @Override
    @Transactional
    public void crearOActualizarCliente(Cliente cliente) throws SQLException {
    
        Session session = null;

        try { 
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(cliente);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin crearCliente
    
    @Override
    @Transactional
    public void borrarCliente(Cliente cliente) throws SQLException {
        
        Session session = null;
        
        try { // Commitea la transacción
            session = sessionFactory.getCurrentSession();
            session.delete(cliente);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin borrarCliente

    @Override
    @Transactional
    public Collection<Cliente> obtenerTodosLosClientes() throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Cliente";
            Query q = session.createQuery(hql);
            
            Collection<Cliente> ret = q.list();
            
            if(ret.isEmpty()) return null;
            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerTodosLosClientes
    
    // Setter y getter para inyectar el SessionFactory

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}

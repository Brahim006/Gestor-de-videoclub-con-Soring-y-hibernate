/*
 * Implementación para Hibernate del DAO para la tabla Pelicula en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.PeliculaDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Genero;
import com.domain.hibernate.DTO.Pelicula;
import java.sql.SQLException;
import java.util.Collection;
import javax.transaction.Transactional;
// Imports para el uso de los frameworks
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.JDBCException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Brahim
 */
public class PeliculaDAOHibernateImple implements PeliculaDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public Pelicula obtenerPeliculaPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Pelicula p WHERE p.idPelicula = :id";
            Query q = session.createQuery(hql).setInteger("id", id);

            Pelicula ret = (Pelicula)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPeliculaPorId

    @Override
    @Transactional
    public Collection<Pelicula> obtenerPeliculaPorTitulo(String titulo) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Pelicula p WHERE p.titulo LIKE :titulo";
            Query q = session.createQuery(hql)
                             .setString("titulo", titulo + "%");

            Collection<Pelicula> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPeliculaPorTitulo

    @Override
    @Transactional
    public Collection<Pelicula> obtenerPeliculasPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            Cliente c = (Cliente)session
                                    .get(Cliente.class, cliente.getIdCliente());

            Collection<Pelicula> ret = c.getPeliculas();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPeliculasPorCliente
    
    @Override
    @Transactional
    public Collection<Pelicula> obtenerPeliculasPorGenero(Genero genero) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            Genero g = (Genero)session.get(Genero.class, genero.getIdGenero());
            
            Collection<Pelicula> ret = g.getPeliculas();
            
            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerPeliculasPorGenero
    
    @Override
    @Transactional
    public void crearOActualizarPelicula(Pelicula pelicula) throws SQLException{
    
        Session session = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(pelicula);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin crearPelicula

    @Override
    @Transactional
    public void borrarPelicula(Pelicula pelicula) throws SQLException {
    
        Session session = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(pelicula);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin borrarPelicula

    @Override
    @Transactional
    public Collection<Pelicula> obtenerTodasLasPeliculas() throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Pelicula";
            Query q = session.createQuery(hql);
            
            Collection<Pelicula> ret = q.list();
            
            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerTodasLasPeliculas
    
    // Setter y getter para inyectar el SessionFactory

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}

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
// Imports para el uso de los frameworks
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.JDBCException;
import com.domain.hibernate.HibernateSessionFactory;

/**
 *
 * @author Brahim
 */
public class PeliculaDAOHibernateImple implements PeliculaDAO {

    @Override
    public Pelicula obtenerPeliculaPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String hql = "FROM Pelicula p WHERE p.idPelicula = ?";
            Query q = session.createQuery(hql).setInteger(0, id);

            Pelicula ret = (Pelicula)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerPeliculaPorId

    @Override
    public Collection<Pelicula> obtenerPeliculaPorTitulo(String titulo) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            
            String hql = "FROM Pelicula p WHERE p.titulo LIKE ?";
            Query q = session.createQuery(hql).setString(0, titulo + "%");

            Collection<Pelicula> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerPeliculaPorTitulo

    @Override
    public Collection<Pelicula> obtenerPeliculasPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
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
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerPeliculasPorCliente
    
    @Override
    public Collection<Pelicula> obtenerPeliculasPorGenero(Genero genero) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            
            Genero g = (Genero)session.get(Genero.class, genero.getIdGenero());
            
            Collection<Pelicula> ret = g.getPeliculas();
            
            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
    
    } // fin obtenerPeliculasPorGenero
    
    @Override
    public void crearOActualizarPelicula(Pelicula pelicula) throws SQLException{
    
        Session session = null;
        
        try {
            session = HibernateSessionFactory.getSession();
        
            Transaction tr = session.beginTransaction();
            session.saveOrUpdate(pelicula);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin crearPelicula

    @Override
    public void borrarPelicula(Pelicula pelicula) throws SQLException {
    
        Session session = null;
        
        try {
            session = HibernateSessionFactory.getSession();
        
            Transaction tr = session.beginTransaction();
            session.delete(pelicula);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin borrarPelicula
    
}

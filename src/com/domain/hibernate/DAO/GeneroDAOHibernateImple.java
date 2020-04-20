/*
 * Implementación para Hibernate del DAO para la tabla Genero en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.GeneroDAO;
import com.domain.hibernate.DTO.Genero;
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
public class GeneroDAOHibernateImple implements GeneroDAO {

    @Autowired
    private SessionFactory sessionFactory; 
    
    @Override
    @Transactional
    public Genero obtenerGeneroPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            // consulta
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Genero g WHERE g.idGenero = :id";
            Query q = session.createQuery(hql).setInteger("id", id);
            // Obtengo el único resultado (o null)
            Genero ret = (Genero)q.uniqueResult();
            
            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerGeneroPorId

    @Override
    @Transactional
    public Collection<Genero> obtenerGeneroPorDescripcion(String descripcion) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            // consulta
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Genero g WHERE g.descripcion LIKE :desc";
            Query q = session.createQuery(hql)
                             .setString("desc", descripcion + "%");

            Collection<Genero> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerGeneroPorDescripcion

    @Override
    @Transactional
    public void crearOActualizarGenero(Genero genero) throws SQLException {
    
        Session session  = null;
 
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(genero);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } 
        
    } // fir crearGenero

    @Override
    @Transactional
    public void borrarGenero(Genero genero) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            session.delete(genero);
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin borrarGenero

    @Override
    @Transactional
    public Collection<Genero> obtenerTodosLosGeneros() throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Genero";
            Query q = session.createQuery(hql);
            
            Collection<Genero> ret = q.list();
            
            if(ret.isEmpty()) return null;
            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerTodosLosGeneros
    
    // Setter y getter para inyectar el SessionFactory

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}

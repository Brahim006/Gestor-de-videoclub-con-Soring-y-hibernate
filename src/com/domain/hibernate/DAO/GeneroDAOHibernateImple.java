/*
 * Implementación para Hibernate del DAO para la tabla Genero en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.GeneroDAO;
import com.domain.hibernate.DTO.Genero;
import java.sql.SQLException;
import java.util.Collection;
// Imports para el uso de los frameworks
import com.domain.hibernate.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.JDBCException;

/**
 *
 * @author User
 */
public class GeneroDAOHibernateImple implements GeneroDAO {

    @Override
    public Genero obtenerGeneroPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            // consulta
            session = HibernateSessionFactory.getSession();
            
            String hql = "FROM Genero g WHERE g.idGenero = ?";
            Query q = session.createQuery(hql).setInteger(0, id);
            // Obtengo el único resultado (o null)
            Genero ret = (Genero)q.uniqueResult();
            
            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerGeneroPorId

    @Override
    public Collection<Genero> obtenerGeneroPorDescripcion(String descripcion) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            // consulta
            session = HibernateSessionFactory.getSession();
            
            String hql = "FROM Genero g WHERE g.descripcion LIKE ?";
            Query q = session.createQuery(hql).setString(0, descripcion + "%");

            Collection<Genero> ret = q.list();

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
        
    } // fin obtenerGeneroPorDescripcion

    @Override
    public void crearOActualizarGenero(Genero genero) throws SQLException {
    
        Session session  = null;
 
        try {
            session = HibernateSessionFactory.getSession();
            Transaction tr = session.beginTransaction();
            session.saveOrUpdate(genero);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fir crearGenero

    @Override
    public void borrarGenero(Genero genero) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            Transaction tr = session.beginTransaction();
            session.delete(genero);
            tr.commit();
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin borrarGenero

    @Override
    public Collection<Genero> obtenerTodosLosGeneros() throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            
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
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
    
    } // fin obtenerTodosLosGeneros
    
}

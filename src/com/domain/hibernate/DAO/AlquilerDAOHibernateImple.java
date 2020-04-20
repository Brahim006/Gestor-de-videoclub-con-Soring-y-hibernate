/*
 * Implementación para Hibernate del DAO para la tabla Alquiler en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.AlquilerDAO;
import com.domain.hibernate.DTO.Alquiler;
import com.domain.hibernate.DTO.AlquilerPK;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Promocion;
import java.sql.Date;
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
public class AlquilerDAOHibernateImple implements AlquilerDAO{

    @Autowired
    private SessionFactory sessionFactory; 
    
    @Override
    @Transactional
    public Collection<Alquiler> obtenerAlquilerPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Alquiler a WHERE a.pk.idCliente = :id";
            Query q = session.createQuery(hql)
                             .setInteger("id", cliente.getIdCliente());

            Collection<Alquiler> ret = q.list();
    
        if(ret.isEmpty()) return null;
        else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerAlquilerPorCliente

    @Override
    @Transactional
    public Collection<Alquiler> obtenerAlquilerPorPelicula(Pelicula pelicula) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Alquiler a WHERE a.pk.idPelicula = :id";
            Query q = session.createQuery(hql)
                             .setInteger("id", pelicula.getIdPelicula());
        
        Collection<Alquiler> ret = q.list();
        
        if(ret.isEmpty()) return null;
        else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerAlquilerPorPelicula

    @Override
    @Transactional
    public Collection<Alquiler> obtenerAlquilerPorFecha(Date fecha) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Alquiler a WHERE a.pk.fecha = :date";
            Query q = session.createQuery(hql).setDate("date", fecha);

            Collection<Alquiler> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerAlquilerPorFecha

    @Override
    @Transactional
    public Collection<Alquiler> obtenerAlquilerPorDias(int dias) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Alquiler a WHERE a.dias = :dias";
            Query q = session.createQuery(hql).setInteger("dias", dias);

            Collection<Alquiler> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerAlquilerPorDias

    @Override
    @Transactional
    public Collection<Alquiler> obtenerAlquilerPorPromocion(Promocion promocion) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Alquiler a WHERE a.idPromocion = :id";
            Query q = session.createQuery(hql)
                             .setInteger("id", promocion.getIdPromocion());

            Collection<Alquiler> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerAlquilerPorPromocion

    @Override
    @Transactional
    public Alquiler obtenerAlquilerPorClavePrimaria(AlquilerPK pk) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String sql = "FROM Alquiler a WHERE a.pk.idPelicula = :idP " +
                    "AND a.pk.idCliente = :idC " +
                    "AND a.pk.date = :fecha";
            Query q = session.createQuery(sql);
            // seteo los valores
            q.setInteger("idP", pk.getIdPelicula());
            q.setInteger("idC", pk.getIdCliente());
            q.setDate("fecha", pk.getFecha());

            Alquiler ret = (Alquiler)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerAlquilerPorClavePrimaria

    @Override
    @Transactional
    public void crearOActualizarAlquiler(Alquiler alquiler) throws SQLException{
    
        Session session = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(alquiler);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin crearAlquiler

    @Override
    @Transactional
    public void borrarAlquiler(Alquiler alquiler) throws SQLException {
    
        Session session = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(alquiler);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin borarAlquiler

    @Override
    @Transactional
    public Collection<Alquiler> obtenerTodosLosAlquileres() 
                                                        throws SQLException {
    
        Session session = null;

        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Alquiler";
            Query q = session.createQuery(hql);
            
            Collection<Alquiler> ret = q.list();
            
            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerTodosLosAlquileres
    
    // Setter y getter para inyectar el SessionFactory

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}

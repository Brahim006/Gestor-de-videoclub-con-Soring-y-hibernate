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
// Imports para el uso de los frameworks
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.JDBCException;
import com.domain.hibernate.HibernateSessionFactory;

/**
 *
 * @author User
 */
public class AlquilerDAOHibernateImple implements AlquilerDAO{

    @Override
    public Collection<Alquiler> obtenerAlquilerPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String hql = "FROM Alquiler a WHERE a.pk.idCliente = ?";
            Query q = session.createQuery(hql)
                                         .setInteger(0, cliente.getIdCliente());

            Collection<Alquiler> ret = q.list();
    
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
        
    } // fin obtenerAlquilerPorCliente

    @Override
    public Collection<Alquiler> obtenerAlquilerPorPelicula(Pelicula pelicula) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            String hql = "FROM Alquiler a WHERE a.pk.idPelicula = ?";
            Query q = session.createQuery(hql).setInteger(0, 
                                                      pelicula.getIdPelicula());
        
        Collection<Alquiler> ret = q.list();
        
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
        
    } // fin obtenerAlquilerPorPelicula

    @Override
    public Collection<Alquiler> obtenerAlquilerPorFecha(Date fecha) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            String hql = "FROM Alquiler a WHERE a.pk.fecha = ?";
            Query q = session.createQuery(hql).setDate(0, fecha);

            Collection<Alquiler> ret = q.list();

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
    
    } // fin obtenerAlquilerPorFecha

    @Override
    public Collection<Alquiler> obtenerAlquilerPorDias(int dias) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            String hql = "FROM Alquiler a WHERE a.dias = ?";
            Query q = session.createQuery(hql).setInteger(0, dias);

            Collection<Alquiler> ret = q.list();

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
    
    } // fin obtenerAlquilerPorDias

    @Override
    public Collection<Alquiler> obtenerAlquilerPorPromocion(Promocion promocion) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
            String hql = "FROM Alquiler a WHERE a.idPromocion = ?";
            Query q = session.createQuery(hql)
                                     .setInteger(0, promocion.getIdPromocion());

            Collection<Alquiler> ret = q.list();

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
    
    } // fin obtenerAlquilerPorPromocion

    @Override
    public Alquiler obtenerAlquilerPorClavePrimaria(AlquilerPK pk) 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String sql = "FROM Alquiler a WHERE a.pk.idPelicula = ? " +
                    "AND a.pk.idCliente = ? " +
                    "AND a.pk.date = ?";
            Query q = session.createQuery(sql);
            // seteo los valores
            q.setInteger(0, pk.getIdPelicula());
            q.setInteger(1, pk.getIdCliente());
            q.setDate(2, pk.getFecha());

            Alquiler ret = (Alquiler)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerAlquilerPorClavePrimaria

    @Override
    public void crearOActualizarAlquiler(Alquiler alquiler) throws SQLException{
    
        Session session = null;
        
        try {
            session = HibernateSessionFactory.getSession();
            Transaction tr = session.beginTransaction();
            session.saveOrUpdate(alquiler);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
    
    } // fin crearAlquiler

    @Override
    public void borrarAlquiler(Alquiler alquiler) throws SQLException {
    
        Session session = null;
        
        try {
            session = HibernateSessionFactory.getSession();
            Transaction tr = session.beginTransaction();
            session.delete(alquiler);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin borarAlquiler

    @Override
    public Collection<Alquiler> obtenerTodosLosAlquileres() 
                                                        throws SQLException {
    
        Session session = null;

        try {
            
            session = HibernateSessionFactory.getSession();
            
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
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerTodosLosAlquileres
    
}

/*
 * Implementación para Hibernate del DAO para la tabla Promocion en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.PromocionDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;
// Imports para el funcionamiento del framework
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.JDBCException;
import com.domain.hibernate.HibernateSessionFactory;

/**
 *
 * @author Brahim
 */
public class PromocionDAOHibernateImple implements PromocionDAO {

    @Override
    public Promocion obtenerPromocionPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String hql = "FROM Promocion p WHERE p.idPromocion = ?";
            Query q = session.createQuery(hql).setInteger(0, id);

            Promocion ret = (Promocion)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin obtenerPromocionPorId

    @Override
    public Collection<Promocion> obtenerPromocionPorMonto(int monto) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String hql = "FROM Promocion p WHERE p.descuento = ?";
            Query q = session.createQuery(hql).setInteger(0, monto);

            Collection<Promocion> ret = q.list();

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
        
    } // fin obtenerPromocionPorMonto

    @Override
    public Collection<Promocion> obtenerPromocionPorRango(int min, int max) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            String hql = "FROM Promocion p WHERE p.descuento >= ? " + 
                    "AND p.descuento <= ?";

            Query q = session.createQuery(hql);
            q.setInteger(0, min);
            q.setInteger(1, max);

            Collection<Promocion> ret = q.list();

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
    
    } // fin obtenerPromocionPorRango

    @Override
    public Collection<Promocion> obtenerPromocionesPorCliente(Cliente cliente) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = HibernateSessionFactory.getSession();
        
            Cliente c = (Cliente)session.get(Cliente.class, cliente
                                                               .getIdCliente());

            Collection<Promocion> ret = c.getPromociones();

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
    
    } // fin obtenerPromocionesPorCliente
    
    @Override
    public void crearPromocion(Promocion promocion) throws SQLException {
    
        Session session = null;

        try {
            session = HibernateSessionFactory.getSession();
        
            Transaction tr = session.beginTransaction();
            session.save(promocion);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin crearPromocion

    @Override
    public void borrarPromocion(Promocion promocion) throws SQLException {
    
        Session session = null;
        
        try {
            session = HibernateSessionFactory.getSession();
        
            Transaction tr = session.beginTransaction();
            session.delete(promocion);
            tr.commit();
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        } finally { // liberación de la sesión
            if(session != null) session.close();
        }
        
    } // fin borrarPromocion
    
}

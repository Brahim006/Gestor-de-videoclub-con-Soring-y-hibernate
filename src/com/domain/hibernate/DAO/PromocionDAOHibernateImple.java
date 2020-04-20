/*
 * Implementación para Hibernate del DAO para la tabla Promocion en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.PromocionDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;
import javax.transaction.Transactional;
// Imports para el funcionamiento del framework
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
public class PromocionDAOHibernateImple implements PromocionDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public Promocion obtenerPromocionPorId(int id) throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Promocion p WHERE p.idPromocion = :id";
            Query q = session.createQuery(hql).setInteger("id", id);

            Promocion ret = (Promocion)q.uniqueResult();

            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPromocionPorId

    @Override
    @Transactional
    public Collection<Promocion> obtenerPromocionPorMonto(int monto) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Promocion p WHERE p.descuento = :monto";
            Query q = session.createQuery(hql).setInteger("monto", monto);

            Collection<Promocion> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPromocionPorMonto

    @Override
    @Transactional
    public Collection<Promocion> obtenerPromocionPorRango(int min, int max) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
            String hql = "FROM Promocion p WHERE p.descuento >= :min " + 
                    "AND p.descuento <= :max";

            Query q = session.createQuery(hql);
            q.setInteger("min", min);
            q.setInteger("max", max);

            Collection<Promocion> ret = q.list();

            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    
    } // fin obtenerPromocionPorRango

    @Override
    @Transactional
    public Collection<Promocion> obtenerPromocionesPorCliente(Cliente cliente) 
                                                          throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
        
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
        }
    
    } // fin obtenerPromocionesPorCliente
    
    @Override
    @Transactional
    public void crearOActualizarPromocion(Promocion promocion) 
                                                          throws SQLException {
    
        Session session = null;

        try {
            session = sessionFactory.getCurrentSession();
            session.saveOrUpdate(promocion);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin crearPromocion

    @Override
    @Transactional
    public void borrarPromocion(Promocion promocion) throws SQLException {
    
        Session session = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            session.delete(promocion);
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin borrarPromocion

    @Override
    @Transactional
    public Collection<Promocion> obtenerTodasLasPromociones() 
                                                        throws SQLException {
    
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Promocion";
            Query q = session.createQuery(hql);
            
            Collection<Promocion> ret = q.list();
            
            if(ret.isEmpty()) return null;
            return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerTodasLasPromociones

    @Override
    @Transactional
    public Collection<Promocion> obtenerPromocionPorDescripcion
                                    (String descripcion) throws SQLException {
        
        Session session = null;
        
        try {
            
            session = sessionFactory.getCurrentSession();
            
            String hql = "FROM Promocion p WHERE p.descripcion LIKE :desc";
            Query q = session.createQuery(hql)
                             .setString("desc", descripcion + "%");
            
            Collection<Promocion> ret = q.list();
            
            if(ret.isEmpty()) return null;
            else return ret;
            
        } catch (JDBCException je) { // SQLException devuelta al facade
            throw je.getSQLException();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        
    } // fin obtenerPromocionPorDescripcion
    
    // Setter y getter para inyectar el SessionFactory

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}

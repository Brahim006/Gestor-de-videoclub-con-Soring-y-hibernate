/*
 * Implementación para Hibernate del DAO para la tabla Promocion en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.PromocionDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
// Imports para el funcionamiento del framework
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Brahim
 */
public class PromocionDAOHibernateImple extends HibernateDaoSupport 
                                                       implements PromocionDAO {

    @Override
    public Promocion obtenerPromocionPorId(int id) throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Promocion p WHERE p.idPromocion = :d";
        Query q = session.createQuery(hql).setInteger("d", id);
        
        Collection<Promocion> coll = q.list();
        
        Promocion ret = null;
        
        for(Promocion p : coll){
            ret = new Promocion();
            ret.setIdPromocion(p.getIdPromocion());
            ret.setDescuento(p.getDescuento());
            ret.setDescripcion(p.getDescripcion());
        }
        
        return ret;
        
    } // fin obtenerPromocionPorId

    @Override
    public Collection<Promocion> obtenerPromocionPorMonto(int monto) 
                                                          throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Promocion p WHERE p.descuento = :d";
        Query q = session.createQuery(hql).setInteger("d", monto);
        
        Collection<Promocion> coll = q.list();
        
        ArrayList<Promocion> ret = new ArrayList<>();
        
        for(Promocion p : coll){
            ret.add(p);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerPromocionPorMonto

    @Override
    public Collection<Promocion> obtenerPromocionPorRango(int min, int max) 
                                                          throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Promocion p WHERE p.descuento >= :d " + 
                "AND p.descuento <= :e";
        
        Query q = session.createQuery(hql);
        q.setInteger("d", min);
        q.setInteger("e", max);
        
        Collection<Promocion> coll = q.list();
        ArrayList<Promocion> ret =  new ArrayList<>();
        
        for(Promocion p : coll){
            ret.add(p);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerPromocionPorRango

    @Override
    public Collection<Promocion> obtenerPromocionesPorCliente(Cliente cliente) 
                                                          throws SQLException {
    
        Session session = getSession();
        
        Cliente c = (Cliente)session.get(Cliente.class, cliente.getIdCliente());
        
        ArrayList<Promocion> ret = new ArrayList<>();
        
        for(Promocion p : c.getPromociones()){
            ret.add(p);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerPromocionesPorCliente
    
    @Override
    public void crearPromocion(Promocion promocion) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.save(promocion);

        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin crearPromocion

    @Override
    public void borrarPromocion(Promocion promocion) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.delete(promocion);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin borrarPromocion
    
}

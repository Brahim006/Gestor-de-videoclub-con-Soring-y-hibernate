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
import java.util.ArrayList;
import java.util.Collection;
// Imports para el uso de los frameworks
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author User
 */
public class AlquilerDAOHibernateImple extends HibernateDaoSupport 
                                                        implements AlquilerDAO{

    @Override
    public Collection<Alquiler> obtenerAlquilerPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Alquiler a WHERE a.pk.idCliente = :d";
        Query q = session.createQuery(hql)
                                       .setInteger("d", cliente.getIdCliente());
        
        Collection<Alquiler> coll = q.list();
        ArrayList<Alquiler> ret = new ArrayList<>();
        
        for(Alquiler a : coll){
            ret.add(a);
        }
    
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerAlquilerPorCliente

    @Override
    public Collection<Alquiler> obtenerAlquilerPorPelicula(Pelicula pelicula) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Alquiler a WHERE a.pk.idPelicula = :d";
        Query q = session.createQuery(hql).setInteger("d", 
                                                    pelicula.getIdPelicula());
        
        Collection<Alquiler> coll = q.list();
        ArrayList<Alquiler> ret = new ArrayList<>();
        
        for(Alquiler a : coll){
            ret.add(a);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerAlquilerPorPelicula

    @Override
    public Collection<Alquiler> obtenerAlquilerPorFecha(Date fecha) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Alquiler a WHERE a.pk.fecha = ':d'";
        Query q = session.createQuery(hql).setDate("d", fecha);
        
        Collection<Alquiler> coll = q.list();
        ArrayList<Alquiler> ret = new ArrayList<>();
        
        for(Alquiler a : coll){
            ret.add(a);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerAlquilerPorFecha

    @Override
    public Collection<Alquiler> obtenerAlquilerPorDias(int dias) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Alquiler a WHERE a.dias = :d";
        Query q = session.createQuery(hql).setInteger("d", dias);
        
        Collection<Alquiler> coll = q.list();
        ArrayList<Alquiler> ret = new ArrayList<>();
        
        for(Alquiler a : coll){
            ret.add(a);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerAlquilerPorDias

    @Override
    public Collection<Alquiler> obtenerAlquilerPorPromocion(Promocion promocion) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Alquiler a WHERE a.idPromocion = :d";
        Query q = session.createQuery(hql)
                                   .setInteger("d", promocion.getIdPromocion());
        
        Collection<Alquiler> coll = q.list();
        ArrayList<Alquiler> ret = new ArrayList<>();
        
        for(Alquiler a : coll){
            ret.add(a);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerAlquilerPorPromocion

    @Override
    public Alquiler obtenerAlquilerPorClavePrimaria(AlquilerPK pk) 
                                                        throws SQLException {
    
        Session session = getSession();
        
    } // fin obtenerAlquilerPorClavePrimaria

    @Override
    public void crearAlquiler(Alquiler alquiler) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.save(alquiler);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
    
    } // fin crearAlquiler

    @Override
    public void borrarAlquiler(Alquiler alquiler) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.delete(alquiler);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin borarAlquiler
    
}

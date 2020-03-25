/*
 * Implementación para Hibernate del DAO para la tabla Genero en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.GeneroDAO;
import com.domain.hibernate.DTO.Genero;
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
public class GeneroDAOHibernateImple extends HibernateDaoSupport 
                                                        implements GeneroDAO {

    @Override
    public Genero obtenerGeneroPorId(int id) throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Genero g WHERE g.idGenero = :d";
        Query q = session.createQuery(hql).setInteger("d", id);
        
        Collection<Genero> coll = q.list();
        
        Genero ret = null;
        
        for(Genero g : coll){
            ret = new Genero();
            ret.setIdGenero(g.getIdGenero());
            ret.setDescripcion(g.getDescripcion());
        }
        
        return ret;
        
    } // fin obtenerGeneroPorId

    @Override
    public Collection<Genero> obtenerGeneroPorDescripcion(String descripcion) 
                                                        throws SQLException {
    
        Session session =  getSession();
        
        String hql = "FROM Genero g WHERE g.descripcion LIKE ':d%'";
        Query q = session.createQuery(hql).setString("d", descripcion);
        
        Collection<Genero> coll = q.list();
        ArrayList<Genero> ret = new ArrayList<>();
        
        for(Genero g : coll){
            ret.add(g);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerGeneroPorDescripcion

    @Override
    public void crearGenero(Genero genero) throws SQLException {
    
        Session session  = getSession();
        
        Transaction tr = session.beginTransaction();
        session.save(genero);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fir crearGenero

    @Override
    public void borrarGenero(Genero genero) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.delete(genero);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin borrarGenero
    
}

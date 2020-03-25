/*
 * Implementación para Hibernate del DAO para la tabla Pelicula en la bd
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.PeliculaDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
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
 * @author Brahim
 */
public class PeliculaDAOHibernateImple extends HibernateDaoSupport 
                                                        implements PeliculaDAO {

    @Override
    public Pelicula obtenerPeliculaPorId(int id) throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Pelicula p WHERE p.idPelicula = :d";
        Query q = session.createQuery(hql).setInteger("d", id);
        
        Collection<Pelicula> coll = q.list();
        Pelicula ret = null;
        
        for(Pelicula p : coll){
            ret = new Pelicula();
            ret.setIdPelicula(p.getIdPelicula());
            ret.setTitulo(p.getTitulo());
            ret.setCopias(p.getCopias());
        }
        
        return ret;
        
    } // fin obtenerPeliculaPorId

    @Override
    public Collection<Pelicula> obtenerPeliculaPorTitulo(String titulo) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Pelicula p WHERE p.titulo LIKE ':d%'";
        Query q = session.createQuery(hql).setString("d", titulo);
        
        Collection<Pelicula> coll = q.list();
        ArrayList<Pelicula> ret = new ArrayList<>();
        
        for(Pelicula p : coll){
            ret.add(p);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerPeliculaPorTitulo

    @Override
    public Collection<Pelicula> obtenerPeliculasPorCliente(Cliente cliente) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        Cliente c = (Cliente)session.get(Cliente.class, cliente.getIdCliente());
        
        ArrayList<Pelicula> ret = new ArrayList<>();
        
        for(Pelicula p : c.getPeliculas()){
            ret.add(p);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerPeliculasPorCliente
    
    @Override
    public void crearPelicula(Pelicula pelicula) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.save(pelicula);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin crearPelicula

    @Override
    public void borrarPelicula(Pelicula pelicula) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.delete(pelicula);
        
        try {
            tr.commit();
        } catch (HibernateException he) {
            throw new SQLException(he.getMessage());
        }
        
    } // fin borrarPelicula
    
}

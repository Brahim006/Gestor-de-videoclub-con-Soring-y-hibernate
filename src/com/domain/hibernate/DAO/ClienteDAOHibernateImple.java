/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.hibernate.DAO;

import com.domain.DAO.ClienteDAO;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;
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
public class ClienteDAOHibernateImple extends HibernateDaoSupport 
                                                        implements ClienteDAO {

    @Override
    public Cliente obtenerClientePorId(int id) throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Cliente c WHERE c.idCliente = :d";
        Query q = session.createQuery(hql).setInteger("d", id);
        
        Collection<Cliente> coll = q.list();
        Cliente ret = null;
        
        for(Cliente c:coll){ // Sólo puede haber uno ó ningún resultado
            ret = new Cliente();
            ret.setIdCliente(c.getIdCliente());
            ret.setNombre(c.getNombre());
        }
        
        return ret;
        
    } // fin obtenerClientePorID

    @Override
    public Collection<Cliente> obtenerClientePorNombre(String nombre) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        String hql = "FROM Cliente c WHERE c.nombre LIKE = ':d%'";
        Query q = session.createQuery(hql).setString("d", nombre);
        
        ArrayList<Cliente> ret = new ArrayList<>();
        Collection<Cliente> coll = q.list();
        
        for(Cliente c:coll){
            Cliente aux = new Cliente();
            aux.setIdCliente(c.getIdCliente());
            aux.setNombre(c.getNombre());
            ret.add(aux);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin ObtenerClientePorNombre

    @Override
    public Collection<Cliente> obtenerClientesPorPelicula(Pelicula pelicula) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        // Busco la película
        Pelicula p = (Pelicula)session.get(Pelicula.class, 
                                           pelicula.getIdPelicula());
        // Obtengo la colección de clientes asociados a esa película
        ArrayList<Cliente> ret = new ArrayList<>();
        
        for(Cliente c:p.getClientes()){
            ret.add(c);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
    
    } // fin obtenerClientesPorPelicula

    @Override
    public Collection<Cliente> obtenerClientesPorPromocion(Promocion promocion) 
                                                        throws SQLException {
    
        Session session = getSession();
        
        Promocion p = (Promocion)session.get(Promocion.class, 
                                             promocion.getIdPromocion());
        
        ArrayList<Cliente> ret = new ArrayList<>();
        
        for(Cliente c : p.getClientes()){
            ret.add(c);
        }
        
        if(ret.isEmpty()) return null;
        else return ret;
        
    } // fin obtenerClientesPorPromocion
    
    @Override
    public void crearCliente(Cliente cliente) throws SQLException {
    
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.save(cliente);
        
        try { // Commitea la transacción
            tr.commit();
        } 
        catch (HibernateException he) { //Convierte la excepción en SQLException
            throw new SQLException(he.getMessage());
        }
        
    } // fin crearCliente
    
    @Override
    public void borrarCliente(Cliente cliente) throws SQLException {
        
        Session session = getSession();
        
        Transaction tr = session.beginTransaction();
        session.delete(cliente);
        
        try { // Commitea la transacción
            tr.commit();
        } catch (HibernateException he){//Convierte la excepción en SQLException
            throw new SQLException(he.getMessage());
        }
        
    } // fin borrarCliente
    
}

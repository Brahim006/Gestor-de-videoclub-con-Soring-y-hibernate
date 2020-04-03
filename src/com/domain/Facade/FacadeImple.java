/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.Facade;

import com.domain.hibernate.DTO.*;
import com.domain.hibernate.DAO.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
public class FacadeImple implements Facade {
    
    private PeliculaDAOHibernateImple peliculaDAO;
    private ClienteDAOHibernateImple clienteDAO;
    private PromocionDAOHibernateImple promocionDAO;
    private GeneroDAOHibernateImple generoDAO;
    private AlquilerDAOHibernateImple alquilerDAO;
    
    /**
     * Crea un objeto FacadeImple inicializando todos sus DAOs
     */
    public FacadeImple(){
        peliculaDAO = new PeliculaDAOHibernateImple();
        clienteDAO = new ClienteDAOHibernateImple();
        promocionDAO = new PromocionDAOHibernateImple();
        generoDAO = new GeneroDAOHibernateImple();
        alquilerDAO = new AlquilerDAOHibernateImple();
    }

    @Override
    public Collection<Cliente> obtenerCliente(int id) throws SQLException {
        ArrayList<Cliente> ret = new ArrayList<>();
        ret.add(clienteDAO.obtenerClientePorId(id));
        return ret;
    }

    // Métodos de acceso a la tabla Cliente
    
    @Override
    public Collection<Cliente> obtenerCliente(String nombre) 
                                                        throws SQLException {
        return clienteDAO.obtenerClientePorNombre(nombre);
    }
    
    @Override
    public Collection<Cliente> obtenerClientes(Pelicula pelicula) 
                                                        throws SQLException {
        return clienteDAO.obtenerClientesPorPelicula(pelicula);
    }
    
    @Override
    public Collection<Cliente> obtenerClientes(Promocion promocion) 
                                                        throws SQLException {
        return clienteDAO.obtenerClientesPorPromocion(promocion);
    }

    @Override
    public void crearOActualizarCliente(Cliente cliente) throws SQLException {
        clienteDAO.crearOActualizarCliente(cliente);
    }

    @Override
    public void borrar(Cliente cliente) throws SQLException {
        clienteDAO.borrarCliente(cliente);
    }

    // Métodos de acceso a la tabla Promocion
    
    @Override
    public Collection<Promocion> obtenerPromocion(int id) throws SQLException {
        ArrayList<Promocion> ret = new ArrayList<>();
        ret.add(promocionDAO.obtenerPromocionPorId(id));
        return ret;
    }

    @Override
    public Collection<Promocion> obtenerPromocion(int min, int max) 
                                                        throws SQLException {
        return promocionDAO.obtenerPromocionPorRango(min, max);
    }

    @Override
    public Collection<Promocion> obtenerPromociones(Cliente cliente) 
                                                        throws SQLException {
        return promocionDAO.obtenerPromocionesPorCliente(cliente);
    }
    
    @Override
    public void crearOActualizarPromocion(Promocion promocion) 
                                                        throws SQLException {
        promocionDAO.crearOActualizarPromocion(promocion);
    }

    @Override
    public void borrar(Promocion promocion) throws SQLException {
        promocionDAO.borrarPromocion(promocion);
    }

    // Métodos de acceso a la tabla Genero
    
    @Override
    public Collection<Genero> obtenerGenero(int id) throws SQLException {
        ArrayList<Genero> ret = new ArrayList<>();
        ret.add(generoDAO.obtenerGeneroPorId(id));
        return ret;
    }

    @Override
    public Collection<Genero> obtenerGenero(String descripcion) 
                                                        throws SQLException {
        return generoDAO.obtenerGeneroPorDescripcion(descripcion);
    }

    @Override
    public void crearOActualizarGenero(Genero genero) throws SQLException {
        generoDAO.crearOActualizarGenero(genero);
    }

    @Override
    public void borrar(Genero genero) throws SQLException {
        generoDAO.borrarGenero(genero);
    }

    // Métodos de acceso a la tabla Pelicula
    
    @Override
    public Collection<Pelicula> obtenerPelicula(int id) throws SQLException {
        ArrayList<Pelicula> ret = new ArrayList<>();
        ret.add(peliculaDAO.obtenerPeliculaPorId(id));
        return ret;
    }

    @Override
    public Collection<Pelicula> obtenerPelicula(String titulo) 
                                                        throws SQLException {
        return peliculaDAO.obtenerPeliculaPorTitulo(titulo);
    }
    
    @Override
    public Collection<Pelicula> obtenerPeliculas(Cliente cliente) 
                                                        throws SQLException {
        return peliculaDAO.obtenerPeliculasPorCliente(cliente);
    }

    @Override
    public void crearOActualizarPelicula(Pelicula pelicula) throws SQLException{
        peliculaDAO.crearOActualizarPelicula(pelicula);
    }

    @Override
    public void borrar(Pelicula pelicula) throws SQLException {
        peliculaDAO.borrarPelicula(pelicula);
    }

    // Métodos de acceso a la tabla Alquiler
    
    @Override
    public Collection<Alquiler> obtenerAlquiler(Cliente cliente) 
                                                        throws SQLException {
        return alquilerDAO.obtenerAlquilerPorCliente(cliente);
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler(Pelicula pelicula) 
                                                        throws SQLException {
        return alquilerDAO.obtenerAlquilerPorPelicula(pelicula);
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler(Date fecha) 
                                                          throws SQLException {
        return alquilerDAO.obtenerAlquilerPorFecha(fecha);
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler(int dias) throws SQLException {
        return alquilerDAO.obtenerAlquilerPorDias(dias);
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler(Promocion promocion) 
                                                        throws SQLException {
        return alquilerDAO.obtenerAlquilerPorPromocion(promocion);
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler(AlquilerPK pk) 
                                                        throws SQLException {
        ArrayList<Alquiler> ret = new ArrayList<>();
        ret.add(alquilerDAO.obtenerAlquilerPorClavePrimaria(pk));
        return ret;
    }

    @Override
    public void crearOActualizarAlquiler(Alquiler alquiler) throws SQLException{
        alquilerDAO.crearOActualizarAlquiler(alquiler);
    }

    @Override
    public void borrar(Alquiler alquiler) throws SQLException {
        alquilerDAO.borrarAlquiler(alquiler);
    }
    
}

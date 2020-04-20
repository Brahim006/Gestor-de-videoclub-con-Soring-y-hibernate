/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.domain.Facade;

import com.domain.hibernate.DTO.*;
import com.domain.DAO.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public class FacadeImple implements Facade {
    
    private PeliculaDAO peliculaDAO;
    private ClienteDAO clienteDAO;
    private PromocionDAO promocionDAO;
    private GeneroDAO generoDAO;
    private AlquilerDAO alquilerDAO;
    
    /**
     * Crea un objeto FacadeImple inicializando todos sus DAOs
     */
    public FacadeImple(){}

    @Override
    public Cliente obtenerCliente(int id) throws SQLException {
        return (Cliente)clienteDAO.obtenerClientePorId(id);
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
    public Promocion obtenerPromocion(int id) throws SQLException {
        return (Promocion)promocionDAO.obtenerPromocionPorId(id);
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
    public Genero obtenerGenero(int id) throws SQLException {
        return (Genero)generoDAO.obtenerGeneroPorId(id);
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
    public Pelicula obtenerPelicula(int id) throws SQLException {
        return (Pelicula)peliculaDAO.obtenerPeliculaPorId(id);
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
    public Alquiler obtenerAlquiler(AlquilerPK pk) 
                                                        throws SQLException {
        return (Alquiler)alquilerDAO.obtenerAlquilerPorClavePrimaria(pk);
    }

    @Override
    public void crearOActualizarAlquiler(Alquiler alquiler) throws SQLException{
        alquilerDAO.crearOActualizarAlquiler(alquiler);
    }

    @Override
    public void borrar(Alquiler alquiler) throws SQLException {
        alquilerDAO.borrarAlquiler(alquiler);
    }

    @Override
    public Collection<Cliente> obtenerCliente() throws SQLException {
        return clienteDAO.obtenerTodosLosClientes();
    }

    @Override
    public Collection<Promocion> obtenerPromocion() throws SQLException {
        return promocionDAO.obtenerTodasLasPromociones();
    }

    @Override
    public Collection<Genero> obtenerGenero() throws SQLException {
        return generoDAO.obtenerTodosLosGeneros();
    }

    @Override
    public Collection<Pelicula> obtenerPelicula() throws SQLException {
        return peliculaDAO.obtenerTodasLasPeliculas();
    }

    @Override
    public Collection<Alquiler> obtenerAlquiler() throws SQLException {
        return alquilerDAO.obtenerTodosLosAlquileres();
    }

    @Override
    public Collection<Promocion> obtenerPromocion(String descripcion) 
                                                        throws SQLException {
        return promocionDAO.obtenerPromocionPorDescripcion(descripcion);
    }

    @Override
    public Collection<Pelicula> obtenerPelicula(Genero genero) 
                                                           throws SQLException {
        return peliculaDAO.obtenerPeliculasPorGenero(genero);
    }
    
    // Setters y getters para los DAOs

    public void setPeliculaDAO(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void setPromocionDAO(PromocionDAO promocionDAO) {
        this.promocionDAO = promocionDAO;
    }

    public void setGeneroDAO(GeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

    public void setAlquilerDAO(AlquilerDAO alquilerDAO) {
        this.alquilerDAO = alquilerDAO;
    }

    public PeliculaDAO getPeliculaDAO() {
        return peliculaDAO;
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public PromocionDAO getPromocionDAO() {
        return promocionDAO;
    }

    public GeneroDAO getGeneroDAO() {
        return generoDAO;
    }

    public AlquilerDAO getAlquilerDAO() {
        return alquilerDAO;
    }
    
}
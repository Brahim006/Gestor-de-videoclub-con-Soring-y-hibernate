/*
 * Interfaz que determina el comportamiento que debe tener un DAO que acceda
 * a la tabla Cliente en la base de datos
 */
package com.domain.DAO;

import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public interface ClienteDAO {
    
    /**
     * Retorna un cliente según su ID
     * @param id ID que identifica al cliente.
     * @return Un objeto Cliente según el ID especificado o null en caso de
     * que dicho cliente no exista.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Cliente obtenerClientePorId(int id) throws SQLException;
    
    /**
     * Retorna uno ó más clientes cuyos nombres sean iguales ó coincidan en sus
     * primeras letras con el nombre especificado.
     * @param nombre Nombre ó primeras letras del nombre del/los cliente/s
     * solicitado/s.
     * @return Una colección de clientes que cumplen con el criterio de
     * búsqueda ó null si no hubo coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Cliente> obtenerClientePorNombre(String nombre)
                                                            throws SQLException;
    
    /**
     * Inserta un nuevo cliente en la base de datos ó actualiza uno ya existente
     * @param cliente un objeto CLiente que tenga las características deseadas
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void crearOActualizarCliente(Cliente cliente) throws SQLException;
    
    /**
     * Retorna todos los clientes que hayan alquilado cierta película
     * @param pelicula Un objeto Pelicula sobre la cual se quiera consultar
     * todos los clientes que la tienen alquilada actualmente.
     * @return Una colección conteniendo todos los clientes que cumplan el
     * criterio de búsqueda ó null en caso de que no hayan coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Cliente> obtenerClientesPorPelicula(Pelicula pelicula)
                                                            throws SQLException;
    
    /**
     * Retorna todos los clientes que son beneficiarios de una promoción en
     * específico.
     * @param promocion Un objeto Promocion que represente a la promoción sobre
     * la que se quiere realzar la consulta.
     * @return Una colección de clientes que sean beneficiarios de la promoción
     * ó null en el caso de que la promoción no tenga beneficiarios.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Cliente> obtenerClientesPorPromocion(Promocion promocion)
                                                            throws SQLException;
    
    /**
     * Dado un objeto Cliente, borra la fila equivalente en la base de datos.
     * @param cliente Un objeto de tipo Cliente que tenga las 
     * características deseadas.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void borrarCliente(Cliente cliente)throws SQLException;
    
}

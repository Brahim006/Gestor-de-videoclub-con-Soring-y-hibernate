/*
 * Interfaz que determina el comportamiento que debe tener un DAO que acceda
 * a la tabla Alquiler en la base de datos
 */
package com.domain.DAO;

import com.domain.hibernate.DTO.Alquiler;
import com.domain.hibernate.DTO.AlquilerPK;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Promocion;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author User
 */
public interface AlquilerDAO {
    
    /**
     * Retorna los alquileres efectuados por un cliente específico
     * @param cliente Un objeto Cliente representando al cliente sobre el que
     * se quiere efectuar la consulta
     * @return Una colección de alquileres efectuados por ese cliente ó null en 
     * caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Alquiler> obtenerAlquilerPorCliente(Cliente cliente)
                                                            throws SQLException;
    
    /**
     * Retorna los alquileres que se efectuaron sobre una película en específico
     * @param pelicula Un objeto Pelicula representando a la película sobre el
     * que se quiere efectuar la consulta.
     * @return Una colección de objetos Alquiler realizados sobre esa película ó
     * null en caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Alquiler> obtenerAlquilerPorPelicula(Pelicula pelicula)
                                                            throws SQLException;
    
    /**
     * Retorna los alquileres que se efectuaron en una fecha específica
     * @param fecha Un objeto sql.Date representando la fecha sobre la cual se
     * quiere consultar.
     * @return Una colección de objetos Alquiler realizados en ese día ó null en 
     * caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Alquiler> obtenerAlquilerPorFecha(Date fecha)
                                                            throws SQLException;
    
    /**
     * Retorna los alquileres que se efectuaron por una cantidad específica de
     * días
     * @param dias La cantidad de días sobre las que se quiere realizar la
     * consulta
     * @return Una colección de objetos Alquiler realizados por esa cantidad 
     * días ó null en caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Alquiler> obtenerAlquilerPorDias(int dias)
                                                            throws SQLException;
    
    /**
     * Retorna los alquileres que se efectuaron bajo una promoción en específico
     * @param promocion Un objeto Promoción que tenga los parámetros sobre los
     * que se quiere realizar la consulta
     * @return Una colección de objetos Alquiler realizados bajo esa promoción ó
     * null en caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Alquiler> obtenerAlquilerPorPromocion(Promocion promocion)
                                                            throws SQLException;
    
    /**
     * Retorna un alquiler bajo una clave primaria única
     * @param pk Un objeto AlquilerPK que representa una clave primaria única
     * compuesta por el idPelicula, idCliente y la fecha en la que se realizó el
     * alquiler
     * @return Un objeto Alquiler que tiene esa clave primaria única ó null en 
     * caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Alquiler obtenerAlquilerPorClavePrimaria(AlquilerPK pk)
                                                            throws SQLException;
    
    /**
     * Inserta una fila en la tabla Alquiler con las características indicadas
     * (de ser posible)
     * @param alquiler Un objeto Alquiler que representa la fila a ser insertada
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void crearOActualizarAlquiler(Alquiler alquiler) throws SQLException;
    
    /**
     * Dado un objeto Alquiler, borra la fila equivalente en la base de datos.
     * @param alquiler Un objeto de tipo Alquiler que tenga las 
     * características deseadas.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void borrarAlquiler(Alquiler alquiler) throws SQLException;
    
}

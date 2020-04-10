/*
 * Interfaz que determina el comportamiento que debe tener un DAO que acceda
 * a la tabla Genero en la base de datos
 */
package com.domain.DAO;

import com.domain.hibernate.DTO.Genero;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public interface GeneroDAO {
    
    /**
     * Retorna todos los géneros registrados en la base de datos.
     * @return Una colección de objetos Genero representando todas las entradas
     * de la tabla genero.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Genero> obtenerTodosLosGeneros() throws SQLException;
    
    /**
     * Retorna un genero según su id.
     * @param id ID único del genero buscado.
     * @return Un objeto Genero según el criterio de búsqueda ó null en el 
     * caso de que no hayan coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Genero obtenerGeneroPorId(int id) throws SQLException;
    
    /**
     * Retorna una colección de géneros por su descripción ó las primeras letras
     * de ésta.
     * @param descripcion El nombre del género en cuestión
     * @return Uno ó varios objetos Genero según el criterio de búsqueda ó
     * null en el caso de que no hayan coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Genero> obtenerGeneroPorDescripcion(String descripcion)
                                                            throws SQLException;
    
    /**
     * Inserta un género nuevo en la base de datos ó modifica uno ya existente.
     * @param genero Un objeto Genero con las características a insertar en
     * la base de datos.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void crearOActualizarGenero(Genero genero) throws SQLException;
    
    /**
     * Dado un objeto Genero, borra la fila equivalente en la base de datos.
     * @param genero Un objeto de tipo Genero que tenga las 
     * características deseadas.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void borrarGenero(Genero genero) throws SQLException;
    
}

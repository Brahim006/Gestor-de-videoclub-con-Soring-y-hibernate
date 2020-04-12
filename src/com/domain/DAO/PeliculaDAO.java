/*
 * Interfaz que determina el comportamiento que debe tener un DAO que acceda
 * a la tabla Pelicula en la base de datos
 */
package com.domain.DAO;

import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Pelicula;
import com.domain.hibernate.DTO.Genero;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public interface PeliculaDAO {
    
    /**
     * Retorna todas las películas registradas en la base de datos.
     * @return Una colección de objetos Pelicula que representa a todas las 
     * entradas de la tabla de películas
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Pelicula> obtenerTodasLasPeliculas() throws SQLException;
    
    /**
     * Retorna una película desde la base de datos según su ID.
     * @param id ID único de la película
     * @return Un objeto Pelicula que cumple con los criterios de búsqueda ó
     * null en caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Pelicula obtenerPeliculaPorId(int id) throws SQLException;
    
    /**
     * Retorna una película desde la base de datos según las primeras letras de
     * su título coincidan con el título pedido.
     * @param titulo Título ó primeras letras del título que se deseen buscar.
     * @return Una colección de objetos Pelicula que cumple con los criterios de
     * búsqueda ó null en caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Pelicula> obtenerPeliculaPorTitulo(String titulo)
                                                            throws SQLException;
    
    /**
     * Retorna todas las películas pertenecientes a un género específico.
     * @param genero Un objeto Genero representando al género sobre el cual se
     * quiere consultar sus películas.
     * @return Una colección de objetos Pelicula que cumple con los criterios de
     * búsqueda ó null en caso de que no haya coincidencias.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Pelicula> obtenerPeliculaPorGenero(Genero genero) 
                                                            throws SQLException;
    
    /**
     * Inserta una película en la base de datos.
     * @param pelicula Objeto Pelicula que representa la fila a ser insertada.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void crearOActualizarPelicula(Pelicula pelicula) throws SQLException;
    
    /**
     * Retorna todas las películas que haya alquilado cierto cliente
     * @param cliente Un objeto Cliente sobre la cual se quiera consultar
     * todas las películas que tiene alquiladas actualmente.
     * @return Una colección conteniendo todas las películas que cumplan el
     * criterio de búsqueda ó null en caso de que no hayan coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Pelicula> obtenerPeliculasPorCliente(Cliente cliente)
                                                            throws SQLException;
    
    /**
     * Retorna una colección de objetos Pelicula que tienen una asociación
     * many-to-one con una fila de la tabla Cliente.
     * @param genero Un objeto de tipo Genero al cual pertenecen las películas
     * retornadas
     * @return Una colección conteniendo todas las películas que cumplan el
     * criterio de búsqueda ó null en caso de que no hayan coincidencias.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Pelicula> obtenerPeliculasPorGenero(Genero genero)
                                                            throws SQLException;
    
    /**
     * Dado un objeto Pelicula, borra la fila equivalente en la base de datos.
     * @param pelicula Un objeto de tipo Pelicula que tenga las 
     * características deseadas.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void borrarPelicula(Pelicula pelicula) throws SQLException;
    
}

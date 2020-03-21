/*
 * Interfaz que determina el comportamiento del Facade
 */
package com.domain.Facade;

import com.domain.hibernateDTO.*;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public interface Facade {
    
    /**
     * Retorna un cliente según su ID
     * @param id - ID que identifica al cliente.
     * @return - Un objeto Cliente según el ID especificado o null en caso de
     * que dicho cliente no exista.
     */
    public Cliente obtenerClientePorId(int id);
    
    /**
     * Retorna uno ó más clientes cuyos nombres sean iguales ó coincidan en sus
     * primeras letras con el nombre especificado.
     * @param nombre - Nombre ó primeras letras del nombre del/los cliente/s
     * solicitado/s.
     * @return - Una colección de clientes que cumplen con el criterio de
     * búsqueda ó null si no hubo coincidencias.
     */
    public Collection<Cliente> obtenerClientePorNombre(String nombre);
    
    /**
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente - un objeto CLiente que tenga las características deseadas
     * @return - Un booleano informando el éxito de la operación.
     */
    public boolean crearCliente(Cliente cliente);
    
    /**
     * Retorna una Promoción según su ID
     * @param id - ID única de la promoción
     * @return - Un objeto Promocion que cumpla con el criterio de búsqueda ó
     * null si no hubo coincidencias.
     */
    public Promocion obtenerPromocionPorId(int id);
    
    /**
     * Retorna todas las promociones que hagan un descuento con el monto
     * especificado.
     * @param monto - Monto de descuento sobre el cual se quieren buscar
     * promociones.
     * @return - Una colección con todas las promociones que cumplan con el
     * criterio de búsqueda ó null en el caso de que no haya coincidencias.
     */
    public Collection<Promocion> obtenerPromocionPorMonto(int monto);
    
    /**
     * Retorna todas las promociones que hagan un descuento que se encuentre en
     * el rango especificado.
     * @param min - Monto mínimo
     * @param max - Monto máximo
     * @return - Una colección con todas las promociones que cumplan con el
     * criterio de búsqueda ó null en el caso de que no haya coincidencias.
     */
    public Collection<Promocion> obtenerPromocionPorRango(int min, int max);
    
    /**
     * Crea una promoción y la añade a la 
     * @param promocion - Un objeto de tipo Promocion que tenga las 
     * características deseadas.
     * @return Un booleano informando el éxito de la operación.
     */
    public boolean crearPromocion(Promocion promocion);
    
    /**
     * Retorna un genero según su id.
     * @param id - ID único del genero buscado.
     * @return - Un objeto Genero según el criterio de búsqueda ó null en el 
     * caso de que no hayan coincidencias.
     */
    public Genero obtenerGeneroPorId(int id);
    
    /**
     * Retorna una colección de géneros por su descripción ó las primeras letras
     * de ésta.
     * @param descripcion
     * @return - Uno ó varios objetos Genero según el criterio de búsqueda ó
     * null en el caso de que no hayan coincidencias.
     */
    public Collection<Genero> obtenerGeneroPorDescripcion(String descripcion);
    
    /**
     * Inserta un género nuevo en la base de datos.
     * @param genero - Un objeto Genero con las características a insertar en
     * la base de datos.
     * @return Un booleano informando el éxito de la operación.
     */
    public boolean crearGenero(Genero genero);
    
    /**
     * Retorna una película desde la base de datos según su ID.
     * @param id - ID único de la película
     * @return Un objeto Pelicula que cumple con los criterios de búsqueda ó
     * null en caso de que no haya coincidencias.
     */
    public Pelicula obtenerPeliculaPorId(int id);
    
    /**
     * Retorna una película desde la base de datos según las primeras letras de
     * su título coincidan con el título pedido.
     * @param titulo - Título ó primeras letras del título que se deseen buscar.
     * @return Una colección de objetos Pelicula que cumple con los criterios de
     * búsqueda ó null en caso de que no haya coincidencias.
     */
    public Collection<Pelicula> obtenerPeliculaPorTitulo(String titulo);
    
    /**
     * Inserta una película en la base de datos.
     * @param pelicula - Objeto Pelicula que representa la fila a ser insertada.
     * @return Un booleano informando el éxito de la operación.
     */
    public boolean crearPelicula(Pelicula pelicula);
    
}

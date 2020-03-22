/*
 * Interfaz que determina el comportamiento del Facade
 */
package com.domain.Facade;

import com.domain.hibernate.DTO.Genero;
import com.domain.hibernate.DTO.Alquiler;
import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Promocion;
import com.domain.hibernate.DTO.AlquilerPK;
import com.domain.hibernate.DTO.Pelicula;
import java.sql.SQLException;
import java.util.Collection;
import java.sql.Date;

/**
 *
 * @author Brahim
 */
public interface Facade {
    
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
     * Inserta un nuevo cliente en la base de datos.
     * @param cliente un objeto CLiente que tenga las características deseadas
     * @return Un booleano informando el éxito de la operación.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public boolean crearCliente(Cliente cliente) throws SQLException;
    
    /**
     * Retorna una Promoción según su ID
     * @param id ID única de la promoción
     * @return Un objeto Promocion que cumpla con el criterio de búsqueda ó
     * null si no hubo coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Promocion obtenerPromocionPorId(int id) throws SQLException;
    
    /**
     * Retorna todas las promociones que hagan un descuento con el monto
     * especificado.
     * @param monto Monto de descuento sobre el cual se quieren buscar
     * promociones.
     * @return Una colección con todas las promociones que cumplan con el
     * criterio de búsqueda ó null en el caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Promocion> obtenerPromocionPorMonto(int monto) 
                                                            throws SQLException;
    
    /**
     * Retorna todas las promociones que hagan un descuento que se encuentre en
     * el rango especificado.
     * @param min Monto mínimo
     * @param max Monto máximo
     * @return Una colección con todas las promociones que cumplan con el
     * criterio de búsqueda ó null en el caso de que no haya coincidencias.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Promocion> obtenerPromocionPorRango(int min, int max)
                                                            throws SQLException;
    
    /**
     * Crea una promoción y la añade a la 
     * @param promocion Un objeto de tipo Promocion que tenga las 
     * características deseadas.
     * @return Un booleano informando el éxito de la operación.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public boolean crearPromocion(Promocion promocion) throws SQLException;
    
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
     * Inserta un género nuevo en la base de datos.
     * @param genero Un objeto Genero con las características a insertar en
     * la base de datos.
     * @return Un booleano informando el éxito de la operación.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public boolean crearGenero(Genero genero) throws SQLException;
    
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
     * Inserta una película en la base de datos.
     * @param pelicula Objeto Pelicula que representa la fila a ser insertada.
     * @return Un booleano informando el éxito de la operación.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public boolean crearPelicula(Pelicula pelicula) throws SQLException;
    
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
     * @param pk Un objeto AlquilerPK que representa una clave primaria única
     * compuesta por el idPelicula, idCliente y la fecha en la que se realizó el
     * alquiler
     * @param promocion Un objeto Promocion que representa la promoción
     * aplicada al efectuarse el alquiler ó null en el caso de que no se
     * apliquen descuentos
     * @param dias La cantidad de días sobre las que se realiza el alquiler
     * @return Un booleano informando el éxito de la operación
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public boolean crearAlquiler(AlquilerPK pk, Promocion promocion, int dias)
                                                            throws SQLException;
    
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
     * Retorna todas las promociones de las que es beneficiario un cliente en
     * específico.
     * @param cliente Un objeto Cliente representando al cliente sobre el cual
     * se quiere realizar la consulta.
     * @return Una colección conteniendo todas las promociones de las cuales es
     * beneficiario el clienteó null en caso de que no tenga.
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Promocion> obtenerPromocionesPorCliente(Cliente cliente)
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
    
}

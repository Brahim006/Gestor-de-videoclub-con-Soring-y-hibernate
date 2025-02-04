/*
 * Interfaz que determina el comportamiento que debe tener un DAO que acceda
 * a la tabla Promocion en la base de datos
 */
package com.domain.DAO;

import com.domain.hibernate.DTO.Cliente;
import com.domain.hibernate.DTO.Promocion;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author Brahim
 */
public interface PromocionDAO {
    
    /**
     * Retorna todas las promociones registradas en la base de datos.
     * @return Una colección de objetos Promocion que representa todas las
     * entradas de la tabla de promociones.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Promocion> obtenerTodasLasPromociones() 
                                                        throws SQLException;
    
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
     * Retorna una promoción según su descripción.
     * @param descripcion Una cadena que represente la descripción de las
     * promociones buscadas ó sus primeras letras.
     * @return Un objeto Promocion que cumpla con el criterio de búsqueda ó
     * null si no hubo coincidencias.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public Collection<Promocion> 
         obtenerPromocionPorDescripcion(String descripcion) throws SQLException;
    
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
     * @throws java.sql.SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void crearOActualizarPromocion(Promocion promocion) 
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
     * Dado un objeto Promocion, borra la fila equivalente en la base de datos.
     * @param promocion Un objeto de tipo Promocion que tenga las 
     * características deseadas.
     * @throws SQLException Arroja una excepción al tener problemas al
     * conectarse a la base de datos ó al realizar consultas/modificaciones.
     */
    public void borrarPromocion(Promocion promocion) throws SQLException;
    
}

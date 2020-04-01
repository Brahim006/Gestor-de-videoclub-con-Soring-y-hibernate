/*
 * Clase que representa una clave primaria compuesta para el mapeo de la tabla
 * alquiler en el cual la combinación id_pelicula, id_cliente y fecha es
 * única
 */
package com.domain.hibernate.DTO;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.sql.Date;

/**
 *
 * @author User
 */
@Embeddable
public class AlquilerPK implements Serializable{
    
    @Column(name="id_pelicula")
    private int idPelicula;
    
    @Column(name="id_cliente")
    private int idCliente;
    
    @Column(name="fecha")
    private Date fecha;
    
    /**
     * Crea un objeto AlquilerPK con todos sus atributos por defecto
     */
    public AlquilerPK(){}
    
    /**
     * Crea un objeto AlquilerPK seteando todos sus atributos
     * @param idPelicula ID de la película que se alquiló.
     * @param idCliente ID del cliente que alquiló la película.
     * @param fecha Objeto sql.Date que representa la fecha en la que se realizó
     * el alquiler.
     */
    public AlquilerPK(int idPelicula, int idCliente, Date fecha){
        
        this.idCliente = idCliente;
        this.idPelicula = idPelicula;
        this.fecha = fecha;
        
    }

    @Override
    public String toString(){
        
        return "ID de la película: " + idPelicula + ", ID del cliente: " +
                idCliente + ", fecha del alquiler: " + fecha.toString();
        
    }

    @Override
    public boolean equals(Object obj) {
        
        AlquilerPK pk = (AlquilerPK)obj;
        
        return 
                this.idPelicula == pk.getIdPelicula() &&
                this.idCliente == pk.getIdCliente() &&
                this.fecha.equals(pk.fecha);
        
    }
    
    
    
    // Setters y Getters

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}

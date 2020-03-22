/*
 * Clase que representa una clave primaria compuesta para el mapeo de la tabla
 * alquiler en el cual la combinación id_pelicula, id_cliente y fecha es
 * única
 */
package com.domain.hibernate.DTO;

import javax.persistence.Embeddable;
import javax.persistence.Column;
import java.sql.Date;

/**
 *
 * @author User
 */
@Embeddable
public class AlquilerPK {
    
    @Column(name="id_pelicula")
    private int idPelicula;
    
    @Column(name="id_cliente")
    private int idCliente;
    
    @Column(name="fecha")
    private Date fecha;
    
    public AlquilerPK(){} // Constructor para que el objeto sea un Beans
    
    public AlquilerPK(int dPelicula, int idCliente, Date fecha){
        
        this.idCliente = idCliente;
        this.idPelicula = idPelicula;
        this.fecha = fecha;
        
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

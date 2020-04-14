/*
 * Clase que mappea a la tabla "alquiler" en la base de datos
 */
package com.domain.hibernate.DTO;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Brahim
 */
@Entity
@Table(name="alquiler")
public class Alquiler implements Serializable {
    
    /**
     * Crea un objeto Alquiler con todos sus atributos por defecto.
     */
    public Alquiler(){}
    
    /**
     * Crea un objeto Alquiler que recibe un objeto AlquilerPK que representa
     * una clave primaria compuesta.
     * @param pk Objeto AlquilerPK que encapsula atributos correspondientes a 
     * una clave primeria compuesta única. 
     * @param dias Cantidad de días por los que se efectúa el alquiler.
     * @param idPromocion ID que representa a la promoción que afecta a este 
     * alquiler ó null en caso de que no se apliquen descuentos.
     */
    public Alquiler(AlquilerPK pk, int dias, int idPromocion){
        
        this.pk = pk;
        this.dias = dias;
        this.idPromocion = idPromocion;
        
    }
    
    /**
     * Crea un objeto Alquiler que crea su propia clave primaria compuesta única
     * @param idPelicula ID de la película que se alquiló.
     * @param idCliente ID del cliente que alquiló la película.
     * @param fecha Objeto sql.Date que representa la fecha en la que se realizó
     * el alquiler.
     * @param dias Cantidad de días por los que se efectúa el alquiler.
     * @param idPromocion ID que representa a la promoción que afecta a este 
     * alquiler ó null en caso de que no se apliquen descuentos.
     */
    public Alquiler(int idPelicula, int idCliente, Date fecha, int dias, 
                                                               int idPromocion){
        
        this(new AlquilerPK(idPelicula, idCliente, fecha), dias, idPromocion);
        
    }
    
    @Id
    private AlquilerPK pk;

    @Column(name="dias")
    private int dias;
    
    @Column(name="id_promocion")
    private int idPromocion;
    
    @Override
    public String toString(){
        
        return pk.toString() + ", ID de la promoción: " + idPromocion +
                ", días de alquiler: " + dias;
        
    }

    @Override
    public boolean equals(Object obj) {
        
        return (obj != null) ? pk.equals(((Alquiler)obj).pk) : false ;
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.pk);
        hash = 79 * hash + this.dias;
        hash = 79 * hash + this.idPromocion;
        return hash;
    }
    
    // Setters y Getters

    public AlquilerPK getPk() {
        return pk;
    }

    public void setPk(AlquilerPK pk) {
        this.pk = pk;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }
    
}

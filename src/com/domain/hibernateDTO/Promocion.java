/*
 * Clase que mappea a la tabla "promocion" en la base de datos
 */
package com.domain.hibernateDTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 *
 * @author User
 */
@Entity
@Table(name="promocion")
public class Promocion {
    
    @Id
    @Column(name="id_promocion")
    private int idPromocion;
    
    @Column(name="descuento")
    private int descuento;
    
    @Column(name="descripcion")
    private String descripcion;

    // Setters y Getters

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}

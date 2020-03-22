/*
 * Clase que mappea a la tabla "promocion" en la base de datos
 */
package com.domain.hibernateDTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.ArrayList;

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
    
    // JoinTable para encapsular la relación con la tabla Cliente
    @ManyToMany
    @JoinTable(
            name="alquiler",
            joinColumns = { @JoinColumn(name="id_promocion") },
            inverseJoinColumns = { @JoinColumn(name="id_cliente") })
    private Collection<Cliente> clientes =  new ArrayList<>();

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

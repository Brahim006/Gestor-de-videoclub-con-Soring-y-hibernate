/*
 * Clase que mappea a la tabla "promocion" en la base de datos
 */
package com.domain.hibernate.DTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
@Entity
@Table(name="promocion")
public class Promocion {
    
    /**
     * Crea un objeto Promocion con todos sus atributos por defecto.
     */
    public Promocion(){}
    
    /**
     * Crea un objeto Promocion teniendo encuenta sus asociaciones a otras 
     * tablas en la base de datos.
     * @param idPromocion ID de la promoción que funge como clave primaria en la 
     * base de datos.
     * @param descuento Cantidad neta que se descuenta del precio del alquiler.
     * @param descripcion Descripción del descuento.
     */
    public Promocion(int idPromocion, int descuento, String descripcion){
        
        this.idPromocion = idPromocion;
        this.descuento = descuento;
        this.descripcion = descripcion;
        
    }
    
    /**
     * Crea un objeto Promocion teniendo encuenta sus asociaciones a otras 
     * tablas en la base de datos.
     * @param idPromocion ID de la promoción que funge como clave primaria en la 
     * base de datos.
     * @param descuento descripcion Cantidad neta que se descuenta del precio 
     * del alquiler.
     * @param descripcion Descripción del descuento.
     * @param clientes Una colección de objetos Cliente cuya tabla en la base de
     * datos representa una relacion many-to-many a través de la tabla Alquiler.
     */
    public Promocion(int idPromocion, int descuento, String descripcion, 
                                                Collection<Cliente> clientes){
        
        this(idPromocion, descuento, descripcion);
        this.clientes = clientes;
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Override
    public String toString(){
        
        return "ID de la promocion: " + idPromocion + ", descripción: " +
                ", descuento: " + descuento + "$";
                
    }

    @Override
    public boolean equals(Object obj) {
        return this.idPromocion == ((Promocion)obj).getIdPromocion();
    }
    
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

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }
    
}

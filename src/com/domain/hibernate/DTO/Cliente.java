/*
 * Clase que mappea a la tabla "cliente" en la base de datos
 */
package com.domain.hibernate.DTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="cliente")
public class Cliente {
    
    /**
     * Crea un objeto Cliente con todos sus parámetros seteados por defecto.
     */
    public Cliente(){}
    
    /**
     * Crea un objeto cliente que no está asociado a otras tablas
     * @param idCliente Número único que identifica al cliente y fonge como
     * clave primaria en la base de datos.
     * @param nombre Nombre del cliente, no necesariamente único.
     */
    public Cliente(int idCliente, String nombre){
        
        this.idCliente = idCliente;
        this.nombre = nombre;
        
    }
    
    /**
     * Crea un objeto Cliente teniendo encuenta sus asociaciones a otras tablas
     * en la base de datos.
     * @param idCliente Número único que identifica al cliente y fonge como
     * clave primaria en la base de datos.
     * @param nombre Nombre del cliente, no necesariamente único.
     * @param peliculas Colección de objetos Pelicula que se relacionan en la
     * base de datos a la tabla Cliente.
     * @param promociones Colección de objetos Promocion que se relacionan en la
     * base de datos a la tabla Cliente.
     */
    public Cliente(int idCliente, String nombre, Collection<Pelicula> peliculas,
            Collection<Promocion> promociones){
        
        this(idCliente, nombre);
        this.peliculas = peliculas;
        this.promociones = promociones;
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_cliente")
    private int idCliente;
    
    @Column(name="nombre")
    private String nombre;
    
    // Join table para encapsular la relación con la tabla pelicula
    @ManyToMany
    @JoinTable(
        name="alquiler",
        joinColumns = { @JoinColumn(name="id_cliente") },
        inverseJoinColumns = { @JoinColumn(name="id_pelicula") })
    private Collection<Pelicula> peliculas = new ArrayList<>();
    
    // Join table para encapsular la relación con la tabla Promocion
    @ManyToMany
    @JoinTable(
            name="alquiler",
            joinColumns = { @JoinColumn(name="id_cliente") },
            inverseJoinColumns = { @JoinColumn(name="id_promocion") })
    private Collection<Promocion> promociones = new ArrayList<>();
    
    @Override
    public String toString(){
        
        return idCliente + "- " + nombre;
        
    }

    @Override
    public boolean equals(Object obj) {
        return this.idCliente == ((Cliente)obj).getIdCliente();
    }
    
    // Setters y Getters

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Collection<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public Collection<Promocion> getPromociones() {
        return promociones;
    }

    public void setPromociones(Collection<Promocion> promociones) {
        this.promociones = promociones;
    }
    
}

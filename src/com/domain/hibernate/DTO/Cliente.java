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
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author User
 */
@Entity
@Table(name="cliente")
public class Cliente {
    
    @Id
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
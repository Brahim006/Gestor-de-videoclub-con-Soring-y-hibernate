/*
 * Clase que mappea a la tabla "genero" en la base de datos
 */
package com.domain.hibernateDTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author User
 */
@Entity
@Table(name="genero")
public class Genero {
    
    @Id
    @Column(name="id_genero")
    private int idGenero;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany
    @JoinColumn(name="id_pelicula")
    private Collection<Pelicula> peliculas = new ArrayList<>();
    
    //Setters y Getters

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Collection<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    
}

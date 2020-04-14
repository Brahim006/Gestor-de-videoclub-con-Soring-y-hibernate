/*
 * Clase que mappea a la tabla "genero" en la base de datos
 */
package com.domain.hibernate.DTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
@Entity
@Table(name="genero")
public class Genero implements Serializable {
    
    /**
     * Crea un objeto Genero con todos sus parámetros seteados por defecto.
     */
    public Genero(){}
    
    /**
     * Crea un objeto Genero que no está asociado a otras tablas.
     * @param idGenero ID del género que funge como clave primaria en la base de
     * datos.
     * @param descripcion Descripción del género. 
     */
    public Genero(int idGenero, String descripcion){
        
        this.idGenero = idGenero;
        this.descripcion = descripcion;
        
    }
    
    /**
     * Crea un objeto Genero teniendo encuenta sus asociaciones a otras tablas
     * en la base de datos.
     * @param idGenero ID del género que funge como clave primaria en la base de
     * datos.
     * @param descripcion Descripción del género. 
     * @param peliculas Colección de objetos Pelicula que se relacionan con la
     * fila que representa a este género en la base de datos.
     */
    public Genero(int idGenero, String descripcion,
                                            Collection<Pelicula> peliculas){
        
        this(idGenero, descripcion);
        this.peliculas = peliculas;
        
    }
    
    @Id
    @Column(name="id_genero")
    private int idGenero;
    
    @Column(name="descripcion")
    private String descripcion;
    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="id_pelicula")
    private Collection<Pelicula> peliculas = new ArrayList<>();
    
    @Override
    public String toString(){
        
        return idGenero + "- " + descripcion;
        
    }

    @Override
    public boolean equals(Object obj) {
        return this.idGenero == ((Genero)obj).getIdGenero();
    }
    
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

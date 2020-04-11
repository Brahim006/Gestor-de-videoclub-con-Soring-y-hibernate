/*
 * Clase que mappea a la tabla "pelicula" en la base de datos
 */
package com.domain.hibernate.DTO;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author Brahim
 */
@Entity
@Table(name="pelicula")
public class Pelicula {
    
    /**
     * Crea un objeto Pelicula con todos sus valores por defecto.
     */
    public Pelicula(){}
    
    /**
     * Crea un objeto Pelicula que no está asociado a otras tablas
     * @param idPelicula ID de la película que funge como clave primaria en la 
     * base de datos.
     * @param titulo Título de la película.
     * @param copias Cantidad de copias en total.
     */
    public Pelicula(int idPelicula, String titulo, int copias){
        
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.copias = copias;
        
    }
    
    /**
     * Crea un objeto Pelicula teniendo encuenta sus asociaciones a otras tablas
     * en la base de datos.
     * @param idPelicula ID de la película que funge como clave primaria en la 
     * base de datos.
     * @param titulo Título de la película.
     * @param copias Cantidad de copias en total.
     * @param genero Un objeto género que representa a la fila de la tabla
     * Genero que provee la clave foránea en la base de datos.
     * @param clientes Una colección de objetos Cliente cuya tabla en la base de
     * datos representa una relacion many-to-many a través de la tabla Alquiler.
     */
    public Pelicula(int idPelicula, String titulo, int copias, Genero genero, 
                                                Collection<Cliente> clientes){
        
        this(idPelicula, titulo, copias);
        this.genero = genero;
        this.clientes = clientes;
        
    }
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id_pelicula")
    private int idPelicula;
    
    @ManyToOne
    @JoinColumn(name="id_genero")
    private Genero genero;
    
    @Column(name="titulo")
    private String titulo;
    
    @Column(name="copias")
    private int copias;
    
    // Join table para encapsular la relación con la tabla cliente
    @ManyToMany
    @JoinTable(
        name="alquiler",
        joinColumns = { @JoinColumn(name="id_pelicula")},
        inverseJoinColumns = {@JoinColumn(name="id_cliente")})
    private Collection<Cliente> clientes = new ArrayList<>();
    
    @Override
    public String toString(){
        
        return idPelicula + "- " + titulo;
        
    }

    @Override
    public boolean equals(Object obj) {
        return this.idPelicula == ((Pelicula)obj).getIdPelicula();
    }
    
    // Setters y Getters

    public int getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(int idPelicula) {
        this.idPelicula = idPelicula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCopias() {
        return copias;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public Collection<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = clientes;
    }
    
}

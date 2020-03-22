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
import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author User
 */
@Entity
@Table(name="pelicula")
public class Pelicula {
    
    @Id
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

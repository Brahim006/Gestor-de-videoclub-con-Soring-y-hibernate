/*
 * Clase que mappea a la tabla "alquiler" en la base de datos
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
@Table(name="alquiler")
public class Alquiler {
    
    @Id
    private AlquilerPK pk;

    @Column(name="dias")
    private int dias;
    
    @Column(name="id_promocion")
    private int idPromocion;
    
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

package com.itep.cabure.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author fernanda.franklin
 * version 1.0
 * since 22/10/2014
 */
@Embeddable
public class ValidaPK implements Serializable{
    @SequenceGenerator(name = "VALIDA_SEQ", sequenceName = "VALIDA_VAL_PK_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALIDA_SEQ")
    @Column(name = "PK_VALIDA", nullable = false)
    public long idValida;
    
    @ManyToOne
    @JoinColumn(name = "PFK_USUA_VALIDA", referencedColumnName = "PK_USUA", nullable = false)
    public Usuario idUsuario;

    /**
     * @return the idValida
     */
    public long getIdValida() {
        return idValida;
    }

    /**
     * @param idValida the idValida to set
     */
    public void setIdValida(long idValida) {
        this.idValida = idValida;
    }

    /**
     * @return the idUsuario
     */
    public Usuario getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }   
}
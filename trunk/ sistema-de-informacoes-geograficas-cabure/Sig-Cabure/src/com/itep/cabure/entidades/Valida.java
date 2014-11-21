package com.itep.cabure.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 *
 * @author fernanda.franklin
 * version 1.0  
 * since 22/10/2014
 */
@Entity
@Table(name = "VALIDA")
public class Valida implements Serializable{

    @Id
    @SequenceGenerator(name = "VALIDA_SEQ", sequenceName = "VALIDA_PK_VALI_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALIDA_SEQ")
    @Column(name = "PK_VALIDA", nullable = false)
    private long idValida;
  
    @MapKey
    @OneToOne
    @JoinColumn(name = "PFK_USUA_VALIDA", referencedColumnName = "PK_USUA")
    private Usuario idUsuario;
    
    @Column(name = "VALI_FLAG")
    private int flag;
    
    @Column(name = "VALI_TOKEN")
    private String token;
   
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

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the linkValida to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the flag
     */
    public int getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(int flag) {
        this.flag = flag;
    }

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
}
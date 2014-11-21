package com.itep.cabure.entidades;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;

/**
 *
 * @author fernanda.franklin
 * @since 06/10/2014
 */
@Entity
@Table(name = "PUBLICACAO")
public class Publicacao implements Serializable{

    @Id
    @SequenceGenerator(name = "PUBLICACAO_SEQ", sequenceName = "PUBLICACAO_PK_PUBLI_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUBLICACAO_SEQ")
    @Column(name = "PK_PUBLI", nullable = false)
    private long idPublicacao;

    // Contem a descrição da publicação feito pelo usuário
    @Column(name = "PUBL_DS_ARQUIVO", nullable = false, length = 255)
    private String descricaoPublicacao;

    // Contem as coordenadas de local onde foi feito a publicação
    @Column(name = "PUBL_MD_COORDENADAS", length = 255)
    private String coordPublicacao;

    /**
     * @return the descricaoPublicacao
     */
    public String getDescricaoPublicacao() {
        return descricaoPublicacao;
    }

    /**
     * @param descricaoPublicacao the descricaoPublicacao to set
     */
    public void setDescricaoPublicacao(String descricaoPublicacao) {
        this.descricaoPublicacao = descricaoPublicacao;
    }

    /**
     * @return the coordPublicacao
     */
    public String getCoordPublicacao() {
        return coordPublicacao;
    }

    /**
     * @param coordPublicacao the coordPublicacao to set
     */
    public void setCoordPublicacao(String coordPublicacao) {
        this.coordPublicacao = coordPublicacao;
    }

}

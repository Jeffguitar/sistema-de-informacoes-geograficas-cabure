package com.itep.cabure.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Descrição da entidade referente a pessoa
 *
 * @author fernanda.franklin
 * @version 2.0?
 * @since 29/09/2014
 */
@Entity
@Table(name = "PESSOA_PUBLICACAO")
public class PessoaPublicacao {

    @EmbeddedId
    public PessoaPublicacaoPK idsPessoaPubli;

    @Column(name = "PEPU_DT_DATA_PUBLICADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataPublicada;

    /**
     * @return the dataPublicada
     */
    public Calendar getDataPublicada() {
        return dataPublicada;
    }

    /**
     * @param dataPublicada the dataPublicada to set
     */
    public void setDataPublicada(Calendar dataPublicada) {
        this.dataPublicada = dataPublicada;
    }

    /**
     * @return the idsPessoaPubli
     */
    public PessoaPublicacaoPK getIdsPessoaPubli() {
        return idsPessoaPubli;
    }

    /**
     * @param idsPessoaPubli the idsPessoaPubli to set
     */
    public void setIdsPessoaPubli(PessoaPublicacaoPK idsPessoaPubli) {
        this.idsPessoaPubli = idsPessoaPubli;
    }


}
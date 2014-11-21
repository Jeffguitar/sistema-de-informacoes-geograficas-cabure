package com.itep.cabure.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Contem os códigos que permite identificar as
 * publicações feitas pelas empresas
 * 
 * @author fernanda.franklin
 * @since 06/10/2014
 * @version 2.0? 
 */
@Entity
@Table(name = "PUBLICACAO_EMPRESA")
public class PublicacaoEmpresa {

    @EmbeddedId
    public PublicacaoEmpresaPK idsPubliEmpresa;

    @Column(name = "PUEM_DT_DATA_PUBLICADA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataPublicada; // A data no qual a publicação foi feita

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
     * @return the idsPubliEmpresa
     */
    public PublicacaoEmpresaPK getIdsPubliEmpresa() {
        return idsPubliEmpresa;
    }

    /**
     * @param idsPubliEmpresa the idsPubliEmpresa to set
     */
    public void setIdsPubliEmpresa(PublicacaoEmpresaPK idsPubliEmpresa) {
        this.idsPubliEmpresa = idsPubliEmpresa;
    }

}

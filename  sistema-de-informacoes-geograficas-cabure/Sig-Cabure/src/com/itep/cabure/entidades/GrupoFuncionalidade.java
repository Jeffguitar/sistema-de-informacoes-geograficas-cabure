package com.itep.cabure.entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author macario.granja
 * @since 01/09/2014
 * @version 1.0?
 */
@Entity
@Table(name = "GRUPO_FUNCIONALIDADE")
public class GrupoFuncionalidade implements Serializable {

    @EmbeddedId
    public GrupoFuncionalidadePK idsGrupoFuncionalidade;

    @Column(name = "GRFU_DT_DATA_INSERCAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInsercao;

    /**
     * @return the dataInsercao
     */
    public Calendar getDataInsercao() {
        return dataInsercao;
    }

    /**
     * @param dataInsercao the dataInsercao to set
     */
    public void setDataInsercao(Calendar dataInsercao) {
        this.dataInsercao = dataInsercao;
    }

    /**
     * @return the idsGrupoFuncionade
     */
    public GrupoFuncionalidadePK getIdsGrupoFuncionade() {
        return idsGrupoFuncionalidade;
    }

    /**
     * @param idsGrupoFuncionalidade the idsGrupoFuncionade to set
     */
    public void setIdsGrupoFuncionade(GrupoFuncionalidadePK idsGrupoFuncionalidade) {
        this.idsGrupoFuncionalidade = idsGrupoFuncionalidade;
    }
}

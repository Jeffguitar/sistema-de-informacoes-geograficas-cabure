package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class GrupoFuncionalidadePK implements Serializable {

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "PFK_FUNC_GRUP", referencedColumnName = "PK_FUNC", nullable = false)
    public Funcionalidade idFuncionalidadeGrupo;

    @ManyToOne
    @JoinColumn(name = "PFK_GRUP_FUNC", referencedColumnName = "PK_GRUP", nullable = false)
    private Grupo idGrupoFuncionalidade;

    /**
     * @return the idGrupoFuncionalidade
     */
    public Grupo getIdGrupoFuncionalidade() {
        return idGrupoFuncionalidade;
    }

    /**
     * @param idGrupoFuncionalidade the idGrupoFuncionalidade to set
     */
    public void setIdGrupoFuncionalidade(Grupo idGrupoFuncionalidade) {
        this.idGrupoFuncionalidade = idGrupoFuncionalidade;
    }

    /**
     * @return the idFuncionalidadeGrupo
     */
    public Funcionalidade getIdFuncionalidadeGrupo() {
        return idFuncionalidadeGrupo;
    }

    /**
     * @param idFuncionalidadeGrupo the idFuncionalidadeGrupo to set
     */
    public void setIdFuncionalidadeGrupo(Funcionalidade idFuncionalidadeGrupo) {
        this.idFuncionalidadeGrupo = idFuncionalidadeGrupo;
    }

}

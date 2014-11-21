package com.itep.cabure.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fernanda.franklin
 * @since 07/10/2014
 * @version 2.0? DESCRIÇÃO: Contém todas as funcionalidades do sistema. Ela
 * permite verificar as funcionalidades para um determinado grupo de usuário.
 */
@Entity
@Table(name = "FUNCIONALIDADE")
public class Funcionalidade {

    @Id
    @SequenceGenerator(name = "FUNCIONALIDADE_SEQ", sequenceName = "FUNCIONALIDADE_PK_FUNC_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONALIDADE_SEQ")
    @Column(name = "PK_FUNC")
    public long idFuncionalidade; // Código que identifica uma determinado funcionalidade

    @Column(name = "FUNC_NM_NOME", nullable = false, length = 28)
    private String funcionalidade; // Determina o nome da funcionalidade

    @Column(name = "FUNC_DS_FUNC", length = 255)
    private String descricaoFunc; // Descreve o conteúdo da funcionalidade. Nao é obrigatório.

    /**
     * @return the funcionalidade
     */
    public String getFuncionalidade() {
        return funcionalidade;
    }

    /**
     * @param funcionalidade the funcionalidade to set
     */
    public void setFuncionalidade(String funcionalidade) {
        this.funcionalidade = funcionalidade;
    }

    /**
     * @return the descricaoFunc
     */
    public String getDescricaoFunc() {
        return descricaoFunc;
    }

    /**
     * @param descricaoFunc the descricaoFunc to set
     */
    public void setDescricaoFunc(String descricaoFunc) {
        this.descricaoFunc = descricaoFunc;
    }

    /**
     * @return the idFuncionalidade
     */
    public long getIdFuncionalidade() {
        return idFuncionalidade;
    }

    /**
     * @param idFuncionalidade the idFuncionalidade to set
     */
    public void setIdFuncionalidade(long idFuncionalidade) {
        this.idFuncionalidade = idFuncionalidade;
    }

}

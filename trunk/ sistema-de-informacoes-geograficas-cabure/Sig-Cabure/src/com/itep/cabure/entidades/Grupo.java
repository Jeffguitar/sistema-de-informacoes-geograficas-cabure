package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fernanda.franklin
 * @since 02/10/2014
 * @version 2.0? DESCRIÇÃO = registra os diferenteS grupoS de perfis que
 * permitem fazer o controle de permissões.
 */
@Entity
@Table(name = "GRUPO")
public class Grupo implements Serializable {

    @Id
    @SequenceGenerator(name = "GRUPO_SEQ", sequenceName = "GRUPO_PK_GRUP_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPO_SEQ")
    @Column(name = "PK_GRUP", nullable = false)
    private long grupo; // Código do grupo que permite verificar em que grupo pertence o usuário em fim  de controlar as permissões

    @Column(name = "GRUP_NM_NOME", nullable = false, length = 128)
    private String nomeGrupo; // Contem o nome dos perfis de acesso ao sistema. Esse campo é obrigatório pois auxilia no controle de acesso ao sistema e as permissões

    @Column(name = "GRUP_DS_GRUPO", length = 255)
    private String descricaoGrupo; // Descreve os perfis de cada grupo.

    /**
     * @return the grupo
     */
    public long getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(long grupo) {
        this.grupo = grupo;
    }

    /**
     * @return the nomeGrupo
     */
    public String getNomeGrupo() {
        return nomeGrupo;
    }

    /**
     * @param nomeGrupo the nomeGrupo to set
     */
    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    /**
     * @return the descricaoGrupo
     */
    public String getDescricaoGrupo() {
        return descricaoGrupo;
    }

    /**
     * @param descricaoGrupo the descricaoGrupo to set
     */
    public void setDescricaoGrupo(String descricaoGrupo) {
        this.descricaoGrupo = descricaoGrupo;
    }


}

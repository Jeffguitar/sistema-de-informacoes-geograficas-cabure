package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;

/**
 * Descrição da entidade referente a pessoa
 *
 * @author fernanda.franklin
 * @version 2.0?
 * @since 29/09/2014
 */
@Entity
@Table(name = "Pessoa")
public class Pessoa implements Serializable {

    @Id
    @Column(name = "PK_CPF_PESS", nullable = false, length = 16)
    private String cpf;

    @Column(name = "PESS_NM_NOME", nullable = false, length = 68)
    private String nome;

    @Column(name = "PESS_NM_ORGAO_EMISSOR", nullable = false, length = 16)
    private String orgaoEmissor;

    @Column(name = "PESS_NR_RG_PESSOA", nullable = false, length = 16)
    private String rg;

    /**
     * Relacionamento com a Tabela Grupo
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_GRUP_PESSOA", referencedColumnName = "PK_GRUP", nullable = false)
    private Grupo grupo; // Cï¿½digo do grupo que permite verificar em que grupo pertence o usuï¿½rio em fim  de controlar as permissï¿½es

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the orgaoEmissor
     */
    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    /**
     * @param orgaoEmissor the orgaoEmissor to set
     */
    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the grupo
     */
    public Grupo getGrupo() {
        return grupo;
    }

    /**
     * @param grupo the grupo to set
     */
    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}

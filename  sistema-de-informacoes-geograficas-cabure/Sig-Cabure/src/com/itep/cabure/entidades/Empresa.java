package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

/**
 * Descrição da entidade referente a Empresa, representa a tabela empreendedor que registra os
 * dados dos empreendedores que efetuam uma solicitação de licença
 *
 * @author macario.granja
 * @version 1.0?
 * @since 20/08/2014 
 */
@Entity
@Table(name = "EMPRESA")
public class Empresa implements Serializable {

    @Id
    @Column(name = "pk_cnpj_empr", nullable = false, length = 24)
    private String cnpj;

    @Column(name = "empr_nr_inscricao_uf", nullable = false, length = 16)
    private String inscricao;

    @Column(name = "empr_nm_razao_socail", nullable = false, length = 128)
    private String razaoSocial;

    /**
     * Relacionamento com a Tabela Grupo
     */
    @ManyToOne
    @JoinColumn(name = "FK_GRUP_EMPR", referencedColumnName = "PK_GRUP", nullable = false)
    private Grupo grupo;

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the inscricao
     */
    public String getInscricao() {
        return inscricao;
    }

    /**
     * @param inscricao the inscricao to set
     */
    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
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

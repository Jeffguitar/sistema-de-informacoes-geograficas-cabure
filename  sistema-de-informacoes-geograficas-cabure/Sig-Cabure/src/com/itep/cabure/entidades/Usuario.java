package com.itep.cabure.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Descrição da entidade referente aos usuarios que acessão o sistema, fornecendo parametros para autenticação.
 * 
 * @author macario.granja
 * @since 29/08/2014
 * @version 1.0?
 */
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @Column(name = "PK_USUA", nullable = false, length = 24)
    private String usuario;

    @Column(name = "USUA_CD_SENHA", nullable = false, length = 24)
    private String senha;
    
    /**
     * Relacionamento com a Tabela Grupo
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_GRUP_USUA", referencedColumnName = "PK_GRUP", nullable = false)
    private Grupo grupo;

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
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
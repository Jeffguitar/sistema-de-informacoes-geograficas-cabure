package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * Descrição da entidade Relatorio fornecendo parametros para controle. 
 *  
 * 
 * @author fernanda.franklin
 * @since 06/10/2014
 * @version 1.0?
 */
@Embeddable
public class PublicacaoEmpresaPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "PFK_CNPJ_EMPR_PUEM", referencedColumnName = "PK_CNPJ_EMPR", nullable = false)
    private Empresa idEmpresa; // Código que permite identificar o empreendedor. Esse código é incremental que permite a autonomia do banco

    @ManyToOne
    @JoinColumn(name = "PFK_PUBLI_PUEM", referencedColumnName = "PK_PUBLI", nullable = false)
    private Publicacao idPublicacao; // Contém o código da publicação , que identifica a publicação feita por uma dertminada empresa

    /**
     * @return the idEmpresa
     */
    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    /**
     * @param idEmpresa the idEmpresa to set
     */
    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    /**
     * @return the idPublicacao
     */
    public Publicacao getIdPublicacao() {
        return idPublicacao;
    }

    /**
     * @param idPublicacao the idPublicacao to set
     */
    public void setIdPublicacao(Publicacao idPublicacao) {
        this.idPublicacao = idPublicacao;
    }
}

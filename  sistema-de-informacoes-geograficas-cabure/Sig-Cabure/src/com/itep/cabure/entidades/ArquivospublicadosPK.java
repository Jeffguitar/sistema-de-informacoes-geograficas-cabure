package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 *
 * Classe que mapeia o auto relacionamento entre as Classes Arquivos publicados  
 *
 * @author fernanda.franklin
 * @since 06/10/2014
 * @version 1.0? 
 */
@Embeddable
public class ArquivospublicadosPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "PFK_ARQU_ARPU", referencedColumnName = "PK_ARQU", nullable = false)
    private Arquivos idArquivosPublicados; // código que permite identificar o arquivo publicado

    @ManyToOne
    @JoinColumn(name = "PFK_PUBLI_ARPU", referencedColumnName = "PK_PUBLI", nullable = false)
    private Publicacao idPublicacao; // Código que permite identificar a publicação correspondente a uma determinada publicação

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

    /**
     * @return the idArquivosPublicados
     */
    public Arquivos getIdArquivosPublicados() {
        return idArquivosPublicados;
    }

    /**
     * @param idArquivosPublicados the idArquivosPublicados to set
     */
    public void setIdArquivosPublicados(Arquivos idArquivosPublicados) {
        this.idArquivosPublicados = idArquivosPublicados;
    }
 

}

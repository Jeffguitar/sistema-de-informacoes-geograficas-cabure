package com.itep.cabure.entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * Descrição da entidade referente a pessoa
 *
 * @author fernanda.franklin
 * @version 2.0?
 * @since 29/09/2014
 */
@Embeddable
public class PessoaPublicacaoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "PFK_CPF_PESS_PEPU", referencedColumnName = "PK_CPF_PESS", nullable = false)
    private Pessoa idPessoa; // permite identificar quem foi a pessoa que fez uma determinada publicação

    @ManyToOne
    @JoinColumn(name = "PFK_PUBLI_PEPU", referencedColumnName = "PK_PUBLI", nullable = false)
    private Publicacao idPublicacao; // Contem o código que determina o dono da publicação

    public Pessoa getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Pessoa idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Publicacao getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Publicacao idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

}

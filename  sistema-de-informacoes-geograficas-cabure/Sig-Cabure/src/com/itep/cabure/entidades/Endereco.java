package com.itep.cabure.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.FetchType;

/**
 *
 * Descrição da entidade referente ao Endereco
 *
 * @author fernanda.franklin
 * @version 2.0?
 * @since 29/09/2014
 */
@Entity
@Table(name = "ENDERECO")
public class Endereco implements Serializable{

    @Id
    @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "ENDERECO_PK_ENDE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
    @Column(name = "PK_ENDE", nullable = false)
    public long idEndereco;

    @Column(name = "ENDE_NR_CEP", nullable = false, length = 16)
    private String cep;

    @Column(name = "ENDE_DS_COMPLEMENTO", length = 36)
    private String complemento;

    @Column(name = "ENDE_LOGRADOURO", nullable = false, length = 128)
    private String logradouro;

    @Column(name = "ENDE_NM_BAIRRO", nullable = false, length = 128)
    private String bairro;

    @Column(name = "ENDE_NR_NUMERO", nullable = false, length = 16)
    private int numero;

    @Column(name = "ENDE_MUNICIPIO", nullable = false, length = 28)
    private String municipio;

    @Column(name = "ENDE_NM_UF", nullable = false, length = 14)
    private String uf;

    /**
     * Relacionamento com a tabela pessoa
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CPF_PESS_ENDE", referencedColumnName = "PK_CPF_PESS")
    private Pessoa pessoa;

    /**
     * Relacionamento com a tabela empresa
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CNPJ_EMPR_ENDE", referencedColumnName = "PK_CNPJ_EMPR")
    private Empresa empresa;

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    /**
     * @return the empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
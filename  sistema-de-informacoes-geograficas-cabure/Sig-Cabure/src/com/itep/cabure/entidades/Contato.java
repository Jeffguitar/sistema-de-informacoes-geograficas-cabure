package com.itep.cabure.entidades;

import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 *
 * Descrição da entidade referente ao Contato, representa a tabela que contém os registros de
 * contatos dos usuários: telefone, fax,email.
 * 
 * @author macario.granja
 * @version 1.0?
 * @since 20/08/2014
 */
@Entity
@Table(name = "CONTATO")
public class Contato {

    @Id
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "CONTATO_PK_TELE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @Column(name = "PK_CONTA", nullable = false)
    private long idContato;

    @Column(name = "CONTA_CELULAR", length = 16)
    private String celular;

    @Column(name = "CONTA_TELEFONE", nullable = false, length = 16)
    private String telefoneEmpresa;

    @Column(name = "CONTA_EMAIL", nullable = false, length = 128)
    private String email;

    @Column(name = "CONTA_FAX", length = 16)
    private String fax;

    /**
     * Relacionamento com a tabela pessoa
     */
    @ManyToOne
    @JoinColumn(name = "FK_CPF_PESS_ENDE", referencedColumnName = "PK_CPF_PESS")
    private Pessoa pessoa;

    /**
     * Relacionamento com a tabela empresa
     */
    @ManyToOne
    @JoinColumn(name = "FK_CNPJ_EMPR_ENDE", referencedColumnName = "PK_CNPJ_EMPR")
    private Empresa empresa;

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the telefoneEmpresa
     */
    public String getTelefoneEmpresa() {
        return telefoneEmpresa;
    }

    /**
     * @param telefoneEmpresa the telefoneEmpresa to set
     */
    public void setTelefoneEmpresa(String telefoneEmpresa) {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
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

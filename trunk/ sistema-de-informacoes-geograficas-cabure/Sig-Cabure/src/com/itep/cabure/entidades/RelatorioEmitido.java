package com.itep.cabure.entidades;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Descri��o da entidade Relatorio fornecendo parametros para controle.
 * 
 * @author fernanda.franklin
 * @since 29/08/2014
 * @version 1.0?
 */
@Entity
@Table(name = "RELATORIO_EMITIDO")
public class RelatorioEmitido {

    @Id
    @Column(name = "PK_REEM", nullable = false, length = 16)
    private String codigoRelatorio; // C�dgio que identifica cada relat�rio emitido pelo sistema

    @Column(name = "REEM_OBJECT", nullable = false, length = 1024)
    private long shapeRelatorio; // Guarda os limites do empreendimento.

    @Column(name = "REEM_DT_DATA", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataEmissao; // Determina a data no qual foi gerado o relat�rio

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CPF_PESS_REEM", referencedColumnName = "PK_CPF_PESS")
    private Pessoa pessoa; // C�digo do usu�rio que gerou relat�rio no caso do empreendedor seja pessoa f�sica

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CNPJ_EMPR_REEM", referencedColumnName = "PK_CNPJ_EMPR")
    private Empresa empresa; // C�digo que permite identificar o empreendedor. Esse c�digo � incremental que permite a autonomia do banco

    /**
     * @return the codigoRelatorio
     */
    public String getCodigoRelatorio() {
        return codigoRelatorio;
    }

    /**
     * @param codigoRelatorio the codigoRelatorio to set
     */
    public void setCodigoRelatorio(String codigoRelatorio) {
        this.codigoRelatorio = codigoRelatorio;
    }

    /**
     * @return the shapeRelatorio
     */
    public long getShapeRelatorio() {
        return shapeRelatorio;
    }

    /**
     * @param shapeRelatorio the shapeRelatorio to set
     */
    public void setShapeRelatorio(long shapeRelatorio) {
        this.shapeRelatorio = shapeRelatorio;
    }

    /**
     * @return the dataEmissao
     */
    public Calendar getDataEmissao() {
        return dataEmissao;
    }

    /**
     * @param dataEmissao the dataEmissao to set
     */
    public void setDataEmissao(Calendar dataEmissao) {
        this.dataEmissao = dataEmissao;
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

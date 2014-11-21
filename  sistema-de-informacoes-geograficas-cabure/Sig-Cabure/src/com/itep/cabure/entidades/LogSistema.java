package com.itep.cabure.entidades;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe responsavel por
 *
 * @author fernanda.franklin
 * @since 02/10/2014
 * @version 2.0?
 */
@Entity
@Table(name = "LOG_SISTEMA")
public class LogSistema implements Serializable {

    @Id
    @SequenceGenerator(name = "LOGSISTEMA_SEQ", sequenceName = "LOG_SISTEMA_PK_LOSI_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOGSISTEMA_SEQ")
    @Column(name = "PK_LOSI", nullable = false)
    private long idLogSistema;

    @Column(name = "LOSI_ACAO_EFETUADA", nullable = false, length = 255)
    private String acaoEfetuada;

    @Column(name = "LOSI_DT_DATA_HORA_LOGADO", nullable = false, length = 128)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataHoraLogado;

    @Column(name = "LOSI_NM_ARQUIVO")
    private String nomeArquivo;

    /**
     * Relacionamento com a Tabela USUARIO
     */
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USUA_LOSI", referencedColumnName = "PK_USUA", nullable = false)
    private Usuario usuario;

    /**
     * @return the acaoEfetuada
     */
    public String getAcaoEfetuada() {
        return acaoEfetuada;
    }

    /**
     * @param acaoEfetuada the acaoEfetuada to set
     */
    public void setAcaoEfetuada(String acaoEfetuada) {
        this.acaoEfetuada = acaoEfetuada;
    }

    /**
     * @return the dataHoraLogado
     */
    public Calendar getDataHoraLogado() {
        return dataHoraLogado;
    }

    /**
     * @param dataHoraLogado the dataHoraLogado to set
     */
    public void setDataHoraLogado(Calendar dataHoraLogado) {
        this.dataHoraLogado = dataHoraLogado;
    }

    /**
     * @return the nomeArquivo
     */
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    /**
     * @param nomeArquivo the nomeArquivo to set
     */
    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}

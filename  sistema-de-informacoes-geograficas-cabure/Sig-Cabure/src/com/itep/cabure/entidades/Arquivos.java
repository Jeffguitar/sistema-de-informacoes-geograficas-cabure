package com.itep.cabure.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author fernanda.franklin
 * @since 06/10/2014
 * @version 2.0? DESCRIÇÃO: Contém todos os registros a respeito dos arquivos
 * inserido no sistema.
 */
@Entity
public class Arquivos {

    @Id
    @SequenceGenerator(name = "ARQUIVOS_SEQ", sequenceName = "ARQUIVOS_PK_ARQU_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ARQUIVOS_SEQ")
    @Column(name = "PK_ARQU", nullable = false)
    private long idArquivo; // código que permite identificar cada arquivo inserido no sistema

    // Registra o nome do arquivo inserido no sistema
    @Column(name = "ARQU_NM_NOME", nullable = false, length = 128)
    private String nomeArquivo;

    // O tipo de arquivo inserido : image, video,pdf, doc...
    @Column(name = "ARQU_TIPO_ARQUIVO", nullable = false, length = 16)
    private String tipoArquivo;

    // Contem o tamanho do arquivo inserido por usaúrio que pode ajudar no controle do espaço utilizado por usuário
    @Column(name = "ARQU_MD_TAMANHO", nullable = false, length = 128)
    private Integer tamanhoArquivo;

    // Indica o caminho onde o arquivo do usuário se localiza
    @Column(name = "ARQU_CAMINHO_ARQUIVOS")
    private String caminhoArquivo;

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
     * @return the tipoArquivo
     */
    public String getTipoArquivo() {
        return tipoArquivo;
    }

    /**
     * @param tipoArquivo the tipoArquivo to set
     */
    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     * @return the tamanhoArquivo
     */
    public Integer getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    /**
     * @param tamanhoArquivo the tamanhoArquivo to set
     */
    public void setTamanhoArquivo(Integer tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    /**
     * @return the caminhoArquivo
     */
    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    /**
     * @param caminhoArquivo the caminhoArquivo to set
     */
    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

}

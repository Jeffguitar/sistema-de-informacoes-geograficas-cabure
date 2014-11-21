package com.itep.cabure.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author fernanda.franklin
 * @since 06/10/2014
 * @version 2.0? DESCRI��O: Essa tabela contem os c�digo dos arquivos e as
 * publica��o
 */
@Entity
@Table(name = "ARQUIVOS_PUBLICADOS")
public class ArquivosPublicados {

    @EmbeddedId
    private ArquivospublicadosPK idsArquivosPublicados;

    /**
     * @return the idsArquivosPublicados
     */
    public ArquivospublicadosPK getIdsArquivosPublicados() {
        return idsArquivosPublicados;
    }

    /**
     * @param idsArquivosPublicados the idsArquivosPublicados to set
     */
    public void setIdsArquivosPublicados(ArquivospublicadosPK idsArquivosPublicados) {
        this.idsArquivosPublicados = idsArquivosPublicados;
    }

}

package com.itep.cabure.dao;

import com.itep.cabure.entidades.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author Macário
 * @version 1.0?
 * @since 20/08/2014
 */
public class HibernateUtil {
    private static SessionFactory factory;

    static {
        AnnotationConfiguration configuration = new AnnotationConfiguration();

        configuration.addAnnotatedClass(Arquivos.class);
        configuration.addAnnotatedClass(ArquivosPublicados.class);
        configuration.addAnnotatedClass(ArquivospublicadosPK.class);
        configuration.addAnnotatedClass(Contato.class);
        configuration.addAnnotatedClass(Empresa.class);
        configuration.addAnnotatedClass(Endereco.class);
        configuration.addAnnotatedClass(Funcionalidade.class);
        configuration.addAnnotatedClass(Grupo.class);
        configuration.addAnnotatedClass(GrupoFuncionalidade.class);
        configuration.addAnnotatedClass(GrupoFuncionalidadePK.class);
        configuration.addAnnotatedClass(LogSistema.class);
        configuration.addAnnotatedClass(Pessoa.class);
        configuration.addAnnotatedClass(PessoaPublicacao.class);
        configuration.addAnnotatedClass(PessoaPublicacaoPK.class);
        configuration.addAnnotatedClass(Publicacao.class);
        configuration.addAnnotatedClass(PublicacaoEmpresa.class);
        configuration.addAnnotatedClass(PublicacaoEmpresaPK.class);
        configuration.addAnnotatedClass(RelatorioEmitido.class);
        configuration.addAnnotatedClass(Usuario.class);  
        configuration.addAnnotatedClass(Valida.class);
//        configuration.addAnnotatedClass(ValidaPK.class);
        
        configuration.configure();
        factory = configuration.buildSessionFactory();
    }

    /**
     *
     * @return
     */
    public Session getSession() {
        return factory.openSession();
    }
}
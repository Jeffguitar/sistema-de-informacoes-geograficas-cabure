package com.itep.cabure.dao;

import com.itep.cabure.entidades.LogSistema;
import com.itep.cabure.entidades.Usuario;
import java.util.List;
import org.hibernate.Session;

/**
 * descrição da entidade de persistencia para a classe Pessoa
 *
 * @author macario.granja
 * @version 1.0?
 * @since 20/08/2014
 */
public class LogSistemaDAO {

    //private Connection connection;
    private Session hibernateSession;

    public LogSistemaDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public LogSistemaDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(LogSistema c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(LogSistema c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(LogSistema c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param user
     * @return
     */
    public LogSistema busca(Usuario user) {
        List<LogSistema> l = getLista();
        if (l != null) {
            for (LogSistema pf : l) {
                if (pf.getUsuario() == user) {
                    return pf;
                }
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public List<LogSistema> getLista() {
        List<LogSistema> candidatos = this.hibernateSession.createCriteria(LogSistema.class).list();
        if (!candidatos.isEmpty()) {
            return candidatos;
        }
        return null;
    }

    /**
     *
     */
    public void fechar() {
        this.hibernateSession.close();
    }
}
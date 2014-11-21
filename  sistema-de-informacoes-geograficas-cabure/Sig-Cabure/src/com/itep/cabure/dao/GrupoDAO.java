package com.itep.cabure.dao;

import com.itep.cabure.entidades.Grupo;
import java.util.List;
import org.hibernate.Session;

/**
 * Descrição da entidade de persistencia para a classe Endereco
 *
 * @author macario.granja
 * @version 1.0?
 * @since 01/09/2014
 */
public class GrupoDAO {

    //private Connection connection;
    private Session hibernateSession;

    /**
     *
     */
    public GrupoDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public GrupoDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Grupo c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Grupo c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Grupo c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param oIndentificador
     * @return
     */
    public Grupo busca(String oIndentificador) {
        List<Grupo> l = getLista();
        if (l != null) {
            for (Grupo pf : l) {
                if (pf.getNomeGrupo() == oIndentificador) {
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
    public List<Grupo> getLista() {
        List<Grupo> candidatos = this.hibernateSession.createCriteria(Grupo.class).list();
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
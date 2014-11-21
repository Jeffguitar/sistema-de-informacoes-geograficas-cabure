package com.itep.cabure.dao;

import com.itep.cabure.entidades.Contato;
import java.util.List;
import org.hibernate.Session;

/**
 * Descrição da entidade de persistencia para a classe Contato
 *
 * @author macario.granja
 * @version 1.0?
 * @since 22/08/2014
 */
public class ContatoDAO {

    //private Connection connection;

    private Session hibernateSession;

    /**
     *
     */
    public ContatoDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public ContatoDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Contato c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Contato c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Contato c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param pfCpfPessoa
     * @return
     */
    public Contato busca(String pfCpfPessoa) {
        List<Contato> l = getLista();
        if (l != null) {
            for (Contato pf : l) {
                if (pf.getEmail().equals(pfCpfPessoa)) {
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

    public List<Contato> getLista() {
        List<Contato> candidatos = this.hibernateSession.createCriteria(Contato.class).list();
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

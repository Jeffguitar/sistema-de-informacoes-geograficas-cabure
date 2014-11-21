package com.itep.cabure.dao;

import com.itep.cabure.entidades.Valida;
import java.util.List;
import org.hibernate.Session;

/**
 * Descrição da entidade de persistencia para a classe Contato
 *
 * @author macario.granja
 * @version 1.0?
 * @since 22/08/2014
 */
public class ValidaDAO {

    //private Connection connection;

    private Session hibernateSession;

    /**
     *
     */
    public ValidaDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public ValidaDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Valida c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Valida c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Valida c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param identificador
     * @return
     */
    public Valida busca(String identificador) {
        List<Valida> l = getLista();
        if (l != null) {
            for (Valida pf : l) {
                if (pf.getIdUsuario().getUsuario().equals(identificador)) {
                    return pf;
                }
            }
        }
        return null;
    }

    public List<Valida> getLista() {
        List<Valida> candidatos = this.hibernateSession.createCriteria(Valida.class).list();
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

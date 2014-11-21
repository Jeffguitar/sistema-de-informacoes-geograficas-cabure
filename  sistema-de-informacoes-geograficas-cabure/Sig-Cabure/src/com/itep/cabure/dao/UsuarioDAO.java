package com.itep.cabure.dao;

import com.itep.cabure.entidades.Usuario;
import java.util.List;
import org.hibernate.Session;

/**
 * Descrição da entidade de persistencia para a classe Contato
 *
 * @author macario.granja
 * @version 1.0?
 * @since 22/08/2014
 */
public class UsuarioDAO {

    //private Connection connection;
    private Session hibernateSession;

    /**
     *
     */
    public UsuarioDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public UsuarioDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Usuario c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Usuario c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Usuario c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param chaveUsuario 
     * @return
     */
    public Usuario busca(String chaveUsuario) {
        List<Usuario> l = getLista();
        if (l != null) {
            for (Usuario pf : l) {
                if (pf.getUsuario().equals(chaveUsuario)) {
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
    public List<Usuario> getLista() {
        List<Usuario> candidatos = this.hibernateSession.createCriteria(Usuario.class).list();
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

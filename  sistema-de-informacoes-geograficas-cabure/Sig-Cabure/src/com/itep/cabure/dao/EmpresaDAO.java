package com.itep.cabure.dao;

import com.itep.cabure.entidades.Empresa;
import java.util.List;
import org.hibernate.Session;

/**
 * descrição da entidade de persistencia para a classe Empresa
 *
 * @author macario.granja
 * @version 1.0?
 * @since 20/08/2014
 */
public class EmpresaDAO {

    //private Connection connection;
    private Session hibernateSession;

    /**
     *
     */
    public EmpresaDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public EmpresaDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Empresa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Empresa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Empresa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param oCnpj
     * @return
     */
    public Empresa busca(String oCnpj) {
        List<Empresa> l = getLista();
        if (l != null) {
            for (Empresa pf : l) {
                if (pf.getCnpj().equals(oCnpj)) {
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
    public List<Empresa> getLista() {
        List<Empresa> candidatos = this.hibernateSession.createCriteria(Empresa.class).list();
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

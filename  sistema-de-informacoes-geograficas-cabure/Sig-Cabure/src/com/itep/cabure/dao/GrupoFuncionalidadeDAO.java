package com.itep.cabure.dao;

import com.itep.cabure.entidades.GrupoFuncionalidade;
import java.util.List;
import org.hibernate.Session;

/**
 * descrição da entidade de persistencia para a classe Endereco
 *
 * @author macario.granja
 * @version 1.0?
 * @since 01/09/2014
 */
public class GrupoFuncionalidadeDAO {

    //private Connection connection;
    private Session hibernateSession;

    /**
     *
     */
    public GrupoFuncionalidadeDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public GrupoFuncionalidadeDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(GrupoFuncionalidade c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(GrupoFuncionalidade c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(GrupoFuncionalidade c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param oIndentificador
     * @return
     */
    public GrupoFuncionalidade busca(String oIndentificador) {
        List<GrupoFuncionalidade> l = getLista();
        if (l != null) {
            for (GrupoFuncionalidade pf : l) {
                if (pf.getIdsGrupoFuncionade().getIdGrupoFuncionalidade().equals(oIndentificador)) {
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
    public List<GrupoFuncionalidade> getLista() {
        List<GrupoFuncionalidade> candidatos = this.hibernateSession.createCriteria(GrupoFuncionalidade.class).list();
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

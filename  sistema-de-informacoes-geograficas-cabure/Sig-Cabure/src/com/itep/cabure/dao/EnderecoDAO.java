package com.itep.cabure.dao;

import com.itep.cabure.entidades.Endereco;
import java.util.List;
import org.hibernate.Session;

/**
 * descrição da entidade de persistencia para a classe Endereco
 *
 * @author macario.granja
 * @version 1.0?
 * @since 22/08/2014
 */
public class EnderecoDAO {

    //private Connection connection;
    private Session hibernateSession;

    public EnderecoDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public EnderecoDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Endereco c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Endereco c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Endereco c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param oIndentificador
     * @return
     */
    public Endereco busca(String oIndentificador) {
        List<Endereco> l = getLista();
        if (l != null) {
            for (Endereco pf : l) {
                if (pf.getPessoa().getCpf().equals(oIndentificador) || pf.getEmpresa().getCnpj().equals(oIndentificador) ) {
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
    public List<Endereco> getLista() {
        List<Endereco> candidatos = this.hibernateSession.createCriteria(Endereco.class).list();
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

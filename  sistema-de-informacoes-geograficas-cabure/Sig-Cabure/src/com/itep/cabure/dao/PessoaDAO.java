package com.itep.cabure.dao;

import com.itep.cabure.entidades.Pessoa;
import java.util.List;
import org.hibernate.Session;

/**
 * DescriÁ„o da entidade de persistencia para a classe Pessoa
 *
 * @author macario.granja
 * @version 1.0?
 * @since 20/08/2014
 */
public class PessoaDAO {

    //private Connection connection;
    private Session hibernateSession;

    public PessoaDAO() {
        hibernateSession = new HibernateUtil().getSession();
    }

    /**
     *
     * @param session
     */
    public PessoaDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     *
     * @param c
     */
    public void salvar(Pessoa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void altera(Pessoa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param c
     */
    public void remove(Pessoa c) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(c);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     *
     * @param oCpf
     * @return
     */
    public Pessoa buscaCpf(String oCpf) {
        List<Pessoa> l = getLista();
        if (l != null) {
            for (Pessoa pf : l) {
            	System.out.println(pf.getCpf());
            	System.out.println(oCpf);
                if (pf.getCpf().equals(oCpf)) {
                    return pf;
                }
            }
        }
        return null;
    }

    /**
     * Responsavel por coletar todos os registros do banco e permitir a manipula√ß√£o 
     * como objetos
     * 
     * @return
     */
    public List<Pessoa> getLista() {
        List<Pessoa> candidatos = this.hibernateSession.createCriteria(Pessoa.class).list();
        if (!candidatos.isEmpty()) {
            return candidatos;
        }
        return null;
    }

    /**
     * Responsavel por fechar a conex√£o com o banco no modelo DAO
     */
    public void fechar() {
        this.hibernateSession.close();
    }
}
package com.itep.cabure.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.*;

/**
 * Classe genérica responsável por realizar as operações de CRUD para todas as
 * entidades do Banco de Dados
 * 
 * Última atualização: 12/11/2014
 *
 * @author fernanda.franklin
 * @version 1.0
 * @since 25/09/2014
 * @param <T> - Objecto de trabalho
 * 
 */
public class GenericDAO<T> implements IDao<T> {

    private Session hibernateSession;
    
    /**
     * Inicialização de Sessão
     */
    public GenericDAO() {
        hibernateSession = new HibernateUtil().getSession(); 
    }

    public GenericDAO(Session session) {
        this.hibernateSession = session;
    }

    /**
     * CRUD (C) -> Create
     *
     * @param objeto
     */
    @Override
    public void salvar(T objeto) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.save(objeto);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     * CRUD (U) -> Update
     *
     * @param objeto
     */
    @Override
    public void alterar(T objeto) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.update(objeto);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     * CRUD (D) -> Delete
     *
     * @param objeto
     */
    @Override
    public void remover(T objeto) {
        this.hibernateSession.beginTransaction();
        this.hibernateSession.delete(objeto);
        this.hibernateSession.getTransaction().commit();
    }

    /**
     * CRUD (R) -> Retrieve
     *
     * @param objeto
     * @return
     */
    @Override
    public List<T> getList(Object objeto) {

        List<T> objectList = this.hibernateSession.createCriteria(objeto.getClass()).list();

        if (!objectList.isEmpty()) {
            return objectList;
        }
        return null;
    }

    /**
     * Realiza uma busca no banco de dados que, em linguagem SQL, corresponde a
     * seguinte consulta:
     *
     * <b> Select * from objeto where campoBusca like paramBusca order by
     * paramOrdenacao asc </b>
     *
     * @param objeto
     * @param campoBusca
     * @param paramBusca
     * @param paramOrdenacao
     * @return Uma lista com os resultados ou Null em caso da busca retornar
     * vazio
     */
    @Override
    public List<T> acessaRegistrosLike(Object objeto, String campoBusca, String paramBusca, String paramOrdenacao) {

        paramBusca = "%" + paramBusca + "%";

        List<T> objectList = this.hibernateSession.createCriteria(objeto.getClass())
                .add(Restrictions.ilike(campoBusca, paramBusca))
                .addOrder(Property.forName(paramOrdenacao).asc())
                .list();

        if (!objectList.isEmpty()) {
            return objectList;
        }

        return null;
    }

    /**
     * Realiza uma busca no banco de dados que, em linguagem SQL, corresponde a
     * seguinte consulta:
     *
     * <b> Select * from objeto where campoBusca = paramBusca </b>
     *
     * @param objeto
     * @param campoBusca
     * @return Instancia de um objeto/entidade buscado
     */
    @Override
    public Object acessaRegistro(Object objeto, String campoBusca, String paramBusca) {

        objeto = (Object) this.hibernateSession.createCriteria(objeto.getClass())
                .add(Restrictions.eq(campoBusca, paramBusca))
                .uniqueResult();

        return objeto;
    }

    /**
     * /**
     * Realiza uma busca no banco de dados que, em linguagem SQL, corresponde a
     * seguinte consulta:
     *
     * <b> Select * from objeto where campoBusca = paramBusca </b>
     *
     * @param objeto
     * @param campoBusca
     * @param paramBusca
     * @return
     */
    @Override
    public Object acessaRegistroObjeto(Object objeto, String campoBusca, Object paramBusca) {

        objeto = (Object) this.hibernateSession.createCriteria(objeto.getClass())
                .add(Restrictions.eq(campoBusca, paramBusca))
                .uniqueResult();

        return objeto;
    }

    /**
     * Finaliza a sessão
     */
    @Override
    public void fechar() {
        // TODO Auto-generated method stub
        this.hibernateSession.close();
    }

}

package com.itep.cabure.dao;

import java.util.List;

/**
 * Implementação dos métodos básicos de CRUD - Create, Retrieve, Update e Delete
 * Create - Salva / Busca - Retrieve / Update - Altera / Remove - Delete
 *
 * @author fernanda.franklin
 * @version 1.0 since 26/09/2014
 *
 */
public interface IDao<T> {

    public void salvar(T objeto);

    public void alterar(T objeto);

    public void remover(T objeto);

   public Object acessaRegistro(Object objeto, String campoBusca, String paramBusca);
    
    public Object acessaRegistroObjeto(Object objeto, String parametro, Object itemBuscado);
    
    public List<T> acessaRegistrosLike(Object objeto, String campoBusca, String paramBusca, String paramOrdenacao);

    public List<T> getList(Object objeto);
    
    public void fechar();
}

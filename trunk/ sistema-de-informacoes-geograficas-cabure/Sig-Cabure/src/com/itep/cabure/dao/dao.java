package com.itep.cabure.dao;

/**
 *
 * @author macario.granja
 */
interface dao {

    /**
     * Métodos que obrigatoriamente devem ser implementados pela Classe que
     * implementar esta Interface
     */
    public  void salvar(Object entidade);

    public void altera ();

    public void remove ();

    public void fechar ();
}

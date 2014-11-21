package com.itep.cabure.controler;

import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.entidades.Empresa;
import com.itep.cabure.entidades.LogSistema;
import com.itep.cabure.entidades.Pessoa;
import com.itep.cabure.entidades.Usuario;
import com.itep.cabure.entidades.Valida;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author fernanda.franklin Esta classe instancia m�todos  <b>Registro de
 * Log</b>,
 * @version 1.0
 * @since 13/10/2014
 */
public class LogControler {

    /**
     * Recebe como par�metro um usu�rio para que seja registrado o log desse
     * usu�rio de acordo com o tipo de log passado via par�metro
     *
     * @param usuario
     * @param tipoLog
     */
    public void setLog(Usuario usuario, PadroesLog tipoLog) {

        System.out.println("Entrou em set log");
        GenericDAO<LogSistema> registraLogDAO = new GenericDAO<LogSistema>();
        LogSistema registraLog = new LogSistema();
        Calendar dataHoraLog = Calendar.getInstance();

        registraLog.setDataHoraLogado(dataHoraLog);

        switch (tipoLog) {
            case CADASTRO_USUARIO:
                registraLog.setAcaoEfetuada("usuario cadastrado");
                break;
            case AUTENTICA_USUARIO:
                System.out.println("entrou para atualizar log");
                registraLog.setAcaoEfetuada("usuario autenticado");
                break;
            case DESATIVA_USUARIO:
                registraLog.setAcaoEfetuada("usuario inativado");
                break;
            case ATIVA_USUARIO:
                registraLog.setAcaoEfetuada("usuario reativado");
                break;
            case LOGIN:
                registraLog.setAcaoEfetuada("login");
                break;
            case LOGOUT:
                registraLog.setAcaoEfetuada("logout");
                break;
            case FALHA:
                registraLog.setAcaoEfetuada("falha + erro"); // FALTA INCLUIR C�DIGO DE ERRO
                break;
            case NOVA_SENHA_SOLICITADA:
                registraLog.setAcaoEfetuada("alteracao senha solicitada");
                break;
            case SENHA_ALTERADA:
                registraLog.setAcaoEfetuada("senha alterada");
                break;
        }

        registraLog.setUsuario(usuario);

        registraLogDAO.salvar(registraLog);
        registraLogDAO.fechar();

    }

    /**
     * Ser� respons�vel por criar uma inst�ncia de valida para um usu�rio APENAS
     * no momento do cadastro - Nesse ponto, o m�todo ir� receber o par�metro
     * valida como null. Uma vez j� cadastro, este m�todo ir� controlar as
     * mudan�as dos valores do flag, quando requisitado, e ir� sempre receber o
     * par�metro usuario como null.
     *
     * @param usuario - ser� passado apenas quando for para o status
     * "ESPERANDO_AUTENTICA��O", para a chamada dos demais ser� null
     * @param valida - ser� passado apenas quando for para altera��o.
     * @param flagStatus
     */
    public void setValida(Usuario usuario, Valida usuarioValida, FlagStatus flagStatus) {

        GenericDAO<Valida> validaUsuarioDAO = new GenericDAO<Valida>();

        String uuid = UUID.randomUUID().toString();

        switch (flagStatus) {
            case ESPERANDO_AUTENTICACAO:
                Valida valida = new Valida();
                valida.setIdUsuario(usuario);
                valida.setFlag(0);
                valida.setToken(uuid);
                validaUsuarioDAO.salvar(valida);
                break;
            case ATIVO:
                usuarioValida.setFlag(1);
                invalidaToken(usuarioValida);
                validaUsuarioDAO.alterar(usuarioValida);
                break;
            case SOLICITACAO_NOVA_SENHA:
                usuarioValida.setFlag(2);
                usuarioValida.setToken(uuid);
                validaUsuarioDAO.alterar(usuarioValida);
                break;
            case BLOQUEIO_CONTA:
                usuarioValida.setFlag(3);
                usuarioValida.setToken(uuid);
                validaUsuarioDAO.alterar(usuarioValida);
                break;
        }
        validaUsuarioDAO.fechar();
    }
    
    public FlagStatus getFlagStatus(int numflagStatus){
        
        switch (numflagStatus){
            case 0:
                return FlagStatus.ESPERANDO_AUTENTICACAO;
            case 1:
                return FlagStatus.ATIVO;
            case 2:
                return FlagStatus.SOLICITACAO_NOVA_SENHA;
            case 3:
                return FlagStatus.BLOQUEIO_CONTA;
        }
        
        return null;
    }

    /**
     * Retorna os dados de Valida��o que est�o associados a um usu�rio
     *
     * @param usuario
     * @return
     */
    public Valida getValidaUsuario(Usuario usuario) {

        GenericDAO<Valida> validaDAO = new GenericDAO<Valida>();

        Valida valida = new Valida();
        Valida validaUsuario = (Valida) validaDAO.acessaRegistroObjeto(valida, "idUsuario", usuario);

        validaDAO.fechar();
        return validaUsuario;
    }

    /**
     * Retorna uma inst�ncia de Valida que est� associado a um token
     *
     * @param token
     * @return valida
     */
    public Valida getTokenValida(String token) {

        GenericDAO<Valida> validaDAO = new GenericDAO<Valida>();

        Valida valida = new Valida();
        Valida validaToken = (Valida) validaDAO.acessaRegistro(valida, "token", token);

        validaDAO.fechar();

        return validaToken;

    }

    /**
     * Retorna o usu�rio que est� associado a uma inst�ncia de valida
     *
     * @param idUsuario
     * @return usuario
     */
    public Usuario getUsuarioValida(String idUsuario) {

        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

        Usuario usuarioBusca = new Usuario();
        Usuario usuario = (Usuario) usuarioDAO.acessaRegistro(usuarioBusca, "usuario", idUsuario);

        usuarioDAO.fechar();

        return usuario;

    }

    /**
     * Ap�s o primeiro acesso do usu�rio a um link - por exemplo, ativa��o de
     * conta - ir� invalidar o token para o link se tornar inacess�vel
     *
     * @param valida
     */
    public void invalidaToken(Valida valida) {

        GenericDAO<Valida> validaDAO = new GenericDAO<Valida>();
        valida.setToken(null);

        validaDAO.alterar(valida);
        validaDAO.fechar();
    }

    /**
     * Retorna os dados da pessoa que est� associada a um usu�rio.
     *
     * @param usuario
     * @return pessoa
     */
    public Pessoa getPessoaLogada(Usuario usuario) {

        GenericDAO<Pessoa> pessoaDAO = new GenericDAO<Pessoa>();

        Pessoa pessoa = new Pessoa();
        Pessoa pessoaBuscada = (Pessoa) pessoaDAO.acessaRegistro(pessoa, "cpf", usuario.getUsuario());

        return pessoaBuscada;
    }

    /**
     * Retorna o usu�rio a partir do conte�do de uma String que pode ser um CPF
     * ou CNPJ
     *
     * @param String loginUsuario
     * @return usuario
     */
    public Usuario getUsuario(String loginUsuario) {
        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

        // Verifica se usu�rio j� existe na base
        Usuario usuario = new Usuario();
        Usuario usuarioBuscado = (Usuario) usuarioDAO.acessaRegistro(usuario, "usuario", loginUsuario);
        
        return usuarioBuscado;
    }

    // ************* FALTA IMPLEMENTAR *************************
    // getContatoPessoa
    // getContatoEmpresa
    // getEnderecoPessoa
    // getEnderecoEmpresa
    // getSenha
    // getUsuario 

    
    
    
    
    /**
     * Retorna os dados da empresa que est� associada a um usu�rio.
     *
     * @param usuario
     * @return empresa
     */
    public Empresa getEmpresaLogada(Usuario usuario) {
        GenericDAO<Empresa> empresaDAO = new GenericDAO<Empresa>();

        Empresa empresa = new Empresa();
        empresa = (Empresa) empresaDAO.acessaRegistro(empresa, "cnpj", usuario.getUsuario());

        return empresa;
    }

    /**
     * Retorna se um usu�rio � pessoa f�sica ou jur�dica
     *
     * @param usuario
     * @return CPF: se pessoa f�sica; CNPJ: se pessoa jur�dica
     */
    public String getTipoUsuario(String usuario) {
        if (usuario.length() == 14) {
            return "CPF";
        } else {
            return "CNPJ";
        }
    }
}
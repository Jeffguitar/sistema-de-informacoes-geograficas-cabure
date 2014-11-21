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
 * @author fernanda.franklin Esta classe instancia métodos  <b>Registro de
 * Log</b>,
 * @version 1.0
 * @since 13/10/2014
 */
public class LogControler {

    /**
     * Recebe como parâmetro um usuário para que seja registrado o log desse
     * usuário de acordo com o tipo de log passado via parâmetro
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
                registraLog.setAcaoEfetuada("falha + erro"); // FALTA INCLUIR CÓDIGO DE ERRO
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
     * Será responsável por criar uma instância de valida para um usuário APENAS
     * no momento do cadastro - Nesse ponto, o método irá receber o parâmetro
     * valida como null. Uma vez já cadastro, este método irá controlar as
     * mudanças dos valores do flag, quando requisitado, e irá sempre receber o
     * parâmetro usuario como null.
     *
     * @param usuario - será passado apenas quando for para o status
     * "ESPERANDO_AUTENTICAÇÃO", para a chamada dos demais será null
     * @param valida - será passado apenas quando for para alteração.
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
     * Retorna os dados de Validação que estão associados a um usuário
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
     * Retorna uma instância de Valida que está associado a um token
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
     * Retorna o usuário que está associado a uma instância de valida
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
     * Após o primeiro acesso do usuário a um link - por exemplo, ativação de
     * conta - irá invalidar o token para o link se tornar inacessível
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
     * Retorna os dados da pessoa que está associada a um usuário.
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
     * Retorna o usuário a partir do conteúdo de uma String que pode ser um CPF
     * ou CNPJ
     *
     * @param String loginUsuario
     * @return usuario
     */
    public Usuario getUsuario(String loginUsuario) {
        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

        // Verifica se usuário já existe na base
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
     * Retorna os dados da empresa que está associada a um usuário.
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
     * Retorna se um usuário é pessoa física ou jurídica
     *
     * @param usuario
     * @return CPF: se pessoa física; CNPJ: se pessoa jurídica
     */
    public String getTipoUsuario(String usuario) {
        if (usuario.length() == 14) {
            return "CPF";
        } else {
            return "CNPJ";
        }
    }
}
package com.itep.cabure.controler;

import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.email.SendEmailMultiThread;
import com.itep.cabure.entidades.Contato;
import com.itep.cabure.entidades.Endereco;
import com.itep.cabure.entidades.Pessoa;
import com.itep.cabure.entidades.Empresa;
import com.itep.cabure.entidades.Grupo;
import com.itep.cabure.entidades.Usuario;
import com.itep.cabure.entidades.Valida;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Servlet responsável pelo cadastro de novos usuários no Caburé. Estes usuários
 * podem ser pessoas físicas ou jurídicas, os quais serão vinculadas a um perfil
 * entre dois perfis existentes: <b>Colaborador</b> ou <b>Empreendedor</b>.
 *
 * @author fernanda.franklin
 * @since 13/10/2014
 * @version 1.3?
 */
public class Cadastro extends HttpServlet {

    public String ipServer = "200.17.134.46";
    LogControler logControler = new LogControler();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Classe respons?vel pela persistencia do dado do usu?rio e encaminhamento
     * dos dados de email.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");

        HttpSession httpSession = request.getSession(true);
        httpSession.setMaxInactiveInterval(12000);

        String operacao = request.getParameter("operacao");

        if (operacao.equals("colaborador")) {
            Boolean isEmpreendedor = false;
            processaCadastroPessoaFisica(request, response, httpSession, isEmpreendedor);
        } else if (operacao.equals("empreendedorFisico")) {
            Boolean isEmpreendedor = true;
            processaCadastroPessoaFisica(request, response, httpSession, isEmpreendedor);
        } else if (operacao.equals("empreendedorJuridico")) {
            processaCadastroPessoaJuridica(request, response, httpSession);
        } else {
            response.sendRedirect("reg.jsp?err=Operação inválida");
        }
    }
    
    /**
     * Método responsável por tratar o cadastro de usuario de uma pessoa
     * Colaborador
     *
     * @author fernanda.franklin
     * @since 13/10/2014
     * @version 1.3
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @param HttpSession httpSession
     * @param isEmpreendedor boolean
     *
     */
    private void processaCadastroPessoaFisica(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, Boolean isEmpreendedor) {
        try {

            String oSenha = request.getParameter("senha");
            String oSenhaRepetida = request.getParameter("repSenha");
           

            if (oSenha.equals(oSenhaRepetida)) {
                String email = request.getParameter("txt_email");
                String nome = request.getParameter("txt_nome_user");
                nome = letrasIniciaisMaiusculas(nome);

                boolean isEmailValido = isEmailValido(email);

                if (isEmailValido) {
                    String oCpf = request.getParameter("txt_cpf");

                    if (isCpfValido(oCpf)) {
                        
                        LogControler log = new LogControler();
                        Usuario usuarioBuscado = log.getUsuario(oCpf);
                        Usuario usuario = new Usuario();

                        if (usuarioBuscado == null) {

                            // <editor-fold defaultstate="collapsed" desc="Retorna o grupo ao qual o usuário selecionou para iniciar seu cadastro">                         
                            GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();
                            Grupo grupoNovoUsuario = new Grupo();

                            Grupo grupo = new Grupo();
                            if (isEmpreendedor) {
                                grupoNovoUsuario = (Grupo) grupoDAO.acessaRegistro(grupo, "nomeGrupo", "Empreendedor");
                            } else {
                                grupoNovoUsuario = (Grupo) grupoDAO.acessaRegistro(grupo, "nomeGrupo", "Colaborador");
                            }
                            grupoDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistência de Usuário">
                            GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();
                            usuario.setUsuario(oCpf);
                            usuario.setSenha(oSenhaRepetida);
                            usuario.setGrupo(grupoNovoUsuario);

                            usuarioDAO.salvar(usuario);

                            usuarioDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistencia da Pessoa">
                            GenericDAO<Pessoa> pessoaDAO = new GenericDAO<Pessoa>();
                            Pessoa usuarioPessoaFisica = new Pessoa();

                            usuarioPessoaFisica.setCpf(oCpf);
                            usuarioPessoaFisica.setNome(nome);
                            usuarioPessoaFisica.setOrgaoEmissor(request.getParameter("orgaoemissor"));
                            usuarioPessoaFisica.setRg(request.getParameter("txt_rg"));
                            usuarioPessoaFisica.setGrupo(grupoNovoUsuario);

                            pessoaDAO.salvar(usuarioPessoaFisica);
                            pessoaDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistência do Endereço">
                            GenericDAO<Endereco> enderecoDAO = new GenericDAO<Endereco>();
                            Endereco endereco = new Endereco();

                            endereco.setCep(request.getParameter("TXT_CEP"));
                            endereco.setLogradouro(request.getParameter("TXT_LOGRADOURO"));
                            endereco.setNumero(Integer.parseInt(request.getParameter("TXT_NUMERO")));
                            endereco.setComplemento(request.getParameter("txt_complemento"));
                            endereco.setBairro(request.getParameter("TXT_BAIRRO"));
                            endereco.setMunicipio(request.getParameter("TXT_CIDADE"));
                            endereco.setUf(request.getParameter("CBO_UF"));

                            endereco.setPessoa(usuarioPessoaFisica);

                            enderecoDAO.salvar(endereco);
                            enderecoDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistência do Contato">
                            GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
                            Contato contato = new Contato();// daoContato.busca(nomeLogin);
                            contato.setPessoa(usuarioPessoaFisica);
                            contato.setTelefoneEmpresa(request.getParameter("txt_telefone"));
                            contato.setEmail(email);
                            contato.setCelular(request.getParameter("txt_telefone_celular"));

                            contatoDAO.salvar(contato);
                            contatoDAO.fechar();
                            // </editor-fold>

                            //Cria Token / Persistência Valida
                            logControler.setValida(usuario, null, FlagStatus.ESPERANDO_AUTENTICACAO);
 
                            Valida validaUsuario = new Valida();
                            validaUsuario = logControler.getValidaUsuario(usuario);
                            
                            //Persistencia do Grupo Iniciando o envio de email.
                            processaEnvioEmailColaborador(usuarioPessoaFisica, validaUsuario, contato, request);
                            
                            // Registro de Log de Cadastro
                            logControler.setLog(usuario, PadroesLog.CADASTRO_USUARIO);

                           httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", usuario.getUsuario());
                            
                            response.sendRedirect("cadastroFinalizado.jsp");
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="Alerta - CPF já Cadastrado">
                            response.sendRedirect("reg.jsp?err=Cpf já cadastrado. Tente novamente" + "&txt_nome_user=" + request.getParameter("txt_nome_user")
                                    + "&txt_cpf=" + request.getParameter("txt_cpf")
                                    + "&txt_rg=" + request.getParameter("txt_rg")
                                    + "&orgaoemissor=" + request.getParameter("orgaoemissor")
                                    + "&TXT_CEP=" + request.getParameter("TXT_CEP")
                                    + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                                    + "&TXT_LOGRADOURO=" + request.getParameter("TXT_LOGRADOURO")
                                    + "&TXT_NUMERO=" + request.getParameter("TXT_NUMERO")
                                    + "&txt_complemento=" + request.getParameter("txt_complemento")
                                    + "&TXT_BAIRRO=" + request.getParameter("TXT_BAIRRO")
                                    + "&TXT_CIDADE=" + request.getParameter("TXT_CIDADE")
                                    + "&estado=" + request.getParameter("estado")
                                    + "&txt_email=" + request.getParameter("txt_email")
                                    + "&txt_telefone=" + request.getParameter("txt_telefone")
                                    + "&txt_telefone_celular=" + request.getParameter("txt_telefone_celular")
                                    + "&checkedColaborador=selected");
                            // </editor-fold>
                        }

                    } else {
                        // <editor-fold defaultstate="collapsed" desc="Alerta - CPF Inválido">
                        response.sendRedirect("reg.jsp?err13=Cpf inválido tente novamente. Tente novamente"
                                + "&txt_nome_user=" + request.getParameter("txt_nome_user")
                                + "&txt_cpf=" + request.getParameter("txt_cpf")
                                + "&txt_rg=" + request.getParameter("txt_rg")
                                + "&orgaoemissor=" + request.getParameter("orgaoemissor")
                                + "&TXT_CEP=" + request.getParameter("TXT_CEP")
                                + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                                + "&TXT_LOGRADOURO=" + request.getParameter("TXT_LOGRADOURO")
                                + "&TXT_NUMERO=" + request.getParameter("TXT_NUMERO")
                                + "&txt_complemento=" + request.getParameter("txt_complemento")
                                + "&TXT_BAIRRO=" + request.getParameter("TXT_BAIRRO")
                                + "&TXT_CIDADE=" + request.getParameter("TXT_CIDADE")
                                + "&estado=" + request.getParameter("estado")
                                + "&txt_email=" + request.getParameter("txt_email")
                                + "&txt_telefone=" + request.getParameter("txt_telefone")
                                + "&txt_telefone_celular=" + request.getParameter("txt_telefone_celular")
                                + "&checkedColaborador=selected");
                        // </editor-fold>
                    }
                } else {
                    // <editor-fold defaultstate="collapsed" desc="Alerta - Email inválido">
                    response.sendRedirect("reg.jsp?err=Email inválido. Tente novamente"
                            + "&txt_nome_user=" + request.getParameter("txt_nome_user")
                            + "&txt_cpf=" + request.getParameter("txt_cpf")
                            + "&txt_rg=" + request.getParameter("txt_rg")
                            + "&orgaoemissor=" + request.getParameter("orgaoemissor")
                            + "&TXT_CEP=" + request.getParameter("TXT_CEP")
                            + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                            + "&TXT_LOGRADOURO=" + request.getParameter("TXT_LOGRADOURO")
                            + "&TXT_NUMERO=" + request.getParameter("TXT_NUMERO")
                            + "&txt_complemento=" + request.getParameter("txt_complemento")
                            + "&TXT_BAIRRO=" + request.getParameter("TXT_BAIRRO")
                            + "&TXT_CIDADE=" + request.getParameter("TXT_CIDADE")
                            + "&estado=" + request.getParameter("estado")
                            + "&txt_email=" + request.getParameter("txt_email")
                            + "&txt_telefone=" + request.getParameter("txt_telefone")
                            + "&txt_telefone_celular=" + request.getParameter("txt_telefone_celular")
                            + "&checkedColaborador=selected");
                    //</editor-fold>
                }
            } else {
                // <editor-fold defaultstate="collapsed" desc="Alerta - Senhas Diferentes">
                response.sendRedirect("reg.jsp?err=Senhas diferentes. Tente novamente"
                        + "&txt_nome_user=" + request.getParameter("txt_nome_user")
                        + "&txt_cpf=" + request.getParameter("txt_cpf")
                        + "&txt_rg=" + request.getParameter("txt_rg")
                        + "&orgaoemissor=" + request.getParameter("orgaoemissor")
                        + "&TXT_CEP=" + request.getParameter("TXT_CEP")
                        + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                        + "&TXT_LOGRADOURO=" + request.getParameter("TXT_LOGRADOURO")
                        + "&TXT_NUMERO=" + request.getParameter("TXT_NUMERO")
                        + "&txt_complemento=" + request.getParameter("txt_complemento")
                        + "&TXT_BAIRRO=" + request.getParameter("TXT_BAIRRO")
                        + "&TXT_CIDADE=" + request.getParameter("TXT_CIDADE")
                        + "&estado=" + request.getParameter("estado")
                        + "&txt_email=" + request.getParameter("txt_email")
                        + "&txt_telefone=" + request.getParameter("txt_telefone")
                        + "&txt_telefone_celular=" + request.getParameter("txt_telefone_celular")
                        + "&checkedColaborador=selected");
                // </editor-fold>
            }
        } catch (Exception ex) {
            try {
                //<editor-fold defaultstate="collapsed" desc="Algum erro - Supper Bug">
                response.sendRedirect("reg.jsp?err=" + "Supper bug"
                        + "&txt_nome_user=" + request.getParameter("txt_nome_user")
                        + "&txt_cpf=" + request.getParameter("txt_cpf")
                        + "&txt_rg=" + request.getParameter("txt_rg")
                        + "&orgaoemissor=" + request.getParameter("orgaoemissor")
                        + "&TXT_CEP=" + request.getParameter("TXT_CEP")
                        + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                        + "&TXT_LOGRADOURO=" + request.getParameter("TXT_LOGRADOURO")
                        + "&TXT_NUMERO=" + request.getParameter("TXT_NUMERO")
                        + "&txt_complemento=" + request.getParameter("txt_complemento")
                        + "&TXT_BAIRRO=" + request.getParameter("TXT_BAIRRO")
                        + "&TXT_CIDADE=" + request.getParameter("TXT_CIDADE")
                        + "&estado=" + request.getParameter("estado")
                        + "&txt_email=" + request.getParameter("txt_email")
                        + "&txt_telefone=" + request.getParameter("txt_telefone")
                        + "&txt_telefone_celular=" + request.getParameter("txt_telefone_celular")
                        + "&checkedColaborador=selected");
                // </editor-fold>
            } catch (IOException ex1) {
                Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
     /**
     * Método responsável por tratar o cadastro de usuário de uma pessoa
     * Empreendedor
     *
     * @author fernanda.franklin
     * @since 13/10/2014
     * @version 1.3
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @param HttpSession httpSession
     */
    private void processaCadastroPessoaJuridica(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        try {
            String oSenha = request.getParameter("senha_E");
            String oSenhaRepetida = request.getParameter("repSenha_E");

            if (oSenha.equals(oSenhaRepetida)) {
                String email = request.getParameter("txt_email_E");
                String razaoSocial = request.getParameter("NomeRazaoSocial");
                razaoSocial = letrasIniciaisMaiusculas(razaoSocial);
                
                boolean isEmailValido = isEmailValido(email);

                if (isEmailValido == true) {

                    String oCnpj = request.getParameter("txt_cnpj");

                    if (isCnpjValido(oCnpj)) {
                        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

                        // Verifica se usuário já existe na base
                        Usuario usuario = new Usuario();
                        Usuario usuarioBuscado = (Usuario) usuarioDAO.acessaRegistro(usuario, "usuario", oCnpj);

                        if (usuarioBuscado == null) {

                            // <editor-fold defaultstate="collapsed" desc="Retorna o grupo ao qual o usuário selecionou para iniciar seu cadastro">                          
                            GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();

                            Grupo grupoNovoUsuario = new Grupo();
                            grupoNovoUsuario = (Grupo) grupoDAO.acessaRegistro(grupoNovoUsuario, "nomeGrupo", "Empreendedor");

                            grupoDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistência de Usuário">
                            usuario.setUsuario(oCnpj);
                            usuario.setSenha(oSenhaRepetida);
                            usuario.setGrupo(grupoNovoUsuario);

                            usuarioDAO.salvar(usuario);
                            usuarioDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistencia da Empresa">
                            GenericDAO<Empresa> empresaDAO = new GenericDAO<Empresa>();
                            Empresa empresa = new Empresa();

                            empresa.setCnpj(oCnpj);
                            empresa.setRazaoSocial(razaoSocial);
                            empresa.setInscricao(request.getParameter("rg-inscricao"));
                            empresa.setGrupo(grupoNovoUsuario);

                            empresaDAO.salvar(empresa);
                            empresaDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collapsed" desc="Persistência do Endereço">
                            GenericDAO<Endereco> enderecoDAO = new GenericDAO<Endereco>();
                            Endereco endereco = new Endereco();

                            endereco.setCep(request.getParameter("TXT_CEP_E"));
                            endereco.setLogradouro(request.getParameter("TXT_LOGRADOURO_E"));
                            endereco.setNumero(Integer.parseInt(request.getParameter("TXT_NUMERO_E")));
                            endereco.setComplemento(request.getParameter("txt_complemento_E"));
                            endereco.setBairro(request.getParameter("TXT_BAIRRO_E"));
                            endereco.setMunicipio(request.getParameter("TXT_CIDADE_E"));
                            endereco.setUf(request.getParameter("CBO_UF_E"));

                            endereco.setEmpresa(empresa);

                            enderecoDAO.salvar(endereco);
                            enderecoDAO.fechar();
                            // </editor-fold>

                            // <editor-fold defaultstate="collpsed" desc="Persistência do Contato">
                            GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
                            Contato contato = new Contato();

                            contato.setEmpresa(empresa);
                            contato.setTelefoneEmpresa(request.getParameter("txt_telefone_E"));
                            contato.setEmail(email);
                            contato.setCelular(request.getParameter("txt_telefone_celular_E"));

                            contatoDAO.salvar(contato);
                            contatoDAO.fechar();
                            // </editor-fold>

                            //Cria Token / Persistência Valida
                            logControler.setValida(usuario, null, FlagStatus.ESPERANDO_AUTENTICACAO);
                            
                            Valida validaUsuario = new Valida();
                            validaUsuario = logControler.getValidaUsuario(usuario);
                            
                            // Envia email para confirmação do cadastro
                            processEnvioEmailEmpreendedor(empresa, validaUsuario, contato, request);

                            logControler.setLog(usuario, PadroesLog.CADASTRO_USUARIO);
                            
                            httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", usuario.getUsuario());

                            response.sendRedirect("cadastroFinalizado.jsp");

                        } else {
                            //<editor-fold defaultstate="collapsed" desc="Alerta - CNPJ já Cadastrado">
                            response.sendRedirect("reg.jsp?err=CNPJ já cadastrado."
                                    + "&nrs=" + request.getParameter("NomeRazaoSocial")
                                    + "&cnpj=" + request.getParameter("txt_cnpj")
                                    + "&inscricao=" + request.getParameter("rg-inscricao")
                                    + "&cep_e=" + request.getParameter("TXT_CEP_E")
                                    + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                                    + "&numero_e=" + request.getParameter("TXT_NUMERO_E")
                                    + "&complemento_e=" + request.getParameter("txt_complemento_E")
                                    + "&bairro_e=" + request.getParameter("TXT_BAIRRO_E")
                                    + "&cidade_e=" + request.getParameter("TXT_CIDADE_E")
                                    + "&estado_e=" + request.getParameter("CBO_UF_E")
                                    + "&email_e=" + request.getParameter("txt_email_E")
                                    + "&fone_e=" + request.getParameter("txt_telefone_E")
                                    + "&celular_e=" + request.getParameter("txt_telefone_celular_E")
                                    + "&checkedEmpreendedor=selected");
                            // </editor-fold>
                        }
                    } else {
                        // <<editor-fold defaultstate="collapsed" desc="Alerta - CNPJ inválido">
                        response.sendRedirect("reg.jsp?err13=CNPJ invalido tente novamente"
                                + "&nrs=" + request.getParameter("NomeRazaoSocial")
                                + "&cnpj=" + request.getParameter("txt_cnpj")
                                + "&inscricao=" + request.getParameter("rg-inscricao")
                                + "&cep_e=" + request.getParameter("TXT_CEP_E")
                                + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                                + "&numero_e=" + request.getParameter("TXT_NUMERO_E")
                                + "&complemento_e=" + request.getParameter("txt_complemento_E")
                                + "&bairro_e=" + request.getParameter("TXT_BAIRRO_E")
                                + "&cidade_e=" + request.getParameter("TXT_CIDADE_E")
                                + "&estado_e=" + request.getParameter("CBO_UF_E")
                                + "&email_e=" + request.getParameter("txt_email_E")
                                + "&fone_e=" + request.getParameter("txt_telefone_E")
                                + "&celular_e=" + request.getParameter("txt_telefone_celular_E")
                                + "&checkedEmpreendedor=selected");
                        // </editor-fold>
                    }
                } else {
                    // <editor-fold defaultstate="collapsed" desc="Alerta - Email inválido">
                    response.sendRedirect("reg.jsp?err=Email inválido"
                            + "&nrs=" + request.getParameter("NomeRazaoSocial")
                            + "&cnpj=" + request.getParameter("txt_cnpj")
                            + "&inscricao=" + request.getParameter("rg-inscricao")
                            + "&cep_e=" + request.getParameter("TXT_CEP_E")
                            + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                            + "&numero_e=" + request.getParameter("TXT_NUMERO_E")
                            + "&complemento_e=" + request.getParameter("txt_complemento_E")
                            + "&bairro_e=" + request.getParameter("TXT_BAIRRO_E")
                            + "&cidade_e=" + request.getParameter("TXT_CIDADE_E")
                            + "&estado_e=" + request.getParameter("CBO_UF_E")
                            + "&email_e=" + request.getParameter("txt_email_E")
                            + "&fone_e=" + request.getParameter("txt_telefone_E")
                            + "&celular_e=" + request.getParameter("txt_telefone_celular_E")
                            + "&checkedEmpreendedor=selected");
                    // </editor-fold>
                }
            } else {
                // <editor-fold defaultstate="collapsed" desc="Alerta - Senhas Diferentes">
                response.sendRedirect("reg.jsp?err=Senha diferentes. Tente novamente."
                        + "&nrs=" + request.getParameter("NomeRazaoSocial")
                        + "&cnpj=" + request.getParameter("txt_cnpj")
                        + "&inscricao=" + request.getParameter("rg-inscricao")
                        + "&cep_e=" + request.getParameter("TXT_CEP_E")
                        + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                        + "&numero_e=" + request.getParameter("TXT_NUMERO_E")
                        + "&complemento_e=" + request.getParameter("txt_complemento_E")
                        + "&bairro_e=" + request.getParameter("TXT_BAIRRO_E")
                        + "&cidade_e=" + request.getParameter("TXT_CIDADE_E")
                        + "&estado_e=" + request.getParameter("CBO_UF_E")
                        + "&email_e=" + request.getParameter("txt_email_E")
                        + "&fone_e=" + request.getParameter("txt_telefone_E")
                        + "&celular_e=" + request.getParameter("txt_telefone_celular_E")
                        + "&checkedEmpreendedor=selected");
                // </editor-fold>
            }
        } catch (Exception ex) {
            try {
                //<editor-fold defaultstate="collapsed" desc="Algum erro - Supper Bug Empreendedor">
                response.sendRedirect("reg.jsp?err=" + "Erro de Cadastro - Empreendedor"
                        + "&nrs=" + request.getParameter("NomeRazaoSocial")
                        + "&cnpj=" + request.getParameter("txt_cnpj")
                        + "&inscricao=" + request.getParameter("rg-inscricao")
                        + "&cep_e=" + request.getParameter("TXT_CEP_E")
                        + "&logradouro_e=" + request.getParameter("TXT_LOGRADOURO_E")
                        + "&numero_e=" + request.getParameter("TXT_NUMERO_E")
                        + "&complemento_e=" + request.getParameter("txt_complemento_E")
                        + "&bairro_e=" + request.getParameter("TXT_BAIRRO_E")
                        + "&cidade_e=" + request.getParameter("TXT_CIDADE_E")
                        + "&estado_e=" + request.getParameter("CBO_UF_E")
                        + "&email_e=" + request.getParameter("txt_email_E")
                        + "&fone_e=" + request.getParameter("txt_telefone_E")
                        + "&celular_e=" + request.getParameter("txt_telefone_celular_E")
                        + "&checkedEmpreendedor=selected");
                // </editor-fold>
            } catch (IOException ex1) {
                Logger.getLogger(Cadastro.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    
    /**
     * Metodo para envio de email de boas vindas com link para autenticação de
     * conta para o usuario, com perfil Colaborador, recém-cadastrado.
     *
     * @author macario.granja
     * @param Pessoa oPessoa
     * @since 27/08/2014
     */
    private void processaEnvioEmailColaborador(Pessoa usuarioPessoaFisica, Valida validaUsuario, Contato contatoPessoa, HttpServletRequest request) {

        String filePath = "/envioEmailAutCadastro.html";
        File file;
        String emailMessage = "";
        try {
            // <editor-fold defaultstate="collapsed" desc="Declarações de servlets e String Builder para ler o HTML do email">
            ServletContext servletContext = request.getSession().getServletContext();
            String relativeWebPath = filePath;
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            file = new File(absoluteDiskPath);

            FileInputStream is;
            is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // </editor-fold>
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count == 38) {
                    sb.append(line).append(usuarioPessoaFisica.getNome());
                } else if (count == 48) {
                    sb.append(line).append("http://" + ipServer + ":8080/cabure/AutenticarConta.do?token=")
                            .append(validaUsuario.getToken());
                } else {
                    sb.append(line).append("\n");
                }
            }
            emailMessage = sb.toString();
            is.close();       
        } catch (IOException ex1) {
            System.out.println("Certso: " + ex1);
        } catch (Exception xos) {
            System.out.println("Certso: " + xos);
        }

        Thread en;
        en = new Thread(new SendEmailMultiThread(contatoPessoa.getEmail(), "Bem-vindo ao Caburé", emailMessage));
        en.start();

    }

    /**
     * Metodo para envio de email de boas vindas com link para autenticação de
     * conta para o usuario, com perfil Empreendedor, recém-cadastrado.
     *
     * @author macario.granja
     * @param Pessoa oPessoa
     * @since 27/08/2014
     */
    private void processEnvioEmailEmpreendedor(Empresa usuarioEmpresa, Valida validaUsuario, Contato contatoEmpresa, HttpServletRequest request) {

        String filePath = "/envioEmailAutCadastro.html";
        File file;
        String emailMessage = "";
        try {
            // <editor-fold defaultstate="collapsed" desc="Declarações de servlets e String Builder para ler o HTML do email">
            ServletContext servletContext = request.getSession().getServletContext();
            String relativeWebPath = filePath;
            String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
            file = new File(absoluteDiskPath);

            FileInputStream is;
            is = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // </editor-fold>    
            
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count == 38) {
                    sb.append(line).append(usuarioEmpresa.getRazaoSocial());
                } else if (count == 48) {
                    sb.append(line).append("http://" + ipServer + "/cabure/AutenticarConta.do?token=")
                            .append(validaUsuario.getToken());
                } else {
                    sb.append(line).append("\n");
                }
            }
            emailMessage = sb.toString();
            is.close();
        } catch (IOException ex1) {
            System.out.println("Certso: " + ex1);
        } catch (Exception xos) {
            System.out.println("Certso: " + xos);
        }

        Thread en;
        en = new Thread(new SendEmailMultiThread(contatoEmpresa.getEmail(), "Bem-vindo ao Caburé", emailMessage));
        en.start();

    }
    
    /**
     * Método para validação do cpf do paciente pelo algoritmo da receita
     * federal.
     *
     * @author macario.granja
     * @since 20/08/2014
     * @version 1.1
     * @param request servlet request
     * @param response servlet response
     *
     */
    private boolean isCpfValido(String strCpf) {
        strCpf = strCpf.replace('.', ' ');//onde há ponto coloca espaço
        strCpf = strCpf.replace('/', ' ');//onde há barra coloca espaço
        strCpf = strCpf.replace('-', ' ');//onde há traço coloca espaço
        strCpf = strCpf.replaceAll(" ", "");//retira espaço
        if (strCpf.equals("00000000000") || strCpf.equals("11111111111") || strCpf.equals("22222222222")
                || strCpf.equals("33333333333") || strCpf.equals("44444444444") || strCpf.equals("55555555555")
                || strCpf.equals("66666666666") || strCpf.equals("77777777777") || strCpf.equals("88888888888")
                || strCpf.equals("99999999999")) {
            return false;
        }
        if (strCpf.equals("")) {
            return false;
        }
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;
        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;
        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(strCpf.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * digitoCPF;
            d2 = d2 + (12 - nCount) * digitoCPF;
        }
        resto = (d1 % 11);
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }
        d2 += 2 * digito1;
        resto = (d2 % 11);
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
        return nDigVerific.equals(nDigResult);
    }

    /**
     *
     * Método de validação do CNPJ no lado do servidor
     *
     * @author macario.granja
     * @since 20/08/2014
     * @version 1.1
     * @param cnpj
     * @return
     */
    private boolean isCnpjValido(String cnpj) {
        if (!cnpj.substring(0, 1).equals("")) {
            try {
                cnpj = cnpj.replace('.', ' ');//onde há ponto coloca espaço
                cnpj = cnpj.replace('/', ' ');//onde há barra coloca espaço
                cnpj = cnpj.replace('-', ' ');//onde há traço coloca espaço
                cnpj = cnpj.replaceAll(" ", "");//retira espaço
                int soma = 0, dig;
                String cnpj_calc = cnpj.substring(0, 12);

                if (cnpj.length() != 14) {
                    return false;
                }

                char[] chr_cnpj = cnpj.toCharArray();

                /* Primeira parte */
                for (int i = 0; i < 4; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                    }
                }

                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                    }
                }

                dig = 11 - (soma % 11);
                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

                /* Segunda parte */
                soma = 0;

                for (int i = 0; i < 5; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                    }
                }

                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                    }
                }

                dig = 11 - (soma % 11);

                cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);

                return cnpj.equals(cnpj_calc);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
 
    /**
     * Método para validação do email fornecido pelo usuario na hora do cadastro
     *
     * @author macario.granja
     * @param String email
     * @since 27/08/2014
     */
    private boolean isEmailValido(String email) {
        if (email != null) {
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            Matcher m = p.matcher(email);
            boolean matchFound = m.matches();
            if (matchFound) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("parametro email null");
            return false;
        }
    }

    /**
     * Processa dados para habilitar as letra inicias de uma String para
     * maiusculas
     *
     * @author macario.granja
     * @param oNome
     * @since 27/08/2014
     */
    public String letrasIniciaisMaiusculas(String oNome) {
        String frase = oNome;
        frase = frase.toLowerCase();
        StringBuffer frase2 = new StringBuffer(frase);
        for (int i = 0; i < frase2.length(); i++) {
            Character letra = frase2.charAt(i);
            if (i == 0) {
                letra = Character.toUpperCase(letra);
                frase2.setCharAt(i, letra);
            } else if ((i > 0) && (frase2.charAt(i - 1) == ' ')) {
                letra = Character.toUpperCase(letra);
                frase2.setCharAt(i, letra);
            }
        }
        frase = frase2.toString();
        return frase;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

       // String operacao = request.getParameter("operacao");
        String idUsuario = request.getParameter("idUsuario");

        if (idUsuario != null) {
            //processaAtivacaoUsuario(request, response, httpSession);
            System.out.println("Entrou no processo da requisição");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

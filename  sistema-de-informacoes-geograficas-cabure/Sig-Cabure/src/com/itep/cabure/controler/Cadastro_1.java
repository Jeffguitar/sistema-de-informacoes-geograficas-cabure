package com.itep.cabure.controler;

//import com.itep.cabure.dao.ContatoDAO;
//import com.itep.cabure.dao.EnderecoDAO;
//import com.itep.cabure.dao.EmpresaDAO;
//import com.itep.cabure.dao.GrupoDAO;
//import com.itep.cabure.dao.PessoaDAO;
//import com.itep.cabure.dao.UsuarioDAO;
import com.itep.cabure.dao.GenericDAO;
import com.itep.cabure.email.SendEmailMultiThread;
import com.itep.cabure.entidades.Contato;
import com.itep.cabure.entidades.Endereco;
import com.itep.cabure.entidades.Pessoa;
import com.itep.cabure.entidades.Empresa;
import com.itep.cabure.entidades.Grupo;
import com.itep.cabure.entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @description Servlet responsável pelo cadastro de usuários
 * @author macario.granja
 * @since 22/08/2014
 * @version 1.1?
 */
public class Cadastro_1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @description Classe respons?vel pela persistencia do dado do usu?rio e
     * encaminhamento dos dados de email.
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
            processaCadastroColaborador(request, response, httpSession);
        } else if (operacao.equals("empreendedor")) {
            processaCadastroEmpreendedor(request, response, httpSession);
        } else {
            response.sendRedirect("reg.jsp?err=Operação inválida");
        }
    }

    /*
     * Método para validação do email forneceido pelo paciente na hora do
     * cadastro
     *
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
     * @param String nome
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

    /**
     * Metodo para envio de email para usuario que se cadastra
     *
     * @param Pessoa oPessoa
     * @since 27/08/2014
     */
    private void processEnvioEmailColaborador(Pessoa oUsuarioIndividual, Contato oContato) {
        //iniciando protocolo para
        Thread en;
        StringBuilder r = new StringBuilder();
        //Cabecalho
        r.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        r.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head>");
        r.append("<body>");
        //Corpo
        r.append("<div style=\"width: 150px; height: 400px;margin-left: 10px;margin-top: 0px;margin-right: 0px;float:left;border: solid 0px #000; text-align: center;\">");
        r.append("<div style=\"width: 550px; height: 220px;margin-left: 10px;margin-top: 0px;margin-right: 0px;float:left border: solid 0px #000;} #pol{color: #006400;\">");
        r.append("<h2 id=\"pol\">Bem Vindo!</h2>");
        r.append("Caro(a) ").append(oUsuarioIndividual.getNome()).append(".<br><br>");
        r.append("Você está cadastrado(a) no nosso sistema ");
        r.append("<a href=").append("http://www.cabure.cprh.pe.gov.br/").append(">CPRH - Caburé</a> ");
        r.append("e seus dados estão armazenados.<BR><BR>");
        //Rodape
        r.append("<div style=\"color: #607B8B;\">");
        r.append("Atenciosamente,<br>Equipe Caburé");
        r.append("<br><a href=\"mailto:#\">suporte@ugeo.itep.br</a>");
        r.append("<br>Telefone: (81) 3333-9999");
        r.append("</div></body></html>");

        String emailMessage = r.toString();
        en = new Thread(new SendEmailMultiThread(oContato.getEmail(), "Confirmação de Cadastro de Usuário", emailMessage));
        en.start();
    }

    /**
     * Metodo para envio de email para usuario que se cadastra
     *
     * @param Pessoa oPessoa
     * @since 27/08/2014
     */
    private void processEnvioEmailEmpreendedor(Empresa oUsuarioIndividual, Contato oContato) {
        //iniciando protocolo para
        Thread en;
        StringBuilder r = new StringBuilder();
        //Cabecalho
        r.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        r.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head>");
        r.append("<body>");
        //Corpo
        r.append("<div style=\"width: 150px; height: 400px;margin-left: 10px;margin-top: 0px;margin-right: 0px;float:left;border: solid 0px #000; text-align: center;\">");
        r.append("<div style=\"width: 550px; height: 220px;margin-left: 10px;margin-top: 0px;margin-right: 0px;float:left border: solid 0px #000;} #pol{color: #006400;\">");
        r.append("<h2 id=\"pol\">Bem Vindo!</h2>");
        r.append("Caro(a) ").append(oUsuarioIndividual.getRazaoSocial()).append(".<br><br>");
        r.append("Sua empresa está cadastrado(a) no Caburé");
        r.append("<a href=").append("http://www.cabure.cprh.pe.gov.br/").append("> CPRH - Caburé </a>");
        r.append("e seus dados estão armazenados.<BR><BR>");
        //Rodape
        r.append("<div style=\"color: #607B8B;\">");
        r.append("Atenciosamente,<br>Equipe Caburé");
        r.append("<br><a href=\"mailto:#\">suporte@ugeo.itep.br</a>");
        r.append("<br>Telefone: (81) 3333-9999");
        r.append("</div></body></html>");

        String emailMessage = r.toString();
        en = new Thread(new SendEmailMultiThread(oContato.getEmail(), "Confirmação de Cadastro de Usuário", emailMessage));
        en.start();
    }

    /**
     * Método responsável por tratar o cadastro de usuario de uma pessoa
     * Colaborador
     *
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @param HttpSession httpSession
     */
    private void processaCadastroColaborador(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        try {
            String oSenha = request.getParameter("senha");
            String oSenhaRepetida = request.getParameter("repSenha");

            if (oSenha.equals(oSenhaRepetida)) {
                String email = request.getParameter("txt_email");
                String nome = request.getParameter("txt_nome_user");
                nome = letrasIniciaisMaiusculas(nome);
                System.out.println("Problemas - Saída email");
                boolean emailValido = isEmailValido(email);
                if (emailValido == true) {
                    String oCpf = request.getParameter("txt_cpf");
                    System.out.println("al +++++++++++");

                    if (isCpfValido(oCpf) == true) {
                        System.out.println("x2 +++++++++++");

                        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

                        // Verifica se usuário já existe na base
                        Usuario usuario = new Usuario();
                        Usuario usuarioBuscado = (Usuario) usuarioDAO.acessaRegistro(usuario, "usuario", oCpf);

                        //usuarioDAO.fechar();
                        //UsuarioDAO daouser = new UsuarioDAO();
                        //Usuario allUser = daouser.busca(oCpf);
                        // daouser.fechar();
                        System.out.println("x1 +++++++++++");

                        if (usuarioBuscado == null) {
                            System.out.println("Usuario Buscado é Nulo");
                            // Busca grupo do usuário                           
                            GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();
                            Grupo grupoNovoUsuario = new Grupo();

                            // Retorna o grupo cujo nome é igual à Colaborador
                            Grupo grupo = new Grupo();
                            grupoNovoUsuario = (Grupo) grupoDAO.acessaRegistro(grupo, "grupo", "Colaborador");

                            grupoDAO.fechar();

                            System.out.println("Passou por busca de grupo");

                            // Persistência Usuário
                            usuario.setUsuario(oCpf);
                            usuario.setSenha(oSenhaRepetida);
                            usuario.setGrupo(grupoNovoUsuario);

                            usuarioDAO.salvar(usuario);

                            usuarioDAO.fechar();

                            System.out.println("Passou por cadastro de usuario");

                            //Persistencia da Pessoa
                            GenericDAO<Pessoa> pessoaDAO = new GenericDAO<Pessoa>();
                            Pessoa usuarioIndividual = new Pessoa();

                            usuarioIndividual.setCpf(oCpf);
                            usuarioIndividual.setNome(nome);
                            usuarioIndividual.setOrgaoEmissor(request.getParameter("orgaoemissor"));
                            usuarioIndividual.setRg(request.getParameter("txt_rg"));
                            usuarioIndividual.setGrupo(grupoNovoUsuario);

                            pessoaDAO.salvar(usuarioIndividual);
                            pessoaDAO.fechar();

                            System.out.println("Passou por cadastro de pessoa");

                            //persistir endereco
                            GenericDAO<Endereco> enderecoDAO = new GenericDAO<Endereco>();
                            Endereco endereco = new Endereco();

                            endereco.setCep(request.getParameter("TXT_CEP"));
                            endereco.setLogradouro(request.getParameter("TXT_LOGRADOURO"));
                            endereco.setNumero(Integer.parseInt(request.getParameter("TXT_NUMERO")));
                            endereco.setComplemento(request.getParameter("txt_complemento"));
                            endereco.setBairro(request.getParameter("TXT_BAIRRO"));
                            endereco.setMunicipio(request.getParameter("TXT_CIDADE"));
                            endereco.setUf(request.getParameter("CBO_UF"));

                            endereco.setPessoa(usuarioIndividual);

                            enderecoDAO.salvar(endereco);
                            enderecoDAO.fechar();
                            System.out.println("passou por cadastro de endereco");

                            //persistencia do contato
                            GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
                            Contato contato = new Contato();// daoContato.busca(nomeLogin);
                            contato.setPessoa(usuarioIndividual);
                            contato.setTelefoneEmpresa(request.getParameter("txt_telefone"));
                            contato.setEmail(email);
                            contato.setCelular(request.getParameter("txt_telefone_celular"));
                            contato.setFax(null);

                            contatoDAO.salvar(contato);
                            contatoDAO.fechar();

                            //Persistencia do Grupo Iniciando o envio de email.
                            processEnvioEmailColaborador(usuarioIndividual, contato);

                            //Se tudo estiver tranquilo ele redireciona para a pagina inicial
                            httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", usuarioIndividual.getCpf());
                            httpSession.setAttribute("SESSION_AUTORIZADO_LOGADO", contato.getEmail());

                            response.sendRedirect("index.jsp");
                        } else {
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
                                    + "&checkedColaborador=checked");
                        }
                    } else {
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
                                + "&checkedColaborador=checked");
                    }
                } else {
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
                            + "&checkedColaborador=checked"
                    );
                }
            } else {
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
                        + "&checkedColaborador=checked"
                );
            }
        } catch (Exception ex) {
            try {
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
                        + "&checkedColaborador=checked"
                );
            } catch (IOException ex1) {
                Logger.getLogger(Cadastro_1.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /**
     * Método responsável por tratar o cadastro de usuário de uma pessoa
     * Empreendedor
     *
     * @param HttpServletRequest request
     * @param HttpServletResponse response
     * @param HttpSession httpSession
     */
    private void processaCadastroEmpreendedor(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
        try {
            String oSenha = request.getParameter("senha_E");
            String oSenhaRepetida = request.getParameter("repSenha_E");

            if (oSenha.equals(oSenhaRepetida)) {

                String email = request.getParameter("txt_email_E");
                String razaoSocial = request.getParameter("NomeRazaoSocial");
                razaoSocial = letrasIniciaisMaiusculas(razaoSocial);
                boolean emailValido = isEmailValido(email);

                if (emailValido == true) {

                    String oCnpj = request.getParameter("txt_cnpj");

                    if (isCnpjValido(oCnpj) == true) {
                        System.out.println("lo");

                        GenericDAO<Usuario> usuarioDAO = new GenericDAO<Usuario>();

                        // Verifica se usuário já existe na base
                        Usuario usuario = new Usuario();
                        usuario = (Usuario) usuarioDAO.acessaRegistro(usuario, "usuario", oCnpj);

                        System.out.println("pou");
                        if (usuario == null) {
                            System.out.println("Entrou no Cnpj " + usuario.getUsuario());

                            // Persistencia do Grupo                            
                            // Busca grupo do usuário                           
                            GenericDAO<Grupo> grupoDAO = new GenericDAO<Grupo>();
                            Grupo grupoNovoUsuario = new Grupo();

                            // Retorna o grupo cujo nome é igual à Colaborador
                            grupoNovoUsuario = (Grupo) grupoDAO.acessaRegistro(grupoNovoUsuario, "grupo", "Colaborador");

                            grupoDAO.fechar();

                            System.out.println("la");

                            // Persistência Usuário
                            usuario.setUsuario(oCnpj);
                            usuario.setSenha(oSenhaRepetida);
                            usuario.setGrupo(grupoNovoUsuario);

                            usuarioDAO.salvar(usuario);

                            usuarioDAO.fechar();

                            // persistência da do da empresa
                            //Persistencia da Pessoa
                            GenericDAO<Empresa> empresaDAO = new GenericDAO<Empresa>();
                            Empresa empresa = new Empresa();

                            empresa.setCnpj(oCnpj);
                            empresa.setRazaoSocial(razaoSocial);
                            empresa.setInscricao(request.getParameter("rg-inscricao"));
                            empresa.setGrupo(grupoNovoUsuario);

                            empresaDAO.salvar(empresa);
                            empresaDAO.fechar();

                            System.out.println("alaqqw3al +++++++++++");

                            //persistir endereco
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

                            //persistencia do contato
                            GenericDAO<Contato> contatoDAO = new GenericDAO<Contato>();
                            Contato contato = new Contato();// daoContato.busca(nomeLogin);
                            contato.setEmpresa(empresa);
                            contato.setTelefoneEmpresa(request.getParameter("txt_telefone"));
                            contato.setEmail(email);
                            contato.setCelular(request.getParameter("txt_telefone_celular"));
                            contato.setFax(null);

                            contatoDAO.salvar(contato);
                            contatoDAO.fechar();

                            // Persistencia do Grupo Iniciando o envio de email.
                            processEnvioEmailEmpreendedor(empresa, contato);

                            // Se tudo estiver tranquilo ele redireciona para a pagina inicial
                            httpSession.setAttribute("SESSION_IDENTIFICADOR_LOGADO", empresa.getCnpj());
                            response.sendRedirect("index.jsp");

                        } else {
                            response.sendRedirect("reg.jsp?err=Cpf já cadastrado."
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
                                    + "&checkedEmpreendedor=checked");
                        }
                    } else {
                        response.sendRedirect("reg.jsp?err13=Cpf invalido tente novamente"
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
                                + "&checkedEmpreendedor=checked"
                        );
                    }
                } else {
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
                            + "&checkedEmpreendedor=checked"
                    );
                }
            } else {
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
                        + "&checkedEmpreendedor=checked"
                );
            }
        } catch (Exception ex) {
            try {
                response.sendRedirect("reg.jsp?err=" + "Supper bug Empreendedor"
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
                        + "&checkedEmpreendedor=checked");
            } catch (IOException ex1) {
                Logger.getLogger(Cadastro_1.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    /**
     * Método para validação do cpf do paciente pelo algoritmo da receita
     * federal.
     *
     * @param request servlet request
     * @param response servlet response
     * @since 27/08/2014
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
}

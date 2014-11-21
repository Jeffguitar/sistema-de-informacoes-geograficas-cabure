package com.itep.cabure.email;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 * Classe clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp
 * @author Macário Granja
 * @since 22/08/2014
 * @version 1.0?
 */
public class SendMail {
    private String mailSMTPServer = "smtp.gmail.com";
    private String mailSMTPServerPort= "465";
    private String emailOrigem = "suporte.ugeo@gmail.com";
    private String senha = "piclespicles";
    
    private String assunto = null;
    private String corpo = null;
    
    /*
     * quando instanciar um Objeto ja sera atribuido o servidor SMTP do GMAIL
     * e a porta usada por ele
     */
    public SendMail() {} //Para o GMAIL
    
    public SendMail(String oAssunto, String oCorpo) { //Para o GMAIL
        this.assunto = oAssunto; 
        this.corpo = oCorpo;
    }

    public void sendMailAnexo(String to, String subject, String message, String path) throws SendFailedException {
        String from = "suporte.ugeo@gmail.com";
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Cria um autenticador que sera usado a seguir
        SimpleAuth auth = new SimpleAuth(emailOrigem, senha);

        /* Session - objeto que ira realizar a conexão com o servidor
         * Como há necessidade de autenticação é criada uma autenticacao que
         * é responsavel por solicitar e retornar o usuário e senha para
         * autenticação
         */
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(false); //Habilita o LOG das ações executadas durante o envio do email

        //Objeto que contém a mensagem
        MimeMessage msg = new MimeMessage(session);
        try {

            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//Setando o destinatário
            msg.setFrom(new InternetAddress(from, "Cabure"));//Setando a origem do email
            msg.setSubject(subject);//Setando o assunto
            msg.setContent(message, "text/html; charset=iso-8859-1");//Setando o conteúdo/corpo do email

            BodyPart textPart = new MimeBodyPart();
            textPart.setContent(message, "text/html; charset=iso-8859-1");

            BodyPart attachFilePart = new MimeBodyPart();
            FileDataSource fds = new FileDataSource(path);
            attachFilePart.setDataHandler(new DataHandler(fds));
            attachFilePart.setFileName(fds.getName());

            Multipart mp = new MimeMultipart();
            mp.addBodyPart(textPart);
            mp.addBodyPart(attachFilePart);

            msg.setContent(mp);
        } catch (Exception e) {
            System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }

        Transport tr;//Objeto encarregado de enviar os dados para o email
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario do gmail
             *  3 - sua senha do gmail
             */
            tr.connect(mailSMTPServer, emailOrigem, senha);
            msg.saveChanges(); // don't forget this
            //envio da mensagem
            tr.sendMessage(msg, msg.getAllRecipients());
            tr.close();
        } catch (javax.mail.MessagingException eme) {
            System.out.println("Email invalido!!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }
    }
    //Envio de email do cadastro
    public void envioMailSemAnexo(String to, String subject, String message) throws SendFailedException {
        String from = "suporte.ugeo@gmail.com";
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", emailOrigem); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Cria um autenticador que sera usado a seguir
        SimpleAuth auth = new SimpleAuth(emailOrigem, senha);

        /* Session - objeto que ira realizar a conexão com o servidor
         * Como há necessidade de autenticação é criada uma autenticacao que
         * é responsavel por solicitar e retornar o usuário e senha para
         * autenticação
         */
        Session session = Session.getInstance(props, auth);
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email
        Message msg = new MimeMessage(session);        //Objeto que contém a mensagem
        try {
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//Setando o destinatário
            //msg.addRecipient(Message.RecipientType.CC, new InternetAddress("first.lady@whitehouse.gov"));//Setando o destinatário com compia
            //msg.addRecipient(Message.RecipientType.BCC, new InternetAddress("first.lady@whitehouse.gov"));//Setando o destinatário com copia oculta
            msg.setFrom(new InternetAddress(from, "Cabure"));//Setando a origem do email
            msg.setSubject(subject);//Setando o assunto
            msg.setContent(message, "text/html; charset=iso-8859-1");//Setando o conteúdo/corpo do email

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html; charset=iso-8859-1");

            Multipart mutipart = new MimeMultipart();
            mutipart.addBodyPart(messageBodyPart);

            msg.setContent(mutipart);
        } catch (NullPointerException ne) {
            System.out.println("Excecao envioMailSemAnexo: " + ne.toString());
        } catch (SendFailedException sfe) {
            System.out.println("Excecao envioMailSemAnexo: " + sfe.toString());
        } catch (Exception e) {
            System.out.println(">> Erro envioMailSemAnexo: Completar Mensagem");
            e.printStackTrace();
        }

        //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario do gmail
             *  3 - sua senha do gmail
             */
            tr.connect(mailSMTPServer, emailOrigem, senha);
            msg.saveChanges(); // don't forget this
            tr.sendMessage(msg, msg.getAllRecipients());//envio da mensagem
            tr.close();
        } catch (javax.mail.MessagingException eme) {
            System.out.println("Email invalido!!");
            System.out.println(">> Erro: Envio Mensagem");
            eme.printStackTrace();
        }
    }

    public void envioMailSemAnexoCC(String to, String subject, String message) throws SendFailedException {
        String from = "suporte.ugeo@gmail.com";
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL
        props.put("mail.smtp.auth", "true"); //ativa autenticacao
        props.put("mail.smtp.user", from); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", mailSMTPServerPort); //porta
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        //Cria um autenticador que sera usado a seguir
        SimpleAuth auth = new SimpleAuth(emailOrigem, senha);

        /* Session - objeto que ira realizar a conexão com o servidor
         * Como há necessidade de autenticação é criada uma autenticacao que
         * é responsavel por solicitar e retornar o usuário e senha para
         * autenticação
         */
        Session session = Session.getDefaultInstance(props, auth);
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email
        Message msg = new MimeMessage(session);        //Objeto que contém a mensagem
        try {
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//Setando o destinatário
            
            msg.setFrom(new InternetAddress(from, "Cabure"));//Setando a origem do email
            msg.setSubject(subject);//Setando o assunto
            msg.setContent(message, "text/plain; iso-8859-1");//Setando o conteúdo/corpo do email

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(message, "text/html");

            Multipart mutipart = new MimeMultipart();
            mutipart.addBodyPart(messageBodyPart);

            msg.setContent(mutipart);
        } catch (Exception e) {
            System.out.println(">> Erro: Completar Mensagem");
            e.printStackTrace();
        }

        //Objeto encarregado de enviar os dados para o email
        Transport tr;
        try {
            tr = session.getTransport("smtp"); //define smtp para transporte
            /*
             *  1 - define o servidor smtp
             *  2 - seu nome de usuario do gmail
             *  3 - sua senha do gmail
             */
            tr.connect(mailSMTPServer, emailOrigem, senha);
            msg.saveChanges(); // don't forget this
            tr.sendMessage(msg, msg.getAllRecipients());//envio da mensagem
            tr.close();
        } catch (javax.mail.MessagingException eme) {
            System.out.println("Email invalido!!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println(">> Erro: Envio Mensagem");
            e.printStackTrace();
        }
    }
}

class SimpleAuth extends Authenticator {

    public String username = null;
    public String password = null;

    public SimpleAuth(String user, String pwd) {
        username = user;
        password = pwd;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
    }
}

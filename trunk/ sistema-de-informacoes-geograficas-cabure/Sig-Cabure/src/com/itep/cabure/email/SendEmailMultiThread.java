/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itep.cabure.email;

import javax.mail.SendFailedException;

/**
 * Classe responsavel pelo envio de email implementando multiThread
 * @author Macário Granja
 * @since 22/08/2014
 * envio.cabure@cprh.pe.gov.br
 * @version 1.0?
 * 
 */
public class SendEmailMultiThread implements Runnable{
    
    private String to;
    private String assunto;
    private String corpo;

    public SendEmailMultiThread(String oTo, String oAssunto, String oCorpo){
        this.to = oTo;
        this.assunto = oAssunto;
        this.corpo = oCorpo;
    }
    
    public void run(){
    SendMail sm = new SendMail();
        try {
            sm.envioMailSemAnexo(to, assunto, corpo);
        } catch (SendFailedException ex) {
            System.out.println("MultiThread erro: "+ ex.toString());
        }
    }
}

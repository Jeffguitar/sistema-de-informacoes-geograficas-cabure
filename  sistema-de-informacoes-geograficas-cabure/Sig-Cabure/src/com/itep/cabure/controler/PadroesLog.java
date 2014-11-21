/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itep.cabure.controler;

/**
 *
 * @author fernanda.franklin
 */
public enum PadroesLog {

    CADASTRO_USUARIO, //usuario cadastrado
    AUTENTICA_USUARIO, // usuario autenticado
    DESATIVA_USUARIO, // usario inativado
    ATIVA_USUARIO, // usuario reativado
    
    LOGIN, // logon 
    LOGOUT, // logout
    
    FALHA, // falha + erro
    SENHA_ALTERADA, //senha alterada - usuário altera senha no seu perfil
    NOVA_SENHA_SOLICITADA; //senha recuperada - usuário confirma envio de email para nova senha
; 
      
}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS.exceptions;

import javax.xml.ws.WebFault;

/**
 *
 * @author rodrigomelo
 */
@WebFault(name = "PublicacaoFault")
public class PublicacaoException extends Exception {
    
    public PublicacaoException(String mensagem){
        super(mensagem);
    }
    
    public String getFaultInfo(){
        return "Requisição inválida";
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS.exceptions;

import javax.xml.ws.WebFault;

/**
 * Classe para tratamento de exceções de Autor
 * @author rodrigomelo
 * 
 */
@WebFault(name = "AutorFault")
public class AutorException extends Exception {

    public AutorException(String mensagem) {
        super(mensagem);
    }

    public String getFaultInfo() {
        return "Requisição inválida";
    }

}

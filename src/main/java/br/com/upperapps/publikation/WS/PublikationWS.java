/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS;

import br.com.upperapps.publikation.Autor;
import br.com.upperapps.publikation.session.AutorFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author rodrigomelo
 */
@WebService(serviceName = "PublikationWS")
public class PublikationWS {

    @EJB
    private AutorFacade autorFacade;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Operação que retorna uma lista de autores.
     * @return 
     */
    
    @WebMethod(operationName = "listaAutores")
    public List<Autor> listaAutores() {
        List<Autor> autores = new ArrayList<>();
        
        autores = autorFacade.findAll();
        
        return autores;
    }
}

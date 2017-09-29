/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS;

import br.com.upperapps.publikation.Autor;
import br.com.upperapps.publikation.Publicacao;
import br.com.upperapps.publikation.session.AutorFacade;
import br.com.upperapps.publikation.session.PublicacaoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author rodrigomelo
 */
@WebService(serviceName = "PublikationWS")
public class PublikationWS {

    @EJB
    private AutorFacade autorFacade;
    
    @EJB
    private PublicacaoFacade publicacaoFacade;


    /**
     * Operação que retorna uma lista de autores.
     * @return 
     */
    
    @WebMethod(operationName = "listarAutores")
    @WebResult(name = "autor")
    public List<Autor> listaAutores() {
        
        List<Autor> autores = new ArrayList<>();
        
        try {
          autores = autorFacade.findAll();          
        } catch (Exception e) {
          throw new RuntimeException("Erro ao listar os autores: " + e);
        }
        
        return autores;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "listarPublicacoes")
    @WebResult(name = "publicacao")
    public List<Publicacao> listaPublicacoes() {
        
        List<Publicacao> publicacoes = new ArrayList<>();
        
        try {
          publicacoes = publicacaoFacade.findAll();          
        } catch (Exception e) {
          throw new RuntimeException("Erro ao listar as publicacões: " + e);
        }
        
        return publicacoes;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "buscarAutor")
    @WebResult(name = "autor")
    public Autor buscarAutor(@WebParam(name = "id") Integer id) {
        Autor autor = new Autor();
        
        try {
            autor = autorFacade.find(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o autor de id " + id + ": " + e);
        }
        return autor;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "buscarPublicacao")
    @WebResult(name = "publicacao")
    public Publicacao buscarPublicacao(@WebParam(name = "id") Integer id) {
        Publicacao publicacao = new Publicacao();
        
        try {
            publicacao = publicacaoFacade.find(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar a publicacao de id " + id + ": " + e);
        }
        
        return publicacao;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "atualizaAutor")
    public void atualizaAutor(@WebParam(name = "autor") Autor autor) {
        try {
            autorFacade.edit(autor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o autor: " + e);
        }
        
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "atualizaPublicacao")
    public void atualizaPublicacao(@WebParam(name = "publicacao") Publicacao publicacao) {
        try {
            publicacaoFacade.edit(publicacao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar a publicação: " + e);
        }
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "deletarAutor")
    public void deletarAutor(@WebParam(name = "id") Integer id) {
        try {
            autorFacade.remove(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao remover o autor: " + e);
        }
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "deletarPublicacao")
    public String deletarPublicacao(@WebParam(name = "id") Integer id) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "buscaColaboradoresAutorTop")
    public String buscaColaboradoresAutorTop() {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "buscaReferenciasPorAutor")
    public String buscaReferenciasPorAutor(@WebParam(name = "autor") Autor autor) {
        //TODO write your implementation code here:
        return null;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "salvarAutor")
    public void salvarAutor(@WebParam(name = "autor") Autor autor) {
        try {
            autorFacade.create(autor);            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar o autor: " + e);
        }
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "salvarPublicacao")
    public String salvarPublicacao(@WebParam(name = "publicacao") Publicacao publicacao) {
        //TODO write your implementation code here:
        return null;
    }
}

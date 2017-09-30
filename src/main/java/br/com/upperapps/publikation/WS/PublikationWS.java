/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS;

import br.com.upperapps.publikation.Autor;
import br.com.upperapps.publikation.AutorTopDTO;
import br.com.upperapps.publikation.ListaAutor;
import br.com.upperapps.publikation.ListaPublicacao;
import br.com.upperapps.publikation.Publicacao;
import br.com.upperapps.publikation.WS.exceptions.AutorException;
import br.com.upperapps.publikation.session.AutorFacade;
import br.com.upperapps.publikation.session.PublicacaoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import org.eclipse.persistence.exceptions.QueryException;

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
    @WebResult(name = "autores")
    public ListaAutor listaAutores() throws AutorException {
        
        List<Autor> autores = new ArrayList<>();
        
        try {
          autores = autorFacade.findAll();          
        } catch (Exception e) {
          throw new AutorException("Erro ao listar os autores. Causa: " + e.getCause());
        }
        
        return new ListaAutor(autores);
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
    @WebMethod(operationName = "buscarAutor")
    @WebResult(name = "autor")
    public Autor buscarAutor(@WebParam(name = "id") Integer id) throws AutorException{
        Autor autor = new Autor();
        
        try {
            autor = autorFacade.find(id);
            
            if(autor == null){
                throw new AutorException("Não existe autor com este id.");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o autor. Causa: " + e.getMessage());
        }
        return autor;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "atualizaAutor")
    public void atualizaAutor(@WebParam(name = "autor") Autor autor) throws AutorException{
        try {
            autorFacade.edit(autor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o autor: " + e);
        }
        
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "deletarAutor")
    public void deletarAutor(@WebParam(name = "id") Integer id) throws AutorException{
        try {
            Autor autor = autorFacade.find(id);
            
            if(autor == null){
                throw new AutorException("O autor informado não existe.");
            }
            
            autorFacade.remove(id);
            
        } catch (EJBException e) {
            throw new AutorException("Erro ao remover o autor. Causa: " + e.getCause());
        }

    }
    
    /**
     * Operações de consulta especializadas.
     */
    
    /**
     * Retorna os colaboradores do autor com o maior nº de publicações.
     * @return AutorTop
     */
    // TODO implementar este método.
    
//    @WebMethod(operationName = "buscaColaboradoresAutorTop")
//    public AutorTopDTO buscaColaboradoresAutorTop() {
//        //TODO write your implementation code here:
//        return null;
//    }

    /**
     * Busca as referências bibliográficas de um determinado autor.
     * @param Integer autorID
     * @return List<Publicacao>
     */
    @WebMethod(operationName = "buscaReferenciasPorAutor")
    public List<Publicacao> buscaReferenciasPorAutor(@WebParam(name = "autorID") Integer autorID) throws AutorException{
        List<Publicacao> publicacoes = new ArrayList<>();
        
        try {
            publicacoes = publicacaoFacade.buscaReferenciasPorAutor(autorID);
        } catch (EJBException e) {
            throw new AutorException("Erro ao buscar referências do autor. Causa  EJBException: " + e.getCause());
        }
        
        return publicacoes;
    }
    
    /**
     * Operação de Web service
     * @return 
     * @return  
     */
    @WebMethod(operationName = "listarPublicacoes")
    @WebResult(name = "publicacoes")
    public ListaPublicacao listaPublicacoes() {
        
        List<Publicacao> publicacoes = new ArrayList<>();
        
        try {
          publicacoes = publicacaoFacade.findAll();          
        } catch (Exception e) {
          throw new RuntimeException("Erro ao listar as publicacões: " + e);
        }
        
        return new ListaPublicacao(publicacoes);
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
    @WebMethod(operationName = "deletarPublicacao")
    public String deletarPublicacao(@WebParam(name = "id") Integer id) {
        //TODO write your implementation code here:
        return null;
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

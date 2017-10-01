/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.WS;

import br.com.upperapps.publikation.Autor;
import br.com.upperapps.publikation.AutorPublicacao;
import br.com.upperapps.publikation.AutorTopDTO;
import br.com.upperapps.publikation.ListaAutor;
import br.com.upperapps.publikation.ListaPublicacao;
import br.com.upperapps.publikation.Publicacao;
import br.com.upperapps.publikation.WS.exceptions.AutorException;
import br.com.upperapps.publikation.WS.exceptions.AutorPublicacaoException;
import br.com.upperapps.publikation.WS.exceptions.PublicacaoException;
import br.com.upperapps.publikation.session.AutorFacade;
import br.com.upperapps.publikation.session.AutorPublicacaoFacade;
import br.com.upperapps.publikation.session.PublicacaoFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
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
    
    @EJB
    private AutorPublicacaoFacade autorPublicacaoFacade;

    /**
     * Operação que retorna uma lista de autores.
     *
     * @return ListaAutor (List)
     * @throws AutorException
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
     * Operação para salvar um Ator
     *
     * @param Autor autor
     * @throws AutorException
     */
    @WebMethod(operationName = "salvarAutor")
    public void salvarAutor(@WebParam(name = "autor") Autor autor) throws AutorException{
        try {
            autorFacade.create(autor);
        } catch (Exception e) {
            throw new AutorException("Erro ao salvar o autor. Causa: " + e.getCause());
        }
    }

    /**
     * Operação para busca de um Autor pelo ID
     *
     * @param Integer id
     * @return Autor
     * @throws AutorException
     */
    @WebMethod(operationName = "buscarAutor")
    @WebResult(name = "autor")
    public Autor buscarAutor(@WebParam(name = "id") Integer id) throws AutorException {
        Autor autor = new Autor();

        try {
            autor = autorFacade.find(id);

            if (autor == null) {
                throw new AutorException("Não existe autor com este id.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o autor. Causa: " + e.getMessage());
        }
        return autor;
    }

    /**
     * Operação para atualização de um Autor
     *
     * @param Autor autor
     * @throws AutorException
     */
    @WebMethod(operationName = "atualizaAutor")
    public void atualizaAutor(@WebParam(name = "autor") Autor autor) throws AutorException {
        try {
            autorFacade.edit(autor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o autor: " + e);
        }

    }

    /**
     * Operação para deletar um Autor
     *
     * @param Integer id
     * @throws AutorException
     */
    @WebMethod(operationName = "deletarAutor")
    public void deletarAutor(@WebParam(name = "id") Integer id) throws AutorException {
        try {
            Autor autor = autorFacade.find(id);

            if (autor == null) {
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
     *
     * @return AutorTopDTO
     * @throws AutorException
     */
    @WebMethod(operationName = "buscaColaboradoresAutorTop")
    @WebResult(name = "autorTop")
    public AutorTopDTO buscaColaboradoresAutorTop() throws AutorException {
        try {
            Autor autor = autorFacade.buscarAutorTop();

            List<Autor> colaboradores = autorFacade.buscaColaboradoresDoAutor(autor);

            AutorTopDTO autorTop = new AutorTopDTO(autor, colaboradores);

            return autorTop;

        } catch (Exception e) {
            throw new AutorException("Erro ao retornar o autor top e seus colaboradores. Causa: " + e.getCause());
        }
    }

    /**
     * Busca as referências bibliográficas de um determinado autor.
     *
     * @param Integer autorID
     * @return List<Publicacao>
     * @throws AutorException
     */
    @WebMethod(operationName = "buscaReferenciasPorAutor")
    public List<Publicacao> buscaReferenciasPorAutor(@WebParam(name = "autorID") Integer autorID) throws AutorException {
        List<Publicacao> publicacoes = new ArrayList<>();

        try {
            publicacoes = publicacaoFacade.buscaReferenciasPorAutor(autorID);
        } catch (EJBException e) {
            throw new AutorException("Erro ao buscar referências do autor. Causa  EJBException: " + e.getCause());
        }

        return publicacoes;
    }

    /**
     * Operações de CRUD de Publicacao.
     *
     */
    
    /**
     * Operação que retorna uma lista de publicações.
     * Foi utilizada uma classe customizada para formatar a saída do XML.
     * 
     * @return ListaPublicacao.
     * @throws PublicacaoException 
     */
    @WebMethod(operationName = "listarPublicacoes")
    @WebResult(name = "publicacoes")
    public ListaPublicacao listaPublicacoes() throws PublicacaoException {

        List<Publicacao> publicacoes = new ArrayList<>();

        try {
            publicacoes = publicacaoFacade.findAll();
        } catch (Exception e) {
            throw new PublicacaoException("Erro ao listar as publicacões. Causa: " + e.getCause());
        }

        return new ListaPublicacao(publicacoes);
    }

    /**
     * Operação para buscar publicação pelo id.
     * 
     * @param id
     * @return Publicacao
     * @throws PublicacaoException 
     */
    @WebMethod(operationName = "buscarPublicacao")
    @WebResult(name = "publicacao")
    public Publicacao buscarPublicacao(@WebParam(name = "id") Integer id) throws PublicacaoException{
        Publicacao publicacao = new Publicacao();

        try {
            publicacao = publicacaoFacade.find(id);
        } catch (Exception e) {
            throw new PublicacaoException("Erro ao buscar a publicacao de id " + id + ": " + e);
        }

        return publicacao;
    }
    
    /**
     * Operação para salvar novas publicações.
     * 
     * @param Publicacao publicacao
     * @throws PublicacaoException 
     */
    @WebMethod(operationName = "salvarPublicacao")
    public void salvarPublicacao(@WebParam(name = "publicacao") Publicacao publicacao) throws PublicacaoException{
        try {

            publicacaoFacade.create(publicacao);
            
        } catch (Exception e) {
            throw new PublicacaoException("Erro ao salvar a publicacao. Causa: " + e.getCause());
        }
    }

    /**
     * Operação para atualização de uma publicação
     * 
     * @param publicacao
     * @throws PublicacaoException 
     */
    @WebMethod(operationName = "atualizaPublicacao")
    public void atualizaPublicacao(@WebParam(name = "publicacao") Publicacao publicacao) throws PublicacaoException{
        try {
            publicacaoFacade.edit(publicacao);
        } catch (Exception e) {
            throw new PublicacaoException("Erro ao atualizar a publicação: " + e);
        }
    }

    /**
     * Operação que permite remover uma publicação.
     * 
     * @param id
     * @throws PublicacaoException 
     */
    @WebMethod(operationName = "deletarPublicacao")
    public void deletarPublicacao(@WebParam(name = "id") Integer id) throws PublicacaoException{
        try {
            Publicacao publicacao = publicacaoFacade.find(id);

            if (publicacao == null) {
                throw new PublicacaoException("A publicação informada não existe.");
            }

            publicacaoFacade.remove(id);

        } catch (EJBException e) {
            throw new PublicacaoException("Erro ao remover a publicacação. Causa: " + e.getCause());
        }
    }

    /**
     * Operação que permite associar um autor a uma publicação.
     * 
     * @param autor
     * @param publicacao
     * @throws AutorPublicacaoException 
     */
    @WebMethod(operationName = "associarAutorPublicacao")
    public void associarAutorPublicacao(@WebParam(name = "autor") Autor autor, @WebParam(name = "publicacao") Publicacao publicacao) throws AutorPublicacaoException{
        
        AutorPublicacao associacao = new AutorPublicacao();
        
        associacao.setAutor(autor);
        associacao.setPublicacao(publicacao);
        
        autorPublicacaoFacade.create(associacao);

    }
}

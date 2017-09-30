/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.session;

import br.com.upperapps.publikation.Autor;
import br.com.upperapps.publikation.Publicacao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.exceptions.QueryException;

/**
 *
 * @author rodrigomelo
 */
@Stateless
public class PublicacaoFacade extends AbstractFacade<Publicacao> {

    @PersistenceContext(unitName = "br.com.upperapps_Publikation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacaoFacade() {
        super(Publicacao.class);
    }
    
    public List<Publicacao> buscaReferenciasPorAutor(Integer autorID) throws QueryException, EJBException{
        List<Publicacao> publicacoes = new ArrayList<>();
        
        publicacoes = em.createNamedQuery("Publicacao.buscaReferenciasPorAutor")
                        .setParameter("autorID", autorID)
                        .getResultList();
        return publicacoes;
    }
    
//    public List<Publicacao> buscaReferenciasPorAutor(Autor autor) throws QueryException, EJBException{
//        List<Publicacao> publicacoes = new ArrayList<>();
//        
//        publicacoes = em.createNamedQuery("Publicacao.buscaReferenciasPorAutor")
//                        .setParameter("autor", autor)
//                        .getResultList();
//        return publicacoes;
//    }
    
}

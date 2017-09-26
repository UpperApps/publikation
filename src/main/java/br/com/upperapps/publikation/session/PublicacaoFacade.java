/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.session;

import br.com.upperapps.publikation.Publicacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}

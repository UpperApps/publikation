/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.session;

import br.com.upperapps.publikation.AutorPublicacao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rodrigomelo
 */
@Stateless
public class AutorPublicacaoFacade extends AbstractFacade<AutorPublicacao> {

    @PersistenceContext(unitName = "br.com.upperapps_Publikation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorPublicacaoFacade() {
        super(AutorPublicacao.class);
    }

}

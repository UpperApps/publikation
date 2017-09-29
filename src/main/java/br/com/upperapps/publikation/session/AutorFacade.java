/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.session;

import br.com.upperapps.publikation.Autor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author rodrigomelo
 */
@Stateless
public class AutorFacade extends AbstractFacade<Autor> {

    @PersistenceContext(unitName = "br.com.upperapps_Publikation_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AutorFacade() {
        super(Autor.class);
    }
    
    public void remove(Integer id){
        try {
            em.createNamedQuery("Autor.delete")
                .setParameter("id", id)
                .executeUpdate();
        } catch (DatabaseException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao remover o autor: " + e);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation.session;

import br.com.upperapps.publikation.Autor;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public void remove(Integer id) throws EJBException {
        em.createNamedQuery("Autor.delete")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Autor buscarAutorTop() {

        Autor autor = (Autor) em.createNativeQuery("SELECT \n"
                + "    a.*\n"
                + "FROM\n"
                + "    publikation.Autor a,\n"
                + "    (SELECT \n"
                + "        a.id, COUNT(ap.Publicacao_id) tot_publicacoes\n"
                + "    FROM\n"
                + "        publikation.Autor a, publikation.Autor_Publicacao ap\n"
                + "    WHERE\n"
                + "        a.id = ap.Autor_id\n"
                + "    GROUP BY a.id) t1\n"
                + "WHERE\n"
                + "    a.id = t1.id\n"
                + "        AND t1.tot_publicacoes = (SELECT \n"
                + "            MAX(t2.tot_publicacoes) max_publicacoes\n"
                + "        FROM\n"
                + "            (SELECT \n"
                + "                a.id, COUNT(ap.Publicacao_id) tot_publicacoes\n"
                + "            FROM\n"
                + "                publikation.Autor a, publikation.Autor_Publicacao ap\n"
                + "            WHERE\n"
                + "                a.id = ap.Autor_id\n"
                + "            GROUP BY a.id) t2)\n"
                + "LIMIT 1", Autor.class)
                .getSingleResult();

        return autor;
    }

    public List<Autor> buscaColaboradoresDoAutor(Autor autor) {

        List<Autor> autores = em.createNativeQuery("select a.* from publikation.Autor a, publikation.Autor_Publicacao ap\n"
                + "where a.id = ap.Autor_id and a.id <> ?\n"
                + "and ap.Publicacao_id in (SELECT ap.Publicacao_id FROM publikation.Autor_Publicacao ap\n"
                + "where ap.Autor_id = ?)", Autor.class)
                .setParameter(1, autor.getId())
                .setParameter(2, autor.getId())
                .getResultList();

        return autores;
    }
}

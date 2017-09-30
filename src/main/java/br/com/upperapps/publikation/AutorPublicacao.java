/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigomelo
 */
@Entity
@Table(name = "Autor_Publicacao", catalog = "publikation", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AutorPublicacao.findAll", query = "SELECT a FROM AutorPublicacao a")
    , @NamedQuery(name = "AutorPublicacao.findById", query = "SELECT a FROM AutorPublicacao a WHERE a.id = :id")})
public class AutorPublicacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @JoinColumn(name = "Autor_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Autor autor;
    @JoinColumn(name = "Publicacao_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Publicacao publicacao;

    public AutorPublicacao() {
    }

    public AutorPublicacao(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutorid(Autor autor) {
        this.autor = autor;
    }

    public Publicacao getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(Publicacao publicacao) {
        this.publicacao = publicacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorPublicacao)) {
            return false;
        }
        AutorPublicacao other = (AutorPublicacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.upperapps.publikation.AutorPublicacao[ id=" + id + " ]";
    }

}

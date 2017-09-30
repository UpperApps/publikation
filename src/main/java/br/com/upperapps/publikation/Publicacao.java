/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rodrigomelo
 */
@Entity
@Table(catalog = "publikation", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacao.findAll", query = "SELECT p FROM Publicacao p")
    , @NamedQuery(name = "Publicacao.findById", query = "SELECT p FROM Publicacao p WHERE p.id = :id")
    , @NamedQuery(name = "Publicacao.findByTitulo", query = "SELECT p FROM Publicacao p WHERE p.titulo = :titulo")
    , @NamedQuery(name = "Publicacao.findByPaginaInicial", query = "SELECT p FROM Publicacao p WHERE p.paginaInicial = :paginaInicial")
    , @NamedQuery(name = "Publicacao.findByPaginaFinal", query = "SELECT p FROM Publicacao p WHERE p.paginaFinal = :paginaFinal")
    , @NamedQuery(name = "Publicacao.findByDataPublicacao", query = "SELECT p FROM Publicacao p WHERE p.dataPublicacao = :dataPublicacao")
    , @NamedQuery(name = "Publicacao.buscaReferenciasPorAutor", query = "SELECT p FROM Publicacao p JOIN p.autorPublicacaoList ap WHERE ap.autor.id = :autorID")})
public class Publicacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    private String titulo;
    private Integer paginaInicial;
    private Integer paginaFinal;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublicacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "publicacao")
    private List<AutorPublicacao> autorPublicacaoList;

    public Publicacao() {
    }

    public Publicacao(Integer id) {
        this.id = id;
    }

    public Publicacao(Integer id, String titulo, Date dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.dataPublicacao = dataPublicacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(Integer paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public Integer getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(Integer paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    @XmlTransient
    public List<AutorPublicacao> getAutorPublicacaoList() {
        return autorPublicacaoList;
    }

    public void setAutorPublicacaoList(List<AutorPublicacao> autorPublicacaoList) {
        this.autorPublicacaoList = autorPublicacaoList;
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
        if (!(object instanceof Publicacao)) {
            return false;
        }
        Publicacao other = (Publicacao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.upperapps.publikation.Publicacao[ id=" + id + " ]";
    }
    
}

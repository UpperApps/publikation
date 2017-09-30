/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe que representa o autor com o maior nº de publicações e lista seus
 * colaboradores.
 *
 * @author rodrigomelo
 */
@XmlRootElement(name = "AutorTop")
public class AutorTopDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @XmlElement
    private Autor autor;
    @XmlElement
    private List<Autor> colaboradores;

    public AutorTopDTO(Autor autor, List<Autor> colaboradores) {
        this.autor = autor;
        this.colaboradores = colaboradores;
    }

    public AutorTopDTO() {

    }

    public Autor getAutor() {
        return autor;
    }

    public List<Autor> getColaboradores() {
        return colaboradores;
    }

}

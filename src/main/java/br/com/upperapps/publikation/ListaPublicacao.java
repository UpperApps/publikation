/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.upperapps.publikation;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigomelo
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaPublicacao {

    @XmlElement(name = "publicacao")
    private List<Publicacao> publicacao;

    public ListaPublicacao(List<Publicacao> publicacao) {
        this.publicacao = publicacao;
    }

    public ListaPublicacao() {

    }
}

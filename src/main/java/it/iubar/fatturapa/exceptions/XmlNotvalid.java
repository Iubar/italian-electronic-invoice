package it.iubar.fatturapa.exceptions;

public class XmlNotvalid extends Exception {

    public XmlNotvalid(){
        super("Fallito il controllo sull'XML");
    }
}

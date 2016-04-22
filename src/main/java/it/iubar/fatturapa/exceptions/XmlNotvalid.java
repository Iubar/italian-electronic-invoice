package it.iubar.fatturapa.exceptions;

public class XmlNotvalid extends Exception {

    public XmlNotvalid(String message){
        super("Fallito il controllo sull'XML: " + message);
    }
}

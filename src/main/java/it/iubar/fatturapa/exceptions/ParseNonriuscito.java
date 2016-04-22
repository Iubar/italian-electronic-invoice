package it.iubar.fatturapa.exceptions;

public class ParseNonriuscito extends Exception {

    public ParseNonriuscito(){
        super("String to org.w3c.dom.Document non riuscito");
    }
}

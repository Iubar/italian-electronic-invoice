package it.iubar.fatturapa;

public class CodeFatturaException extends Exception {

	public CodeFatturaException(String m){
		super(m);
	}
	
	public CodeFatturaException(){
		super("E' stato riscontrato un errore nella fattura.");
	}
}

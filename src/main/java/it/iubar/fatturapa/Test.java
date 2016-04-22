package it.iubar.fatturapa;

import it.iubar.fatturapa.exceptions.AuthException;
import it.iubar.fatturapa.exceptions.ParseNonriuscito;
import it.iubar.fatturapa.exceptions.XmlNotvalid;
import org.w3c.dom.Document;

public class Test {

	public static void main(String[] args) throws Exception{
		FatturaUtil.setUser("andrea@iubar.it");
		try {
			Document d = FatturaUtil.getFattura("1");
		} catch (AuthException e) {
			e.printStackTrace();
		} catch (ParseNonriuscito parseNonriuscito) {
			parseNonriuscito.printStackTrace();
		} catch (XmlNotvalid xmlNotvalid) {
			xmlNotvalid.printStackTrace();
		}
	}
}

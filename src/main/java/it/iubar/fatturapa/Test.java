package it.iubar.fatturapa;

import it.iubar.fatturapa.exceptions.AuthException;

public class Test {
	public static void main(String[] args) throws Exception{
		FatturaUtil.setUrl("http://www.fatturatutto.it/app/api/test/fattura-esempio/1");
		FatturaUtil.setUser("andrea@iubar.it");
		try {
			System.out.println(FatturaUtil.getInfo(FatturaUtil.XML_TAG));
		} catch (AuthException e) {
			e.printDetails();
		}
	}
}

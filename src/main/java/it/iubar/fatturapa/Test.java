package it.iubar.fatturapa;

public class Test {
	public static void main(String[] args) throws Exception{
		String url = "http://www.fatturatutto.it/app/api/test/fattura-esempio";
		FatturaUtil.setUser("andrea@iubar.it");
		System.out.println(FatturaUtil.getInfo(url, FatturaUtil.XML_TAG));
	}
}

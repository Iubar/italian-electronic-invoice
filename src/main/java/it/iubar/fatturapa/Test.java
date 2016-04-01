package it.iubar.fatturapa;

public class Test {
	public static void main(String[] args) throws Exception{
		String url = "http://www.fatturatutto.it/app/api/test/fattura-esempio";
		System.out.println(FatturaUtil.getXmlString(url));
	}
}

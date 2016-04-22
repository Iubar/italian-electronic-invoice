package it.iubar.fatturapa;

public class Test {

	public static void main(String[] args) throws Exception{
		FatturaUtil.setUser("andrea@iubar.it");
		System.out.println(FatturaUtil.getFattura("1"));
	}
}

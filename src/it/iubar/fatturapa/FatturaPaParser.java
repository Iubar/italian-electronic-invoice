package it.iubar.fatturapa;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang3.time.StopWatch;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FatturaPaParser implements Runnable {

	public static void parser(Document doc) throws ParserConfigurationException, SAXException, IOException{

			
		NodeList nList2 = doc.getElementsByTagName("IdTrasmittente");
		Element eElement2 = (Element) nList2.item(0);
		String s2 = eElement2.getElementsByTagName("IdCodice").item(0).getTextContent();
		System.out.println(s2);
		
		NodeList nList3 = doc.getElementsByTagName("CedentePrestatore");
		Element eElement3 = (Element) nList3.item(0);
		String s3 = eElement3.getElementsByTagName("Denominazione").item(0).getTextContent();
		System.out.println(s3);
		
		/////////////////////////Body///////////////
		/////////////DatiGenerali-->DatiGeneraliDocumento///////////////
		
		NodeList nListB1 = doc.getElementsByTagName("DatiGeneraliDocumento");
		Element eElementB1 = (Element) nListB1.item(0);
		String sb1 = eElementB1.getElementsByTagName("TipoDocumento").item(0).getTextContent();
		System.out.println(sb1);
		
		NodeList nListB2 = doc.getElementsByTagName("DatiGeneraliDocumento");
		Element eElementB2 = (Element) nListB2.item(0);
		String sb2 = eElementB2.getElementsByTagName("Divisa").item(0).getTextContent();
		System.out.println(sb2);
		
		NodeList nListB3 = doc.getElementsByTagName("DatiGeneraliDocumento");
		Element eElementB3 = (Element) nListB3.item(0);
		String sb3 = eElementB3.getElementsByTagName("Data").item(0).getTextContent();
		System.out.println(sb3);
		
		NodeList nListB4 = doc.getElementsByTagName("DatiGeneraliDocumento");
		Element eElementB4 = (Element) nListB4.item(0);
		String sb4 = eElementB4.getElementsByTagName("Numero").item(0).getTextContent();
		System.out.println(sb4);
		
		NodeList nListB5 = doc.getElementsByTagName("DatiGeneraliDocumento");
		Element eElementB5 = (Element) nListB5.item(0);
		String sb5 = eElementB5.getElementsByTagName("Causale").item(0).getTextContent();
		System.out.println(sb5);
		
			NodeList nListB52 = doc.getElementsByTagName("DatiGeneraliDocumento");
			Element eElementB52 = (Element) nListB52.item(0);
			String sb52 = eElementB52.getElementsByTagName("Causale").item(0).getTextContent();
			System.out.println(sb52);
			
		///////////////////////////////DatiGenerali-->DatiOrdineAcquisto///////////////////////////////////	
			
			
		NodeList nListB6 = doc.getElementsByTagName("DatiOrdineAcquisto");
		Element eElementB6 = (Element) nListB6.item(0);
		String sb6 = eElementB6.getElementsByTagName("RiferimentoNumeroLinea").item(0).getTextContent();
		System.out.println(sb6);
		
		NodeList nListB7 = doc.getElementsByTagName("DatiOrdineAcquisto");
		Element eElementB7 = (Element) nListB7.item(0);
		String sb7 = eElementB7.getElementsByTagName("IdDocumento").item(0).getTextContent();
		System.out.println(sb7);
		
		NodeList nListB8 = doc.getElementsByTagName("DatiOrdineAcquisto");
		Element eElementB8 = (Element) nListB8.item(0);
		String sb8 = eElementB8.getElementsByTagName("NumItem").item(0).getTextContent();
		System.out.println(sb5);
		
		NodeList nListB9 = doc.getElementsByTagName("DatiOrdineAcquisto");
		Element eElementB9 = (Element) nListB9.item(0);
		String sb9 = eElementB9.getElementsByTagName("CodiceCUP").item(0).getTextContent();
		System.out.println(sb9);
		
		NodeList nListB10 = doc.getElementsByTagName("DatiOrdineAcquisto");
		Element eElementB10 = (Element) nListB10.item(0);
		String sb10 = eElementB10.getElementsByTagName("CodiceCIG").item(0).getTextContent();
		System.out.println(sb10);
		
		//////////////////////////////DatiGenerali-->DatiContratto///////////////////////////
		
		NodeList nListB11 = doc.getElementsByTagName("DatiContratto");
		Element eElementB11 = (Element) nListB11.item(0);
		String sb11 = eElementB11.getElementsByTagName("RiferimentoNumeroLinea").item(0).getTextContent();
		System.out.println(sb11);
		
		NodeList nListB12 = doc.getElementsByTagName("DatiContratto");
		Element eElementB12 = (Element) nListB12.item(0);
		String sb12 = eElementB12.getElementsByTagName("IdDocumento").item(0).getTextContent();
		System.out.println(sb12);
		
		NodeList nListB13 = doc.getElementsByTagName("DatiContratto");
		Element eElementB13 = (Element) nListB13.item(0);
		String sb13 = eElementB13.getElementsByTagName("Data").item(0).getTextContent();
		System.out.println(sb13);
		
		NodeList nListB14 = doc.getElementsByTagName("DatiContratto");
		Element eElementB14 = (Element) nListB14.item(0);
		String sb14 = eElementB12.getElementsByTagName("NumItem").item(0).getTextContent();
		System.out.println(sb14);
		
		NodeList nListB15 = doc.getElementsByTagName("DatiContratto");
		Element eElementB15 = (Element) nListB15.item(0);
		String sb15 = eElementB15.getElementsByTagName("CodiceCUP").item(0).getTextContent();
		System.out.println(sb15);
		
		NodeList nListB16 = doc.getElementsByTagName("DatiContratto");
		Element eElementB16 = (Element) nListB16.item(0);
		String sb16 = eElementB16.getElementsByTagName("CodiceCIG").item(0).getTextContent();
		System.out.println(sb16);
		
		//////////////////////////////DatiGenerali-->DatiConvenzione//////////////////////////////////
		
		NodeList nListB17 = doc.getElementsByTagName("DatiConvenzione");
		Element eElementB17 = (Element) nListB17.item(0);
		String sb17 = eElementB17.getElementsByTagName("RiferimentoNumeroLinea").item(0).getTextContent();
		System.out.println(sb17);
		
		NodeList nListB18 = doc.getElementsByTagName("DatiConvenzione");
		Element eElementB18 = (Element) nListB18.item(0);
		String sb18 = eElementB18.getElementsByTagName("IdDocumento").item(0).getTextContent();
		System.out.println(sb18);
		
		NodeList nListB19 = doc.getElementsByTagName("DatiConvenzione");
		Element eElementB19 = (Element) nListB19.item(0);
		String sb19 = eElementB19.getElementsByTagName("NumItem").item(0).getTextContent();
		System.out.println(sb19);
		
		NodeList nListB20 = doc.getElementsByTagName("DatiConvenzione");
		Element eElementB20 = (Element) nListB20.item(0);
		String sb20 = eElementB20.getElementsByTagName("CodiceCUP").item(0).getTextContent();
		System.out.println(sb20);
		
		NodeList nListB21 = doc.getElementsByTagName("DatiConvenzione");
		Element eElementB21 = (Element) nListB21.item(0);
		String sb21 = eElementB21.getElementsByTagName("CodiceCIG").item(0).getTextContent();
		System.out.println(sb21);
		
		////////////////////////////DatiGenerali-->DatiRicezione///////////////////////////
		
		NodeList nListB22 = doc.getElementsByTagName("DatiRicezione");
		Element eElementB22 = (Element) nListB22.item(0);
		String sb22 = eElementB22.getElementsByTagName("RiferimentoNumeroLinea").item(0).getTextContent();
		System.out.println(sb22);
		
		NodeList nListB23 = doc.getElementsByTagName("DatiRicezione");
		Element eElementB23 = (Element) nListB23.item(0);
		String sb23 = eElementB23.getElementsByTagName("IdDocumento").item(0).getTextContent();
		System.out.println(sb23);
		
		NodeList nListB24 = doc.getElementsByTagName("DatiRicezione");
		Element eElementB24 = (Element) nListB24.item(0);
		String sb24 = eElementB24.getElementsByTagName("NumItem").item(0).getTextContent();
		System.out.println(sb24);
		
		NodeList nListB25 = doc.getElementsByTagName("DatiRicezione");
		Element eElementB25 = (Element) nListB25.item(0);
		String sb25 = eElementB25.getElementsByTagName("CodiceCUP").item(0).getTextContent();
		System.out.println(sb25);
		
		NodeList nListB26 = doc.getElementsByTagName("DatiRicezione");
		Element eElementB26 = (Element) nListB26.item(0);
		String sb26 = eElementB26.getElementsByTagName("CodiceCIG").item(0).getTextContent();
		System.out.println(sb26);
		
	///////////////////////////DatiGenerali-->DatiTrasporto///////////////////////	
		
		
		NodeList nListB27 = doc.getElementsByTagName("DatiTrasporto");
		Element eElementB27 = (Element) nListB27.item(0);
		String sb27 = eElementB27.getElementsByTagName("IdPaese").item(0).getTextContent();
		System.out.println(sb27);
		
		NodeList nListB28 = doc.getElementsByTagName("DatiTrasporto");
		Element eElementB28 = (Element) nListB28.item(0);
		String sb28 = eElementB28.getElementsByTagName("IdCodice").item(0).getTextContent();
		System.out.println(sb28);
		
		NodeList nListB29 = doc.getElementsByTagName("DatiTrasporto");
		Element eElementB29 = (Element) nListB29.item(0);
		String sb29 = eElementB29.getElementsByTagName("Denominazione").item(0).getTextContent();
		System.out.println(sb29);
		
		NodeList nListB30 = doc.getElementsByTagName("DatiTrasporto");
		Element eElementB30 = (Element) nListB30.item(0);
		String sb30 = eElementB30.getElementsByTagName("DataOraConsegna").item(0).getTextContent();
		System.out.println(sb30);
		
	//////////////////////////////////DatiGenerali-->-->DatiBeniServizi-->DettaglioLinee////////////////////////////
		
		
		NodeList nListB31 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB31 = (Element) nListB31.item(0);
		String sb31 = eElementB31.getElementsByTagName("NumeroLinea").item(0).getTextContent();
		System.out.println(sb31);
		
		NodeList nListB32 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB32 = (Element) nListB32.item(0);
		String sb32 = eElementB32.getElementsByTagName("Descrizione").item(0).getTextContent();
		System.out.println(sb32);
		
		NodeList nListB33 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB33 = (Element) nListB33.item(0);
		String sb33 = eElementB33.getElementsByTagName("Quantita").item(0).getTextContent();
		System.out.println(sb33);
		
		NodeList nListB34 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB34 = (Element) nListB34.item(0);
		String sb34 = eElementB34.getElementsByTagName("PrezzoUnitario").item(0).getTextContent();
		System.out.println(sb34);
		
		NodeList nListB35 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB35 = (Element) nListB35.item(0);
		String sb35 = eElementB35.getElementsByTagName("PrezzoTotale").item(0).getTextContent();
		System.out.println(sb35);
		
		NodeList nListB36 = doc.getElementsByTagName("DettaglioLinee");
		Element eElementB36 = (Element) nListB36.item(0);
		String sb36 = eElementB36.getElementsByTagName("AliquotaIVA").item(0).getTextContent();
		System.out.println(sb36);
		
//		////////////////////////////////FatturaElettronicaBody-->DatiBeniServizi-->DatiRiepilogo/////////////////////////////
		
		
		NodeList nListB37 = doc.getElementsByTagName("DatiRiepilogo");
		Element eElementB37 = (Element) nListB37.item(0);
		String sb37 = eElementB37.getElementsByTagName("AliquotaIVA").item(0).getTextContent();
		System.out.println(sb37);
		
		NodeList nListB38 = doc.getElementsByTagName("DatiRiepilogo");
		Element eElementB38 = (Element) nListB38.item(0);
		String sb38 = eElementB38.getElementsByTagName("ImponibileImporto").item(0).getTextContent();
		System.out.println(sb38);
		
		NodeList nListB39 = doc.getElementsByTagName("DatiRiepilogo");
		Element eElementB39 = (Element) nListB39.item(0);
		String sb39 = eElementB39.getElementsByTagName("Imposta").item(0).getTextContent();
		System.out.println(sb39);
		
		NodeList nListB40 = doc.getElementsByTagName("DatiRiepilogo");
		Element eElementB40 = (Element) nListB40.item(0);
		String sb40 = eElementB40.getElementsByTagName("EsigibilitaIVA").item(0).getTextContent();
		System.out.println(sb40);
		
		////////////////////////////////FatturaElettronicaBody-->DatiPagamento///////////////////////////////
		
		NodeList nListB41 = doc.getElementsByTagName("DatiPagamento");
		Element eElementB41 = (Element) nListB41.item(0);
		String sb41 = eElementB41.getElementsByTagName("CondizioniPagamento").item(0).getTextContent();
		System.out.println(sb41);
		
		NodeList nListB42 = doc.getElementsByTagName("DatiPagamento");
		Element eElementB42 = (Element) nListB42.item(0);
		String sb42 = eElementB42.getElementsByTagName("ModalitaPagamento").item(0).getTextContent();
		System.out.println(sb42);
		
		NodeList nListB43 = doc.getElementsByTagName("DatiPagamento");
		Element eElementB43 = (Element) nListB43.item(0);
		String sb43 = eElementB43.getElementsByTagName("DataScadenzaPagamento").item(0).getTextContent();
		System.out.println(sb43);
		
		NodeList nListB44 = doc.getElementsByTagName("DatiPagamento");
		Element eElementB44 = (Element) nListB44.item(0);
		String sb44 = eElementB44.getElementsByTagName("ImportoPagamento").item(0).getTextContent();
		System.out.println(sb44);
		
		
		

//		CedentePrestatore cedente = new CedentePrestatore();
//		cedente.setDenominazione(s3);
		
		// ......
		
		System.out.println("To do....");
		
	}

	@Override
	public void run() {
    	StopWatch clock = new StopWatch();
    	clock.start();
    	
    	System.out.println("");

    	//		  System.out.println("This is currently running on a separate thread, " +  
    	//		            "the id is: " + Thread.currentThread().getId());  
    	try {
    		FatturaPaParser.parser(FatturaPaValidator.document);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	clock.stop();
    	// double seconds = clock.getTime() / 1000.0;
    	System.out.println("");
    	// System.out.println( "It takes " + seconds + " seconds" );
    	System.out.println( "Done in " + clock.toString() + " (hh:mm:ss:mm)");
	}
	
 
	
}

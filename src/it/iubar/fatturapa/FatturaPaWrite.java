package it.iubar.fatturapa;

import it.iubar.fatturapa.entities.Allegati;
import it.iubar.fatturapa.entities.AltriDatiGestionali;
import it.iubar.fatturapa.entities.Causale;
import it.iubar.fatturapa.entities.CodcieArticolo;
import it.iubar.fatturapa.entities.DatiCassaPrevidenziale;
import it.iubar.fatturapa.entities.DatiContatto;
import it.iubar.fatturapa.entities.DatiConvenzione;
import it.iubar.fatturapa.entities.DatiDDT;
import it.iubar.fatturapa.entities.DatiFattureCollegate;
import it.iubar.fatturapa.entities.DatiOrdineAcquisto;
import it.iubar.fatturapa.entities.DatiPagamento;
import it.iubar.fatturapa.entities.DatiRicezione;
import it.iubar.fatturapa.entities.DatiRiepilogo;
import it.iubar.fatturapa.entities.DettaglioLinee;
import it.iubar.fatturapa.entities.DettaglioPagamento;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaCon;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaConve;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaDDT;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaFatCol;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaOrd;
import it.iubar.fatturapa.entities.RiferimentoNumeroLineaRicez;
import it.iubar.fatturapa.entities.ScontoMaggiorazione;
import it.iubar.fatturapa.entities.ScontoMaggiorazioneBS;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 




//import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FatturaPaWrite 
{
	private static final boolean DEBUG = true;
	
	private void builtDatiGenereali(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// DatiGenerali elements
		Element elemDatiGen = doc.createElement("DatiGenerali");
		elemFatEletBody.appendChild(elemDatiGen);

			builtDatiGenDoc(doc, elemDatiGen);
			
			if(isDatiOrdineAcquisto()){
				List<DatiOrdineAcquisto> datOrdAcquisti = getListDatiOrdineAcquisto();
				for(DatiOrdineAcquisto datOrdAcquisto : datOrdAcquisti){
					builtDatiOrdAcq(doc, elemDatiGen, datOrdAcquisto);
				}
			}
			
			if(isDatiContatto()){
				List<DatiContatto> datContatti = getListDatiContatto();
				for(DatiContatto datContatto : datContatti){
					builtDatiContratto(doc, elemDatiGen, datContatto);
				}
			}
			
			if(isDatiConvenzione()){
				List<DatiConvenzione> datConvenzioni = getListDatiConvenzione();
				for(DatiConvenzione datConvenzione : datConvenzioni){
					builtDatiConvenzione(doc, elemDatiGen, datConvenzione);
				}
			}
			
			if(isDatiRicezione()){
				List<DatiRicezione> datRicezioni = getListDatiRicezione();
				for(DatiRicezione datRicezione : datRicezioni){
					builtDatiRicezione(doc, elemDatiGen, datRicezione);
				}
			}
			
			if(isDatiFattureCollegate()){
				List<DatiFattureCollegate> datFattCollegate = getListDatiFattureCollegate();
				for(DatiFattureCollegate datFattCollegata : datFattCollegate){
					builtDatiFattureCollegate(doc, elemDatiGen, datFattCollegata);
				}
			}
			
			if(isDatiSAL()){
				// DatiSAL elements
				Element elemDatiSAL = doc.createElement("DatiSAL");
				elemDatiGen.appendChild(elemDatiSAL);
				
					// RiferimentoFase elements
					Element elmRiferFase = doc.createElement("RiferimentoFase"); // questo elemento deve essere miglorato
					elmRiferFase.appendChild(doc.createTextNode("1"));
					elemDatiSAL.appendChild(elmRiferFase);
			}
			
			if(isDatiDDT()){
				List<DatiDDT> datiDDT = getListDatiDDT();
				for(DatiDDT datoDDT : datiDDT){
					builtDatiDDT(doc, elemDatiGen, datoDDT);
				}
			}
			
			if(isDatiTrasporto()){
				builtDatiTrasporto(doc, elemDatiGen);
			}
			
			if(isFatturaPrincipale()){
				// FatturaPrincipale elements
				Element elemFatturaPrincipale = doc.createElement("FatturaPrincipale");
				elemDatiGen.appendChild(elemFatturaPrincipale);
				
					// NumeroFatturaPrincipale elements
					Element elemNumeroFatturaPrincipale = doc.createElement("NumeroFatturaPrincipale"); // questo elemento deve essere miglorato
					elemNumeroFatturaPrincipale.appendChild(doc.createTextNode("1"));
					elemFatturaPrincipale.appendChild(elemNumeroFatturaPrincipale);
					
					// DataFatturaPrincipale elements
					Element elemDataFatturaPrincipale = doc.createElement("DataFatturaPrincipale"); // questo elemento deve essere miglorato
					elemDataFatturaPrincipale.appendChild(doc.createTextNode("2015-03-27"));
					elemFatturaPrincipale.appendChild(elemDataFatturaPrincipale);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiOrdineAcquisto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiContatto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiConvenzione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}

	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiRicezione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiFattureCollegate()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiSAL()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiDDT()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiTrasporto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isFatturaPrincipale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiOrdineAcquisto> getListDatiOrdineAcquisto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiOrdineAcquisto> ordini = new ArrayList<DatiOrdineAcquisto>();
		DatiOrdineAcquisto ordine = new DatiOrdineAcquisto();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiContatto> getListDatiContatto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiContatto> ordini = new ArrayList<DatiContatto>();
		DatiContatto ordine = new DatiContatto();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiConvenzione> getListDatiConvenzione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiConvenzione> ordini = new ArrayList<DatiConvenzione>();
		DatiConvenzione ordine = new DatiConvenzione();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiRicezione> getListDatiRicezione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiRicezione> ordini = new ArrayList<DatiRicezione>();
		DatiRicezione ordine = new DatiRicezione();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiFattureCollegate> getListDatiFattureCollegate()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiFattureCollegate> ordini = new ArrayList<DatiFattureCollegate>();
		DatiFattureCollegate ordine = new DatiFattureCollegate();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiDDT> getListDatiDDT()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiDDT> ordini = new ArrayList<DatiDDT>();
		DatiDDT ordine = new DatiDDT();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiGenDoc(Document doc, Element elemDatiGen) throws ParserConfigurationException, TransformerException
	{
	
		// DatiGeneraliDocumento elements
		Element elemDatiGenDoc = doc.createElement("DatiGeneraliDocumento");
		elemDatiGen.appendChild(elemDatiGenDoc);
			
			// TipoDocumento elements
			Element elemTipoDoc = doc.createElement("TipoDocumento"); 
			elemTipoDoc.appendChild(doc.createTextNode("TD01"));
			elemDatiGenDoc.appendChild(elemTipoDoc);
			
			// Divisa elements
			Element elemDivisa = doc.createElement("Divisa"); // questo elemento deve essere miglorato
			elemDivisa.appendChild(doc.createTextNode("EUR"));
			elemDatiGenDoc.appendChild(elemDivisa);
			
			
			// Data elements
			Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
			elemData.appendChild(doc.createTextNode("2015-03-13"));
			elemDatiGenDoc.appendChild(elemData);
			
			// Numero elements
			Element elemNumero = doc.createElement("Numero"); // questo elemento deve essere miglorato
			elemNumero.appendChild(doc.createTextNode("123"));
			elemDatiGenDoc.appendChild(elemNumero);
			
			if(isDatiRitenuta()) { 
				builtDatiRitenuta(doc, elemDatiGenDoc);
			}
			
			if(isDatiBollo()) { 
				builtDatiBollo(doc, elemDatiGenDoc);
			}
			
			if(isDatiCassPrevid()){
				List<DatiCassaPrevidenziale> datiCasPreviden = getListDatiCassPrev();
				for(DatiCassaPrevidenziale datoCasPreviden : datiCasPreviden){
					builtDatiCassPrevid(doc, elemDatiGenDoc, datoCasPreviden);
				}
			}
			 
			if(isScontoMaggiorazione()){
				List<ScontoMaggiorazione> sconti = getListScontoMaggiorazione();
				for(ScontoMaggiorazione sconto : sconti){
					builtScontoMAggiorazione(doc, elemDatiGenDoc, sconto);
				}
			}
			
			if(isImportoTotaleDocumento()){
				// ImportoTotaleDocumento elements
				Element elemImportoTotaleDocumento = doc.createElement("ImportoTotaleDocumento"); // questo elemento deve essere miglorato
				elemImportoTotaleDocumento.appendChild(doc.createTextNode("1485"));
				elemDatiGenDoc.appendChild(elemImportoTotaleDocumento);
			}
			
			if(isArrotondamento()){
				// Arrotondamento elements
				Element elemArrotondamento = doc.createElement("Arrotondamento"); // questo elemento deve essere miglorato
				elemArrotondamento.appendChild(doc.createTextNode("186"));
				elemDatiGenDoc.appendChild(elemArrotondamento);
				
			}
			
			if(isCausale()){
				List<Causale> causali = getListCausale();
				for(Causale causale : causali){
					// Causale1 elements
					Element elemCausale1 = doc.createElement("Causale"); 
					elemCausale1.appendChild(doc.createTextNode(causale.getCausale1())); 
					elemDatiGenDoc.appendChild(elemCausale1);
								
					// Causale2 elements
					Element elemCausale2 = doc.createElement("Causale"); 
					elemCausale2.appendChild(doc.createTextNode(causale.getCausale2()));
					elemDatiGenDoc.appendChild(elemCausale2);
				}
			}
			
			if(isArt73()){
				// Art73 elements
				Element elemArt73 = doc.createElement("Art73"); 
				elemArt73.appendChild(doc.createTextNode("SI"));
				elemDatiGenDoc.appendChild(elemArt73);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiRitenuta()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiBollo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiCassPrevid()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isScontoMaggiorazione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isImportoTotaleDocumento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isArrotondamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCausale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isArt73()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiCassaPrevidenziale> getListDatiCassPrev()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiCassaPrevidenziale> ordini = new ArrayList<DatiCassaPrevidenziale>();
		DatiCassaPrevidenziale ordine = new DatiCassaPrevidenziale();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<ScontoMaggiorazione> getListScontoMaggiorazione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<ScontoMaggiorazione> ordini = new ArrayList<ScontoMaggiorazione>();
		ScontoMaggiorazione ordine = new ScontoMaggiorazione();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<Causale> getListCausale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<Causale> ordini = new ArrayList<Causale>();
		Causale ordine = new Causale();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiRitenuta(Document doc, Element elemDatiGenDoc) throws ParserConfigurationException, TransformerException
	{
		// DatiRitenuta elements
		Element elemDatiRiten = doc.createElement("DatiRitenuta");
		elemDatiGenDoc.appendChild(elemDatiRiten);
			
			// TipoRitenuta elements
			Element elemTipoRitenuta = doc.createElement("TipoRitenuta"); // questo elemento deve essere miglorato
			elemTipoRitenuta.appendChild(doc.createTextNode("RT01"));
			elemDatiRiten.appendChild(elemTipoRitenuta);
				
			// ImportoRitenuta elements
			Element elemImportoRitenuta = doc.createElement("ImportoRitenuta"); // questo elemento deve essere miglorato
			elemImportoRitenuta.appendChild(doc.createTextNode("100.50"));
			elemDatiRiten.appendChild(elemImportoRitenuta);
			
			// AliquotaRitenuta elements
			Element elemAliquotaRitenuta = doc.createElement("AliquotaRitenuta"); // questo elemento deve essere miglorato
			elemAliquotaRitenuta.appendChild(doc.createTextNode("22.00"));
			elemDatiRiten.appendChild(elemAliquotaRitenuta);
			
			// CausaleRitenuta elements
			Element elemCausaleRitenuta = doc.createElement("CausaleRitenuta"); // questo elemento deve essere miglorato
			elemCausaleRitenuta.appendChild(doc.createTextNode("LM"));
			elemDatiRiten.appendChild(elemCausaleRitenuta);
	}
	
	private void builtDatiBollo(Document doc, Element elemDatiGenDoc) throws ParserConfigurationException, TransformerException
	{
		// DatiBollo elements
		Element elemDatiBollo = doc.createElement("DatiRitenuta");
		elemDatiGenDoc.appendChild(elemDatiBollo);
		
			// BolloVirtuale elements
			Element elemBolloVirtuale = doc.createElement("BolloVirtuale"); // questo elemento deve essere miglorato
			elemBolloVirtuale.appendChild(doc.createTextNode("SI"));
			elemDatiBollo.appendChild(elemBolloVirtuale);
				
			// ImportoBollo elements
			Element elemImportoBollo = doc.createElement("ImportoBollo"); // questo elemento deve essere miglorato
			elemImportoBollo.appendChild(doc.createTextNode("506.74"));
			elemDatiBollo.appendChild(elemImportoBollo);
	}
	
	private void builtDatiCassPrevid(Document doc, Element elemDatiGenDoc, DatiCassaPrevidenziale datoCasPreviden) throws ParserConfigurationException, TransformerException
	{
		// DatiCassaPrevidenziale elements
		Element elemDatiCassPrevid = doc.createElement("DatiCassaPrevidenziale");
		elemDatiGenDoc.appendChild(elemDatiCassPrevid);
		
			// TipoCassa elements
			Element elemTipoCassa = doc.createElement("TipoCassa"); // questo elemento deve essere miglorato
			elemTipoCassa.appendChild(doc.createTextNode(datoCasPreviden.getTipoCassa())); 
			elemDatiCassPrevid.appendChild(elemTipoCassa);
				
			// AlCassa elements
			Element elemAlCassa = doc.createElement("AlCassa"); // questo elemento deve essere miglorato
			elemAlCassa.appendChild(doc.createTextNode(datoCasPreviden.getAlCassa())); 
			elemDatiCassPrevid.appendChild(elemAlCassa);
			
			// ImportoContriburoCassa elements
			Element elemImpContrCassa = doc.createElement("ImportoContriburoCassa"); // questo elemento deve essere miglorato
			elemImpContrCassa.appendChild(doc.createTextNode(datoCasPreviden.getImporContribCassa())); 
			elemDatiCassPrevid.appendChild(elemImpContrCassa);
			
			if(isImponibileCassa()){
				
				// ImponibileCassa elements
				Element elemImponCassa = doc.createElement("ImponibileCassa"); // questo elemento deve essere miglorato
				elemImponCassa.appendChild(doc.createTextNode(datoCasPreviden.getImponibCassa())); 
				elemDatiCassPrevid.appendChild(elemImponCassa);
			}
			
			// AliquotaIVA elements
			Element elemAliquotaIVA = doc.createElement("AliquotaIVA"); // questo elemento deve essere miglorato
			elemAliquotaIVA.appendChild(doc.createTextNode(datoCasPreviden.getAliquotaIVA())); 
			elemDatiCassPrevid.appendChild(elemAliquotaIVA);
			
			if(isRitenuta()){
				//Ritenuta elements
				//controllare grafico(PDF) perchè presenta un segno strano 
				Element elemRitenuta = doc.createElement("Ritenuta"); // questo elemento deve essere miglorato
				elemRitenuta.appendChild(doc.createTextNode(datoCasPreviden.getRitenuta())); 
				elemDatiCassPrevid.appendChild(elemRitenuta);				
			}
			
			if(isNatura()){
				//Natua elements		
				Element elemNatura = doc.createElement("Natura"); // questo elemento deve essere miglorato
				elemNatura.appendChild(doc.createTextNode(datoCasPreviden.getNatura())); 
				elemDatiCassPrevid.appendChild(elemNatura);	
			}
			
			if(isRiferimentoAmministrazione()){
				//RiferimentoAmministrazione elements		
				Element elemRiferimentoAmministrazione = doc.createElement("RiferimentoAmministrazione"); // questo elemento deve essere miglorato
				elemRiferimentoAmministrazione.appendChild(doc.createTextNode(datoCasPreviden.getRiferAmministra())); 
				elemDatiCassPrevid.appendChild(elemRiferimentoAmministrazione);	
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isImponibileCassa()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRitenuta()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNatura()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoAmministrazione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtScontoMAggiorazione(Document doc, Element elemDatiGenDoc, ScontoMaggiorazione sconto) throws ParserConfigurationException, TransformerException
	{
		// ScontoMaggiorazione elements
		Element elemScontoMaggiorazione = doc.createElement("ScontoMaggiorazione");
		elemDatiGenDoc.appendChild(elemScontoMaggiorazione);
		
			// Tipo elements
			Element elemTipo = doc.createElement("Tipo"); // questo elemento deve essere miglorato
			elemTipo.appendChild(doc.createTextNode(sconto.getElemTipo())); 
			elemScontoMaggiorazione.appendChild(elemTipo);
			
			if(isPercentuale()){
				// Percentuale elements
				Element elemPercentuale = doc.createElement("Percentuale"); // questo elemento deve essere miglorato
				elemPercentuale.appendChild(doc.createTextNode(sconto.getPercentuale())); 
				elemScontoMaggiorazione.appendChild(elemPercentuale);
			}
			
			if(isImporto()){
				// Importo elements
				Element elemImporto = doc.createElement("Importo"); // questo elemento deve essere miglorato
				elemImporto.appendChild(doc.createTextNode(sconto.getImporto())); 
				elemScontoMaggiorazione.appendChild(elemImporto);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isPercentuale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isImporto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtDatiOrdAcq(Document doc, Element elemDatiGen, DatiOrdineAcquisto datOrdAcquisto) throws ParserConfigurationException, TransformerException
	{

		// DatiOrdineAcquisto elements
		Element ElemDatiOrdAcq = doc.createElement("DatiOrdineAcquisto");
		elemDatiGen.appendChild(ElemDatiOrdAcq);
			
			if(isRiferimentoNumeroLineaOrd()){
				List<RiferimentoNumeroLineaOrd> ordini = getListRiferimentoNumeroLineaOrd();
				for(RiferimentoNumeroLineaOrd ordine : ordini){
					// RiferimentoNumeroLinea elements
					Element elmRiferNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elmRiferNumLin.appendChild(doc.createTextNode("1"));
					ElemDatiOrdAcq.appendChild(elmRiferNumLin);
				}
			}	
			
			// IdDocumento elements
			Element elemIdDoc = doc.createElement("IdDocumento"); // questo elemento deve essere miglorato
			elemIdDoc.appendChild(doc.createTextNode(datOrdAcquisto.getIdDocumento())); 
			ElemDatiOrdAcq.appendChild(elemIdDoc);
			
			if(isDataOrd()){
				// Data elements
				Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
				elemData.appendChild(doc.createTextNode(datOrdAcquisto.getData())); 
				ElemDatiOrdAcq.appendChild(elemData);
			}
			
			if(isNumItemOrd()){
				// NumItem elements
				Element elemNumItem = doc.createElement("NumItem"); // questo elemento deve essere miglorato
				elemNumItem.appendChild(doc.createTextNode(datOrdAcquisto.getNumItem()));  
				ElemDatiOrdAcq.appendChild(elemNumItem);
			}
			
			if(isCodiceCommessaConvenzioneOrd()){
				// CodiceCommessaConvenzione elements
				Element elemCodComConven = doc.createElement("CodiceCommessaConvenzione"); // questo elemento deve essere miglorato
				elemCodComConven.appendChild(doc.createTextNode(datOrdAcquisto.getCodComConven())); 
				ElemDatiOrdAcq.appendChild(elemCodComConven);
			}
			
			if(isCodiceCUPOrd()){
				// CodiceCUP elements
				Element elemCodCUP = doc.createElement("CodiceCUP"); // questo elemento deve essere miglorato
				elemCodCUP.appendChild(doc.createTextNode(datOrdAcquisto.getCodCUP())); 
				ElemDatiOrdAcq.appendChild(elemCodCUP);
			}
			
			if(isCodiceCIGOrd()){
				// CodiceCIG elements
				Element elemCodCIG = doc.createElement("CodiceCIG"); // questo elemento deve essere miglorato
				elemCodCIG.appendChild(doc.createTextNode(datOrdAcquisto.getCodCIG()));
				ElemDatiOrdAcq.appendChild(elemCodCIG);
			}	
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumItemOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCommessaConvenzioneOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCUPOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCIGOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaOrd> getListRiferimentoNumeroLineaOrd()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaOrd> ordini = new ArrayList<RiferimentoNumeroLineaOrd>();
		RiferimentoNumeroLineaOrd ordine = new RiferimentoNumeroLineaOrd();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiContratto(Document doc, Element elemDatiGen, DatiContatto datContatto) throws ParserConfigurationException, TransformerException // TODO: rinomiknare DatiContatto in DatiContratto
	{
		// DatiContratto elements
		Element elemDatiCont = doc.createElement("DatiContratto");
		elemDatiGen.appendChild(elemDatiCont);
		
			if(isRiferimentoNumeroLineaCon()){
				List<RiferimentoNumeroLineaCon> ordini = getListRiferimentoNumeroLineaCon();
				for(RiferimentoNumeroLineaCon ordine : ordini){
					// RiferimentoNumeroLinea elements
					Element elmRiferNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elmRiferNumLin.appendChild(doc.createTextNode("1"));
					elemDatiCont.appendChild(elmRiferNumLin);
				}
			}	
			
			// IdDocumento elements
			Element elemIdDoc = doc.createElement("IdDocumento"); // questo elemento deve essere miglorato
			elemIdDoc.appendChild(doc.createTextNode(datContatto.getIdDocumento())); 
			elemDatiCont.appendChild(elemIdDoc);
			
			if(isDataCon()){
				// Data elements
				Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
				elemData.appendChild(doc.createTextNode(datContatto.getData())); 
				elemDatiCont.appendChild(elemData);
			}
			
			if(isNumItemCon()){
				// NumItem elements
				Element elemNumItem = doc.createElement("NumItem"); // questo elemento deve essere miglorato
				elemNumItem.appendChild(doc.createTextNode(datContatto.getNumItem())); 
				elemDatiCont.appendChild(elemNumItem);
			}
			
			if(isCodiceCommessaConvenzioneCon()){
				// CodiceCommessaConvenzione elements
				Element elemCodComConven = doc.createElement("CodiceCommessaConvenzione"); // questo elemento deve essere miglorato
				elemCodComConven.appendChild(doc.createTextNode(datContatto.getCodComConven())); 
				elemDatiCont.appendChild(elemCodComConven);
			}
			
			if(isCodiceCUPCon()){
				// CodiceCUP elements
				Element elemCodCUP = doc.createElement("CodiceCUP"); // questo elemento deve essere miglorato
				elemCodCUP.appendChild(doc.createTextNode(datContatto.getCodCUP())); 
				elemDatiCont.appendChild(elemCodCUP);
			}
			
			if(isCodiceCIGCon()){
				// CodiceCIG elements
				Element elemCodCIG = doc.createElement("CodiceCIG"); // questo elemento deve essere miglorato
				elemCodCIG.appendChild(doc.createTextNode(datContatto.getCodCIG())); 
				elemDatiCont.appendChild(elemCodCIG);
			}		
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumItemCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCommessaConvenzioneCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCUPCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCIGCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaCon> getListRiferimentoNumeroLineaCon()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaCon> ordini = new ArrayList<RiferimentoNumeroLineaCon>();
		RiferimentoNumeroLineaCon ordine = new RiferimentoNumeroLineaCon();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiConvenzione(Document doc, Element elemDatiGen, DatiConvenzione datConvenzione) throws ParserConfigurationException, TransformerException
	{
		// DatiConvenzione elements
		Element elemDatiConven = doc.createElement("DatiConvenzione");
		elemDatiGen.appendChild(elemDatiConven);
		
			if(isRiferimentoNumeroLineaConve()){
				List<RiferimentoNumeroLineaConve> ordini = getListRiferimentoNumeroLineaConve();
				for(RiferimentoNumeroLineaConve ordine : ordini){
					// RiferimentoNumeroLinea elements
					Element elmRiferNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elmRiferNumLin.appendChild(doc.createTextNode("1"));
					elemDatiConven.appendChild(elmRiferNumLin);
				}
			}	
			
			// IdDocumento elements
			Element elemIdDoc = doc.createElement("IdDocumento"); // questo elemento deve essere miglorato
			elemIdDoc.appendChild(doc.createTextNode(datConvenzione.getIdDocumento())); 
			elemDatiConven.appendChild(elemIdDoc);
			
			if(isDataConve()){
				// Data elements
				Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
				elemData.appendChild(doc.createTextNode(datConvenzione.getData())); 
				elemDatiConven.appendChild(elemData);
			}
			
			if(isNumItemConve()){
				// NumItem elements
				Element elemNumItem = doc.createElement("NumItem"); // questo elemento deve essere miglorato
				elemNumItem.appendChild(doc.createTextNode(datConvenzione.getNumItem())); 
				elemDatiConven.appendChild(elemNumItem);
			}
			
			if(isCodiceCommessaConvenzioneConve()){
				// CodiceCommessaConvenzione elements
				Element elemCodComConven = doc.createElement("CodiceCommessaConvenzione"); // questo elemento deve essere miglorato
				elemCodComConven.appendChild(doc.createTextNode(datConvenzione.getCodComConven())); 
				elemDatiConven.appendChild(elemCodComConven);
			}
			
			if(isCodiceCUPConve()){
				// CodiceCUP elements
				Element elemCodCUP = doc.createElement("CodiceCUP"); // questo elemento deve essere miglorato
				elemCodCUP.appendChild(doc.createTextNode(datConvenzione.getCodCUP())); 
				elemDatiConven.appendChild(elemCodCUP);
			}
			
			if(isCodiceCIGConve()){
				// CodiceCIG elements
				Element elemCodCIG = doc.createElement("CodiceCIG"); // questo elemento deve essere miglorato
				elemCodCIG.appendChild(doc.createTextNode(datConvenzione.getCodCIG())); 
				elemDatiConven.appendChild(elemCodCIG);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumItemConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCommessaConvenzioneConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCUPConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCIGConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaConve> getListRiferimentoNumeroLineaConve()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaConve> ordini = new ArrayList<RiferimentoNumeroLineaConve>();
		RiferimentoNumeroLineaConve ordine = new RiferimentoNumeroLineaConve();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiRicezione(Document doc, Element elemDatiGen, DatiRicezione datRicezione) throws ParserConfigurationException, TransformerException
	{
		// DatiRicezione elements
		Element elemDatiRicezione = doc.createElement("DatiRicezione");
		elemDatiGen.appendChild(elemDatiRicezione);
		
			if(isRiferimentoNumeroLineaRicez()){
				List<RiferimentoNumeroLineaRicez> ordini = getListRiferimentoNumeroLineaRicez();
				for(RiferimentoNumeroLineaRicez ordine : ordini){
					// RiferimentoNumeroLinea elements
					Element elmRiferNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elmRiferNumLin.appendChild(doc.createTextNode("1"));
					elemDatiRicezione.appendChild(elmRiferNumLin);
				}
			}	
			
			// IdDocumento elements
			Element elemIdDoc = doc.createElement("IdDocumento"); // questo elemento deve essere miglorato
			elemIdDoc.appendChild(doc.createTextNode(datRicezione.getIdDocumento())); 
			elemDatiRicezione.appendChild(elemIdDoc);
			
			if(isDataRicez()){
				// Data elements
				Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
				elemData.appendChild(doc.createTextNode(datRicezione.getData())); 
				elemDatiRicezione.appendChild(elemData);
			}
			
			if(isNumItemRicez()){
				// NumItem elements
				Element elemNumItem = doc.createElement("NumItem"); // questo elemento deve essere miglorato
				elemNumItem.appendChild(doc.createTextNode(datRicezione.getNumItem())); 
				elemDatiRicezione.appendChild(elemNumItem);
			}
			
			if(isCodiceCommessaConvenzioneRicez()){
				// CodiceCommessaConvenzione elements
				Element elemCodComConven = doc.createElement("CodiceCommessaConvenzione"); // questo elemento deve essere miglorato
				elemCodComConven.appendChild(doc.createTextNode(datRicezione.getCodComConven())); 
				elemDatiRicezione.appendChild(elemCodComConven);
			}
			
			if(isCodiceCUPRicez()){
				// CodiceCUP elements
				Element elemCodCUP = doc.createElement("CodiceCUP"); // questo elemento deve essere miglorato
				elemCodCUP.appendChild(doc.createTextNode(datRicezione.getCodCUP())); 
				elemDatiRicezione.appendChild(elemCodCUP);
			}
			
			if(isCodiceCIGRicez()){
				// CodiceCIG elements
				Element elemCodCIG = doc.createElement("CodiceCIG"); // questo elemento deve essere miglorato
				elemCodCIG.appendChild(doc.createTextNode(datRicezione.getCodCIG())); 
				elemDatiRicezione.appendChild(elemCodCIG);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumItemRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCommessaConvenzioneRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCUPRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCIGRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaRicez> getListRiferimentoNumeroLineaRicez()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaRicez> ordini = new ArrayList<RiferimentoNumeroLineaRicez>();
		RiferimentoNumeroLineaRicez ordine = new RiferimentoNumeroLineaRicez();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiFattureCollegate(Document doc, Element elemDatiGen, DatiFattureCollegate datFattCollegata) throws ParserConfigurationException, TransformerException
	{
		// DatiFattureCollegate elements
		Element elemDatiFattureCollegate = doc.createElement("FattureCollegate");
		elemDatiGen.appendChild(elemDatiFattureCollegate);
		
			if(isRiferimentoNumeroLineaFatCol()){
				List<RiferimentoNumeroLineaFatCol> ordini = getListRiferimentoNumeroLineaFatCol();
				for(RiferimentoNumeroLineaFatCol ordine : ordini){
					// RiferimentoNumeroLinea elements
					Element elmRiferNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elmRiferNumLin.appendChild(doc.createTextNode("1"));
					elemDatiFattureCollegate.appendChild(elmRiferNumLin);
				}
			}	
			
			// IdDocumento elements
			Element elemIdDoc = doc.createElement("IdDocumento"); // questo elemento deve essere miglorato
			elemIdDoc.appendChild(doc.createTextNode(datFattCollegata.getIdDocumento())); 
			elemDatiFattureCollegate.appendChild(elemIdDoc);
			
			if(isDataFatCol()){
				// Data elements
				Element elemData = doc.createElement("Data"); // questo elemento deve essere miglorato
				elemData.appendChild(doc.createTextNode(datFattCollegata.getData())); 
				elemDatiFattureCollegate.appendChild(elemData);
			}
			
			if(isNumItemFatCol()){
				// NumItem elements
				Element elemNumItem = doc.createElement("NumItem"); // questo elemento deve essere miglorato
				elemNumItem.appendChild(doc.createTextNode(datFattCollegata.getNumItem())); 
				elemDatiFattureCollegate.appendChild(elemNumItem);
			}
			
			if(isCodiceCommessaConvenzioneFatCol()){
				// CodiceCommessaConvenzione elements
				Element elemCodComConven = doc.createElement("CodiceCommessaConvenzione"); // questo elemento deve essere miglorato
				elemCodComConven.appendChild(doc.createTextNode(datFattCollegata.getCodComConven())); 
				elemDatiFattureCollegate.appendChild(elemCodComConven);
			}
			
			if(isCodiceCUPFatCol()){
				// CodiceCUP elements
				Element elemCodCUP = doc.createElement("CodiceCUP"); // questo elemento deve essere miglorato
				elemCodCUP.appendChild(doc.createTextNode(datFattCollegata.getCodCUP())); 
				elemDatiFattureCollegate.appendChild(elemCodCUP);
			}
			
			if(isCodiceCIGFatCol()){
				// CodiceCIG elements
				Element elemCodCIG = doc.createElement("CodiceCIG"); // questo elemento deve essere miglorato
				elemCodCIG.appendChild(doc.createTextNode(datFattCollegata.getCIG())); 
				elemDatiFattureCollegate.appendChild(elemCodCIG);
			}
	}

	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumItemFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCommessaConvenzioneFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCUPFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceCIGFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaFatCol> getListRiferimentoNumeroLineaFatCol()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaFatCol> ordini = new ArrayList<RiferimentoNumeroLineaFatCol>();
		RiferimentoNumeroLineaFatCol ordine = new RiferimentoNumeroLineaFatCol();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiDDT(Document doc, Element elemDatiGen, DatiDDT datoDDT) throws ParserConfigurationException, TransformerException
	{
		// DatiDDT elements
		Element elemDatiDDT = doc.createElement("DatiDDT");
		elemDatiGen.appendChild(elemDatiDDT);
			
			// NumeroDDT  elements
			Element elemNumeroDDT = doc.createElement("NumeroDDT"); // questo elemento deve essere miglorato
			elemNumeroDDT.appendChild(doc.createTextNode(datoDDT.getNumeroDDT())); 
			elemDatiDDT.appendChild(elemNumeroDDT);
			
			// DataDDT  elements
			Element elemDataDDT = doc.createElement("DataDDT"); // questo elemento deve essere miglorato
			elemDataDDT.appendChild(doc.createTextNode(datoDDT.getDataDDT())); 
			elemDatiDDT.appendChild(elemDataDDT);
			
			if(isRiferimentoNumeroLineaDDT()){
				List<RiferimentoNumeroLineaDDT> ordini = getListRiferimentoNumeroLineaDDT();
				for(RiferimentoNumeroLineaDDT ordine : ordini){
					// RiferimentoNumeroLinea  elements
					Element elemRifNumLin = doc.createElement("RiferimentoNumeroLinea"); // questo elemento deve essere miglorato
					elemRifNumLin.appendChild(doc.createTextNode("1520"));
					elemDatiDDT.appendChild(elemRifNumLin);
				}
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumeroLineaDDT()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<RiferimentoNumeroLineaDDT> getListRiferimentoNumeroLineaDDT()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<RiferimentoNumeroLineaDDT> ordini = new ArrayList<RiferimentoNumeroLineaDDT>();
		RiferimentoNumeroLineaDDT ordine = new RiferimentoNumeroLineaDDT();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDatiTrasporto(Document doc, Element elemDatiGen) throws ParserConfigurationException, TransformerException
	{
		// DatiTrasporto elements
		Element elemDatiTrasporto = doc.createElement("DatiTrasporto");
		elemDatiGen.appendChild(elemDatiTrasporto);
		
			if(isDatiAnagraficiVettore()){
				builtDatiAnagraficiVettore(doc, elemDatiTrasporto);
			}
			
			if(isMezzoTrasporto()){
				// MezzoTrasporto elements
				Element elemMezzoTrasporto = doc.createElement("MezzoTrasporto");
				elemMezzoTrasporto.appendChild(doc.createTextNode("abcedefg"));
				elemDatiTrasporto.appendChild(elemMezzoTrasporto);
			}
			
			if(isCausaleTrasporto()){
				// CausaleTrasporto elements
				Element elemCausaleTrasporto = doc.createElement("CausaleTrasporto");
				elemCausaleTrasporto.appendChild(doc.createTextNode("abcedefg"));
				elemDatiTrasporto.appendChild(elemCausaleTrasporto);
			}
			
			if(isDescrizione()){
				// Descrizione elements
				Element elemDescrizione = doc.createElement("Descrizione");
				elemDescrizione.appendChild(doc.createTextNode("abcedefg"));
				elemDatiTrasporto.appendChild(elemDescrizione);
			}
			
			if(isUnitaMisuraPeso()){
				// UnitaMisuraPeso elements
				Element elemUnitaMisuraPeso = doc.createElement("UnitaMisuraPeso");
				elemUnitaMisuraPeso.appendChild(doc.createTextNode("abcedefg"));
				elemDatiTrasporto.appendChild(elemUnitaMisuraPeso);
			}
			
			if(isPesoLordo()){
				// PesoLordo elements
				Element elemPesoLordo = doc.createElement("PesoLordo");
				elemPesoLordo.appendChild(doc.createTextNode("1500.50"));
				elemDatiTrasporto.appendChild(elemPesoLordo);
			}
			
			if(isPesoNetto()){
				// PesoNetto elements
				Element elemPesoNetto = doc.createElement("PesoNetto");
				elemPesoNetto.appendChild(doc.createTextNode("150.50"));
				elemDatiTrasporto.appendChild(elemPesoNetto);
			}
			
			if(isDataOraRitiro()){
				// DataOraRitiro elements
				Element elemDataOraRitiro = doc.createElement("DataOraRitiro");
				elemDataOraRitiro.appendChild(doc.createTextNode("2015-03-27:09:00:00"));
				elemDatiTrasporto.appendChild(elemDataOraRitiro);
			}
			
			if(isDataInizioTrasporto()){
				// DataInizioTrasporto elements
				Element elemDataInizioTrasporto = doc.createElement("DataInizioTrasporto");
				elemDataInizioTrasporto.appendChild(doc.createTextNode("2015-03-27:20:45:00"));
				elemDatiTrasporto.appendChild(elemDataInizioTrasporto);
			}
			
			if(isTipoResa()){
				// TipoResa elements
				Element elemTipoResa = doc.createElement("TipoResa");
				elemTipoResa.appendChild(doc.createTextNode("ABC"));
				elemDatiTrasporto.appendChild(elemTipoResa);
			}
			
			if(isIndirizzoResa()){
				builtIndirizzoResa(doc, elemDatiTrasporto);
			}
			
			if(isDataOraConsegna()){
				// DataOraConsegna elements
				Element elemDataOraConsegna = doc.createElement("DataOraConsegna");
				elemDataOraConsegna.appendChild(doc.createTextNode("2015-03-27:21:15:30"));
				elemDatiTrasporto.appendChild(elemDataOraConsegna);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiAnagraficiVettore()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isMezzoTrasporto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCausaleTrasporto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDescrizione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isUnitaMisuraPeso()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isPesoLordo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isPesoNetto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataOraRitiro()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataInizioTrasporto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isTipoResa()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isIndirizzoResa()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataOraConsegna()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtDatiAnagraficiVettore(Document doc, Element elemDatiTrasporto) throws ParserConfigurationException, TransformerException
	{
		// DatiAnagraficiVettore elements
		Element elemDatiAnagVet = doc.createElement("DatiAnagraficiVettore");
		elemDatiTrasporto.appendChild(elemDatiAnagVet);
			
			builtIdFiscaleIva(doc, elemDatiAnagVet);
			
			if(isCodiceFiscale())
			{
				// CodiceFiscale  elements
				Element elemCodiceFiscale = doc.createElement("CodiceFiscale"); // questo elemento deve essere miglorato
				elemCodiceFiscale.appendChild(doc.createTextNode("LSEDAT15N4G458K"));
				elemDatiAnagVet.appendChild(elemCodiceFiscale);
			}
			
			builtAnagrafica(doc, elemDatiAnagVet);
			
			if(isNumeroLicenzaGuida()){
				// NumeroLicenzaGuida  elements
				Element elemNumLicenGui = doc.createElement("NumeroLicenzaGuida"); // questo elemento deve essere miglorato
				elemNumLicenGui.appendChild(doc.createTextNode("abcdefg"));
				elemDatiAnagVet.appendChild(elemNumLicenGui);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceFiscale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumeroLicenzaGuida()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
		
	private void builtIdFiscaleIva(Document doc, Element elemDatiAnagVet) throws ParserConfigurationException, TransformerException
	{
		// DatiIdFiscaleIva elements
		Element elemDatiIdFiscaleIva = doc.createElement("DatiIdFiscaleIva");
		elemDatiAnagVet.appendChild(elemDatiIdFiscaleIva);
		
			// IdPaese  elements
			Element elemIdPaese = doc.createElement("IdPaese"); // questo elemento deve essere miglorato
			elemIdPaese.appendChild(doc.createTextNode("IT"));
			elemDatiIdFiscaleIva.appendChild(elemIdPaese);
			
			// IdCodice  elements
			Element elemIdCodice = doc.createElement("IdCodice"); // questo elemento deve essere miglorato
			elemIdCodice.appendChild(doc.createTextNode("abcdef"));
			elemDatiIdFiscaleIva.appendChild(elemIdCodice);
	}
	
	private void builtAnagrafica(Document doc, Element elemDatiAnagVet) throws ParserConfigurationException, TransformerException
	{
		// Anagrafica elements
		Element elemAnagrafica = doc.createElement("Anagrafica");
		elemDatiAnagVet.appendChild(elemAnagrafica);
		
			if(isAnagraficaPersFisic())
			{
				// Denominazione  elements
				Element elemDenominazione = doc.createElement("Denominazione"); // questo elemento deve essere miglorato
				elemDenominazione.appendChild(doc.createTextNode("Mario Rossi"));
				elemAnagrafica.appendChild(elemDenominazione);
			}
			else if(isAnagraficaPersGiurid()){
				// Nome  elements
				Element elemNome = doc.createElement("Nome"); // questo elemento deve essere miglorato
				elemNome.appendChild(doc.createTextNode("Mario"));
				elemAnagrafica.appendChild(elemNome);
				
				// Cognome  elements
				Element elemCognome = doc.createElement("Cognome"); // questo elemento deve essere miglorato
				elemCognome.appendChild(doc.createTextNode("Rossi"));
				elemAnagrafica.appendChild(elemCognome);
			}
			else{
				throw new UnsupportedOperationException("Il valore deve essere per froza presente");
			}
			
			if(isTitolo()){
				// Titolo  elements
				Element elemTitolo = doc.createElement("Titolo"); // questo elemento deve essere miglorato
				elemTitolo.appendChild(doc.createTextNode("abcdefghi"));
				elemAnagrafica.appendChild(elemTitolo);
			}
			
			if(isCodEORI()){
				// CodEORI  elements
				Element elemCodEORI = doc.createElement("CodEORI"); // questo elemento deve essere miglorato
				elemCodEORI.appendChild(doc.createTextNode("abcdefghilmn"));
				elemAnagrafica.appendChild(elemCodEORI);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isAnagraficaPersFisic()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isAnagraficaPersGiurid()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isTitolo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodEORI()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtIndirizzoResa(Document doc, Element elemDatiTrasporto) throws ParserConfigurationException, TransformerException
	{
		// IndirizzoResa elements
		Element elemIndirizzoResa = doc.createElement("IndirizzoResa");
		elemDatiTrasporto.appendChild(elemIndirizzoResa);
			
			// Indirizzo  elements
			Element elemIndirizzo = doc.createElement("Indirizzo"); // questo elemento deve essere miglorato
			elemIndirizzo.appendChild(doc.createTextNode("Via Bubu"));
			elemIndirizzoResa.appendChild(elemIndirizzo);
			
			if(isNumeroCivico()){
				// NumeroCivico  elements
				Element elemNumeroCivico = doc.createElement("NumeroCivico"); // questo elemento deve essere miglorato
				elemNumeroCivico.appendChild(doc.createTextNode("17/b"));
				elemIndirizzoResa.appendChild(elemNumeroCivico);
			}
			
			// CAP  elements
			Element elemCAP = doc.createElement("CAP"); // questo elemento deve essere miglorato
			elemCAP.appendChild(doc.createTextNode("61032"));
			elemIndirizzoResa.appendChild(elemCAP);
			
			// Comune  elements
			Element elemComune = doc.createElement("Comune"); // questo elemento deve essere miglorato
			elemComune.appendChild(doc.createTextNode("Milano"));
			elemIndirizzoResa.appendChild(elemComune);
			
			if(isProvincia()){
				// Provincia  elements
				Element elemProvincia = doc.createElement("Provincia"); // questo elemento deve essere miglorato
				elemProvincia.appendChild(doc.createTextNode("MI"));
				elemIndirizzoResa.appendChild(elemProvincia);
			}
			
			// Nazione  elements
			Element elemNazione = doc.createElement("Nazione"); // questo elemento deve essere miglorato
			elemNazione.appendChild(doc.createTextNode("IT"));
			elemIndirizzoResa.appendChild(elemNazione);
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNumeroCivico()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isProvincia()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtDatiBeniServizi(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// DatiBeniServizi elements
		Element elemDatiBeniServizi = doc.createElement("DatiBeniServizi");
		elemFatEletBody.appendChild(elemDatiBeniServizi);
		
			List<DettaglioLinee> ordini = getDettaglioLinee();
			for(DettaglioLinee ordine : ordini){
				builtDettaglioLinee(doc, elemDatiBeniServizi);
			}
			
			List<DatiRiepilogo> ordiniRiep = getDatiRiepilogo();
			for(DatiRiepilogo ordine : ordiniRiep){
				builtDatiRiepilogo(doc, elemDatiBeniServizi);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DettaglioLinee> getDettaglioLinee()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DettaglioLinee> ordini = new ArrayList<DettaglioLinee>();
		DettaglioLinee ordine = new DettaglioLinee();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiRiepilogo> getDatiRiepilogo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiRiepilogo> ordini = new ArrayList<DatiRiepilogo>();
		DatiRiepilogo ordine = new DatiRiepilogo();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtDettaglioLinee(Document doc, Element elemDatiBeniServizi) throws ParserConfigurationException, TransformerException
	{
		// DettaglioLinee elements
		Element elemDettaglioLinee = doc.createElement("DettaglioLinee");
		elemDatiBeniServizi.appendChild(elemDettaglioLinee);
		
			// NumeroLinea elements
			Element elemNumeroLinea = doc.createElement("NumeroLinea"); // questo elemento deve essere miglorato
			elemNumeroLinea.appendChild(doc.createTextNode("1"));
			elemDettaglioLinee.appendChild(elemNumeroLinea);
		
			if(isTipoCessionePrestazione()){
				// TipoCessionePrestazione elements
				Element elemTipCesPrest = doc.createElement("TipoCessionePrestazione"); // questo elemento deve essere miglorato
				elemTipCesPrest.appendChild(doc.createTextNode("1"));
				elemDettaglioLinee.appendChild(elemTipCesPrest);
			}
			
			if(isCodiceArticolo()){
				List<CodcieArticolo> ordini = getCodcieArticolo();
				for(CodcieArticolo ordine : ordini){
					builtCodiceArticolo(doc, elemDettaglioLinee);
				}
			}
			
			// Descrizione elements
			Element elemDescrizione = doc.createElement("Descrizione"); // questo elemento deve essere miglorato
			elemDescrizione.appendChild(doc.createTextNode("annnnadjfakfasfnjwgnwueg"));
			elemDettaglioLinee.appendChild(elemDescrizione);
			
			if(isQuantita()){
				// Quantita elements
				Element elemQuantita = doc.createElement("Quantita"); // questo elemento deve essere miglorato
				elemQuantita.appendChild(doc.createTextNode("2"));
				elemDettaglioLinee.appendChild(elemQuantita);
			}
			
			if(isUnitaMisura()){
				// UnitaMisura elements
				Element elemUnitaMisura = doc.createElement("UnitaMisura"); // questo elemento deve essere miglorato
				elemUnitaMisura.appendChild(doc.createTextNode("2"));
				elemDettaglioLinee.appendChild(elemUnitaMisura);
			}
			
			if(isDataInizioPeriodo()){
				// DataInizioPeriodo elements
				Element elemDatInizPer = doc.createElement("DataInizioPeriodo"); // questo elemento deve essere miglorato
				elemDatInizPer.appendChild(doc.createTextNode("2015-04-03"));
				elemDettaglioLinee.appendChild(elemDatInizPer);
			}
			
			if(isDataFinePeriodo()){
				// DataFinePeriodo elements
				Element elemDatFinPer = doc.createElement("DataFinePeriodo"); // questo elemento deve essere miglorato
				elemDatFinPer.appendChild(doc.createTextNode("2015-04-08"));
				elemDettaglioLinee.appendChild(elemDatFinPer);
			}
			
			// PrezzoUnitario elements
			Element elemPrezUnit = doc.createElement("PrezzoUnitario"); // questo elemento deve essere miglorato
			elemPrezUnit.appendChild(doc.createTextNode("105"));
			elemDettaglioLinee.appendChild(elemPrezUnit);
			
			if(isScontoMaggiorazioneBS()){
				List<ScontoMaggiorazioneBS> ordini = getScontoMaggiorazioneBS();
				for(ScontoMaggiorazioneBS ordine : ordini){
					builtScontoMaggiorazioneBS(doc, elemDettaglioLinee);
				}
			}
			
			// PrezzoTotale elements
			Element elemPrezTot = doc.createElement("PrezzoTotale"); // questo elemento deve essere miglorato
			elemPrezTot.appendChild(doc.createTextNode("110"));
			elemDettaglioLinee.appendChild(elemPrezTot);
			
			// AliquotaIVA elements
			Element elemAliquIVA = doc.createElement("AliquotaIVA"); // questo elemento deve essere miglorato
			elemAliquIVA.appendChild(doc.createTextNode("22"));
			elemDettaglioLinee.appendChild(elemAliquIVA);
			
			if(isRitenutaBS()){
				// Ritenuta elements
				Element elemRitenuta = doc.createElement("Ritenuta"); // questo elemento deve essere miglorato
				elemRitenuta.appendChild(doc.createTextNode("135"));
				elemDettaglioLinee.appendChild(elemRitenuta);
			}
			
			if(isNaturaBS()){
				// Natura elements
				Element elemNatura = doc.createElement("Natura"); // questo elemento deve essere miglorato
				elemNatura.appendChild(doc.createTextNode("N3"));
				elemDettaglioLinee.appendChild(elemNatura);
			}
			
			if(isRiferimentoAmministrativo()){
				// RiferimentoAmministrativo elements
				Element elemRiferAmminist = doc.createElement("RiferimentoAmministrativo"); // questo elemento deve essere miglorato
				elemRiferAmminist.appendChild(doc.createTextNode("abcdefasdg"));
				elemDettaglioLinee.appendChild(elemRiferAmminist);
			}
			
			if(isAltriDatiGestionali()){
				List<AltriDatiGestionali> ordini = getAltriDatiGestionali();
				for(AltriDatiGestionali ordine : ordini){
					builtAltriDatiGestionali(doc, elemDettaglioLinee);
				}
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isTipoCessionePrestazione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodiceArticolo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<CodcieArticolo> getCodcieArticolo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<CodcieArticolo> ordini = new ArrayList<CodcieArticolo>();
		CodcieArticolo ordine = new CodcieArticolo();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtCodiceArticolo(Document doc, Element elemDettaglioLinee) throws ParserConfigurationException, TransformerException
	{
		// DettaglioLinee elements
		Element elemCodiceArticolo = doc.createElement("CodiceArticolo");
		elemDettaglioLinee.appendChild(elemCodiceArticolo);
		
			// CodiceTipo elements
			Element elemCodiceTipo = doc.createElement("CodiceTipo"); // questo elemento deve essere miglorato
			elemCodiceTipo.appendChild(doc.createTextNode("1"));
			elemCodiceArticolo.appendChild(elemCodiceTipo);
			
			// CodiceValore elements
			Element elemCodiceValore = doc.createElement("CodiceValore"); // questo elemento deve essere miglorato
			elemCodiceValore.appendChild(doc.createTextNode("1"));
			elemCodiceArticolo.appendChild(elemCodiceValore);
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isQuantita()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNaturaBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoAmministrativo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isUnitaMisura()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataInizioPeriodo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataFinePeriodo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<ScontoMaggiorazioneBS> getScontoMaggiorazioneBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<ScontoMaggiorazioneBS> ordini = new ArrayList<ScontoMaggiorazioneBS>();
		ScontoMaggiorazioneBS ordine = new ScontoMaggiorazioneBS();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isScontoMaggiorazioneBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtScontoMaggiorazioneBS(Document doc, Element elemDettaglioLinee) throws ParserConfigurationException, TransformerException
	{
		// DettaglioLinee elements
		Element elemScontoMaggiorazione = doc.createElement("DettaglioLinee");
		elemDettaglioLinee.appendChild(elemScontoMaggiorazione);
		
			// Tipo elements
			Element elemTipo = doc.createElement("Tipo"); // questo elemento deve essere miglorato
			elemTipo.appendChild(doc.createTextNode("1"));
			elemScontoMaggiorazione.appendChild(elemTipo);
			
			if(isPercentualeBS()){
				// Percentuale elements
				Element elemPercentuale = doc.createElement("Percentuale"); // questo elemento deve essere miglorato
				elemPercentuale.appendChild(doc.createTextNode("14"));
				elemScontoMaggiorazione.appendChild(elemPercentuale);
			}
			
			if(isImportoBS()){
				// Importo elements
				Element elemImporto = doc.createElement("Importo"); // questo elemento deve essere miglorato
				elemImporto.appendChild(doc.createTextNode("1100"));
				elemScontoMaggiorazione.appendChild(elemImporto);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isPercentualeBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isImportoBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRitenutaBS()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isAltriDatiGestionali()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<AltriDatiGestionali> getAltriDatiGestionali()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<AltriDatiGestionali> ordini = new ArrayList<AltriDatiGestionali>();
		AltriDatiGestionali ordine = new AltriDatiGestionali();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtAltriDatiGestionali(Document doc, Element elemDettaglioLinee) throws ParserConfigurationException, TransformerException
	{
		// AltriDatiGestionali elements
		Element elemAltriDatiGestionali = doc.createElement("AltriDatiGestionali");
		elemDettaglioLinee.appendChild(elemAltriDatiGestionali);
		
			// TipoDato elements
			Element elemTipoDato = doc.createElement("TipoDato"); // questo elemento deve essere miglorato
			elemTipoDato.appendChild(doc.createTextNode("abcd"));
			elemAltriDatiGestionali.appendChild(elemTipoDato);
			
			if(isRiferimentoTesto()){
				// RiferimentoTesto elements
				Element elemRiferTesto = doc.createElement("RiferimentoTesto"); // questo elemento deve essere miglorato
				elemRiferTesto.appendChild(doc.createTextNode("abcd"));
				elemAltriDatiGestionali.appendChild(elemRiferTesto);
			}
			
			if(isRiferimentoNumero()){
				// RiferimentoTesto elements
				Element elemRiferNumero = doc.createElement("RiferimentoNumero"); // questo elemento deve essere miglorato
				elemRiferNumero.appendChild(doc.createTextNode("11452.00"));
				elemAltriDatiGestionali.appendChild(elemRiferNumero);
			}
			
			if(isRiferimentoData()){
				// RiferimentoData elements
				Element elemRiferNumero = doc.createElement("RiferimentoData"); // questo elemento deve essere miglorato
				elemRiferNumero.appendChild(doc.createTextNode("2015-04-04"));
				elemAltriDatiGestionali.appendChild(elemRiferNumero);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoTesto()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNumero()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoData()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtDatiRiepilogo(Document doc, Element elemDatiBeniServizi) throws ParserConfigurationException, TransformerException
	{
		// DatiRiepilogo elements
		Element elemDatiRiepilogo = doc.createElement("DatiRiepilogo");
		elemDatiBeniServizi.appendChild(elemDatiRiepilogo);
		
			// AliquotaIVA elements
			Element elemAliquIVA = doc.createElement("AliquotaIVA"); // questo elemento deve essere miglorato
			elemAliquIVA.appendChild(doc.createTextNode("22"));
			elemDatiRiepilogo.appendChild(elemAliquIVA);
			
			if(isNaturaDR()){
				// Natura elements
				Element elemNatura = doc.createElement("Natura"); // questo elemento deve essere miglorato
				elemNatura.appendChild(doc.createTextNode("N2"));
				elemDatiRiepilogo.appendChild(elemNatura);
			}
			
			if(isSpeseAccessorieDR()){
				// SpeseAccessorie elements
				Element elemSpesAcces = doc.createElement("SpeseAccessorie"); // questo elemento deve essere miglorato
				elemSpesAcces.appendChild(doc.createTextNode("1002.50"));
				elemDatiRiepilogo.appendChild(elemSpesAcces);
			}
			
			if(isArrotondamentoDR()){
				// Arrotondamento elements
				Element elemArrotondamento = doc.createElement("Arrotondamento"); // questo elemento deve essere miglorato
				elemArrotondamento.appendChild(doc.createTextNode("10.00"));
				elemDatiRiepilogo.appendChild(elemArrotondamento);
			}
			
			// ImponibileImporto elements
			Element elemImponibileImporto = doc.createElement("ImponibileImporto"); // questo elemento deve essere miglorato
			elemImponibileImporto.appendChild(doc.createTextNode("22.00"));
			elemDatiRiepilogo.appendChild(elemImponibileImporto);
			
			// Imposta elements
			Element elemImposta = doc.createElement("Imposta"); // questo elemento deve essere miglorato
			elemImposta.appendChild(doc.createTextNode("22.00"));
			elemDatiRiepilogo.appendChild(elemImposta);
			
			if(isEsigibilitaIVA()){
				// EsigibilitaIVA elements
				Element elemEsigibilitaIVA = doc.createElement("EsigibilitaIVA"); // questo elemento deve essere miglorato
				elemEsigibilitaIVA.appendChild(doc.createTextNode("S"));
				elemDatiRiepilogo.appendChild(elemEsigibilitaIVA);
			}
			
			if(isRiferimentoNormativo()){
				// RiferimentoNormativo elements
				Element elemRiferimentoNormativo = doc.createElement("RiferimentoNormativo"); // questo elemento deve essere miglorato
				elemRiferimentoNormativo.appendChild(doc.createTextNode("abcdefghi"));
				elemDatiRiepilogo.appendChild(elemRiferimentoNormativo);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNaturaDR()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isSpeseAccessorieDR()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isArrotondamentoDR()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isEsigibilitaIVA()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isRiferimentoNormativo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiVeicolo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDatiPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	private void builtDatiVeicoli(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// DatiVeicoli elements
		Element elemDatiVeicoli = doc.createElement("DatiVeicoli");
		elemFatEletBody.appendChild(elemDatiVeicoli);
			
			// Data elements
			Element elemData = doc.createElement("Data"); 
			elemData.appendChild(doc.createTextNode("2015-04-04"));
			elemDatiVeicoli.appendChild(elemData);
			
			// TotalePercorso elements
			Element elemTotalePercorso = doc.createElement("TotalePercorso"); 
			elemTotalePercorso.appendChild(doc.createTextNode("11154"));
			elemDatiVeicoli.appendChild(elemTotalePercorso);
	}
	
	private void builtDatiPagamento(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// DatiPagamento elements
		Element elemDatiPagamento = doc.createElement("DatiPagamento");
		elemFatEletBody.appendChild(elemDatiPagamento);
			
			// CondizioniPagamento elements
			Element elemCondizioniPagamento = doc.createElement("CondizioniPagamento"); 
			elemCondizioniPagamento.appendChild(doc.createTextNode("abcdefghijklmnpqrstuvwxyz"));
			elemDatiPagamento.appendChild(elemCondizioniPagamento);
			
			List<DettaglioPagamento> ordini = getDettaglioPagamento();
			for(DettaglioPagamento ordine : ordini){
				builtDettaglioPagamento(doc, elemDatiPagamento);
			}
	}
	
	private void builtDettaglioPagamento(Document doc, Element elemDatiPagamento) throws ParserConfigurationException, TransformerException
	{
		// DettaglioPagamento elements
		Element elemDettaglioPagamento = doc.createElement("DettaglioPagamento");
		elemDatiPagamento.appendChild(elemDettaglioPagamento);
			
			if(isBenficiario()){
				// Benficiario elements
				Element elemBenficiario = doc.createElement("Benficiario"); 
				elemBenficiario.appendChild(doc.createTextNode("Marco"));
				elemDettaglioPagamento.appendChild(elemBenficiario);
			}
			
			// ModalitaPagamento elements
			Element elemModalitaPagamento = doc.createElement("ModalitaPagamento"); 
			elemModalitaPagamento.appendChild(doc.createTextNode("MP01"));
			elemDettaglioPagamento.appendChild(elemModalitaPagamento);
			
			if(isDataRiferimentoTerminiPagamento()){
				// DataRiferimentoTerminiPagamento elements
				Element elemDataRiferTermPagam = doc.createElement("DataRiferimentoTerminiPagamento"); 
				elemDataRiferTermPagam.appendChild(doc.createTextNode("2015-04-04"));
				elemDettaglioPagamento.appendChild(elemDataRiferTermPagam);
			}
			
			if(isGiorniTerminiPagamento()){
				// GiorniTerminiPagamento elements
				Element elemGiorniTerPagam = doc.createElement("GiorniTerminiPagamento"); 
				elemGiorniTerPagam.appendChild(doc.createTextNode("15"));
				elemDettaglioPagamento.appendChild(elemGiorniTerPagam);
			}
			
			if(isDataScadenzaPagamento()){
				// DataScadenzaPagamento elements
				Element elemDataScadPagam = doc.createElement("DataScadenzaPagamento"); 
				elemDataScadPagam.appendChild(doc.createTextNode("2015-04-27"));
				elemDettaglioPagamento.appendChild(elemDataScadPagam);
			}
			
			// ImportoPagamento elements
			Element elemImpoPagam = doc.createElement("ImportoPagamento"); 
			elemImpoPagam.appendChild(doc.createTextNode("1115420.68"));
			elemDettaglioPagamento.appendChild(elemImpoPagam);
			
			if(isCodUfficioPostale()){
				// CodUfficioPostale elements
				Element elemCodUfficPost = doc.createElement("CodUfficioPostale"); 
				elemCodUfficPost.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemCodUfficPost);
			}
			
			if(isCognomeQuietanzante()){
				// CognomeQuietanzante elements
				Element elemCognomeQuietanzante = doc.createElement("CognomeQuietanzante"); 
				elemCognomeQuietanzante.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemCognomeQuietanzante);
			}
			
			if(isNomeQuietanzante()){
				// NomeQuietanzante elements
				Element elemNomeQuietanzante = doc.createElement("NomeQuietanzante"); 
				elemNomeQuietanzante.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemNomeQuietanzante);
			}
			
			if(isCFQuietanzante()){
				// CFQuietanzante elements
				Element elemCFQuietanzante = doc.createElement("CFQuietanzante"); 
				elemCFQuietanzante.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemCFQuietanzante);
			}
			
			if(isTitoloQuietanzante()){
				// TitoloQuietanzante elements
				Element elemTitoloQuietanzante = doc.createElement("TitoloQuietanzante"); 
				elemTitoloQuietanzante.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemTitoloQuietanzante);
			}
			
			if(isIstitutoFinanziario()){
				// IstitutoFinanziario elements
				Element elemIstitutoFinanziario = doc.createElement("IstitutoFinanziario"); 
				elemIstitutoFinanziario.appendChild(doc.createTextNode("abcdefg"));
				elemDettaglioPagamento.appendChild(elemIstitutoFinanziario);
			}
			
			if(isIBAN()){
				// IBAN elements
				Element elemIBAN = doc.createElement("IBAN"); 
				elemIBAN.appendChild(doc.createTextNode("abcdefghi"));
				elemDettaglioPagamento.appendChild(elemIBAN);
			}
			
			if(isABI()){
				// ABI elements
				Element elemABI = doc.createElement("ABI"); 
				elemABI.appendChild(doc.createTextNode("abcd"));
				elemDettaglioPagamento.appendChild(elemABI);
			}
			
			if(isCAB()){
				// CAB elements
				Element elemCAB = doc.createElement("CAB"); 
				elemCAB.appendChild(doc.createTextNode("abcd"));
				elemDettaglioPagamento.appendChild(elemCAB);
			}
			
			if(isBIC()){
				// BIC elements
				Element elemBIC = doc.createElement("BIC"); 
				elemBIC.appendChild(doc.createTextNode("abcd"));
				elemDettaglioPagamento.appendChild(elemBIC);
			}
			
			if(isScontoPagamentoAnticipato()){
				// ScontoPagamentoAnticipato elements
				Element elemSconPagamAntic = doc.createElement("ScontoPagamentoAnticipato"); 
				elemSconPagamAntic.appendChild(doc.createTextNode("152.56"));
				elemDettaglioPagamento.appendChild(elemSconPagamAntic);
			}
			
			if(isDataLimitePagamentoAnticipato()){
				// DataLimitePagamentoAnticipato elements
				Element elemDatLimPagamAntic = doc.createElement("DataLimitePagamentoAnticipato"); 
				elemDatLimPagamAntic.appendChild(doc.createTextNode("2015-04-04"));
				elemDettaglioPagamento.appendChild(elemDatLimPagamAntic);
			}
			
			if(isPenalitaPagamentiRitardati()){
				// PenalitaPagamentiRitardati elements
				Element elemPenalPagamRitar = doc.createElement("PenalitaPagamentiRitardati"); 
				elemPenalPagamRitar.appendChild(doc.createTextNode("150.54"));
				elemDettaglioPagamento.appendChild(elemPenalPagamRitar);
			}
			
			if(isDataDecorrenzaPenale()){
				// DataDecorrenzaPenale elements
				Element elemDataDecorPenale = doc.createElement("DataDecorrenzaPenale"); 
				elemDataDecorPenale.appendChild(doc.createTextNode("2016-08-25"));
				elemDettaglioPagamento.appendChild(elemDataDecorPenale);
			}
			
			if(isCodicePagamento()){
				// CodicePagamento elements
				Element elemCodicePagamento = doc.createElement("CodicePagamento"); 
				elemCodicePagamento.appendChild(doc.createTextNode("abcdefghilmnopq"));
				elemDettaglioPagamento.appendChild(elemCodicePagamento);
			}
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isBenficiario()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataRiferimentoTerminiPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isGiorniTerminiPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataScadenzaPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodUfficioPostale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCognomeQuietanzante()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isNomeQuietanzante()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCFQuietanzante()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isTitoloQuietanzante()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isIstitutoFinanziario()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isIBAN()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isABI()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCAB()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isBIC()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isScontoPagamentoAnticipato()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataLimitePagamentoAnticipato()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isPenalitaPagamentiRitardati()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDataDecorrenzaPenale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isCodicePagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<DettaglioPagamento> getDettaglioPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DettaglioPagamento> ordini = new ArrayList<DettaglioPagamento>();
		DettaglioPagamento ordine = new DettaglioPagamento();
		ordini.add(ordine);
		
		return ordini;
	}
		
	private void builtFatturaElettronicaBody(Document doc, Element rootElement) throws ParserConfigurationException, TransformerException
	{
		// FatturaElettronicaBody elements
		Element elemFatEletBody = doc.createElement("FatturaElettronicaBody");
		rootElement.appendChild(elemFatEletBody);
		
		builtBody(doc, elemFatEletBody);
			
	}
	
	private void builtBody(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// Blocco per dati generali
		builtDatiGenereali(doc, elemFatEletBody);
		
		// Blocco per dati beni/servizi
		builtDatiBeniServizi(doc, elemFatEletBody);
		
		if(isDatiVeicolo()){
			// Blocco per dati veicoli
			builtDatiVeicoli(doc, elemFatEletBody);
		}
		
		if(isDatiPagamento()){
			List<DatiPagamento> ordini = getDatiPagamento();
			for(DatiPagamento ordine : ordini){
				builtDatiPagamento(doc, elemFatEletBody);
			}
		}
		
		if(isAllegati()){
			List<Allegati> ordini = getAllegati();
			for(Allegati ordine : ordini){
				builtAllegati(doc, elemFatEletBody);
			}
		}
	}
	
	
	@Deprecated
	// TODO implementare il metodo
	private List<DatiPagamento> getDatiPagamento()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<DatiPagamento> ordini = new ArrayList<DatiPagamento>();
		DatiPagamento ordine = new DatiPagamento();
		ordini.add(ordine);
		
		return ordini;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isAllegati()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private List<Allegati> getAllegati()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		
		List<Allegati> ordini = new ArrayList<Allegati>();
		Allegati ordine = new Allegati();
		ordini.add(ordine);
		
		return ordini;
	}
	
	private void builtAllegati(Document doc, Element elemFatEletBody) throws ParserConfigurationException, TransformerException
	{
		// Allegati elements
		Element elemAllegati = doc.createElement("Allegati");
		elemFatEletBody.appendChild(elemAllegati);
			
			// NomeAttachment elements
			Element elemNomeAttachment = doc.createElement("NomeAttachment"); 
			elemNomeAttachment.appendChild(doc.createTextNode("abcdefghijklmnpqrstuvwxyz"));
			elemAllegati.appendChild(elemNomeAttachment);
			
			if(isAlgoritmoCompressione()){
				// AlgoritmoCoompressione elements
				Element elemAlgoritmoCompressione = doc.createElement("AlgoritmoCoompressione"); 
				elemAlgoritmoCompressione.appendChild(doc.createTextNode("abcde"));
				elemAllegati.appendChild(elemAlgoritmoCompressione);
			}
			
			if(isFormatoAttachment()){
				// FormatoAttachment elements
				Element elemFormatoAttachment = doc.createElement("FormatoAttachment"); 
				elemFormatoAttachment.appendChild(doc.createTextNode("abcde"));
				elemAllegati.appendChild(elemFormatoAttachment);
			}
			
			if(isDescrizioneAttachment()){
				// DescrizioneAttachment elements
				Element elemDescrizioneAttachment = doc.createElement("DescrizioneAttachment"); 
				elemDescrizioneAttachment.appendChild(doc.createTextNode("abcde"));
				elemAllegati.appendChild(elemDescrizioneAttachment);
			}
			
			// Attachment elements
			Element elemAttachment = doc.createElement("Attachment"); 
			elemAttachment.appendChild(doc.createTextNode("abcde"));
			elemAllegati.appendChild(elemAttachment);
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isAlgoritmoCompressione()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isFormatoAttachment()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO implementare il metodo
	private boolean isDescrizioneAttachment()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	
	
	private void writeXML(Document doc) throws ParserConfigurationException, TransformerException
	{
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("file.xml"));
		//result = new StreamResult(System.out); 
		
		transformer.transform(source, result);
	}
	
	private void begin(Document doc) throws ParserConfigurationException, TransformerException
	{
		// root elements
		Element rootElement = doc.createElement("FatturaElettronica");
		doc.appendChild(rootElement);
			
			// Giorgia
			builtFatturaElettronicaHeader(doc, rootElement);
			
			// Matteo
			builtFatturaElettronicaBody(doc, rootElement);
	}
	
	private void builtFatturaElettronicaHeader(Document doc, Element rootElement) throws ParserConfigurationException, TransformerException
	{
		Element elemFatturaHeader = doc.createElement("FatturaElettronicaHeader");
		rootElement.appendChild(elemFatturaHeader);
		
		creaDatiTrasmissione(doc, elemFatturaHeader);
		creaPrestatore(doc, elemFatturaHeader);
		creaRapFiscale(doc, elemFatturaHeader);
		creaCessionario(doc, elemFatturaHeader);
		creaTerzoIntermediario(doc, elemFatturaHeader);
	}
	
	private void creaDatiTrasmissione(Document doc, Element elemFatturaHeader) throws ParserConfigurationException, TransformerException
	{
		Element elemDatiTrasm = doc.createElement("DatiTrasmissione");
		elemFatturaHeader.appendChild(elemDatiTrasm);
			
			Element elemIdTrasm = doc.createElement("IdTrasmittente");
			elemDatiTrasm.appendChild(elemIdTrasm );
			
				Element elemidPaese = doc.createElement("IdPaese");
				elemidPaese.appendChild(doc.createTextNode("00"));
				elemIdTrasm.appendChild(elemidPaese);
				
				Element elemIdCodice = doc.createElement("IdCodice");
				elemIdCodice.appendChild(doc.createTextNode("888"));
				elemIdTrasm.appendChild(elemIdCodice);
				
			Element elemProInvio = doc.createElement("ProgressivoInvio");
			elemProInvio.appendChild(doc.createTextNode(" "));
			elemDatiTrasm.appendChild(elemProInvio);
			
			Element elemForTrasm = doc.createElement("FormatoTrasmissione");
			elemForTrasm.appendChild(doc.createTextNode(" "));
			elemDatiTrasm.appendChild(elemForTrasm);
			
			Element elemCodTest = doc.createElement("CodiceDestinatario");
			elemCodTest.appendChild(doc.createTextNode(" "));
			elemDatiTrasm.appendChild(elemCodTest);
			
				if(isContattiTrasmittente()){
					Element elemContTrasm = doc.createElement("ContattiTrasmittente");
					elemContTrasm.appendChild(doc.createTextNode(" "));
					elemDatiTrasm.appendChild(elemContTrasm);
						
					//Attenzione controllare gli if e crearli
					
						Element elemNumTel = doc.createElement("Telefono");
						elemNumTel.appendChild(doc.createTextNode(" "));
						elemContTrasm.appendChild(elemNumTel);
						
						Element elemEmail = doc.createElement("Email");
						elemEmail.appendChild(doc.createTextNode(" "));
						elemContTrasm.appendChild(elemEmail);
				}			
	}
	
	private void creaPrestatore(Document doc, Element elemFatturaHeader) throws ParserConfigurationException, TransformerException
	{
		
		Element elemCedentePrest = doc.createElement("CedentePrestatore");
		elemFatturaHeader.appendChild(elemCedentePrest);
		
		Element datia = creaDatiAnagrafici(doc, elemCedentePrest);
		
		if(isAlboProfessionale()){
			Element elemAlboP = doc.createElement("AlboProfessionale");
			elemAlboP.appendChild(doc.createTextNode(" "));
			datia.appendChild(elemAlboP);
		}
		if(isProvinciaAlbo()){
			Element elemProA =  doc.createElement("ProvinciaAlbo");
			elemProA.appendChild(doc.createTextNode(" "));
			datia.appendChild(elemProA);
		}
		if(isNumIscrAlbo()){
			Element elemNumIA = doc.createElement("NumeroIscrizioneAlbo");
			elemNumIA.appendChild(doc.createTextNode(" "));
			datia.appendChild(elemNumIA);
		}
		if(isDataIscrAlbo()){
			Element elemDataIA = doc.createElement("DataIscrizioneAlbo");
			elemDataIA.appendChild(doc.createTextNode(" "));
			datia.appendChild(elemDataIA);
		}
		
		Element elemRegimeF = doc.createElement("RegimeFiscale");
		elemRegimeF.appendChild(doc.createTextNode(" "));
		datia.appendChild(elemRegimeF);
		
		creaSede(doc, elemCedentePrest);
		creaStabileOrganizzazion(doc, elemCedentePrest);	
		creaIscrizioneRea(doc, elemCedentePrest);
		creaContatti(doc, elemCedentePrest);
		
		Element elemAmministrazione = doc.createElement("RiferimentoAmministrazione");
		elemAmministrazione.appendChild(doc.createTextNode(" "));
		elemCedentePrest.appendChild(elemAmministrazione);
		
		
	}
	
	public Element creaDatiAnagrafici(Document doc, Element elem) throws ParserConfigurationException, TransformerException
	{
		Element elemDatiAna = doc.createElement("DatiAnagrafici");
		elem.appendChild(elemDatiAna);
		
			Element elemIdFiscIVA = doc.createElement("IdFiscaleIVA");
			elemDatiAna.appendChild(elemIdFiscIVA);
			
				Element elemIdPaese = doc.createElement("IdPaese");
				elemIdPaese.appendChild(doc.createTextNode(" "));
				elemIdFiscIVA.appendChild(elemIdPaese);
				
				Element elemIdCodice = doc.createElement("IdCodice");
				elemIdCodice.appendChild(doc.createTextNode(" "));
				elemIdFiscIVA.appendChild(elemIdCodice);
				
			if(isCodiceFiscaleHD()){
				
				Element elemCodiceFiscale = doc.createElement("CodiceFiscale");
				elemCodiceFiscale.appendChild(doc.createTextNode(" "));
				elemDatiAna.appendChild(elemCodiceFiscale);
			}
				
			Element elemAnagrafica = doc.createElement("Anagrafica");
			elemDatiAna.appendChild(elemAnagrafica);
				
				if(isDenominazione()){
					
					Element elemDeno = doc.createElement("Denominazione");
					elemDeno.appendChild(doc.createTextNode(" "));
					elemAnagrafica.appendChild(elemDeno);
					
				}else{
					
					Element elemNome = doc.createElement("Nome");
					elemNome.appendChild(doc.createTextNode(" "));
					elemAnagrafica.appendChild(elemNome);
					
					Element elemCognome = doc.createElement("Cognome");
					elemCognome.appendChild(doc.createAttribute(" "));
					elemAnagrafica.appendChild(elemCognome);
				}
				if(isTitoloHD()){
					Element elemTitolo = doc.createElement("Titolo");
					elemTitolo.appendChild(doc.createTextNode(" "));
					elemAnagrafica.appendChild(elemTitolo);
				}
				if(isCodEori()){
					Element elemCodEori = doc.createElement("CodEori");
					elemCodEori.appendChild(doc.createTextNode(" "));
					elemAnagrafica.appendChild(elemCodEori);
				}
				
		return elemDatiAna;
	}
	
	private void creaSede(Document doc, Element elem) throws ParserConfigurationException, TransformerException
	{
		Element elemSede = doc.createElement("Sede");
		elem.appendChild(elemSede);
		
			Element elemIndirizzo = doc.createElement("Indirizzo");
			elemIndirizzo.appendChild(doc.createTextNode(" "));
			elemSede.appendChild(elemIndirizzo);
			
			if(isNumC()){
				Element elemNumC = doc.createElement("NumeroCivico");
				elemNumC.appendChild(doc.createTextNode(" "));
				elemSede.appendChild(elemNumC);
			}
			
			Element elemCAP = doc.createElement("CAP");
			elemCAP.appendChild(doc.createTextNode(" "));
			elemSede.appendChild(elemCAP);
			
			Element elemComune = doc.createElement("Comune");
			elemComune.appendChild(doc.createTextNode(" "));
			elemSede.appendChild(elemComune);
			
			if(isProvinciaHD()){
				Element elemProvincia = doc.createElement("Provincia");
				elemProvincia.appendChild(doc.createTextNode(" "));
				elemSede.appendChild(elemProvincia);
			}
			
			Element elemNazione = doc.createElement("Nazione");
			elemNazione.appendChild(doc.createTextNode(" "));
			elemSede.appendChild(elemNazione);
	}
	
	private void creaStabileOrganizzazion(Document doc, Element elemCedentePrest) throws ParserConfigurationException, TransformerException
	{
		
		if(isStabOrg()){
			Element elemStabileOrg = doc.createElement("StabileOrganizzazione");
			
				Element elemIndirizzo2 = doc.createElement("Indirizzo");
				elemIndirizzo2.appendChild(doc.createTextNode(" "));
				elemStabileOrg.appendChild(elemIndirizzo2);
				
				if(isNumC()){
					Element elemNumC = doc.createElement("NumeroCivico");
					elemNumC.appendChild(doc.createTextNode(" "));
					elemStabileOrg.appendChild(elemNumC);
				}
				
				Element elemCAP2 = doc.createElement("CAP");
				elemCAP2.appendChild(doc.createTextNode(" "));
				elemStabileOrg.appendChild(elemCAP2);
				
				Element elemComune2 = doc.createElement("Comune");
				elemComune2.appendChild(doc.createTextNode(" "));
				elemStabileOrg.appendChild(elemComune2);
				
				if(isProvincia()){
					Element elemProvincia2 = doc.createElement("Provincia");
					elemProvincia2.appendChild(doc.createTextNode(" "));
					elemStabileOrg.appendChild(elemProvincia2);
				}
				
				Element elemNazione2 = doc.createElement("Nazione");
				elemNazione2.appendChild(doc.createTextNode(" "));
				elemStabileOrg.appendChild(elemNazione2);	
		}
	}

	private void creaIscrizioneRea(Document doc, Element elemCedentePrest) throws ParserConfigurationException, TransformerException
	{
		Element elemRea = doc.createElement("InscrizioneREA");
		elemCedentePrest.appendChild(elemRea);
			Element elemUfficio = doc.createElement("Ufficio");
			elemUfficio.appendChild(doc.createTextNode(" "));
			elemRea.appendChild(elemUfficio);
				
			Element elemNumRea = doc.createElement("NumeroREA");
			elemNumRea.appendChild(doc.createTextNode(" "));
			elemRea.appendChild(elemNumRea);
				
			Element elemCapSoc = doc.createElement("CapitaleSociale");
			elemCapSoc.appendChild(doc.createTextNode(" "));
			elemRea.appendChild(elemCapSoc);
				
			Element elemSocU = doc.createElement("SocioUnico");
			elemSocU.appendChild(doc.createTextNode(" "));
			elemRea.appendChild(elemSocU);
				
			Element elemStaLiq = doc.createElement("StatoLiquidazione");
			elemStaLiq.appendChild(doc.createTextNode(" "));
			elemRea.appendChild(elemStaLiq);
	}
		
	private void creaContatti(Document doc, Element elemCedentePrest) throws ParserConfigurationException, TransformerException
	{
		Element elemContatti = doc.createElement("Contatti");
		elemCedentePrest.appendChild(elemContatti);
		
			Element elemTel = doc.createElement("Telefono");
			elemContatti.appendChild(doc.createTextNode(" "));
			elemContatti.appendChild(elemTel);
				
			Element elemFax = doc.createElement("Fax");
			elemFax.appendChild(doc.createTextNode(" "));
			elemContatti.appendChild(elemFax);
				
			Element elemMail = doc.createElement("Email");
			elemMail.appendChild(doc.createTextNode(" "));
			elemContatti.appendChild(elemMail);
	}

	private void creaRapFiscale(Document doc, Element elemFatturaHeader) throws ParserConfigurationException, TransformerException
	{
		
		Element elemRapFiscale = doc.createElement("RappresentateFiscale");
		elemFatturaHeader.appendChild(elemRapFiscale);
		
		elemRapFiscale.appendChild(creaDatiAnagrafici(doc, elemRapFiscale));
	}

	private void creaCessionario(Document doc, Element elemFatturaHeader) throws ParserConfigurationException, TransformerException
	{
		
		Element elemCessionario = doc.createElement("CessionarioCommittente");
		elemFatturaHeader.appendChild(elemCessionario);
		
		elemCessionario.appendChild(creaDatiAnagrafici(doc, elemCessionario));
		creaSede(doc, elemCessionario);			
		
	}
	
	private void creaTerzoIntermediario(Document doc, Element elemFatturaHeader) throws ParserConfigurationException, TransformerException
	{
		Element elemTerzoInt = doc.createElement("TerzoIntermediarioSoggettoEmittente");
		elemFatturaHeader.appendChild(elemTerzoInt);
		
		elemTerzoInt.appendChild(creaDatiAnagrafici(doc, elemTerzoInt));
	}
	
	@Deprecated
	// TODO: implementare il metodo.... 
	private boolean isContattiTrasmittente()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}

	@Deprecated
	// TODO: implementare il metodo.... 
	private boolean isCodiceFiscaleHD()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isDenominazione() 
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isTitoloHD()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isCodEori()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isAlboProfessionale()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isProvinciaAlbo()
	{
			if(!DEBUG){
				throw new UnsupportedOperationException("Metodo non implementato");
			}
			return true;
		}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isNumIscrAlbo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isDataIscrAlbo()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isNumC()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isProvinciaHD()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	@Deprecated
	// TODO: implementare il metodo....
	private boolean isStabOrg()
	{
		if(!DEBUG){
			throw new UnsupportedOperationException("Metodo non implementato");
		}
		return true;
	}
	
	
	public void start()
	{
		try
		{
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument(); 
			
			// fase iniziale
			begin(doc);
			
			// fase finale	
			writeXML(doc);
			
			System.out.println("File saved!");
		  }
		catch(ParserConfigurationException pce){
			pce.printStackTrace();}
		catch(TransformerException tfe){
			tfe.printStackTrace();}
		catch(Exception exc){}
	}
	
	
	public static void main(String[] args) 
	{
		FatturaPaWrite fat = new  FatturaPaWrite();
		
		fat.start();
	}
}

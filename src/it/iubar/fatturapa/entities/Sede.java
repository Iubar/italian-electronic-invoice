package it.iubar.fatturapa.entities;

public class Sede 
{
	private String indirizzo;
	private String CAP;
	private String comune;
	private String provincia;
	private String nazione;
	
	public Sede(String indirizzo, String CAP, String comune, String provincia, String nazione)
	{
		this.indirizzo = indirizzo;
		this.CAP = CAP;
		this.comune = comune;
		this.provincia = provincia;
		this.nazione = nazione;
	}
	
	public Sede()
	{
		this.indirizzo = "";
		this.CAP = "";
		this.comune = "";
		this.provincia = "";
		this.nazione = "";
	}
	
	public String getIndirizzo(){
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo){
		this.indirizzo = indirizzo;
	}
	public String getCAP(){
		return CAP;
	}
	public void setCAP(String cAP){
		CAP = cAP;
	}
	public String getComune(){
		return comune;
	}
	public void setComune(String comune){
		this.comune = comune;
	}
	public String getProvincia(){
		return provincia;
	}
	public void setProvincia(String provincia){
		this.provincia = provincia;
	}
	public String getNazione(){
		return nazione;
	}
	public void setNazione(String nazione){
		this.nazione = nazione;
	}
}

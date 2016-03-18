package it.iubar.fatturapa.entities;

public class Anagrafica 
{
	private String denominazione;

	public Anagrafica(String denominazione)
	{
		this.denominazione = denominazione;
	}
	
	public Anagrafica()
	{
		this.denominazione = "";
	}
	
	public String getDenominazione(){
		return denominazione;
	}

	public void setDenominazione(String denominazione){
		this.denominazione = denominazione;
	}
	
	
}

package it.iubar.fatturapa.entities;

public class CedentePrestatore 
{
	private IdFiscaleIVA idFisciva;
	private Anagrafica anagrafica;
	private Sede sede;
	private String regimeFiscale;
	
	public CedentePrestatore(IdFiscaleIVA idFisciva, Anagrafica anagrafica, Sede sede, String regimeFiscale)
	{
		this.idFisciva = idFisciva;
		this.anagrafica = anagrafica;
		this.sede = sede;
		this.regimeFiscale = regimeFiscale;
	}
	
	public CedentePrestatore()
	{
		this.idFisciva = null;
		this.anagrafica = null;
		this.sede = null;
		this.regimeFiscale = "";
	}
	
	public IdFiscaleIVA getIdFisciva(){
		return idFisciva;
	}
	
	public void setIdFisciva(IdFiscaleIVA idFisciva){
		this.idFisciva = idFisciva;
	}
	
	public Anagrafica getAnagrafica(){
		return anagrafica;
	}
	
	public void setAnagrafica(Anagrafica anagrafica){
		this.anagrafica = anagrafica;
	}
	
	public Sede getSede(){
		return sede;
	}
	
	public void setSede(Sede sede){
		this.sede = sede;
	}
	
	public String getRegimeFiscale(){
		return regimeFiscale;
	}
	
	public void setRegimeFiscale(String regimeFiscale){
		this.regimeFiscale = regimeFiscale;
	}
	
}

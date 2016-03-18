package it.iubar.fatturapa.entities;

public class IdTrasmittente 
{
	private String IdPaese;
	private String IdCodice;
	
	public IdTrasmittente(String IdPaese, String IdCodice)
	{
		this.IdPaese = IdPaese;
		this.IdCodice = IdCodice;
	}
	
	public IdTrasmittente()
	{
		this.IdPaese = "";
		this.IdCodice = "";
	}
	
	public String getIdPaese(){
		return IdPaese;
	}
	
	public void setIdPaese(String IdPaese){
		this.IdPaese = IdPaese;
	}
	
	public String getIdCodice(){
		return IdCodice;
	}

	public void setIdCodice(String idCodice){
		this.IdCodice = idCodice;
	}
}

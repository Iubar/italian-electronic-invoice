package it.iubar.fatturapa.entities;

public class DatiTrasmissione
{
	private IdTrasmittente id;
	private String progressivoInvio;
	private String formatoTrasmissione;
	private String codiceDestinatario;
	
	public DatiTrasmissione(IdTrasmittente id, String progressivoInvio, String formatoTrasmissione, String codiceDestinatario)
	{
		this.id = id;
		this.progressivoInvio = progressivoInvio;
		this.formatoTrasmissione = formatoTrasmissione;
		this.codiceDestinatario = codiceDestinatario;
	}
	
	public DatiTrasmissione()
	{
		this.id = null;
		this.progressivoInvio = "";
		this.formatoTrasmissione = "";
		this.codiceDestinatario = "";
		
	}
	
	public String getProgressivoInvio(){
		return progressivoInvio;
	}

	public void setProgressivoInvio(String progressivoInvio){
		this.progressivoInvio = progressivoInvio;
	}

	public String getFormatoTrasmissione(){
		return formatoTrasmissione;
	}

	public void setFormatoTrasmissione(String formatoTrasmissione){
		this.formatoTrasmissione = formatoTrasmissione;
	}

	public String getCodiceDestinatario(){
		return codiceDestinatario;
	}

	public void setCodiceDestinatario(String codiceDestinatario){
		this.codiceDestinatario = codiceDestinatario;
	}
	
	public IdTrasmittente getId(){
		return id;
	}

	public void setId(IdTrasmittente id){
		this.id = id;
	}
}

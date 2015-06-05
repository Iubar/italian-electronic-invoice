package it.iubar.fatturapa;

public class DettaglioLinee {

	private String NumeroLinea;
	private String Descrizione;
	private String Quantita;
	private String PrezzoUnitario;
	private String PrezzoTotale;
	private String AliquotaIVA;
	
	public DettaglioLinee(String NumeroLinea, String Descrizione, String Quantita, String PrezzoUnitario, String PrezzoTotale, String AliquotaIVA){
		
		this.AliquotaIVA = AliquotaIVA;
		this.Descrizione = Descrizione;
		this.NumeroLinea = NumeroLinea;
		this.PrezzoTotale = PrezzoTotale;
		this.PrezzoUnitario = PrezzoUnitario;
		this.Quantita = Quantita;
	}
	
	
	public String getNumeroLinea() {
		return NumeroLinea;
	}
	public void setNumeroLinea(String numeroLinea) {
		NumeroLinea = numeroLinea;
	}
	public String getDescrizione() {
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	public String getQuantita() {
		return Quantita;
	}
	public void setQuantita(String quantita) {
		Quantita = quantita;
	}
	public String getPrezzoUnitario() {
		return PrezzoUnitario;
	}
	public void setPrezzoUnitario(String prezzoUnitario) {
		PrezzoUnitario = prezzoUnitario;
	}
	public String getPrezzoTotale() {
		return PrezzoTotale;
	}
	public void setPrezzoTotale(String prezzoTotale) {
		PrezzoTotale = prezzoTotale;
	}
	public String getAliquotaIVA() {
		return AliquotaIVA;
	}
	public void setAliquotaIVA(String aliquotaIVA) {
		AliquotaIVA = aliquotaIVA;
	}
	
	
}

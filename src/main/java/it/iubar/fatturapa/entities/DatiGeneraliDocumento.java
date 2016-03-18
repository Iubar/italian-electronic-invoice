package it.iubar.fatturapa.entities;

public class DatiGeneraliDocumento {

	private String TipoDocumento;
	private String Divisa;
	private String Data;
	private String Numero;
	private String Causale;
	
	public DatiGeneraliDocumento(String TipoDocumento, String Divisa, String Data, String Numero, String causale){
		this.Data = Data;
		this.Divisa = Divisa;
		this.Numero = Numero;
		this.TipoDocumento = TipoDocumento;
		this.Causale = causale;
	}
	
	public String getTipoDocumento() {
		return TipoDocumento;
	}

		public void setTipoDocumento(String tipoDocumento) {
			TipoDocumento = tipoDocumento;
		}

	public String getDivisa() {
		return Divisa;
	}

		public void setDivisa(String divisa) {
			Divisa = divisa;
		}

	public String getData() {
		return Data;
	}

		public void setData(String data) {
			Data = data;
		}

	public String getNumero() {
		return Numero;
	}

		public void setNumero(String numero) {
			Numero = numero;
		}
		
	public String getCausale() {
			return Causale;
		}

		public void Causale(String causale) {
				Causale = causale;
			}
	
	
}

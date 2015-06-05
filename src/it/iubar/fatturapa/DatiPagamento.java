package it.iubar.fatturapa;

public class DatiPagamento {

	private String DatiPagamento;
	private String ModalitaPagamento;
	private String DataScadenzaPagamento;
	private String ImportoPagamento;
	
	public DatiPagamento(String DatiPagamento,String ModalitaPagamento, String DataScadenzaPagamento, String ImportoPagamento){
		
		this.DataScadenzaPagamento = DataScadenzaPagamento;
		this.DatiPagamento = DatiPagamento;
		this.ModalitaPagamento = ModalitaPagamento;
		this.ImportoPagamento = ImportoPagamento;	
	}
	
	public String getDatiPagamento() {
		return DatiPagamento;
	}

	public void setDatiPagamento(String datiPagamento) {
		DatiPagamento = datiPagamento;
	}

	public String getModalitaPagamento() {
		return ModalitaPagamento;
	}

	public void setModalitaPagamento(String modalitaPagamento) {
		ModalitaPagamento = modalitaPagamento;
	}

	public String getDataScadenzaPagamento() {
		return DataScadenzaPagamento;
	}

	public void setDataScadenzaPagamento(String dataScadenzaPagamento) {
		DataScadenzaPagamento = dataScadenzaPagamento;
	}

	public String getImportoPagamento() {
		return ImportoPagamento;
	}

	public void setImportoPagamento(String importoPagamento) {
		ImportoPagamento = importoPagamento;
	}

}

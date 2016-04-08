package it.iubar.fatturapa.exceptions;

public class AuthException extends Exception{
	
	private int code;
	
	public AuthException(String mess, int code){
		super(mess);
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public void printDetails(){
		System.out.println(getDetails());
	}
	
	public String getDetails(){
		return "Error " + getCode() + " - " + getMessage();
	}
}
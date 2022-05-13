package it.fincons.accademy.types;

public class Persona {
	
	private String nome;
	private String cognome;
	private int eta;
	private String codiceFiscale;
	private String sesso;
	
	public Persona() {
		super();
	}
	
	public Persona(String nome, String cognome, int eta, String codiceFiscale, String sesso) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.codiceFiscale = codiceFiscale;
		this.sesso = sesso;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public int getEta() {
		return eta;
	}
	public void setEta(int eta) {
		this.eta = eta;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	
}

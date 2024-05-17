package com.ftninformatika.modul2.restoran.model;

public class Artikal {

	private long id;
	private String naziv;
	private String opis;
	private double cena;
	
	Kategorija kategorija;

	public Artikal() {

	}

	public Artikal(long id, String naziv, String opis, double cena) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Kategorija getKategorija() {
		return kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}
	
}

package com.ftninformatika.modul2.restoran.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Restoran {

	private long id;
	private String naziv;
	private double cenaDostave;

	private Adresa adresa;

	private final Set<Kategorija> kategorije = new LinkedHashSet<>();
	private final Set<Artikal> artikli = new LinkedHashSet<>();

	public Restoran() {

	}

	public Restoran(long id, String naziv, double cenaDostave, Adresa adresa) {
		this.id = id;
		this.naziv = naziv;
		this.cenaDostave = cenaDostave;
		this.adresa = adresa;
	}

	public Kategorija getKategorija(long id) {
		Kategorija kategorija = null;
		for (Kategorija k : kategorije) {
			if (k.getId() == id) {
				kategorija = k;
				break;
			}
		}
		return kategorija;
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

	public double getCenaDostave() {
		return cenaDostave;
	}

	public void setCenaDostave(double cenaDostave) {
		this.cenaDostave = cenaDostave;
	}

	public Set<Kategorija> getKategorije() {
		return kategorije;
	}

	public void setKategorija(Set<Kategorija> kategorije) {
		this.kategorije.clear();
		this.kategorije.addAll(kategorije);
	}

	public void addKategorija(Kategorija kategorija) {
		kategorije.add(kategorija);
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public Set<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(Set<Artikal> artikli) {
		this.artikli.clear();
		this.artikli.addAll(artikli);
	}

	public void addArtikal(Artikal artikal) {
		artikli.add(artikal);
	}
}

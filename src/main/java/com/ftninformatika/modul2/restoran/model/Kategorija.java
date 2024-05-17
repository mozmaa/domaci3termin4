package com.ftninformatika.modul2.restoran.model;

import java.util.LinkedHashSet;
import java.util.Set;

public class Kategorija {

	private long id;
	private String naziv;
	
	private final Set<Artikal> artikli = new LinkedHashSet<Artikal>();

	public Kategorija() {

	}

	public Kategorija(long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
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
	
	public Set<Artikal> getArtikli() {
		return artikli;
	}

	public void setArtikli(Set<Artikal> artikli) {
		this.artikli.clear();
		this.artikli.addAll(artikli);
	}

	public void addArtikal(Artikal artikal) {
		artikal.kategorija = this;
		artikli.add(artikal);
	}
}

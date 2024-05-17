package com.ftninformatika.modul2.restoran.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

public class Porudzbina {

	private long id;
	private Korisnik korisnik;
	private Restoran restoran;
	private String napomena;
	private LocalDateTime datumIVreme;

	private final Set<StavkaPorudzbine> stavkePorudzbine = new LinkedHashSet<>();
	
	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

	public Porudzbina() {

	}

	public Porudzbina(long id, Korisnik korisnik, Restoran restoran, String napomena, LocalDateTime datumIVreme) {
		this.id = id;
		this.korisnik = korisnik;
		this.restoran = restoran;
		this.napomena = napomena;
		this.datumIVreme = datumIVreme;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Restoran getRestoran() {
		return restoran;
	}

	public void setRestoran(Restoran restoran) {
		this.restoran = restoran;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Set<StavkaPorudzbine> getStavkePorudzbine() {
		return stavkePorudzbine;
	}

	public void setStavkePorudzbine(Set<StavkaPorudzbine> stavkePorudzbine) {
		this.stavkePorudzbine.clear();
		this.stavkePorudzbine.addAll(stavkePorudzbine);
	}

	public void addStavkaPorudzbine(StavkaPorudzbine stavkaPorudzbine) {
		this.stavkePorudzbine.add(stavkaPorudzbine);
	}
	
	public String dateToString() {
		return DATE_TIME_FORMATTER.format(this.datumIVreme);
	}
}

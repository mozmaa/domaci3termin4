package com.ftninformatika.modul2.restoran.model;

import java.util.Objects;

public class Korisnik {

	private String korisnickoIme;
	private String lozinka;
	private String eMail;
	private String pol;
	private boolean administrator;
	private Adresa adresa;

	public Korisnik(String korisnickoIme, String lozinka, String eMail, String pol, boolean administrator,
			Adresa adresa) {
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.eMail = eMail;
		this.pol = pol;
		this.administrator = administrator;
		this.adresa = adresa;
	}

	public Korisnik(String korisnickoIme, String eMail, String pol, boolean administrator) {
		this.korisnickoIme = korisnickoIme;
		this.eMail = eMail;
		this.pol = pol;
		this.administrator = administrator;
	}

	public Korisnik() {
	}

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", eMail=" + eMail + ", pol=" + pol
				+ ", administrator=" + administrator + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(korisnickoIme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(korisnickoIme, other.korisnickoIme);
	}

	
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public Adresa getAdresa() {
		return adresa;
	}

	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}

	public String isAdmin() {
		if(this.administrator)
			return "Da";
		return "Ne";
	}
}

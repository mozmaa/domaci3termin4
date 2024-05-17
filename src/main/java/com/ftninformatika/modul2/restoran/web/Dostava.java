package com.ftninformatika.modul2.restoran.web;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ftninformatika.modul2.restoran.model.Adresa;
import com.ftninformatika.modul2.restoran.model.Artikal;
import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.model.Korisnik;
import com.ftninformatika.modul2.restoran.model.Porudzbina;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.model.StavkaPorudzbine;

@Component
public class Dostava {

	private final Map<Long, Adresa> adrese = new LinkedHashMap<>();
	private final Map<Long, Kategorija> kategorije = new LinkedHashMap<>();
	private final Map<Long, Restoran> restorani = new LinkedHashMap<>();
	private final Map<String, Korisnik> korisnici = new LinkedHashMap<>();
	private final Map<Long, Artikal> artikli = new LinkedHashMap<>();
	private final Map<Long, StavkaPorudzbine> stavkePorudzbine = new LinkedHashMap<>();
	private final Map<Long, Porudzbina> porudzbine = new LinkedHashMap<>();
	
	private long maxAdresaId = 0;
	private long maxArtikalId = 0;
	private long maxKategorijaId = 0;
	private long maxPorudzbinaId = 0;
	private long maxRestoranId = 0;
	private long maxStavkaPorudzbineId = 0;

	public Dostava() {

		// kreiranje adresa
		adrese.put(1L, new Adresa(1L, "Bulevar Oslobodjenja", "119", "Novi Sad", 21000));
		adrese.put(2L, new Adresa(2L, "Branka Bajica", "9n", "Novi Sad", 21000));
		adrese.put(3L, new Adresa(3L, "Kralja Petra I", "70", "Sremska Mitrovica", 22000));

		adrese.put(4L, new Adresa(1L, "Sutjeska", "22", "Novi Sad", 21000));
		adrese.put(5L, new Adresa(2L, "Strazilovska", "9", "Novi Sad", 21000));
		adrese.put(6L, new Adresa(3L, "Radnicka", "34", "Novi Sad", 21000));

		// kreiranje kategorija
		kategorije.put(1L, new Kategorija(1L, "Burgeri"));
		kategorije.put(2L, new Kategorija(2L, "Sendvici"));
		kategorije.put(3L, new Kategorija(3L, "Susi"));
		kategorije.put(4L, new Kategorija(4L, "Onigiri"));
		kategorije.put(5L, new Kategorija(5L, "Burito"));

		// kreiranje artikala
		artikli.put(1L, new Artikal(1L, "Dupli burger",
				"Burger meso 120 grama, toljeni sir, burger majonez, heinz kečap, ajsberg, kiseli krastavac", 650));
		artikli.put(2L, new Artikal(2L, "Pileći burger",
				"Pileći burger, tost sir, pančeta, burger majonez, ajsberg, paradajz", 550));
		artikli.put(3L, new Artikal(3L, "Indeks sendvič", "Praška šunka, sir, šampinjoni", 320));
		artikli.put(4L, new Artikal(4L, "Vratolomija sendvič", "Suvi vrat, sir, jaja", 290));

		artikli.put(5L, new Artikal(5L, "Crispy specijal",
				"Hrskave sushi rolnice, pohovane u tempuri. Nori alga, pirinač, pohovana rolnica sa dodatkom krastavca, krem sira, lososa i japanskog nitsume sosa",
				920));
		artikli.put(6L, new Artikal(6L, "Samurai roll",
				"8 komada, nori alga, pirinač, losos, tuna, avokado, obložena breniranim lososom i tunom,unagi sos, samuraj sos, hrskavi crunch krompir",
				990));
		artikli.put(7L,
				new Artikal(7L, "Onigiri sa tunom", "pirinač, tuna, crni susam, japanski majonez i nori alge", 510));
		artikli.put(8L,
				new Artikal(8L, "Onigiri sa lososom", "pirinač, losos, crni susam, japanski majonez i nori alge", 670));

		artikli.put(9L, new Artikal(9L, "Achiote piletina burito",
				"Meksički pirinač, Crni pasulj, Mokahete Salsa, Pico de gallo, Kukuruz salsa, Marinirani Krastavac, Kačkavalj, Crema Fresca",
				560));
		artikli.put(10L, new Artikal(10L, "Chorizo burito",
				"Meksički pirinač, Pinto pasulj, Mokahete Salsa, Pico de gallo, Kukuruz Salsa, Kačkavalj, Marinirani krastavac, Marinirani crveni luk, Chipotle aioli",
				610));
		artikli.put(11L, new Artikal(11L, "Pečene pečurke burito",
				"Beli pirinač, Crni pasulj, Pečene pečurke, Pico de gallo, Kukuruz salsa, Marinirani krastavac, Mokahete salsa, Mix zelenih salata, Krema Freska",
				480));

		// kreiranje restorana
		restorani.put(1L, new Restoran(1L, "Restoran 1", 150, adrese.get(1L)));
		restorani.put(2L, new Restoran(2L, "Restoran 2", 250, adrese.get(2L)));
		restorani.put(3L, new Restoran(3L, "Restoran 3", 200, adrese.get(3L)));
		
		//povezivanje kategorije i artikla
		
		kategorije.get(1L).addArtikal(artikli.get(1L));
		kategorije.get(1L).addArtikal(artikli.get(2L));
		kategorije.get(2L).addArtikal(artikli.get(3L));
		kategorije.get(2L).addArtikal(artikli.get(4L));
		kategorije.get(3L).addArtikal(artikli.get(5L));
		kategorije.get(3L).addArtikal(artikli.get(6L));
		kategorije.get(4L).addArtikal(artikli.get(7L));
		kategorije.get(4L).addArtikal(artikli.get(8L));
		kategorije.get(5L).addArtikal(artikli.get(9L));
		kategorije.get(5L).addArtikal(artikli.get(10L));
		kategorije.get(5L).addArtikal(artikli.get(11L));

		// povezivanje restorana i kategorija
		restorani.get(1L).addKategorija(kategorije.get(1L));
		restorani.get(1L).addKategorija(kategorije.get(2L));
		restorani.get(2L).addKategorija(kategorije.get(3L));
		restorani.get(2L).addKategorija(kategorije.get(4L));
		restorani.get(3L).addKategorija(kategorije.get(5L));

		// povezivanje restorana i artikala
		restorani.get(1L).addArtikal(artikli.get(1L));
		restorani.get(1L).addArtikal(artikli.get(2L));
		restorani.get(1L).addArtikal(artikli.get(3L));
		restorani.get(1L).addArtikal(artikli.get(4L));

		restorani.get(2L).addArtikal(artikli.get(5L));
		restorani.get(2L).addArtikal(artikli.get(6L));
		restorani.get(2L).addArtikal(artikli.get(7L));
		restorani.get(2L).addArtikal(artikli.get(8L));

		restorani.get(3L).addArtikal(artikli.get(9L));
		restorani.get(3L).addArtikal(artikli.get(10L));
		restorani.get(3L).addArtikal(artikli.get(11L));

		// kreiranje stavki porudzbina
		stavkePorudzbine.put(1L, new StavkaPorudzbine(1L, artikli.get(1L), 2));
		stavkePorudzbine.put(2L, new StavkaPorudzbine(2L, artikli.get(2L), 2));
		stavkePorudzbine.put(3L, new StavkaPorudzbine(3L, artikli.get(3L), 3));
		stavkePorudzbine.put(4L, new StavkaPorudzbine(4L, artikli.get(4L), 1));
		stavkePorudzbine.put(5L, new StavkaPorudzbine(5L, artikli.get(5L), 1));
		stavkePorudzbine.put(6L, new StavkaPorudzbine(6L, artikli.get(6L), 2));
		stavkePorudzbine.put(7L, new StavkaPorudzbine(7L, artikli.get(7L), 2));
		stavkePorudzbine.put(8L, new StavkaPorudzbine(8L, artikli.get(8L), 3));
		stavkePorudzbine.put(9L, new StavkaPorudzbine(9L, artikli.get(9L), 1));
		stavkePorudzbine.put(10L, new StavkaPorudzbine(10L, artikli.get(10L), 3));
		stavkePorudzbine.put(11L, new StavkaPorudzbine(11L, artikli.get(11L), 2));
		stavkePorudzbine.put(12L, new StavkaPorudzbine(12L, artikli.get(1L), 2));
		stavkePorudzbine.put(13L, new StavkaPorudzbine(13L, artikli.get(2L), 3));
		stavkePorudzbine.put(14L, new StavkaPorudzbine(14L, artikli.get(3L), 1));
		stavkePorudzbine.put(15L, new StavkaPorudzbine(15L, artikli.get(4L), 1));

		// kreiranje korisnika
		korisnici.put("pera_peric",
				new Korisnik("pera_peric", "a", "pera_peric@gmail.com", "muški", true, adrese.get(4L)));
		korisnici.put("mika_mikic",
				new Korisnik("mika_mikic", "b", "mika_mikic@gmail.com", "ženski", false, adrese.get(5L)));
		korisnici.put("zika_zikic",
				new Korisnik("zika_zikic", "c", "zika_zikic@gmail.com", "muški", false, adrese.get(6L)));

		// kreiranje porudzbine
		porudzbine.put(1L, new Porudzbina(1L, korisnici.get("pera_peric"), restorani.get(1L), "Bez paradajza",
				LocalDateTime.now()));
		porudzbine.get(1L).addStavkaPorudzbine(stavkePorudzbine.get(1L));
		porudzbine.get(1L).addStavkaPorudzbine(stavkePorudzbine.get(2L));
		porudzbine.get(1L).addStavkaPorudzbine(stavkePorudzbine.get(3L));

		porudzbine.put(2L,
				new Porudzbina(2L, korisnici.get("mika_mikic"), restorani.get(2L), "Bez soli", LocalDateTime.now()));
		porudzbine.get(2L).addStavkaPorudzbine(stavkePorudzbine.get(5L));
		porudzbine.get(2L).addStavkaPorudzbine(stavkePorudzbine.get(6L));

		porudzbine.put(3L,
				new Porudzbina(3L, korisnici.get("zika_zikic"), restorani.get(3L), "Bez secera", LocalDateTime.now()));
		porudzbine.get(3L).addStavkaPorudzbine(stavkePorudzbine.get(10L));
		porudzbine.get(3L).addStavkaPorudzbine(stavkePorudzbine.get(11L));
		
		maxAdresaId = 6;
		maxArtikalId = 11;
		maxKategorijaId = 5;
		maxPorudzbinaId = 3;
		maxRestoranId = 3;
		maxStavkaPorudzbineId = 15;
	}

	public Map<Long, Kategorija> getKategorije() {
		return kategorije;
	}

	public Map<Long, Restoran> getRestorani() {
		return restorani;
	}

	public Map<String, Korisnik> getKorisnici() {
		return korisnici;
	}

	public Map<Long, Porudzbina> getPorudzbine() {
		return porudzbine;
	}

	public Map<Long, Artikal> getArtikli() {
		return artikli;
	}
	
	public Map<Long, Adresa> getAdrese(){
		return adrese;
	}

	public long getMaxAdresaId() {
		maxAdresaId++;
		return maxAdresaId;
	}

	public long getMaxArtikalId() {
		maxArtikalId++;
		return maxArtikalId;
	}

	public long getMaxKategorijaId() {
		maxKategorijaId++;
		return maxKategorijaId;
	}

	public long getMaxPorudzbinaId() {
		maxPorudzbinaId++;
		return maxPorudzbinaId;
	}

	public long getMaxRestoranId() {
		maxRestoranId++;
		return maxRestoranId;
	}

	public long getMaxStavkaPorudzbineId() {
		maxStavkaPorudzbineId++;
		return maxStavkaPorudzbineId;
	}
	
	
}

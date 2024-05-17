package com.ftninformatika.modul2.restoran.web.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.modul2.restoran.model.Korisnik;
import com.ftninformatika.modul2.restoran.model.Porudzbina;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.model.StavkaPorudzbine;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/porudzbine")
public class PorudzbinaController {

	private Dostava dostava;
	
	public PorudzbinaController(Dostava dostava) {
		this.dostava = dostava;
	}
	
	@GetMapping("")
	public String getAll(ModelMap request,
			@RequestParam (required = false , defaultValue = "0") long restoranId) {
		Collection<Porudzbina> rezultat = new ArrayList<Porudzbina>();
		for(Porudzbina itPorudzbina : dostava.getPorudzbine().values()) {
			if(restoranId == 0 || itPorudzbina.getRestoran().getId() == restoranId)
				rezultat.add(itPorudzbina);
		}
		request.addAttribute("porudzbine" , rezultat);
		return "porudzbine";
	}
	
	@GetMapping("/prikaz")
	public String get(ModelMap request,
			@RequestParam long id) {
		request.addAttribute("porudzbina" , dostava.getPorudzbine().get(id));
		return "porudzbina-prikaz";
	}
	
	@GetMapping("/dodavanje/selectRestoran")
	public String add(ModelMap request) {
		request.addAttribute("restorani" , dostava.getRestorani().values());
		return "restorani-select";
	}
	
	@PostMapping("/dodavanje/porudzbina")
	public String add(ModelMap request,
			@RequestParam long id) {
		request.addAttribute("korisnici" , dostava.getKorisnici().values());
		request.addAttribute("restoran" , dostava.getRestorani().get(id));
		
		return "porudzbine-dodavanje";
	}
	
	@PostMapping("/dodavanje")
	public String add(ModelMap request,
			@RequestParam long restoranId,
			@RequestParam String korisnickoIme,
			@RequestParam int[] artikalKolicina,
			@RequestParam (required = false, defaultValue = "")String napomena, //Dodaj default
			@RequestParam long[] artikalId) {
		
		
		Set<StavkaPorudzbine> stavke = new LinkedHashSet<StavkaPorudzbine>();
		for(int i = 0; i < artikalId.length;i++) {
			if(artikalKolicina[i] == 0) {
				continue;
			}
			StavkaPorudzbine stavka = new StavkaPorudzbine(dostava.getMaxStavkaPorudzbineId(),
					dostava.getArtikli().get(artikalId[i]), artikalKolicina[i]);
			stavke.add(stavka);
		}
		if(stavke.isEmpty())
			return "redirect:/porudzbine";
			
		long id = dostava.getMaxPorudzbinaId();
		Restoran restoran = dostava.getRestorani().get(restoranId);
		Korisnik korisnik = dostava.getKorisnici().get(korisnickoIme);
		Porudzbina porudzbina = new Porudzbina(id, korisnik, restoran, napomena, LocalDateTime.now());
		porudzbina.setStavkePorudzbine(stavke);
		dostava.getPorudzbine().put(id, porudzbina);
		return "redirect:/porudzbine";
	}
	
}

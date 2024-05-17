package com.ftninformatika.modul2.restoran.web.controller;


import java.util.Iterator;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.modul2.restoran.model.Artikal;
import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/artikli")
public class ArtikalController {
	
	private Dostava dostava;
	
	public ArtikalController(Dostava dostava) {
		this.dostava = dostava;
	}
	
	@GetMapping("")
	public String getAll(ModelMap request) {
		request.addAttribute("artikli" , dostava.getArtikli().values());
		return "artikli";
	}
	
	@GetMapping("/prikaz")
	public String get(ModelMap request,
			@RequestParam long id) {
		request.addAttribute("artikal", dostava.getArtikli().get(id));
		return "artikli-prikaz";
	}
	
	@GetMapping("dodavanje")
	public String add(ModelMap request) {
		request.addAttribute("restorani" , dostava.getRestorani().values());
		request.addAttribute("kategorije", dostava.getKategorije().values());
		return "artikli-dodavanje";
	}
	
	@PostMapping("artikli-dodavanje")
	public String add(@RequestParam String naziv,
			@RequestParam String opis,
			@RequestParam double cena,
			@RequestParam (required = false, defaultValue = "0") long[] kategorijaIds) {
		
		if(kategorijaIds[0] == 0) {
			return "redirect:/artikli";
		}
		long id = dostava.getMaxArtikalId();
		Artikal artikal = new Artikal(id, naziv, opis, cena);
		for(Restoran itRestoran : dostava.getRestorani().values()) {
			for(Kategorija itKategorija : itRestoran.getKategorije()) {
				for(int i = 0; i < kategorijaIds.length; i++) {
					if(itKategorija.getId() == kategorijaIds[i]) {
						itKategorija.addArtikal(artikal);
						itRestoran.addArtikal(artikal);
					}
				}
			}
		}
		dostava.getArtikli().put(id, artikal);
		return "redirect:/artikli";
	}
	
	
	@PostMapping("/izmena")
	public String update(@RequestParam long id,
			@RequestParam String naziv,
			@RequestParam String opis,
			@RequestParam double cena) {
		
		Artikal artikal = dostava.getArtikli().get(id);
		artikal.setNaziv(naziv);
		artikal.setOpis(opis);
		artikal.setCena(cena);
		return "redirect:/artikli";
	}
	
	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		
		Iterator<Entry<Long,Restoran>> itEntryRestoran = dostava.getRestorani().entrySet().iterator();
		while(itEntryRestoran.hasNext()) {
			Restoran itRestoran = itEntryRestoran.next().getValue();
			Iterator <Artikal> itEntryArtikal = itRestoran.getArtikli().iterator();
				while(itEntryArtikal.hasNext()) {
					Artikal itArtikal = itEntryArtikal.next();
					if(itArtikal.getId() == id)
						itEntryArtikal.remove();
				}
			}
		dostava.getArtikli().remove(id);
		return "redirect:/artikli";
	}
}

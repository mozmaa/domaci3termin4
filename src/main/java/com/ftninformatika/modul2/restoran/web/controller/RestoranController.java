package com.ftninformatika.modul2.restoran.web.controller;

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

import com.ftninformatika.modul2.restoran.model.Adresa;
import com.ftninformatika.modul2.restoran.model.Artikal;
import com.ftninformatika.modul2.restoran.model.Kategorija;
import com.ftninformatika.modul2.restoran.model.Restoran;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/restorani")
public class RestoranController {

	private Dostava dostava;

	public RestoranController(Dostava dostava) {
		this.dostava = dostava;
	}

	@GetMapping("")
	public String getAll(ModelMap request,
			@RequestParam(required = false, defaultValue = "0") long kategorijaId) {
		
		Collection<Restoran> rezultat = new ArrayList<Restoran>();
		for(Restoran itRestoran : dostava.getRestorani().values()) {
			for(Kategorija itKategorija : itRestoran.getKategorije()) {
				if(kategorijaId == 0 || itKategorija.getId() == kategorijaId) {
					rezultat.add(itRestoran);
					break;
				}
			}
		}
		request.addAttribute("restorani", rezultat);
		return "restorani";
	}
	
	@GetMapping("/prikaz") //
	public String get(ModelMap request, 
			@RequestParam long id) {
		request.addAttribute("restoran", dostava.getRestorani().get(id));
		return "restorani-prikaz";
	}
	
	@GetMapping("/dodavanje")
	public String add(ModelMap request) {
		request.addAttribute("restorani" , dostava.getRestorani().values());
		request.addAttribute("kategorije" , dostava.getKategorije().values());
		return "restorani-dodavanje";
	}
	
	@PostMapping("/dodavanje")
	public String add(@RequestParam String naziv,
			@RequestParam long[] kategorijaIds,
			@RequestParam double cenaDostave,
			@RequestParam String nazivUlice,
			@RequestParam String broj,
			@RequestParam int postanskiBroj,
			@RequestParam String grad) {
		Set<Kategorija> kategorije = new LinkedHashSet<>();
		for (long itKategorijaId: kategorijaIds) { 
			Kategorija itKategorija = dostava.getKategorije().get(itKategorijaId);
			kategorije.add(itKategorija);
		}
		if (kategorije.isEmpty()) { 
			return "redirect:/restorani";
		}
		
		Set<Artikal> artikli = new LinkedHashSet<>();
		for (Kategorija itKategorija : kategorije) {
			artikli.addAll(itKategorija.getArtikli());
		}
		
		Adresa adresa = new Adresa(postanskiBroj, nazivUlice, broj, grad, postanskiBroj);
		Restoran restoran = new Restoran(dostava.getMaxRestoranId(), naziv, cenaDostave, adresa);
		
		Collection<Restoran> restorani = dostava.getRestorani().values();
		for(Restoran itRestoran : restorani) {
			if(itRestoran.getAdresa().equals(adresa))
				return "redirect:/restorani";
		}
		if(!dostava.getAdrese().containsValue(adresa))
			dostava.getAdrese().put(dostava.getMaxAdresaId(), adresa);
		
		restoran.setKategorija(kategorije);
		restoran.setArtikli(artikli);
		dostava.getRestorani().put(dostava.getMaxRestoranId(), restoran);
		return "redirect:/restorani";
	}
	
	@PostMapping("/restorani-izmena")
	public String update(ModelMap request
			,@RequestParam long restoranId) {
		request.addAttribute("restoran", dostava.getRestorani().get(restoranId));
		request.addAttribute("kategorije", dostava.getKategorije().values());
		return "restorani-izmena";
	}
	
	@PostMapping("/izmena")
	public String update(@RequestParam long id,
			@RequestParam String naziv,
			@RequestParam int[] kategorijaIds,
			@RequestParam double cenaDostave,
			@RequestParam String nazivUlice,
			@RequestParam String broj,
			@RequestParam int postanskiBroj,
			@RequestParam String grad) {
		
		Set<Kategorija> kategorije = new LinkedHashSet<>();
		for (long itKategorijaId: kategorijaIds) { 
			Kategorija itKategorija = dostava.getKategorije().get(itKategorijaId);
			kategorije.add(itKategorija);
		}
		if (kategorije.isEmpty()) { 
			return "redirect:/restorani";
		}
		
		Set<Artikal> artikli = new LinkedHashSet<>();
		for (Kategorija itKategorija : kategorije) {
			artikli.addAll(itKategorija.getArtikli());
		}
		
		Adresa adresa = new Adresa(postanskiBroj, nazivUlice, broj, grad, postanskiBroj);
		if(!dostava.getAdrese().containsValue(adresa))
			dostava.getAdrese().put(dostava.getMaxAdresaId(), adresa);
		
		Restoran restoran = dostava.getRestorani().get(id);
		restoran.setNaziv(naziv);
		restoran.setCenaDostave(cenaDostave);
		restoran.setAdresa(adresa);
		restoran.setKategorija(kategorije);
		restoran.setArtikli(artikli);
		return "redirect:/restorani";
	}
	
	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		dostava.getRestorani().remove(id);
		return "redirect:/restorani";
	}
	
}

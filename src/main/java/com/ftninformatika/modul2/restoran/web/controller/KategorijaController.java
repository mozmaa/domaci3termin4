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
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/kategorije")
public class KategorijaController {

	private Dostava dostava;

	public KategorijaController(Dostava dostava) {
		this.dostava = dostava;
	}
	
	@GetMapping("")
	public String getAll(ModelMap request) {
		request.addAttribute("kategorije", dostava.getKategorije().values());
		return "kategorije";
	}
	
	@GetMapping("/prikaz")
	public String get(ModelMap request,
			@RequestParam long id) {
		request.addAttribute("kategorija" , dostava.getKategorije().get(id));
		return "kategorije-prikaz";
	}
	
	@GetMapping("/dodavanje")
	public String add(ModelMap request) {
		return "kategorije-dodavanje";
	}
	
	@PostMapping("/dodavanje")
	public String add(@RequestParam String naziv) {
		Iterator<Entry<Long,Kategorija>> itEntryKategorija = dostava.getKategorije().entrySet().iterator();
		while(itEntryKategorija.hasNext()) {
			Kategorija itKategorija = itEntryKategorija.next().getValue();
			if(itKategorija.getNaziv().equalsIgnoreCase(naziv))
				return "redirect:/kategorije";
		}
		long id = dostava.getMaxKategorijaId();
		Kategorija kategorija = new Kategorija(id, naziv);
		dostava.getKategorije().put(id, kategorija);
		return "redirect:/kategorije";
	}
	
	@PostMapping("/izmena")
	public String update(@RequestParam long id,
			@RequestParam String naziv) {
		Iterator<Entry<Long,Kategorija>> itEntryKategorija = dostava.getKategorije().entrySet().iterator();
		while(itEntryKategorija.hasNext()) {
			Kategorija itKategorija = itEntryKategorija.next().getValue();
			if(itKategorija.getNaziv().equalsIgnoreCase(naziv))
				return "redirect:/kategorije";
		}
		Kategorija kategorija = dostava.getKategorije().get(id);
		kategorija.setNaziv(naziv);
		return "redirect:/kategorije";
	}
	
	@PostMapping("/brisanje")
	public String delete(@RequestParam long id) {
		Iterator<Entry<Long,Kategorija>> itEntryKategorija = dostava.getKategorije().entrySet().iterator();
		while(itEntryKategorija.hasNext()) {
			Kategorija itKategorija = itEntryKategorija.next().getValue();
			if(itKategorija.getId() == id) {
				Iterator<Entry<Long,Artikal>> itEntryArtikal = dostava.getArtikli().entrySet().iterator();
				while(itEntryArtikal.hasNext()) {
					Artikal itArtikal = itEntryArtikal.next().getValue();
					if(itArtikal.getKategorija().getId() == id)
						itEntryArtikal.remove();
				}
				itEntryKategorija.remove();
			}
		}
		return "redirect:/kategorije";
	}
}

package com.ftninformatika.modul2.restoran.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ftninformatika.modul2.restoran.model.Adresa;
import com.ftninformatika.modul2.restoran.model.Korisnik;
import com.ftninformatika.modul2.restoran.web.Dostava;

@Controller
@RequestMapping("/korisnici")
public class KorisnikController {

	private Dostava dostava;
	
	public KorisnikController(Dostava dostava) {
		this.dostava = dostava;
	}
	
	
	@GetMapping("")
	public String getAll(ModelMap request) {
		request.addAttribute("korisnici" , dostava.getKorisnici().values());
		return "korisnici";
	}
	
	@GetMapping("/prikaz")
	public String get (ModelMap request,
			@RequestParam String korisnickoIme) {
		request.addAttribute("korisnik" , dostava.getKorisnici().get(korisnickoIme));
		return "korisnik-prikaz";
	}
	
	@GetMapping("/dodavanje")
	public String add(ModelMap request) {
		request.addAttribute("korisnici", dostava.getKorisnici().values());
		return "korisnici-dodavanje";	}
	
	@PostMapping("/dodavanje")
	public String add(@RequestParam String korisnickoIme,
			@RequestParam String lozinka,
			@RequestParam String pol,
			@RequestParam String eMail,
			@RequestParam (required = false , defaultValue = "false")boolean administrator,
			@RequestParam String nazivUlice,
			@RequestParam String broj,
			@RequestParam int postanskiBroj,
			@RequestParam String grad ) {
		
		Adresa adresa = new Adresa(postanskiBroj, nazivUlice, broj, grad, postanskiBroj);
		if(!dostava.getAdrese().containsValue(adresa))
			dostava.getAdrese().put(dostava.getMaxAdresaId(), adresa);
		
		Korisnik korisnik = new Korisnik (korisnickoIme, lozinka, eMail, pol, administrator, adresa);
		dostava.getKorisnici().put(korisnickoIme, korisnik);
		return "redirect:/korisnici";
	}
}

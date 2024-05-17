package com.ftninformatika.modul2.restoran.model;

import java.util.Objects;

public class Adresa {


	 private long id;
	 private String ulica;
	 private String broj;
	 private String grad;
	 private int postanskiBroj ;

	 public Adresa() {

	 }  

	 public Adresa(long id, String ulica, String broj, String grad, int postanskiBroj) {
	   this.id = id;
	   this.ulica = ulica;
	   this.broj = broj;
	   this.grad = grad;
	   this.postanskiBroj = postanskiBroj;
	 }
	  
	 public long getId() {
	   return id;
	 }

	 public void setId(long id) {
	   this.id = id;
	 }

	 public String getUlica() {
	   return ulica;
	 }

	 public void setUlica(String ulica) {
		this.ulica = ulica;
	 }

	 public String getBroj() {
		 return broj;
	  }

	 public void setBroj(String broj) {
	    this.broj = broj;
	 }

	 public String getGrad() {
	    return grad;
	 }
	 
	 public void setGrad(String grad) {
	    this.grad = grad;
	 }

	 public int getPostanskiBroj() {
	    return postanskiBroj;
	 }

	 public void setPostanskiBroj(int postanskiBroj) {
	    this.postanskiBroj = postanskiBroj;
	 }
	 
	 public String getAdresaSaPostanskim() {
		 return "" + ulica + " " + broj + ", " + grad + " " + postanskiBroj;
	 }
	 
	 public String getAdresaBezPostanskog() {
		 return "" + ulica + " " + broj + ", " + grad;
	 }
	 
	 @Override
		public int hashCode() {
			return Objects.hash(broj, grad, id, postanskiBroj, ulica);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Adresa other = (Adresa) obj;
			return Objects.equals(broj, other.broj) && Objects.equals(grad, other.grad) && id == other.id
					&& postanskiBroj == other.postanskiBroj && Objects.equals(ulica, other.ulica);
		}
}

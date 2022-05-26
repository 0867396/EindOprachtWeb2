package domain.model;

import execptions.DomainException;

public class Bloem {
    private int id , aantal;
    private String naam , kleur;
    public Bloem(){}
    public Bloem(String naam, String kleur, int aantal) {
        setNaam(naam);
        setKleur(kleur);
        setAantal(aantal);

    }




    public void setAantal(int aantal) {
        if(aantal <= 0)throw new DomainException("getal mag niet null of negative zijn");
        this.aantal = aantal;
    }

    public void setNaam(String naam) {
        if(naam == null || naam.isBlank()) throw new DomainException("vul bloem naam in");
        this.naam = naam;
    }

    public void setKleur(String kleur) {
        if(kleur == null || kleur.isBlank())throw new DomainException("vul bloem Kleur in.");
        this.kleur = kleur;
    }


    public int getId() {
        return id;
    }

    public int getAantal() {
        return aantal;
    }

    public String getNaam() {
        return naam;
    }

    public String getKleur() {
        return kleur;
    }
    public void vervangBloem (Bloem andereBlomen){
      setNaam(andereBlomen.naam);
      setKleur(andereBlomen.kleur);
      setAantal(andereBlomen.aantal);

    }
}

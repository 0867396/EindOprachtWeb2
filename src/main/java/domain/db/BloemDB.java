package domain.db;

import domain.model.Bloem;
import execptions.DomainException;


import java.util.ArrayList;
import java.util.List;

public class BloemDB {
    private int sequence = 0;
    private final List<Bloem> bloemen = new ArrayList<>();

    public BloemDB() {
        this.voegBloemToe(new Bloem("Tulip","Rood",10));
        this.voegBloemToe(new Bloem("Roos","Roze",11));
        this.voegBloemToe(new Bloem("WhiteRosen","White",12));
    }

    public void voegBloemToe(Bloem bloem) {
        if (bloem == null)
            throw new DomainException("Voeg een bloem toe");
        if (vindVolgendsNaam(bloem.getNaam()) != null)
            throw new DomainException("Je mag een bloem maar één keer toevoegen");
        bloemen.add(bloem);

    }




    public void verwijder(String naam){
       for(Bloem b: bloemen){
           if(b.getNaam() == naam)bloemen.remove(naam);
       }

    }




    /**public Bloem find(int id){
     for(Bloem b : bloemen)
     if(b.getId() == id)return b;
     return null;
     }**/

    public Bloem vindVolgendsNaam(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("Naam mag niet leeg zijn");
        for (Bloem b : bloemen) {
            if (b.getNaam().equals(naam))
                return b;
        }
        return null;
    }



    public boolean vindNaam (String naam){
        if (naam == null){
            throw new IllegalArgumentException("Naam kan niet leeg zijn.");
        }
        for (Bloem m : bloemen){
            if (m.getNaam().equals(naam)){
                return true;
            }
        }
        return false;
    }

    public List<Bloem> getBloemen() {
        return bloemen;
    }
    public int getSequence(){
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public void vervangBloem(String naam , Bloem bloem){
        if(naam.isEmpty()) throw new DomainException("naam mag niet leeg zijn");
        for(Bloem b : bloemen){
            if(b.getNaam() == bloem.getNaam() && b.getKleur() == bloem.getKleur())
                b.getNaam().replace(b.getNaam(),bloem.getNaam());
                b.getKleur().replace(b.getKleur(),bloem.getKleur());

        }
    }

    public void vervangNaam(String naam){
        for(Bloem b : bloemen){
            if(b.getNaam() == naam)throw new DomainException("");
            b.getNaam().replace(b.getNaam(),naam);
        }
    }

    public Bloem zoek(String naam){
        if(naam == null || naam.isEmpty()){
            throw new DomainException("Naam mag niet leeg zijn");
        }
        for(Bloem b : bloemen){
            if(b.getNaam().equalsIgnoreCase(naam)){return b;}

        }
        return null;
    }

    public void verwijderBlomen(Bloem bloem){
        if(bloem == null)throw new DomainException("bloem mag niet leeg zijn");
        bloemen.remove(bloem);
    }

    public void vervangKleur(String kleur){
        for(Bloem b : bloemen){
            if(b.getKleur() == kleur)throw new DomainException("");
            b.getNaam().replace(b.getNaam(),kleur);
        }
    }

    public Bloem getMeesteVerkochtBloem(){
        if(bloemen.size() == 0){return null;}
        Bloem meestVerkochtBloem = bloemen.get(0);
        for (Bloem bloem : bloemen) {
            if (bloem.getAantal() > meestVerkochtBloem.getAantal())
                meestVerkochtBloem = bloem;
        }
        return meestVerkochtBloem;
    }

    public int getAantalBlomen() {
        return getBloemen().size();
    }

}


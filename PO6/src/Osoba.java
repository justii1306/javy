public class Osoba extends Wpis{

    private String imie;
    private String nazwisko;
    private Adres adres;

    public Osoba() {
    }

    public Osoba(String imie, String nazwisko, String adres, int nrTelefonu, int nrKierunkowy){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.adres = new Adres(adres, nrTelefonu, nrKierunkowy);
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public void opis() {
        System.out.println(this.imie + " " + this.nazwisko + " ma adres: " + this.adres.toString());
    }
}

public class Firma extends Wpis{

    private String nazwa;
    private Adres adres;

    public Firma() {
    }

    public Firma(String nazwa, String adres, int nrTelefonu, int nrKierunkowy){
        this.nazwa = nazwa;
        this.adres = new Adres(adres, nrTelefonu, nrKierunkowy);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    @Override
    public void opis() {
        System.out.println(this.nazwa + " ma adres: " + this.adres.toString());
    }
}

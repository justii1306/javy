public class Adres {

    private String adres;
    private NrTelefoniczny numer;

    public Adres() {
    }

    public Adres(String adres, int nrTelefonu, int nrKierunkowy) {
        this.adres = adres;
        this.numer = new NrTelefoniczny(nrTelefonu, nrKierunkowy);
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public NrTelefoniczny getNumer() {
        return numer;
    }

    public void setNumer(NrTelefoniczny numer) {
        this.numer = numer;
    }

    @Override
    public String toString(){
        return this.adres + "; Numer telefoniczny: " + this.numer.toString();
    }
}
